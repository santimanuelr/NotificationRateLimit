package com.notifications.app.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@DiscriminatorValue(NotificationType.VALUES.NEWS)
@AllArgsConstructor
@Data
public class NewsNotification extends Notification {

    @Builder
    public NewsNotification(String userId, String message) {
        super(userId, message);
    }

}
