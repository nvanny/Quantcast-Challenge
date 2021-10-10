import java.util.*;
import java.io.*;

class most_active_cookie {

    static HashMap<String, Integer> cookieMap = new HashMap<String, Integer>();
    static String date;
    static String filename;
    static List<String> csv;
    public static void main(String[] args) throws FileNotFoundException {
        // checking for all necessary params
        // System.out.println(args.length);
        if(args.length != 3) {
            System.err.println("Check usage: format of ./most_active_cookie [filename] [-d flag] [date]");
        }

        // checking for d flag
        if(!args[1].equals("-d")) {
            System.err.println("Check usage, -d flag needed");
        } 

        // parses through the csv via a scanner and creates a list to represent the csv
        date = args[2];

        filename = args[0];

        csv = convertCSV();

        printCookies();
    }

    public static List<String> convertCSV() throws FileNotFoundException {
        List<String> csv = new ArrayList<String>();
        try {
            Scanner scan = new Scanner(new FileReader(filename));
            scan.useDelimiter("\n");
            while(scan.hasNext()) {
                csv.add(scan.next());
            }
            scan.close();
        } catch (FileNotFoundException fe) {
            System.err.println("Filename" + filename + "not found");
        } catch(Exception e) {
            System.err.println("Please put a valid file format");
        }
        return csv; 
    }

    public static void printCookies() {
        // gets the most active cookies and prints them
        List<String> mostActiveCookies = getMostActive();
        for(String cookie : mostActiveCookies) {
            System.out.println(cookie);
        }
    }

    public static List<String> getMostActive() {
        List<String> cookieList = new ArrayList<>();
        int max = 0;

        // creating HashMap that contains values for valid cookies matching the timestamp
        for(String cookie : csv) {
            String[] cookieSplit = cookie.split(",");
            String cookieValue = cookieSplit[0];
            String timestamp = cookieSplit[1];
            String currDate = timestamp.substring(0, 10);
            if(currDate.equals(date)) {
                cookieMap.put(cookieValue, cookieMap.getOrDefault(cookieValue, 0)+1);
                max = Math.max(cookieMap.get(cookieValue), max);
            }
        }

        // creates list of valid cookies
        for(String cookieKey : cookieMap.keySet()) {
            if(cookieMap.get(cookieKey) >= max) {
                cookieList.add(cookieKey);
            }
        }

        return cookieList;
    }
}