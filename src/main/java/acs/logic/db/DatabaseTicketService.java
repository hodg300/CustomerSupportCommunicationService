package acs.logic.db;

import acs.boundary.TicketBoundary;
import acs.dao.TicketDao;
import acs.data.TicketEntity;
import acs.logic.TicketService;
import acs.logic.utils.FilterType;
import acs.logic.utils.TicketConverter;
import acs.logic.utils.TimeEnum;
import acs.utils.SortBy;
import acs.utils.SortOrder;
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
        TicketEntity entity = this.converter.toEntity(ticketBoundary);
        entity.setOpen(true);
        //entity.setCreatedTimeStamp(new Date());
        entity.setClosingTimeStamp(null);
        entity.setId(UUID.randomUUID().toString());
        return this.converter.fromEntity(this.ticketDao.save(entity));

    }

    @Override
    @Transactional
    public void closeTicket(TicketBoundary update) {
        TicketEntity ticketEntity = this.ticketDao.findById(update.getId()).orElseThrow(
                () -> new RuntimeException("no ticket found by id: " + update.getId()));
        ticketEntity.setOpen(false);
        ticketEntity.setClosingTimeStamp(new Date());
        this.ticketDao.save(ticketEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketBoundary> getAllTickets(FilterType filterType, String filterValue, int size, int page, SortBy sortBy, SortOrder sortOrder) {
        Sort.Direction direction = sortOrder == SortOrder.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;

        if (filterType != null && filterValue != null) {
            System.out.println(filterType);
            if (filterType.equals(FilterType.BY_TICKET_NAME)) {
                return this.ticketDao
                        .findAllByNameLikeIgnoreCase(filterValue,
                                PageRequest.of(page, size, direction, String.valueOf(sortBy)))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }else if (filterType.equals(FilterType.BY_CREATION)) {
                return this.ticketDao
                        .findAllByCreatedTimeStampBetween(getFromDate(filterValue),new Date(),
                                PageRequest.of(page, size, direction, String.valueOf(sortBy)))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }else if (filterType.equals(FilterType.BY_EMAIL)) {
                return this.ticketDao.findAllByEmail(filterValue,
                        PageRequest.of(page, size, direction, String.valueOf(sortBy)))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }
        }
        System.out.println("simba");
        System.out.println(sortBy);
        System.out.println(String.valueOf(sortBy));
        System.out.println(String.valueOf(sortBy).toString());
        System.out.println(sortBy.toString());
        return this.ticketDao.findAll(PageRequest.of(page, size, direction, sortBy.toString())).getContent()
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
