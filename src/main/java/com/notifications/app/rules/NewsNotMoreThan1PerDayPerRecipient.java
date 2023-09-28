package com.notifications.app.rules;

import com.notifications.app.model.ContextRules;
import com.notifications.app.model.NewsNotification;
import com.notifications.app.model.Notification;
import com.notifications.app.respository.NewsRepository;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.List;

import static com.notifications.app.rules.utils.DateUtils.isMoreThanOneDay;
import static com.notifications.app.service.NotificationServiceImpl.CONTEXT_RULES_KEY;

@Rule(name = "NewsNotMoreThan1PerDayPerRecipient",
        description = "Prevent send more News Notifications than 1 per day per recipient")
public class NewsNotMoreThan1PerDayPerRecipient {

    @Condition
    public boolean isNewsNotification(@Fact(CONTEXT_RULES_KEY) ContextRules contextRules) {
        return contextRules.getNotification() instanceof NewsNotification;
    }

    @Action
    public void checkRateLimit(@Fact(CONTEXT_RULES_KEY) ContextRules contextRules) {
        Notification notification = contextRules.getNotification();
        NewsRepository notificationRepository = contextRules.getNewsRepository();
        List<Notification> notifications = notificationRepository.findTop1ByUserIdOrderByCreationDateDesc(
                notification.getUserId());
        Long countLastHour = notifications.stream().filter(n -> isMoreThanOneDay(n.getCreationDate())).count();
        contextRules.setCanBeSend(countLastHour < 1);
    }

}
