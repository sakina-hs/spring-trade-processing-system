package com.goldman.NotificationService;

import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import com.goldman.NotificationService.model.TradeEvent;

@Component
public class NotificationConsumer {
    @KafkaListener(topics = "trade-settled-topic", groupId = "notification-group")
    public void sendNotification(TradeEvent event) {
        System.out.println("Notification sent for trade: " + event.getTradeId());
    }
}
