package com.notifications.app.service;

import com.notifications.app.model.NewsNotification;
import com.notifications.app.model.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Test
    public void notificationIsSendTest() {

        //When
        //I create a Notification
        Notification notification1 = new NewsNotification();
        notification1.setMessage("asd");
        notification1.setUserId("1");

        //Then
        Assertions.assertDoesNotThrow(() -> notificationService.send("MARKETING", "1", "asd"));

    }

    @Test
    public void notificationIsNotSendDoToRuleTest() {

        //When
        //I create a Notification
        Notification notification1 = new NewsNotification();
        notification1.setMessage("asd");
        notification1.setUserId("1");
        notificationService.deleteAll();

        //Then
        Assertions.assertDoesNotThrow(() -> notificationService.send("MARKETING", "1", "asd"));
        Assertions.assertDoesNotThrow(() -> notificationService.send("MARKETING", "1", "asd"));
        Assertions.assertDoesNotThrow(() -> notificationService.send("MARKETING", "1", "asd"));

        Assertions.assertThrows(Exception.class, () -> notificationService.send("MARKETING", "1", "asd"));
    }

}
