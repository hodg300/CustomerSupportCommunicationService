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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message="Name can not be empty")
    private String name;

    @Email
    private String email;        // EMAIL VARCHAR(255)

    @NotNull
    private Date timeStamp;    // CREATED_TIME_STAMP TIMESTAMP

    public TicketEntity() {
    }

    public TicketEntity(String name, String email, @NotNull Date timeStamp) {
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
