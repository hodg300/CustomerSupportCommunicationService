package acs.data;

import acs.utils.ExternalServiceType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    @NotNull
    private ExternalServiceType externalServiceType;

    private String externalId;

    @NotNull
    private Date createdTimeStamp;

    private Date closingTimeStamp;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CommentEntity> commentsEntities;

    public TicketEntity() {
        commentsEntities = new ArrayList<>();
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

    public ExternalServiceType getExternalServiceType() {
        return externalServiceType;
    }

    public void setExternalServiceType(ExternalServiceType externalServiceType) {
        this.externalServiceType = externalServiceType;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
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

    public List<CommentEntity> getCommentsEntities() {
        return commentsEntities;
    }

    public void setCommentsEntities(List<CommentEntity> commentsEntities) {
        this.commentsEntities = commentsEntities;
    }

    public void addProductElement(CommentEntity comment) {
        this.commentsEntities.add(comment);
    }
}
