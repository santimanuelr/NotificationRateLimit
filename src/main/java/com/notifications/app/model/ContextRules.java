package com.notifications.app.model;

import com.notifications.app.respository.MarketingRepository;
import com.notifications.app.respository.NewsRepository;
import com.notifications.app.respository.NotificationRepository;
import com.notifications.app.respository.StatusRepository;
import lombok.Data;

@Data
public class ContextRules {

    private Notification notification;
    private NotificationRepository notificationRepository;
    private MarketingRepository marketingRepository;
    private NewsRepository newsRepository;
    private StatusRepository statusNotification;
    private Boolean canBeSend = Boolean.TRUE;
}
