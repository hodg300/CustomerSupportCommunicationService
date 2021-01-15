package acs.logic.db;

import acs.boundary.TicketBoundary;
import acs.dao.TicketDao;
import acs.data.TicketEntity;
import acs.logic.TicketService;
import acs.logic.utils.TicketConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DatabaseTicketService implements TicketService {
    private TicketDao ticketDao; // Data access object
    private TicketConverter converter;

    @Autowired
    public DatabaseTicketService(TicketDao ticketDao, TicketConverter converter) {
        this.ticketDao = ticketDao;
        this.converter = converter;
    }

    @Override
    @Transactional
    public TicketBoundary createTicket(TicketBoundary ticketBoundary) {
        ticketBoundary.setOpen(true);
        TicketEntity entity = this.converter.toEntity(ticketBoundary);
        entity.setTicketId(UUID.randomUUID().toString());
        return this.converter.fromEntity(this.ticketDao.save(entity));

    }

    @Override
    @Transactional
    public void closeTicket(TicketBoundary update) {
        TicketEntity ticketEntity = this.ticketDao.findById(update.getTicketId()).orElseThrow(
                () -> new RuntimeException("no ticket found by id: " + update.getTicketId()));
        ticketEntity.setOpen(false);
        this.ticketDao.save(ticketEntity);
    }
}
