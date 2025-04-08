package com.example.TicketingSystem.repositories;

import com.example.TicketingSystem.models.Tickets;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Tickets, String> {
    List<Tickets> findByStatus(String status);
    List<Tickets> findByCreatedBy(String createdBy);
    List<Tickets> findByAssignTo(String assignTo);
    Optional<Tickets> findByTicketId(String ticketId);


    @Query("SELECT t FROM Tickets t " +
            "WHERE (:createdBy IS NULL OR t.createdBy = :createdBy) " +
            "OR (:assignTo IS NULL OR t.assignTo = :assignTo)")
    List<Tickets> findByCreatedByOrAssignTo(@Param("createdBy") String createdBy,
                                            @Param("assignTo") String assignTo);

    List<Tickets> getTicketsByDepartment(@Size(max = 50) String department);

    List<Tickets> findByDepartment(@Size(max = 50) String department);

    int countByAssignTo(String assignTo);

    List<Tickets> findByStatusAndCreatedDateBeforeAndAssignToIsNull(String status, LocalDateTime createdDate);
}
