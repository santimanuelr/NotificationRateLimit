package com.notifications.app.service;

import org.springframework.stereotype.Service;

public interface NotificationService {

    void send(String type, String userId, String message) throws Exception;

    void deleteAll();
}
