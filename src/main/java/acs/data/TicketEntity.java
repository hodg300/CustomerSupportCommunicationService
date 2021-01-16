package acs.data;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import acs.annotations.Email;

@Entity
@Table(name="TICKETS")
public class TicketEntity {
    @Id
    private String id;

    @NotEmpty(message="Name can not be empty")
    private String name;

    @Email
    private String email;// EMAIL VARCHAR(255)

    @NotNull
    private Boolean isOpen;

    @NotNull
    private Date createdTimeStamp;

    private Date closingTimeStamp;

    public TicketEntity() {
    }

    public TicketEntity(String name, String email, @NotNull Boolean isOpen, @NotNull Date createdTimeStamp, Date closingTimeStamp) {
        this.name = name;
        this.email = email;
        this.isOpen = isOpen;
        this.createdTimeStamp = createdTimeStamp;
        this.closingTimeStamp = closingTimeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public Date getClosingTimeStamp() {
        return closingTimeStamp;
    }

    public void setClosingTimeStamp(Date closingTimeStamp) {
        this.closingTimeStamp = closingTimeStamp;
    }
}
