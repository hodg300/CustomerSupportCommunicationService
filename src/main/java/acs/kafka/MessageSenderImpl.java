package acs.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderImpl implements MessageSender{
	private StreamBridge messageBridge;
	private String destination;
	
	@Autowired
	public MessageSenderImpl(StreamBridge messageBridge) {
		this.destination = "t2";
		this.messageBridge = messageBridge;
	}
	
	@Override
	public void sendMessage(RemoteMessage message) {
		System.err.println("sending message - " + message + "...");
		this.messageBridge
			.send(this.destination, message);
		
	}

}
