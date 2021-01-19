package kafka;

import acs.boundary.TicketBoundary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Consumer;

@Configuration
public class KafkaConsumer {

    @Bean
    public Consumer<String> createTicketConsumer(){
        System.out.println("simba");
        return r -> System.err.println(r);
    }

//    @Bean
//    public Consumer<CommentBoundary> createComment(){
//        return r->System.err.println(r);
//    }

}
