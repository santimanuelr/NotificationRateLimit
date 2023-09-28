package com.notifications.app.respository;

import com.notifications.app.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findTop3ByUserIdOrderByCreationDateDesc(String userId);

}
