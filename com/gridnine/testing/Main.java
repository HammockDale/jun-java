import java.time.LocalDateTime;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        List<Flight> flyList = FlightBuilder.createFlights();
        final LocalDateTime myNow = LocalDateTime.now();        //TODO  .plusDays(3).plusHours(7);
        flyList.parallelStream()
                .filter(fl -> {
                    LocalDateTime local = null;
                    for (Segment sg : fl.getSegments()) {
                        if (sg.getArrivalDate().isAfter(myNow)) {
                            return false;
                        }
                        if (local != null) {
                            if (local.plusHours(2).isBefore(sg.getDepartureDate())) {
                                return false;
                            }
                        }
                        if (sg.getDepartureDate().isAfter(sg.getArrivalDate())) {
                            return false;
                        }
                        local = sg.getArrivalDate();
                    }
                    return true;
                })
                .forEach(System.out::println);
    }
}