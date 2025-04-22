package com.goldman.trade_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.goldman.trade_service.avro.TradeAvro;
import com.goldman.trade_service.model.TradeRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TradeServiceTest {

    @InjectMocks
    private TradeService tradeService;

    @Mock
    private KafkaTemplate<String, TradeAvro> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String tradeTopic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessTrade() {
        // Arrange
        TradeRequest request = new TradeRequest(1, "AAPL", 10, 150.0, "BUY");
        TradeAvro tradeAvro = TradeAvro.newBuilder()
                .setTradeId(request.getTradeId())
                .setSymbol(request.getSymbol())
                .setQuantity(request.getQuantity())
                .setPrice(request.getPrice())
                .setTradeType(request.getTradeType())
                .build();

        when(kafkaTemplate.send(eq(tradeTopic), eq(tradeAvro))).thenReturn(null);

        // Act
        tradeService.processTrade(request);

        // Assert
        verify(kafkaTemplate, times(1)).send(tradeTopic, tradeAvro);
    }
}