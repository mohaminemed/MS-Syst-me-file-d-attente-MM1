package MSMM1;
import java.util.*;

public class Simulation {
	private Vector<Evenement> calendrier= new Vector<Evenement>(); 
	private int t,Ts;
	private int count;
	private int nbA;
	private int nbD;
	private double lambda;
	private String Etat_server;
	private int ifile;
	public int[]fileattente;
	public int Taillefile;
	public int tabfil[];
	public int tabsys[];
	private int tempsfil[];
	private int tempsys[];
	private int mu;
	public static int getPoisson(double lambda) {
		double L=Math.exp(-lambda);
		double p=1.0;
		int k=0;
		Random r = new Random();
		do {
			p=p*r.nextDouble();
			k++;
		}while(p>=L);
		return k;
	}
   public void generateEvent() {
	    int deltatT=3;
	    int t=0;
	   while(t<Ts) {
		   int nbClient=getPoisson(lambda);
		   nbA+=nbClient;
		   for(int i=0;i<nbClient;i++) {
		     Evenement e=new Evenement(t,"E_ARRIVE");
		     calendrier.add(e);
		   }
		   t+=deltatT;  
	   }
	   System.out.println(nbA);
	
    }
   public void affCalendrier() {
	   for(int i=0;i<calendrier.size();i++) {
		   System.out.println(calendrier.get(i).toString());
	   }
   }
public Simulation(int ts, double lambda,int mu,int taillefile) {
	super();
	Ts = ts;
	this.lambda = lambda;
	this.mu=mu;
	ifile=0;
	t=0;
	nbA=0;
	nbD=0;
	this.Taillefile=taillefile;
	fileattente=new int[taillefile];
	Etat_server="LIBRE";
	count=0;
	tabfil=new int[taillefile];
	tabsys=new int[taillefile];
	tempsys=new int[taillefile];
	tempsfil=new int[taillefile];
}
public void triCalendrier()
{
    int i = 0;
    while (i<calendrier.size())
    {
        int min_t= calendrier.get(i).getT();
        int ind_min=i;
        for(int j = i+1;j<calendrier.size();j++)
        {
            if(calendrier.get(j).getT()<min_t)
            {
 
                min_t = calendrier.get(j).getT();
                ind_min=j;
                //System.out.println("------------------------------ il y a un tri ");
            }
        }

        Evenement ev_temp = new Evenement(calendrier.get(i).getT(),calendrier.get(i).getType_event());
        calendrier.get(i).setType_event(calendrier.get(ind_min).getType_event());
        calendrier.get(i).setT(calendrier.get(ind_min).getT());
        calendrier.get(ind_min).setType_event(ev_temp.getType_event());
        calendrier.get(ind_min).setT(ev_temp.getT());
        i++;
    }
}
public void arriveClient(int temps) {
	 System.out.println("Execution d'un evenement 'E_ARRIVE' a l'instant "+temps);
	 if(Etat_server.equals("LIBRE")){
		 Etat_server="OCCUPE";
		 Random r= new Random();
		 int t_depart=temps+r.nextInt(mu);
		 Evenement e=new Evenement(t_depart,"E_DEPART");
	     calendrier.add(e);
	     //triCalendrier();
	 }
	 else{
		 
		 if(ifile<Taillefile)
		 {
			 fileattente[ifile]=1;
			 ifile++;
			
			 System.out.println("la taille de la fille "+ifile);
		 }
		 else
			 System.out.println("Client perdu");
	 }
			 
 }
public void departClient(int temps) {
	nbD++;
	System.out.println("Execution d'un evenement 'E_DEPART' a l'instant "+temps);
	 if(ifile>0)
	 {
		 fileattente[ifile-1]=0;
		 ifile--;
		 System.out.println("la taille de la fille "+ifile);
		 Random r= new Random();
		 int t_depart=temps+r.nextInt(mu);
		 Evenement e=new Evenement(t_depart, "E_DEPART");
		 calendrier.add(e);
		// triCalendrier();
	 }
	 else
			Etat_server="LIBRE";
	 

}
public double moyenne(int tab[]){
	double somF=0;
	for(int i=0;i<tab.length;i++)
	{
		somF+=tab[i];
	}
	return somF/(count);
}

public double variance(int tab[]){
	double Var=0;
	for(int i=0;i<tab.length;i++)
	{
		Var+=Math.pow(tab[i]-moyenne(tab),2);
	}
	return Var/(tab.length-1);
}
public double ecartype(int tab[]){
	return Math.sqrt(variance(tab));
}

public void derouleur() {
	int t=0;
	int  i =0;
	System.out.println(calendrier.size());
	// int deltaT=1;
	 while(i<calendrier.size()) {
		 
		 while(i<calendrier.size() && calendrier.get(i).getT()==t)
         {
				if (calendrier.get(i).getType_event().equals("E_ARRIVE"))
					arriveClient(t);
				else
				   departClient(t);
				i++;
		 }
         if (i<calendrier.size()) 
         t=calendrier.get(i).getT();
         triCalendrier();
		 if(ifile<Taillefile)
		 {
		 tabfil[count]=ifile;
		 if(Etat_server=="OCCUPE")tabsys[count]=ifile+1;
		 count++;
		 }
		 
		 /*System.out.println("Nombre des clients moyen dans la file="+moyenne(tabfil));
		 System.out.println("Nombre des clients moyen dans le systeme="+moyenne(tabsys));
		 System.out.println("temps moyen d'attente dans la file="+moyenne(tabfil)/lambda);
		 System.out.println("temps moyen d'attente dans le systeme="+moyenne(tabsys)/lambda);*/
		// t+=deltaT; 	 
	 }
	
	 
	    System.out.println("arrivee " +nbA);
		System.out.println("departs " +nbD);

}
}
