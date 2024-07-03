package org.exampledana;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        FestivalGate gate1= new FestivalGate();
        FestivalStatisticsThread statistics= new FestivalStatisticsThread(gate1);
        statistics.setDaemon(true);
        statistics.start();

        List<FestivalAttendeeThread> attendees=new Vector<>();
        int count=0;
        while (count<100) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            FestivalAttendeeThread[] attendeeThreads=generateAttendees(gate1);
            attendees.addAll(Arrays.asList(attendeeThreads));
            count=attendees.size();
           // System.out.println(count+" persons were counted at the festival queue");

            for (FestivalAttendeeThread attendee:attendeeThreads){
                attendee.start();
            }
        }


        for (FestivalAttendeeThread attendee:attendees){
            try {
                attendee.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("-".repeat(50));
        System.out.println(count+" persons were counted at the festival queue");
        System.out.println("Gate closed! Final statistics:");
        statistics.printStatistics();

    }

    public static FestivalAttendeeThread[] generateAttendees(FestivalGate gate){

            Random random= new Random();
            FestivalAttendeeThread[] attendees= new FestivalAttendeeThread[random.nextInt(5)+1];
            for (int i=0; i<attendees.length; i++){
                attendees[i]=new FestivalAttendeeThread(gate);
            }
            return attendees;
        }
}
