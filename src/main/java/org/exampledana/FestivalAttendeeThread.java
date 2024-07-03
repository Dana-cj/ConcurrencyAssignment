package org.exampledana;

import java.util.Random;

public class FestivalAttendeeThread extends Thread{
    TicketType ticketType;
    FestivalGate gate;
    boolean hasEntered;

    public FestivalAttendeeThread(FestivalGate gate){
        setTicketType();
        this.gate=gate;
    }

    public void run(){
        int waitingTime= new Random().nextInt(10);
        try {
            sleep(waitingTime*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        validateTicket();

        if(waitingTime%6==0){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            validateTicket();
        }
    }

    private void validateTicket() {
        if(!hasEntered) {
            System.out.println("A new attendee validated the ticket! (Thread=" + Thread.currentThread().getName() + ") Ticket type= " + ticketType);
            gate.tickets.add(ticketType);
            hasEntered = true;
            if (gate.isEmpty) {
                gate.isEmpty = false;
            }
        } else {
            System.out.println("This ticket was already registered! Ticket type= " + ticketType);
        }
    }

    private void setTicketType(){
        TicketType[] ticketTypes=  TicketType.values();
        ticketType= ticketTypes[new Random().nextInt(ticketTypes.length)];
    }

    @Override
    public String toString() {
        return "FestivalAttendeeThread{" +
                "ticketType=" + ticketType +
                ", gate=" + gate +
                "} " + super.toString();
    }
}
