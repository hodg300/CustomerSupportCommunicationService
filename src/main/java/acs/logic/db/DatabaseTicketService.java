package acs.logic.db;

import acs.boundary.TicketBoundary;
import acs.dao.TicketDao;
import acs.data.TicketEntity;
import acs.exceptions.NotFoundException;
import acs.logic.TicketService;
import acs.logic.utils.TicketConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return this.converter.
                fromEntity(this.ticketDao.save(this.converter.toEntity(ticketBoundary)));

    }

    @Override
    @Transactional
    public void closeTicket(TicketBoundary update) {
        System.out.println(update.getId());

        TicketEntity ticketEntity = this.ticketDao.findById(update.getId()).orElseThrow(
                () -> new RuntimeException("no ticket found by id: " + update.getId()));

        ticketEntity.setOpen(false);
        this.ticketDao.save(ticketEntity);

    }
}
