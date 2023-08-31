import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public List<Flight> flyFilter(List<Flight> flyList,  LocalDateTime myNow) {
        return (flyList.parallelStream()
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
                .collect(Collectors.toList()));
//                .forEach(System.out::println);
    }


    public static void main(String[] args) {

        List<Flight> flyList = FlightBuilder.createFlights();
        Main m = new Main();
        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime myNow = LocalDateTime.now().plusDays(3).plusHours(7);

        m.flyFilter(flyList, now).forEach(System.out::println);
    }



}