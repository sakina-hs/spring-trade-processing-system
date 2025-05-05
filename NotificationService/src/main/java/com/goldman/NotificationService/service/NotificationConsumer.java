package com.goldman.NotificationService.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import com.goldman.trade_service.avro.TradeAvro;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class NotificationConsumer {

    private final JavaMailSender javaMailSender;
    private static final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);

    // Kafka listener to consume validated trades
    @KafkaListener(topics = "trade-validated-topic", groupId = "notification-service-group")
    public void listen(List<TradeAvro> trades) {
        for (TradeAvro trade : trades) {
            if (trade != null) {
                logger.info("Processing validated trade for notification: {}", trade.getTradeId());
                sendEmail(trade);
            } else {
                logger.warn("Received null trade, skipping...");
            }
        }
    }

    // Method to send email for a specific trade
    public void sendEmail(TradeAvro trade) {
        String subject = "Your Trade Transaction is Successful";
        String emailBody = String.format("""
                Dear User,

                Your trade with ID %d for symbol %s has been successfully processed.

                - Trade Type: %s
                - Quantity: %d
                - Price: %.2f
                - Symbol: %s

                Thank you for using our trading system!

                Regards,
                Trading System Team
                """,
                trade.getTradeId(), // %d
                trade.getTradeType(), // %s
                trade.getTradeType(), // %s
                trade.getQuantity(), // %d
                trade.getPrice(), // %.2f
                trade.getFundname() // %s again
        );

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("tradesystem@email.com");
            messageHelper.setTo("user@email.com"); // You can dynamically set this based on your business logic
            messageHelper.setSubject(subject);
            messageHelper.setText(emailBody);
        };

        try {
            javaMailSender.send(messagePreparator);
            log.info("Notification email sent for trade ID: {}", trade.getTradeId());
        } catch (MailException e) {
            log.error("Exception occurred when sending email for trade ID: {}", trade.getTradeId(), e);
            throw new RuntimeException("Exception occurred when sending email", e);
        }
    }
}
