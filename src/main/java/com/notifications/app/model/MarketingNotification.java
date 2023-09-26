package com.notifications.app.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@DiscriminatorValue(NotificationType.VALUES.MARKETING)
@AllArgsConstructor
@Data
public class MarketingNotification extends Notification {
}
