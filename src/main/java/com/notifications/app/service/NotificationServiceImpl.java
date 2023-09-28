package com.notifications.app.service;

import com.notifications.app.model.MarketingNotification;
import com.notifications.app.model.NewsNotification;
import com.notifications.app.model.Notification;
import com.notifications.app.respository.NotificationRepository;
import com.notifications.app.rules.MarketingNotMore3PerHourPerRecipient;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private Gateway gateway;
    private NotificationRepository notificationRepository;

    public NotificationServiceImpl(Gateway gateway, NotificationRepository notificationRepository) {
        this.gateway = gateway;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void send(String type, String userId, String message) throws Exception {

        MarketingNotification notification1 = new MarketingNotification();
        notification1.setMessage("asd");
        notification1.setUserId("1");

        RulesEngine rulesEngine = new DefaultRulesEngine();
        Facts fact = new Facts();
        fact.put("notification", notification1);
        fact.put("notificationRepository", notificationRepository);
        fact.put("canBeSend", Boolean.TRUE);

        Rules rules = new Rules();
        rules.register(new MarketingNotMore3PerHourPerRecipient());

        rulesEngine.fire(rules, fact);
        if (Boolean.FALSE.equals(notification1.getCanBeSend())) { throw new Exception(); }

        notificationRepository.save(notification1);

    }

    @Override
    public void deleteAll() {
        notificationRepository.deleteAll();
    }
}
