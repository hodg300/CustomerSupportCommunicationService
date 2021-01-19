package acs.kafka;

import acs.boundary.CommentBoundary;
import acs.boundary.TicketBoundary;
import acs.logic.CommentService;
import acs.logic.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Consumer;

@Configuration
public class KafkaConsumer {

    private TicketService ticketService;
    private CommentService commentService;

    @Autowired
    public KafkaConsumer(TicketService ticketService, CommentService commentService) {
        this.ticketService = ticketService;
        this.commentService = commentService;
    }

    @Bean
    public Consumer<TicketBoundary> createTicketConsumer(){
        System.err.println("simba");
        return ticket -> ticketService.createTicket(ticket);
//        return ticket -> System.err.println(ticket);

    }

    @Bean
    public Consumer<CommentBoundary> createCommentConsumer(){
        System.out.println("simba");
        return comment -> commentService.createComment(comment);
//        return r -> System.err.println("im in createCommentConsumer - "+r);
    }

}
