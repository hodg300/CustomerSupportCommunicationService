package acs.logic.db;

import acs.boundary.CommentBoundary;
import acs.dao.CommentDao;
import acs.dao.TicketDao;
import acs.data.CommentEntity;
import acs.data.TicketEntity;
import acs.exceptions.BadRequestException;
import acs.logic.CommentService;
import acs.logic.utils.CommentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class DatabaseCommentService implements CommentService {

    private CommentDao commentDao; // Data access object
    private TicketDao ticketDao; // Data access object
    private CommentConverter converter;

    @Autowired
    public DatabaseCommentService(CommentDao commentDao ,TicketDao ticketDao, CommentConverter converter) {
        this.commentDao = commentDao;
        this.ticketDao = ticketDao;
        this.converter = converter;
    }

    @Override
    public CommentBoundary createComment(CommentBoundary commentBoundary) {
        CommentEntity entity = this.converter.toEntity(commentBoundary);
        TicketEntity ticketEntity = this.ticketDao.findById(entity.getTicketId()).orElseThrow(
                () -> new RuntimeException("No ticket found by id: " + entity.getTicketId()));
        if(ticketEntity.getOpen()){
            entity.setCreatedTimeStamp(new Date());
            entity.setId(UUID.randomUUID().toString());
            return this.converter.fromEntity(commentDao.save(entity));
        }
        throw new BadRequestException("Can't comment to close tickets");
    }
}
