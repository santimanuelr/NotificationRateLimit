package com.notifications.app.rules;

import com.notifications.app.model.ContextRules;
import com.notifications.app.model.NewsNotification;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import static com.notifications.app.service.NotificationServiceImpl.CONTEXT_RULES_KEY;

@Rule(name = "NewsNotMoreThan1PerDayPerRecipient",
        description = "Prevent send more News Notifications than 1 per day per recipient")
public class NewsNotMoreThan1PerDayPerRecipient {

    @Condition
    public boolean isNewsNotification(@Fact(CONTEXT_RULES_KEY) ContextRules contextRules) {
        return contextRules.getNotification() instanceof NewsNotification;
    }

    @Action
    public void checkRateLimit(@Fact(CONTEXT_RULES_KEY) ContextRules contextRules) throws Exception {
        //todo
    }
}
