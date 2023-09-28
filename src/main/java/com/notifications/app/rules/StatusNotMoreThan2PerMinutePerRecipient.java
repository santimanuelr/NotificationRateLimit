package com.notifications.app.rules;

import com.notifications.app.model.Notification;
import com.notifications.app.model.StatusNotification;
import com.notifications.app.respository.NotificationRepository;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "StatusNotMoreThan2PerMinutePerRecipient",
        description = "Prevent send more Status Notifications than 2 per minute per recipient")
public class StatusNotMoreThan2PerMinutePerRecipient {

    @Condition
    public boolean isNewsNotification(@Fact("notification") Notification notification) {
        return notification instanceof StatusNotification;
    }

    @Action
    public void checkRateLimit(@Fact("notification") Notification notification,
                               @Fact("notificationRepository") NotificationRepository notificationRepository,
                               @Fact("canBeSend") Boolean canBeSend) throws Exception {

        //todo
    }

}
