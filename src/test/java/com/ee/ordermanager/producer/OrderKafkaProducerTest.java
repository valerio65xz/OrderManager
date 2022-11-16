package com.ee.ordermanager.producer;

import com.ee.ordermanager.random.Randomize;
import com.ee.ordermanager.model.KafkaKey;
import org.apache.avro.specific.SpecificRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.util.concurrent.ListenableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderKafkaProducerTest {

    @Mock
    private KafkaTemplate<KafkaKey, SpecificRecord> kafkaTemplate;
    @Mock
    private SpecificRecord payload;
    @InjectMocks
    private OrderKafkaProducer orderKafkaProducer;

    @Mock
    private ListenableFuture<SendResult<KafkaKey, SpecificRecord>> result;

    @Test
    public void kafkaMessageShouldBeSent() {
        KafkaKey kafkaKey = Randomize.random(KafkaKey.class);

        when(kafkaTemplate.send(any(Message.class))).thenReturn(result);

        orderKafkaProducer.sendMessage(kafkaKey, payload, "topic");

        verify(kafkaTemplate).send(any(Message.class));
    }

}