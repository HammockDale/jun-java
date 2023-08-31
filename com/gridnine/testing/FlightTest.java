import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.time.LocalDateTime;
import java.util.List;



public class FlightTest {


    public static void main(String[] args) {
        Result results = JUnitCore.runClasses(FlightTest.class);
        for (Failure failure : results.getFailures()) {
            System.out.println("Тест не пройден: " + failure);
            return;
        }
        System.out.println("Тест пройден");

    }


    @Test
    public void testNowFlightFilter() throws Exception {
        System.out.println("testNowFlightFilter()");

        List<Flight> flyList = FlightBuilder.createFlights();
        Main m = new Main();
        LocalDateTime now = LocalDateTime.now();

        List<Flight> lf = m.flyFilter(flyList, now);
        lf.forEach(System.out::println);
        Assert.assertEquals(0, lf.size());
    }


    @Test
    public void testOldFlightFilter() throws Exception {
        System.out.println("testOldFlightFilter()");

        List<Flight> flyList = FlightBuilder.createFlights();
        Main m = new Main();
        LocalDateTime myNow = LocalDateTime.now().plusDays(3).plusHours(7);

        List<Flight> lf = m.flyFilter(flyList, myNow);
        lf.forEach(System.out::println);

        Assert.assertEquals(4, lf.size());
        Assert.assertEquals(true, lf.contains(flyList.get(0)));
        Assert.assertEquals(true, lf.contains(flyList.get(1)));
        Assert.assertEquals(true, lf.contains(flyList.get(2)));
        Assert.assertEquals(true, lf.contains(flyList.get(5)));
    }

}
