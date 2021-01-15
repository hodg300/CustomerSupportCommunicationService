package acs.logic.utils;
import acs.boundary.TicketBoundary;
import acs.data.TicketEntity;
import org.springframework.stereotype.Component;



@Component
public class TicketConverter {

    public TicketBoundary fromEntity(TicketEntity entity) {
        TicketBoundary rv = new TicketBoundary();
        rv.setId(entity.getId());
        rv.setEmail(entity.getEmail());
        rv.setOpen(entity.getOpen());
        rv.setName(entity.getName());
        rv.setTimeStamp(entity.getTimeStamp());

        return rv;
    }

    public TicketEntity toEntity(TicketBoundary boundary) {
        TicketEntity rv = new TicketEntity();
        rv.setEmail(boundary.getEmail());
        rv.setOpen(boundary.getOpen());
        rv.setName(boundary.getName());
        rv.setTimeStamp(boundary.getTimeStamp());
        return rv;
    }
}
