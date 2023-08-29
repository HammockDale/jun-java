package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public class Main{

    public static void main(String[] args) throws BeforeNowException, MoreTwoHorasException, WrongTimeException{


        LocalDateTime now = LocalDateTime.now();
        now =now.plusDays(3).plusHours(7);


        List<Flight> flist = FlightBuilder.createFlights();

            for (Flight fl : flist) {                               //TODO for each
                LocalDateTime local = null;
                try {

                    for (Segment sg : fl.getSegments()) {
                        if (sg.getDepartureDate().isAfter(now)) {
                            System.out.println("Flight up to the current point in time");
                            throw new BeforeNowException();
                        }
                        if (local != null) {
                            if (local.plusHours(2).isBefore(sg.getDepartureDate())) {
                                System.out.println("Parking time more than 2 hours");
                                throw new MoreTwoHorasException();
                            }
                        }

                        if (sg.getDepartureDate().isAfter(sg.getArrivalDate())) {
                            System.out.println("Departure time before arrival time");
                            throw new WrongTimeException();
                        }
//                        System.out.println(sg.toString());
                        local = sg.getArrivalDate();
                    }
                    System.out.println(fl.getSegments().toString());

                } catch (BeforeNowException ex) {
                    ex.printStackTrace();
                } catch (MoreTwoHorasException ex) {
                    ex.printStackTrace();
                } catch (WrongTimeException ex) {
                    ex.printStackTrace();
                }

                System.out.println();
            }

//        } catch (BeforeNowException ex) {
//            ex.printStackTrace();
//        }
        System.out.println("Hello");
    }
}


class BeforeNowException extends Exception{

    public BeforeNowException(){

        super("Flight up to the current point in time");
    }
}


class MoreTwoHorasException extends Exception{

    public MoreTwoHorasException(){

        super("Parking time more than 2 hours");
    }
}


class WrongTimeException extends Exception{

    public WrongTimeException(){

        super("Departure time before arrival time");
    }
}