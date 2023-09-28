package com.notifications.app.service;

import com.notifications.app.model.NotificationType;
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
        //Then
        Assertions.assertDoesNotThrow(() -> notificationService.send(NotificationType.MARKETING.name(), "1", "test"));

    }

    @Test
    public void notificationMarketingIsNotSendDoToRuleTest() {

        //When
        //I delete all Notifications
        notificationService.deleteAll();

        Assertions.assertDoesNotThrow(() -> notificationService.send(NotificationType.MARKETING.name(), "1", "test"));
        Assertions.assertDoesNotThrow(() -> notificationService.send(NotificationType.MARKETING.name(), "1", "test"));
        Assertions.assertDoesNotThrow(() -> notificationService.send(NotificationType.MARKETING.name(), "1", "test"));
        Assertions.assertDoesNotThrow(() -> notificationService.send(NotificationType.NEWS.name(), "1", "test"));

        //Then
        Assertions.assertThrows(Exception.class, () -> notificationService.send(NotificationType.MARKETING.name(), "1", "test"));
    }

    @Test
    public void notificationNewsIsNotSendDoToRuleTest() {

        //When
        //I delete all Notifications
        notificationService.deleteAll();

        Assertions.assertDoesNotThrow(() -> notificationService.send(NotificationType.NEWS.name(), "1", "test"));
        Assertions.assertDoesNotThrow(() -> notificationService.send(NotificationType.STATUS.name(), "2", "test"));
        Assertions.assertDoesNotThrow(() -> notificationService.send(NotificationType.STATUS.name(), "3", "test"));

        //Then
        Assertions.assertThrows(Exception.class, () -> notificationService.send(NotificationType.NEWS.name(), "1", "asd"));
    }

    @Test
    public void notificationStatusIsNotSendDoToRuleTest() {

        //When
        //I delete all Notifications
        notificationService.deleteAll();

        Assertions.assertDoesNotThrow(() -> notificationService.send(NotificationType.STATUS.name(), "1", "test"));
        Assertions.assertDoesNotThrow(() -> notificationService.send(NotificationType.NEWS.name(), "1", "test"));
        Assertions.assertDoesNotThrow(() -> notificationService.send(NotificationType.STATUS.name(), "1", "test"));

        //Then
        Assertions.assertThrows(Exception.class, () -> notificationService.send(NotificationType.STATUS.name(), "1", "asd"));
    }

}
