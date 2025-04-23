package com.example.TicketingSystem.repositories;

import com.example.TicketingSystem.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByEmailIdAndIsReadFalse(String emailId);
    void deleteByIsReadTrue();
}