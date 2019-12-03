package MSMM1;
import java.util.Vector;
public class Calendrier {
    Vector<Evenement> calendrier = new Vector<Evenement>();
    int icalendrier;


    public void ajouterEvent(Evenement ev)
    {
        calendrier.add(ev);
    }


    public void supprimerEvent(int index)
    {
        calendrier.remove(index);
    }


    public void initCalendrier()
    {
        calendrier=new Vector<Evenement>();
        icalendrier=0;
    }

    public Vector<Evenement> rechercheEvents(int t){
        Vector<Evenement> events = new Vector<Evenement>();
        int i = 0 ;
        while (i<calendrier.size()){
            if(calendrier.get(i).getT()<t)
            {
                i++;
            }else break;
        }
        while(i<calendrier.size()&&(calendrier.get(i).getT()==t))
        {
           // if(calendrier.get(i).getT()==t){
                events.add(calendrier.get(i));
          //  }
            i++;
        }
        return events;
    }
    
    public void bonnePosition(Evenement ev) {
    	int i = calendrier.size()-1;
    	while((i>0) && (ev.getT()<=calendrier.get(i-1).getT())) {
    		calendrier.get(i).setType_event(calendrier.get(i-1).getType_event());
    		calendrier.get(i).setT(calendrier.get(i-1).getT());
    		i--;
    	}
    	calendrier.get(i).setType_event(ev.getType_event());
		calendrier.get(i).setT(ev.getT());
    	
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

    public String toString()
    {
        String str = "";
        for (int i=0;i<calendrier.size();i++)
        {
            str = str + "\n"+calendrier.get(i).toString();
        }
        return str;
    }
}
