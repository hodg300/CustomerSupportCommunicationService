package acs.boundary;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class TicketBoundary {

    private Integer id;
    private String name;
    private String email;
    private Date timeStamp;

    public TicketBoundary() {}

    public TicketBoundary(String name, String email, Date timeStamp) {
        this.name = name;
        this.email = email;
        this.timeStamp = timeStamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
