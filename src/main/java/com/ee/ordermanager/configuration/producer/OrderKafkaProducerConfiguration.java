package com.ee.ordermanager.configuration.producer;

import com.ee.ordermanager.model.KafkaKey;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@ConfigurationProperties(prefix = "spring.kafka.producer")
public class OrderKafkaProducerConfiguration extends KafkaProducerConfiguration<KafkaKey, SpecificRecord> {
    
    public OrderKafkaProducerConfiguration(KafkaProperties kafkaProperties) {
        super(kafkaProperties);
    }

    @Bean
    public ProducerFactory<KafkaKey, SpecificRecord> orderKafkaProducerFactory() {
        return super.kafkaProducerFactory();
    }

    @Bean
    public KafkaTemplate<KafkaKey, SpecificRecord> orderKafkaTemplate(
            ProducerFactory<KafkaKey, SpecificRecord> orderKafkaProducerFactory) {
        return super.kafkaTemplate(orderKafkaProducerFactory);
    }

}