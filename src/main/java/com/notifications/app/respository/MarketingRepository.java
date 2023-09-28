package com.notifications.app.respository;

import com.notifications.app.model.MarketingNotification;
import com.notifications.app.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarketingRepository extends JpaRepository<MarketingNotification, Long> {

    List<Notification> findTop3ByUserIdOrderByCreationDateDesc(@Param("userId") String userId);

}
