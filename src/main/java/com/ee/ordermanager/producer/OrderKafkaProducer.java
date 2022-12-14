package com.ee.ordermanager.producer;

import com.ee.ordermanager.model.KafkaKey;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Implementation of the Kafka Producer. This producer will send all the creation or shipping of the orders
 * made by EverythingEverywhere and Acme.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderKafkaProducer {

    protected final KafkaTemplate<KafkaKey, SpecificRecord> kafkaTemplate;

    /**
     * Send the payload mapped in an Avro model with the specified key
     */
    public void sendMessage(KafkaKey kafkaKey, SpecificRecord payload, String topic) {
        Message<SpecificRecord> message = MessageBuilder.withPayload(payload)
                .setHeader(KafkaHeaders.MESSAGE_KEY, kafkaKey)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        kafkaTemplate.send(message).addCallback(
                this::successHandler,
                this::failureHandler
        );
    }

    protected void successHandler(@NonNull SendResult<KafkaKey, SpecificRecord> result) {
        log.info(
                "Message with key '{}' has been successfully sent to the partition '{}' of the topic '{}'",
                result.getProducerRecord().key(),
                result.getRecordMetadata().partition(),
                result.getProducerRecord().topic()
        );
    }

    protected void failureHandler(Throwable throwable) {
        log.error(throwable.getMessage(), throwable);
    }

}