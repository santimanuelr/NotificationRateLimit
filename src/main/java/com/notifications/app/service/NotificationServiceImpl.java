package com.notifications.app.service;

import com.notifications.app.model.*;
import com.notifications.app.respository.MarketingRepository;
import com.notifications.app.respository.NewsRepository;
import com.notifications.app.respository.NotificationRepository;
import com.notifications.app.respository.StatusRepository;
import com.notifications.app.rules.MarketingNotMoreThan3PerHourPerRecipient;
import com.notifications.app.rules.NewsNotMoreThan1PerDayPerRecipient;
import com.notifications.app.rules.StatusNotMoreThan2PerMinutePerRecipient;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    public static final String CONTEXT_RULES_KEY = "context";
    private Gateway gateway;
    private NotificationRepository notificationRepository;
    private MarketingRepository marketingRepository;
    private NewsRepository newsRepository;
    private StatusRepository statusRepository;

    public NotificationServiceImpl(Gateway gateway, NotificationRepository notificationRepository,
                                   MarketingRepository marketingRepository, NewsRepository newsRepository,
                                   StatusRepository statusRepository) {
        this.gateway = gateway;
        this.notificationRepository = notificationRepository;
        this.marketingRepository = marketingRepository;
        this.newsRepository = newsRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public void send(String type, String userId, String message) throws Exception {

        Notification notificationToSend = buildNotification(type, userId, message);

        RulesEngine rulesEngine = new DefaultRulesEngine();
        Facts fact = new Facts();
        ContextRules contextRules = getContextRules(notificationToSend);
        fact.put(CONTEXT_RULES_KEY, contextRules);

        Rules rules = new Rules();
        rules.register(new MarketingNotMoreThan3PerHourPerRecipient());
        rules.register(new NewsNotMoreThan1PerDayPerRecipient());
        rules.register(new StatusNotMoreThan2PerMinutePerRecipient());

        rulesEngine.fire(rules, fact);
        if (Boolean.FALSE.equals(contextRules.getCanBeSend())) { throw new Exception(); }
        gateway.send(notificationToSend);
        notificationRepository.save(notificationToSend);

    }

    private ContextRules getContextRules(Notification notificationToSend) {
        ContextRules contextRules = new ContextRules();
        contextRules.setNotification(notificationToSend);
        contextRules.setNotificationRepository(notificationRepository);
        contextRules.setMarketingRepository(marketingRepository);
        contextRules.setNewsRepository(newsRepository);
        contextRules.setStatusNotification(statusRepository);
        return contextRules;
    }

    private Notification buildNotification(String type, String userId, String message) {
        switch (NotificationType.valueOf(type)) {
            case MARKETING -> {
                return MarketingNotification.builder()
                        .userId(userId)
                        .message(message)
                        .build();
            }
            case NEWS -> {
                return NewsNotification.builder()
                        .userId(userId)
                        .message(message)
                        .build();
            }
            case STATUS -> {
                return StatusNotification.builder()
                        .userId(userId)
                        .message(message)
                        .build();
            }
            default -> {
                return new Notification();
            }
        }
    }

    @Override
    public void deleteAll() {
        notificationRepository.deleteAll();
    }
}
