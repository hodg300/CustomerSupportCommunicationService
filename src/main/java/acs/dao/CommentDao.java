package acs.dao;


import acs.data.CommentEntity;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<CommentEntity, String> {

    List<CommentEntity> findAllByTicket_TicketId(@Param("ticketId") String ticketId, Pageable pageable);
}
