package com.example.TicketingSystem.services;

import com.example.TicketingSystem.models.Notification;
import com.example.TicketingSystem.repositories.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(NotificationRepository notificationRepository, SimpMessagingTemplate messagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(String emailId, String message) {
        // ✅ Save notification in the database
        Notification notification = new Notification(emailId, message, false, LocalDateTime.now());
        notificationRepository.save(notification);

        // ✅ Send real-time WebSocket message (correct destination)
        messagingTemplate.convertAndSendToUser(emailId, "/queue/notifications", notification);
    }
    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteReadNotificationsEveryMinute() {
        notificationRepository.deleteByIsReadTrue();
        System.out.println("✅ Deleted all read notifications (every minute).");
    }
    public boolean markAsRead(Long id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isPresent()) {
            Notification notification = optionalNotification.get();
            notification.setIsRead(true);
            notificationRepository.save(notification);
            return true;
        }
        return false;
    }

}