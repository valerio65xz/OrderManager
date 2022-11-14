package com.ee.ordermanager.service.processor;

import com.ee.ordermanager.configuration.KafkaTopics;
import com.ee.ordermanager.mapper.OrderMapper;
import com.ee.ordermanager.model.*;
import com.ee.ordermanager.model.payload.CreateAcmeOrderPayload;
import com.ee.ordermanager.producer.OrderKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderKafkaProducer orderKafkaProducer;
    private final OrderMapper orderMapper;
    private final KafkaTopics kafkaTopics;

    public void createAcmeOrder(CreateAcmeOrder createAcmeOrder){
        CreateAcmeOrderPayload payload = orderMapper.modelToPayload(createAcmeOrder);

        KafkaKey key = KafkaKey.builder()
                .orderId(createAcmeOrder.getOrderId())
                .company(Company.ACME)
                .eventName(EventName.CREATED)
                .build();

        orderKafkaProducer.sendMessage(key, payload, kafkaTopics.getOrders());
    }

}
