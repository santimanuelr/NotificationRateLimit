package com.notifications.app.respository;

import com.notifications.app.model.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findTop3ByUserIdOrderByCreationDateDesc(@Param("userId") String userId);

    //List<SLSNotification> findByUserIdOrderBySNumber(@Param("userId") String userId, Pageable pageable);
}
