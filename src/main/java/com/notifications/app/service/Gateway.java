package com.notifications.app.service;

import com.notifications.app.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Gateway {

    /* already implemented */

    void send(String userId, String message) {
        log.info("sending message to user " + userId);
    }

    public void send(Notification notificationToSend) {
        log.info("sending message to user " + notificationToSend.getUserId());
    }
}
