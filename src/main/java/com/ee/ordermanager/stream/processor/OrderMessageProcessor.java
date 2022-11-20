package com.ee.ordermanager.stream.processor;

import com.ee.ordermanager.model.Event;
import com.ee.ordermanager.model.KafkaKey;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.streams.KeyValue;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderMessageProcessor {

    private final ObjectMapper jsonMapper;

    /**
     * Transforms the input key and payload from SpecificRecord
     * to Event
     *
     * @param kafkaKey The kafka message key
     * @param payload  The kafka message payload
     * @return key value of the new message
     */
    public KeyValue<KafkaKey, Event> process(KafkaKey kafkaKey, SpecificRecord payload) {
        Event eventPayload = Event.builder()
                .data(payloadToMap(payload))
                .build();

        log.info("Processed order with key {} and value {}", kafkaKey, eventPayload);
        return new KeyValue<>(kafkaKey, eventPayload);
    }

    /**
     * This function converts the kafka message payload to a Map
     *
     * @param payload The kafka message payload
     * @return a map representing the payload
     */
    private Map<String, Object> payloadToMap(SpecificRecord payload) {
        try {
            return jsonMapper.readValue(payload.toString(), new TypeReference<>() {});
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new JsonParseException(exception);
        }
    }

}