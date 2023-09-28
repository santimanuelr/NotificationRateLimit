package com.notifications.app.service;

import com.notifications.app.model.*;
import com.notifications.app.respository.NotificationRepository;
import com.notifications.app.rules.MarketingNotMoreThan3PerHourPerRecipient;
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

    public NotificationServiceImpl(Gateway gateway, NotificationRepository notificationRepository) {
        this.gateway = gateway;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void send(String type, String userId, String message) throws Exception {

        Notification notificationToSend = buildNotification(type, userId, message);

        RulesEngine rulesEngine = new DefaultRulesEngine();
        Facts fact = new Facts();
        ContextRules contextRules = new ContextRules();
        contextRules.setNotification(notificationToSend);
        contextRules.setNotificationRepository(notificationRepository);
        fact.put(CONTEXT_RULES_KEY, contextRules);

        Rules rules = new Rules();
        rules.register(new MarketingNotMoreThan3PerHourPerRecipient());

        rulesEngine.fire(rules, fact);
        if (Boolean.FALSE.equals(contextRules.getCanBeSend())) { throw new Exception(); }

        notificationRepository.save(notificationToSend);

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
