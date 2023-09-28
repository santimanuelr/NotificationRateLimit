package com.notifications.app.model;

import com.notifications.app.respository.NotificationRepository;
import lombok.Data;

@Data
public class ContextRules {

    private Notification notification;
    private NotificationRepository notificationRepository;
    private Boolean canBeSend = Boolean.TRUE;
}
