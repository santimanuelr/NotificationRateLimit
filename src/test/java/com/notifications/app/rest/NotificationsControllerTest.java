package com.notifications.app.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NotificationsControllerTest {

    @Autowired
    NotificationController notificationController;

    @Test
    public void whenInsert6_then6AreExpected() {

        //When
        //I create a Notification
        //Then
        //The notification is save in DB


    }
}
