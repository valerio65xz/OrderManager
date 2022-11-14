package com.ee.ordermanager.stream;

import com.ee.ordermanager.configuration.KafkaTopics;
import com.ee.ordermanager.model.Event;
import com.ee.ordermanager.model.KafkaKey;
import com.ee.ordermanager.stream.processor.MessageProcessor;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

/**
 * Stream that takes messages from orders,
 * map them to the MessageProcessor and then send them
 * to the events topic
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class OrderEventStream {

    private final KafkaTopics kafkaTopics;
    private final MessageProcessor messageProcessor;
    private final KafkaProperties kafkaProperties;

    @Autowired
    public void kStream(StreamsBuilder streamsBuilder) {
        streamsBuilder
                .stream(kafkaTopics.getOrders(), buildConsumeSerdeConfig())
                .map(messageProcessor::process)
                .to(kafkaTopics.getEvents(), buildProduceSerdeConfig());
    }

    /**
     * Configure SerDes for consumed messages
     */
    private Consumed<KafkaKey, SpecificRecord> buildConsumeSerdeConfig() {
        JsonSerde<KafkaKey> keySerde = new JsonSerde<>(KafkaKey.class);
        SpecificAvroSerde<SpecificRecord> payloadSerde = new SpecificAvroSerde<>();
        payloadSerde.configure(kafkaProperties.getProperties(), false);

        return Consumed.with(keySerde, payloadSerde);
    }

    /**
     * Configure SerDes for produced messages
     */
    private Produced<KafkaKey, Event> buildProduceSerdeConfig() {
        return Produced.with(
                new JsonSerde<>(KafkaKey.class),
                new JsonSerde<>(Event.class)
        );
    }

}
