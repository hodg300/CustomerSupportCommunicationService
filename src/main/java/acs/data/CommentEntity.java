package acs.data;

import acs.annotations.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="COMMENTS")
public class CommentEntity {

    @Id
    private String id;

    @NotNull
    private String ticketId;

    @Email
    private String email;

    @NotEmpty(message="Description can not be empty")
    private String description;

    @NotNull
    private Date createdTimeStamp;

    public CommentEntity() {
    }

    public CommentEntity(@NotNull String ticketId, String email, String description) {
        this.ticketId = ticketId;
        this.email = email;
        this.description = description;
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

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }
}
