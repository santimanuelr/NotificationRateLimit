package com.notifications.app.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@DiscriminatorValue(NotificationType.VALUES.STATUS)
@AllArgsConstructor
@Data
public class StatusNotification extends Notification {

    @Builder
    public StatusNotification(String userId, String message) {
        super(userId, message);
    }

}
