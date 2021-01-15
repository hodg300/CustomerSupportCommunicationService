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
    // Create ticket
    @RequestMapping(path = "/ticket", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TicketBoundary createTicket(@RequestBody TicketBoundary ticketBoundary) {
        System.out.println("here1");
        return this.ticketService.createTicket(ticketBoundary);
    }

    //PUT
    // Update user details
    @RequestMapping(path = "/ticket", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void closeTicket(@RequestBody TicketBoundary update) {
        System.out.println("here2");
        this.ticketService.closeTicket(update);
    }




}
