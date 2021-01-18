package acs.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ConsumerDemo {
	@Bean
	public Consumer<RemoteMessage> receiveAndHandleRemoteMessage(){
		return r->System.err.println(r);
	}
}
