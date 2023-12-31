package com.notifications.app.rest;

import com.notifications.app.model.Notification;
import com.notifications.app.respository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("notification")
@RestController
@Slf4j
public class NotificationController {

    NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @PostMapping
    public Mono<Notification> create(@RequestBody Notification notification) {
        log.info("::Post Notification::");
        return Mono.just(notificationRepository.save(notification));
    }

    @GetMapping
    public Flux<Notification> getAll() {
        log.info("::GET ALL Notification::");
        return Flux.fromStream(notificationRepository.findAll().stream());
    }
}
