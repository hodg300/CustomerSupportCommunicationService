package acs.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaDemoApplication implements CommandLineRunner{
	private MessageSender sender;
	
	@Autowired
	public void setSender(MessageSender sender) {
		this.sender = sender;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.sender.sendMessage(new RemoteMessage("hello from spring service"));

		
	}

}
