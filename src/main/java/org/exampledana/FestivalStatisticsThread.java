package org.exampledana;

import java.util.HashMap;
import java.util.Map;

public class FestivalStatisticsThread extends Thread{
    FestivalGate gate;


public FestivalStatisticsThread(FestivalGate gate){
    this.gate=gate;
}
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(gate.isEmpty){
                System.out.println("Statistics can not be generated! No attendee was registered!");
            } else {
                printStatistics();
            }

        }

    }

    public void printStatistics(){
        Map <TicketType, Integer> countTickets=new HashMap<>();
        for (TicketType ticketType:TicketType.values()) {
            countTickets.put(ticketType, 0);
        }
        gate.tickets.stream().forEach(ticketType -> {
           countTickets.replace(ticketType, countTickets.get(ticketType)+1);
        });

        System.out.println("""
                           %d people entered

                           %d people have full tickets

                           %d have free passes

                           %d have full VIP passes

                           %d have one-day passes

                           %d have one-day VIP passes
                """.formatted(countTickets.get(TicketType.FULL)+countTickets.get(TicketType.FREE_PASS)+countTickets.get(TicketType.FULL_VIP)+
                countTickets.get(TicketType.ONE_DAY)+countTickets.get(TicketType.ONE_DAY_VIP), countTickets.get(TicketType.FULL), countTickets.get(TicketType.FREE_PASS), countTickets.get(TicketType.FULL_VIP),
                countTickets.get(TicketType.ONE_DAY), countTickets.get(TicketType.ONE_DAY_VIP)));

    }
}
