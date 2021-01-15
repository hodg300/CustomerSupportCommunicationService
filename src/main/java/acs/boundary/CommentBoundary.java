package acs.boundary;

import java.util.Date;

public class CommentBoundary {

    private String id;
    private String ticketId;
    private String email;
    private String description;
    private Date timeStamp;

    public CommentBoundary() {}

    public CommentBoundary( String ticketId, String email, String description, Date timeStamp) {
        this.ticketId = ticketId;
        this.email = email;
        this.description = description;
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
