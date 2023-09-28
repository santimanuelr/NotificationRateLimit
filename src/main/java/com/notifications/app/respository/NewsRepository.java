package com.notifications.app.respository;

import com.notifications.app.model.NewsNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsNotification, Long> {
}
