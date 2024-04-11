package com.example.tuned.Controller;

import com.example.tuned.Domain.Notification.NotificationMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@AllArgsConstructor
@RequestMapping("notifications")
public class NotificationController {
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<Void> sendNotificationToUsers(@RequestBody NotificationMessage message) {
        messagingTemplate.convertAndSend("/topic/publicmessages", message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
