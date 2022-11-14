package com.ee.ordermanager.configuration.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Since we need multiple kafka producer, with this class we can create any number of producers
 * providing the correct configuration in the application.yml
 */
@RequiredArgsConstructor
public abstract class KafkaProducerConfiguration<K, V> extends KafkaProperties.Producer {

    protected final KafkaProperties kafkaProperties;

    /**
     * Here we are building the kafka producer factory for the single consumer.
     * <p>
     * The kafkaProperties.buildProducerProperties() will return a map with the default producer configurations
     * The this.buildProperties() will return a map with the configuration of the specific producer from application.yml
     * <p>
     * We are merging these 2 maps to override the default properties
     * with the ones taken from the application.yml
     *
     * @return The kafka producer factory
     */
    protected ProducerFactory<K, V> kafkaProducerFactory() {
        return new DefaultKafkaProducerFactory<>(
                Stream.concat(
                        kafkaProperties.buildProducerProperties().entrySet().stream(),
                        this.buildProperties().entrySet().stream()
                ).collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (defaultProperties, customProperties) -> customProperties
                ))
        );
    }

    protected KafkaTemplate<K, V> kafkaTemplate(ProducerFactory<K, V> kafkaProducerFactory) {
        return new KafkaTemplate<>(kafkaProducerFactory);
    }

}