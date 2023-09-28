package com.notifications.app.rest;

import com.notifications.app.model.NewsNotification;
import com.notifications.app.model.Notification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@SpringBootTest
public class NotificationsControllerTest {

    @Autowired
    NotificationController notificationController;

    @Test
    public void whenInsert2_then2AreExpected() {

        //When
        //I create a Notification
        Notification notification1 = new NewsNotification();
        notification1.setMessage("test");
        notification1.setUserId("1");
        Notification notification2 = new Notification();

        notificationController.create(notification1);
        notificationController.create(notification2);

        //Then
        Flux<Notification> result = notificationController.getAll();

        Assertions.assertEquals(2, result.count().block());

    }
}
