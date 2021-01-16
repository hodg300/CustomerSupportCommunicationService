package acs.logic.db;

import acs.boundary.TicketBoundary;
import acs.dao.TicketDao;
import acs.data.TicketEntity;
import acs.exceptions.BadRequestException;
import acs.logic.TicketService;
import acs.logic.utils.TicketFilterType;
import acs.logic.utils.TicketConverter;
import acs.logic.utils.User;
import acs.producers.UserManagementRestService;
import acs.utils.TicketSortBy;
import acs.utils.SortOrder;
import acs.utils.UserRoles;
import acs.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DatabaseTicketService implements TicketService {
    private TicketDao ticketDao; // Data access object
    private TicketConverter converter;
    private UserManagementRestService userManagementRestService;

    @Autowired
    public DatabaseTicketService(TicketDao ticketDao, TicketConverter converter,
                                 UserManagementRestService userManagementRestService) {
        this.ticketDao = ticketDao;
        this.converter = converter;
        this.userManagementRestService = userManagementRestService;
    }

    @Override
    @Transactional
    public TicketBoundary createTicket(TicketBoundary ticketBoundary) {
        this.userManagementRestService.getUser(ticketBoundary.getEmail());//The UserManagementService will throw an exception if the user doesn't exists
        TicketEntity entity = this.converter.toEntity(ticketBoundary);
        entity.setOpen(true);
        entity.setCreatedTimeStamp(new Date());
        entity.setClosingTimeStamp(null);
        entity.setId(UUID.randomUUID().toString());
        return this.converter.fromEntity(this.ticketDao.save(entity));
    }

    @Override
    @Transactional
    public void closeTicket(TicketBoundary update) {
        User user = this.userManagementRestService.getUser(update.getEmail()); //The UserManagementService will throw an exception if the user doesn't exists
        System.out.println("simba");
        System.out.println(user.getRoles());
        if(Arrays.asList(user.getRoles()).contains(Utils.upperCaseToCamelCase(UserRoles.SUPPORT_AGENT.toString()))){ //Only support agents can close tickets
            TicketEntity ticketEntity = this.ticketDao.findById(update.getId()).orElseThrow(
                    () -> new RuntimeException("no ticket found by id: " + update.getId()));
            ticketEntity.setOpen(false);
            ticketEntity.setClosingTimeStamp(new Date());
            this.ticketDao.save(ticketEntity);
            return;
        }
        throw new BadRequestException("The user is not support agent and thus he can't close tickets");
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketBoundary> getAllTickets(TicketFilterType ticketFilterType, String filterValue, int size, int page, TicketSortBy ticketSortBy, SortOrder sortOrder) {
        Sort.Direction direction = sortOrder == SortOrder.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;

        if (ticketFilterType != null && filterValue != null) {
            if (ticketFilterType.equals(TicketFilterType.BY_TICKET_NAME)) {
                return this.ticketDao
                        .findAllByNameLikeIgnoreCase(filterValue,
                                PageRequest.of(page, size, direction, Utils.upperCaseToCamelCase(ticketSortBy.toString())))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }else if (ticketFilterType.equals(TicketFilterType.BY_CREATION)) {
                return this.ticketDao
                        .findAllByCreatedTimeStampBetween(Utils.getFromDate(filterValue),new Date(),
                                PageRequest.of(page, size, direction, Utils.upperCaseToCamelCase(ticketSortBy.toString())))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }else if (ticketFilterType.equals(TicketFilterType.BY_EMAIL)) {
                return this.ticketDao.findAllByEmail(filterValue,
                        PageRequest.of(page, size, direction, Utils.upperCaseToCamelCase(ticketSortBy.toString())))
                        .stream().map(this.converter::fromEntity).collect(Collectors.toList());
            }
        }

        return this.ticketDao.findAll(PageRequest.of(page, size, direction, Utils.upperCaseToCamelCase(ticketSortBy.toString()))).getContent()
                .stream().map(this.converter::fromEntity).collect(Collectors.toList());
    }

}
