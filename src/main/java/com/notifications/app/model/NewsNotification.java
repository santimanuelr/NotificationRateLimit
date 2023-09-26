package com.notifications.app.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue(NotificationType.VALUES.NEWS)
@AllArgsConstructor
@Data
public class NewsNotification extends Notification {
}
