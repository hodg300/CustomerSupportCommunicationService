package acs.boundary;

import java.util.Date;

public class TicketBoundary {

    private String ticketId;
    private String name;
    private String email;
    private Boolean isOpen;
    private Date timeStamp;

    public TicketBoundary() {}

    public TicketBoundary(String name, String email, Date timeStamp) {
        this.name = name;
        this.email = email;
        this.timeStamp = timeStamp;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
