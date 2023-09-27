package com.notifications.app.service;

public interface NotificationService {

    void send(String type, String userId, String message);

}
