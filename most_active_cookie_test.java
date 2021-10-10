import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

public class most_active_cookie_test {
    most_active_cookie MostActiveCookie = new most_active_cookie();

    @Test 
    public void cookieTest() {
        List<String> testCSV = new ArrayList<String>();
        testCSV.add("SAZuXPGUrfbcn5UA,2018-12-08T22:03:00+00:00");
        testCSV.add("4sMM2LxV07bPJzwf,2018-12-08T21:30:00+00:00");
        testCSV.add("fbcn5UAVanZf6UtG,2018-12-08T09:30:00+00:00");

        List<String> test = MostActiveCookie.getMostActive(testCSV);

        assertEquals(testCSV, test);
    }
}