package acs.logic.db;

import acs.boundary.TicketBoundary;
import acs.dao.TicketDao;
import acs.data.TicketEntity;
import acs.logic.TicketService;
import acs.logic.utils.FilterType;
import acs.logic.utils.TicketConverter;
import acs.logic.utils.TimeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    @Transactional(readOnly = true)
    public List<TicketBoundary> getAllTickets(FilterType filterType, String filterValue, int size, int page, String sortBy, String sortOrder) {
        if (filterType != null && filterValue != null) {
            System.out.println(filterType);
            if (filterType.equals(FilterType.BY_TICKET_NAME)) {
                return this.ticketDao
                        .findAllByNameLikeIgnoreCase(filterValue,
                                PageRequest.of(page, size, Sort.Direction.valueOf(sortOrder), sortBy))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }else if (filterType.equals(FilterType.BY_CREATION)) {
                return this.ticketDao
                        .findAllByTimeStampBetween(getFromDate(filterValue),new Date(),
                                PageRequest.of(page, size, Sort.Direction.valueOf(sortOrder), sortBy))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }else if (filterType.equals(FilterType.BY_EMAIL)) {
                return this.ticketDao.findAllByEmail(filterValue,
                        PageRequest.of(page, size, Sort.Direction.valueOf(sortOrder), sortBy))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }
        }
        return this.ticketDao.findAll(PageRequest.of(page, size, Sort.Direction.valueOf(sortOrder), sortBy)).getContent()
                .stream().map(this.converter::fromEntity).collect(Collectors.toList());
    }


    private Date getFromDate(String timeEnum){
        LocalDateTime localDateTime = LocalDateTime.now();
        if(timeEnum.equals(TimeEnum.LAST_DAY.toString())){
            localDateTime = localDateTime.minusDays(1);
        }
        else if(timeEnum.equals(TimeEnum.LAST_WEEK.toString())){
            localDateTime = localDateTime.minusDays(7);
        }
        else if(timeEnum.equals(TimeEnum.LAST_MONTH.toString())){
            localDateTime = localDateTime.minusDays(30);
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

    }
}
