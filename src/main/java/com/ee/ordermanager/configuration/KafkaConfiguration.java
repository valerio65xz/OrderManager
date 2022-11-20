package com.ee.ordermanager.configuration;

import com.ee.ordermanager.model.Event;
import com.ee.ordermanager.model.KafkaKey;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

/**
 * Describes the Kafka Configuration with the Stream Serde properties
 */
@Configuration
@EnableKafka
@EnableKafkaStreams
@RequiredArgsConstructor
public class KafkaConfiguration {

    private final KafkaProperties kafkaProperties;

    /**
     * Configure SerDes for consumed messages
     */
    @Bean
    public Consumed<KafkaKey, SpecificRecord> consumedSerdeConfig() {
        JsonSerde<KafkaKey> keySerde = new JsonSerde<>(KafkaKey.class);
        SpecificAvroSerde<SpecificRecord> payloadSerde = new SpecificAvroSerde<>();
        payloadSerde.configure(kafkaProperties.getProperties(), false);

        return Consumed.with(keySerde, payloadSerde);
    }

    /**
     * Configure SerDes for produced messages
     */
    @Bean
    public Produced<KafkaKey, Event> producedSerdeConfig() {
        return Produced.with(
                new JsonSerde<>(KafkaKey.class),
                new JsonSerde<>(Event.class)
        );
    }

}
