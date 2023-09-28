package com.notifications.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="notification_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String userId;
    protected String message;
    protected LocalDate creationDate;
    protected Boolean canBeSend;


    public Notification(String userId, String message) {
        this.userId = userId;
        this.message = message;
        this.creationDate = LocalDate.now();
        this.canBeSend = Boolean.TRUE;
    }
}
