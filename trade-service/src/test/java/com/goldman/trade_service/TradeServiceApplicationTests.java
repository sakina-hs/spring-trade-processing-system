package com.goldman.trade_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.PostgreSQLContainer;
import com.goldman.trade_service.model.Trade;
import com.goldman.trade_service.model.TradeRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.TimeUnit;
import static org.awaitility.Awaitility.await;
import org.springframework.kafka.core.KafkaTemplate;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.goldman.trade_service.model.TradeEvent;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TradeServiceApplicationTests {

	@ServiceConnection
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

	@LocalServerPort
	private int port;

	@MockBean
	private KafkaTemplate<String, TradeEvent> kafkaTemplate;

	@BeforeEach
	public void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		postgres.start();
	}

	@Test
	void createTradeTest() {
		Trade trade = Trade.builder()
				.symbol("AAPL")
				.quantity(100)
				.price(150.50)
				.build();

		// Create the trade and extract the ID from the response
		Trade savedTrade = given()
				.contentType(ContentType.JSON)
				.body(trade)
				.when()
				.post("/trades/create")
				.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.extract()
				.as(Trade.class);

		// Verify the trade was saved using the returned ID
		given()
				.contentType(ContentType.JSON)
				.when()
				.get("/trades/" + savedTrade.getTradeId())
				.then()
				.statusCode(200)
				.contentType(ContentType.JSON)
				.body("symbol", equalTo("AAPL"))
				.body("quantity", equalTo(100))
				.body("price", equalTo(150.50f));
	}

}
