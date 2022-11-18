package com.ee.ordermanager.configuration;

import com.ee.ordermanager.model.KafkaKey;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Describes the Kafka Producer's configuration reading from the application.yml
 */
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.kafka.producer")
public class KafkaProducerConfiguration extends KafkaProperties.Producer {

    protected final KafkaProperties kafkaProperties;

    /**
     * It builds the kafka producer factory for the single consumer.
     * <p>
     * The kafkaProperties.buildProducerProperties() will return a map with the default producer configurations
     * The this.buildProperties() will return a map with the configuration of the specific producer from application.yml
     * <p>
     * And it merges the 2 maps to override the default properties with the ones taken from the application.yml
     *
     * @return The kafka producer factory
     */
    @Bean
    public ProducerFactory<KafkaKey, SpecificRecord> orderKafkaProducerFactory() {
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

    @Bean
    public KafkaTemplate<KafkaKey, SpecificRecord> orderKafkaTemplate(
            ProducerFactory<KafkaKey, SpecificRecord> orderKafkaProducerFactory) {
        return new KafkaTemplate<>(orderKafkaProducerFactory);
    }

}