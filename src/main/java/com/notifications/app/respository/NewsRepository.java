package com.notifications.app.respository;

import com.notifications.app.model.NewsNotification;
import com.notifications.app.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsNotification, Long> {
    List<Notification> findTop1ByUserIdOrderByCreationDateDesc(String userId);

}
