package com.notifications.app.rules;

import com.notifications.app.model.MarketingNotification;
import com.notifications.app.model.NewsNotification;
import com.notifications.app.model.Notification;
import com.notifications.app.respository.NotificationRepository;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.List;

@Rule(name = "NewsNotMoreThan1PerDayPerRecipient",
        description = "Prevent send more News Notifications than 1 per day per recipient")
public class NewsNotMoreThan1PerDayPerRecipient {

    @Condition
    public boolean isNewsNotification(@Fact("notification") Notification notification) {
        return notification instanceof NewsNotification;
    }

    @Action
    public void checkRateLimit(@Fact("notification") Notification notification,
                               @Fact("notificationRepository") NotificationRepository notificationRepository,
                               @Fact("canBeSend") Boolean canBeSend) throws Exception {
        List<Notification> notifications = notificationRepository.findTop3ByUserIdOrderByCreationDateDesc(
                notification.getUserId());
        if (notifications.size() >= 3) { notification.setCanBeSend(Boolean.FALSE); }
        //todo
    }
}
