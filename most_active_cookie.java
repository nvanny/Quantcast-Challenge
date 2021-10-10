import java.util.*;
import java.io.*;

class most_active_cookie {

    static HashMap<String, Integer> cookieMap = new HashMap<String, Integer>();
    public static void main(String[] args) throws FileNotFoundException{
        // checking for all necessary params
        // System.out.println(args.length);
        if(args.length != 3) {
            System.err.println("Check usage");
        }

        // checking for d flag
        if(!args[1].equals("-d")) {
            System.err.println("Check usage, -d flag needed");
        } 

        String date = args[2];
        List<String> csv = new ArrayList<>();
        Scanner scan = new Scanner(new FileReader(args[0]));
        scan.useDelimiter("\n");
        while(scan.hasNext()) {
            csv.add(scan.next());
        }
        scan.close();

        List<String> mostActiveCookies = getMostActive(date, csv);
        for(String cookie : mostActiveCookies) {
            System.out.println(cookie);
        }
    }

    public static List<String> getMostActive(String date, List<String> csv) {
        List<String> cookieList = new ArrayList<>();
        int max = 0;
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
        for(String cookieKey : cookieMap.keySet()) {
            if(cookieMap.get(cookieKey) >= max) {
                cookieList.add(cookieKey);
            }
        }

        return cookieList;
    }
}