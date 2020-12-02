package kata5;

import kata5.view.JFreeChartHistogramDisplay;
import kata5.view.HistogramDisplay;
import kata5.model.Histogram;
import java.util.Set;
import kata5.view.DatabaseLoader;
import kata5.view.MailDatabaseLoader;
/*import kata5.view.NameDatabaseLoader;*/

public class Kata5 {

    public static String getDomain(String s){
        return s.substring(s.indexOf("@") + 1);
    }
    
    public static void main(String[] args){
        DatabaseLoader loader = new MailDatabaseLoader();
        Set<String> strings = loader.load("jdbc:sqlite:data/us-500.db");
        Histogram<String> histogram = new Histogram<>();
        
        /*Trabajaremos con el dominio de los mails*/
        for(String s: strings){
            histogram.add(getDomain(s));
        }
        
        HistogramDisplay display = new JFreeChartHistogramDisplay("HISTOGRAM...", histogram);
        display.execute();
    }
}