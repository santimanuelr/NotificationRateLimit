package com.notifications.app.respository;

import com.notifications.app.model.StatusNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusNotification, Long>  {
}
