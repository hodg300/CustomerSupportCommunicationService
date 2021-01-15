package acs.rest;

import acs.boundary.TicketBoundary;
import acs.logic.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {
    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

//    //GET
//    @RequestMapping(path = "/users/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public TicketBoundary getUser(@PathVariable("email") String email) {
//        return ticketService.getUser(email);
//    }
//

    //POST
    // Store product
    @RequestMapping(path = "/ticket", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TicketBoundary createTicket(@RequestBody TicketBoundary ticketBoundary) {
        return this.ticketService.createTicket(ticketBoundary);
    }




}
