package com.notifications.app.respository;

import com.notifications.app.model.Notification;
import com.notifications.app.model.StatusNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<StatusNotification, Long>  {
    List<Notification> findTop2ByUserIdOrderByCreationDateDesc(String userId);
}
