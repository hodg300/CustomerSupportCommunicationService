package acs.logic;

import acs.boundary.TicketBoundary;
import acs.logic.utils.FilterType;
import acs.utils.SortBy;
import acs.utils.SortOrder;

import java.util.List;

public interface TicketService {
    TicketBoundary createTicket(TicketBoundary ticketBoundary);

    void closeTicket(TicketBoundary update);

    List<TicketBoundary> getAllTickets(FilterType filterType, String filterValue, int size, int page, SortBy sortBy, SortOrder sortOrder);
}
