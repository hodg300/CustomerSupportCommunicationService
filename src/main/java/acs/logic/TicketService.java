package acs.logic;

import acs.boundary.TicketBoundary;

public interface TicketService {
    TicketBoundary createTicket(TicketBoundary ticketBoundary);
}
