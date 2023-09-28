package com.notifications.app.rules;

import com.notifications.app.model.ContextRules;
import com.notifications.app.model.MarketingNotification;
import com.notifications.app.model.Notification;
import com.notifications.app.respository.MarketingRepository;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static com.notifications.app.service.NotificationServiceImpl.CONTEXT_RULES_KEY;

@Rule(name = "MarketingNotMore3PerHourPerRecipient", description = "Prevent send more Notifications than 3 per hour per recipient")
public class MarketingNotMoreThan3PerHourPerRecipient {

    @Condition
    public boolean isMarketingNotification(@Fact(CONTEXT_RULES_KEY) ContextRules contextRules) {
        return contextRules.getNotification() instanceof MarketingNotification;
    }

    @Action
    public void checkRateLimit(@Fact(CONTEXT_RULES_KEY) ContextRules contextRules) {
        Notification notification = contextRules.getNotification();
        MarketingRepository notificationRepository = contextRules.getMarketingRepository();
        List<Notification> notifications = notificationRepository.findTop3ByUserIdOrderByCreationDateDesc(
                notification.getUserId());
        LocalDateTime now = LocalDateTime.now();
        Long countLastHour = notifications.stream().filter(n -> {
            Duration duration = Duration.between(n.getCreationDate(), now);
            return duration.toHours() < 1;
        }).count();
        contextRules.setCanBeSend(countLastHour < 3);
    }
}
