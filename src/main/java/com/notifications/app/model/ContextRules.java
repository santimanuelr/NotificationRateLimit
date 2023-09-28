package com.notifications.app.model;

import com.notifications.app.respository.MarketingRepository;
import com.notifications.app.respository.NotificationRepository;
import lombok.Data;

@Data
public class ContextRules {

    private Notification notification;
    private NotificationRepository notificationRepository;
    private MarketingRepository marketingRepository;
    private Boolean canBeSend = Boolean.TRUE;
}
