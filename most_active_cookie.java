import java.util.*;
import java.io.*;

class most_active_cookie {

    static HashMap<String, Integer> cookieMap = new HashMap<String, Integer>();
    public static void main(String[] args) throws FileNotFoundException {
        // checking for all necessary params
        // System.out.println(args.length);
        if(args.length != 3) {
            System.err.println("Check usage");
        }

        // checking for d flag
        if(!args[1].equals("-d")) {
            System.err.println("Check usage, -d flag needed");
        } 

        // parses through the csv via a scanner and creates a list to represent the csv
        String date = args[2];

        List<String> csv = convertCSV(date, args[0]);

        printCookies(date, csv);
    }

    public static List<String> convertCSV(String date, String filename) throws FileNotFoundException {
        List<String> csv = new ArrayList<String>();
        Scanner scan = new Scanner(new FileReader(filename));
        scan.useDelimiter("\n");
        while(scan.hasNext()) {
            csv.add(scan.next());
        }
        scan.close();
        return csv;
    }

    public static void printCookies(String date, List<String> csv) {
        // gets the most active cookies and prints them
        List<String> mostActiveCookies = getMostActive(date, csv);
        for(String cookie : mostActiveCookies) {
            System.out.println(cookie);
        }
    }

    public static List<String> getMostActive(String date, List<String> csv) {
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