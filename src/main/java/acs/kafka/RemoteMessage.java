package acs.kafka;

public class RemoteMessage {
	private String message;

	public RemoteMessage() {
	}

	public RemoteMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "message: " + this.message;
	}
}
