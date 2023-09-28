package com.notifications.app.rules;

import com.notifications.app.model.ContextRules;
import com.notifications.app.model.Notification;
import com.notifications.app.model.StatusNotification;
import com.notifications.app.respository.NewsRepository;
import com.notifications.app.respository.StatusRepository;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static com.notifications.app.service.NotificationServiceImpl.CONTEXT_RULES_KEY;

@Rule(name = "StatusNotMoreThan2PerMinutePerRecipient",
        description = "Prevent send more Status Notifications than 2 per minute per recipient")
public class StatusNotMoreThan2PerMinutePerRecipient {

    @Condition
    public boolean isNewsNotification(@Fact(CONTEXT_RULES_KEY) ContextRules contextRules) {
        return contextRules.getNotification() instanceof StatusNotification;
    }

    @Action
    public void checkRateLimit(@Fact(CONTEXT_RULES_KEY) ContextRules contextRules) {
        Notification notification = contextRules.getNotification();
        StatusRepository notificationRepository = contextRules.getStatusNotification();
        List<Notification> notifications = notificationRepository.findTop2ByUserIdOrderByCreationDateDesc(
                notification.getUserId());
        LocalDateTime now = LocalDateTime.now();
        Long countLastHour = notifications.stream().filter(n -> {
            Duration duration = Duration.between(n.getCreationDate(), now);
            return duration.toMinutes() < 2;
        }).count();
        contextRules.setCanBeSend(countLastHour < 2);
    }

}
