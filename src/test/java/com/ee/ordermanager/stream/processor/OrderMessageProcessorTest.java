package com.ee.ordermanager.stream.processor;

import com.ee.ordermanager.model.Company;
import com.ee.ordermanager.model.Event;
import com.ee.ordermanager.model.EventName;
import com.ee.ordermanager.model.KafkaKey;
import com.ee.ordermanager.model.payload.CreateEEOrderPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.streams.KeyValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderMessageProcessorTest {

    private static final String EXPECTED_PAYLOAD_KEY = "eventPayload";
    private static final Integer ORDER_ID = 1;
    private static final EventName EVENT_NAME = EventName.CREATED;
    private static final Company COMPANY = Company.EE;

    private OrderMessageProcessor orderMessageProcessor;


    @BeforeEach
    public void setUp() {
        ObjectMapper jsonMapper = Jackson2ObjectMapperBuilder.json().build();
        this.orderMessageProcessor = new OrderMessageProcessor(jsonMapper);
    }

    @Test
    public void shouldProcessMessage() {
        CreateEEOrderPayload consumedPayload = new CreateEEOrderPayload();

        KafkaKey consumedKafkaKey = KafkaKey.builder()
                .eventName(EVENT_NAME)
                .orderId(ORDER_ID)
                .company(COMPANY)
                .build();

        KafkaKey expectedKafkaKey = KafkaKey.builder()
                .eventName(EVENT_NAME)
                .orderId(ORDER_ID)
                .company(COMPANY)
                .build();

        Event expectedKafkaPayload = Event.builder()
                .data(Map.of(EXPECTED_PAYLOAD_KEY, consumedPayload))
                .build();

        KeyValue<KafkaKey, Event> actual = orderMessageProcessor.process(consumedKafkaKey, consumedPayload);

        assertEquals(expectedKafkaKey, actual.key);

        CreateEEOrderPayload expectedPayload = (CreateEEOrderPayload) expectedKafkaPayload.data().get(EXPECTED_PAYLOAD_KEY);
        CreateEEOrderPayload actualPayload = new ObjectMapper().convertValue(actual.value.data(), CreateEEOrderPayload.class);
        assertEquals(expectedPayload, actualPayload);
    }

}
