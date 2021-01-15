package acs.dao;
import java.util.List;

import acs.data.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDao  extends JpaRepository<TicketEntity, String> {


}