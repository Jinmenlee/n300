package itri.n300.aml;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

      // add option "-s"
      options.addOption("s", true, "search");

      // add option "-f"
      options.addOption("f", true, "fuzzy search");

      //***Parsing Stage***
      //Create a parser
      CommandLineParser parser = new DefaultParser();

      //parse the options passed as command line arguments
      try {
        CommandLine cmd = parser.parse( options, args);

        LocalTime startTime = java.time.LocalTime.now();
        if(cmd.hasOption("a")) { 
            System.out.println("===============================================");
            System.out.println("Index content from : " + startTime);
            System.out.println("===============================================");
            String[] param = {"-index", "indexdir", "-docs", "files"};
            IndexFiles.main(param);

            LocalTime endTime = java.time.LocalTime.now();
            System.out.println("===============================================");
            System.out.println("     Index stop at : " + endTime);
            long millis = Duration.between(startTime, endTime).toMillis();
            long m = millis % 1000;
            System.out.print("total milliseconds : ");
            System.out.println(String.format("   %02d:%02d.%03d", 
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis),
                m)
            );
            System.out.println("===============================================");
           
        } else if(cmd.hasOption("s")) {
            System.out.println("===============================================");
            System.out.println("       Search from : " + startTime);
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

            LocalTime endTime = java.time.LocalTime.now();
            System.out.println("===============================================");
            System.out.println("    Search stop at : " + endTime);
            long millis = Duration.between(startTime, endTime).toMillis();
            long m = millis % 1000;
            System.out.print("total milliseconds : ");
            System.out.println(String.format("   %02d:%02d.%03d", 
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis),
                m)
            );
            System.out.println("===============================================");

        } else if(cmd.hasOption("f")) {
            System.out.println("===============================================");
            System.out.println("   Fuzzy Search from : " + startTime);
            System.out.println("===============================================");
            String[] value = cmd.getOptionValues("f");
            List<String> where = new ArrayList<String>();
            where.add("-query");
            for(String item : value) {
                where.add(item);
            }
            String[] param = new String[where.size()];
            where.toArray(param);
            FuzzySearch.main(param);

            LocalTime endTime = java.time.LocalTime.now();
            System.out.println("===============================================");
            System.out.println("Fuzzy Search stop at : " + java.time.LocalTime.now());
            long millis = Duration.between(startTime, endTime).toMillis();
            long m = millis % 1000;
            System.out.print("  total milliseconds : ");
            System.out.println(String.format("   %02d:%02d.%03d", 
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis),
                m)
            );
            System.out.println("===============================================");

        } else {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Search demo", options);
        }

      } catch (Exception e) {  
          System.out.println(e.toString());
      }
    }
}