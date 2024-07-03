package org.exampledana;

import java.util.*;

public class FestivalGate {
    protected List<TicketType> tickets;
    protected boolean isEmpty=true;

    public FestivalGate(){
        tickets= Collections.synchronizedList(new LinkedList<TicketType>());
    }

}
