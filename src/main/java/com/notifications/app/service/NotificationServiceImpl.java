package com.notifications.app.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private Gateway gateway;

    public NotificationServiceImpl(Gateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void send(String type, String userId, String message) {

        //TODO

    }
}
