package com.ee.ordermanager.stream;

import com.ee.ordermanager.configuration.KafkaTopics;
import com.ee.ordermanager.model.Event;
import com.ee.ordermanager.model.KafkaKey;
import com.ee.ordermanager.stream.processor.OrderMessageProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Stream that takes messages from orders,
 * map them to the MessageProcessor and then send them to the events topic
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class OrderEventStream {

    private final KafkaTopics kafkaTopics;
    private final OrderMessageProcessor orderMessageProcessor;
    private final Consumed<KafkaKey, SpecificRecord> consumedSerdeConfig;
    private final Produced<KafkaKey, Event> producedSerdeConfig;

    @Autowired
    public void kStream(StreamsBuilder streamsBuilder) {
        streamsBuilder
                .stream(kafkaTopics.getOrders(), consumedSerdeConfig)
                .map(orderMessageProcessor::process)
                .to(kafkaTopics.getEvents(), producedSerdeConfig);
    }

}