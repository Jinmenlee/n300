package itri.n300.aml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class App {

    public static void main(String[] args) {

      //***Definition Stage***
      // create Options object
      Options options = new Options();

      // add option "-a"
      options.addOption("a", false, "add content");

      // add option "-m"
      options.addOption("s", true, "search");

      //***Parsing Stage***
      //Create a parser
      CommandLineParser parser = new DefaultParser();

      //parse the options passed as command line arguments
      try {
        CommandLine cmd = parser.parse( options, args);

        //***Interrogation Stage***
        //hasOptions checks if option is present or not
        if(cmd.hasOption("a")) { 
            System.out.println("===============================================");
           System.out.println("Index content from : " + java.time.LocalTime.now());
           System.out.println("===============================================");
           String[] param = {"-index", "indexdir", "-docs", "files"};
           IndexFiles.main(param);
           System.out.println("===============================================");
           System.out.println("     Index stop at : " + java.time.LocalTime.now());
           System.out.println("===============================================");
           
        } else if(cmd.hasOption("s")) {
            System.out.println("===============================================");
            System.out.println("   Search from : " + java.time.LocalTime.now());
            System.out.println("===============================================");
            String[] value = cmd.getOptionValues("s");
            List<String> where = new ArrayList<String>();
            where.add("-query");
            for(String item : value) {
                where.add(item);
            }
            String[] param = new String[where.size()];
            where.toArray(param);
           SearchFiles.main(param);
           System.out.println("===============================================");
           System.out.println("Search stop at : " + java.time.LocalTime.now());
           System.out.println("===============================================");

        } else {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Search demo", options);
        }

      } catch (Exception e) {  
          System.out.println(e.toString());
        // HelpFormatter formatter = new HelpFormatter();
        // formatter.printHelp("Search demo", options);
      }
    }
}