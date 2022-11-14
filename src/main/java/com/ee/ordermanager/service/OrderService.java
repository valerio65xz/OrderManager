package com.ee.ordermanager.service;

import com.ee.ordermanager.configuration.KafkaTopics;
import com.ee.ordermanager.mapper.OrderMapper;
import com.ee.ordermanager.model.*;
import com.ee.ordermanager.model.payload.CreateAcmeOrderPayload;
import com.ee.ordermanager.model.payload.CreateEEOrderPayload;
import com.ee.ordermanager.model.payload.ShipAcmeOrderPayload;
import com.ee.ordermanager.model.payload.ShipEEOrderPayload;
import com.ee.ordermanager.producer.OrderKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderKafkaProducer orderKafkaProducer;
    private final OrderMapper orderMapper;
    private final KafkaTopics kafkaTopics;

    public void createEEOrder(CreateEEOrder createEEOrder){
        CreateEEOrderPayload payload = orderMapper.modelToPayload(createEEOrder);

        KafkaKey key = KafkaKey.builder()
                .orderId(createEEOrder.getOrderId())
                .company(Company.EE)
                .eventName(EventName.CREATED)
                .build();

        orderKafkaProducer.sendMessage(key, payload, kafkaTopics.getOrders());
    }

    public void createAcmeOrder(CreateAcmeOrder createAcmeOrder){
        CreateAcmeOrderPayload payload = orderMapper.modelToPayload(createAcmeOrder);

        KafkaKey key = KafkaKey.builder()
                .orderId(createAcmeOrder.getOrderId())
                .company(Company.ACME)
                .eventName(EventName.CREATED)
                .build();

        orderKafkaProducer.sendMessage(key, payload, kafkaTopics.getOrders());
    }

    public void shipEEOrder(ShipEEOrder shipEEOrder){
        ShipEEOrderPayload payload = orderMapper.modelToPayload(shipEEOrder);

        KafkaKey key = KafkaKey.builder()
                .orderId(shipEEOrder.getOrderId())
                .company(Company.EE)
                .eventName(EventName.SHIPPED)
                .build();

        orderKafkaProducer.sendMessage(key, payload, kafkaTopics.getOrders());
    }

    public void shipAcmeOrder(ShipAcmeOrder shipAcmeOrder){
        ShipAcmeOrderPayload payload = orderMapper.modelToPayload(shipAcmeOrder);

        KafkaKey key = KafkaKey.builder()
                .orderId(shipAcmeOrder.getOrderId())
                .company(Company.ACME)
                .eventName(EventName.SHIPPED)
                .build();

        orderKafkaProducer.sendMessage(key, payload, kafkaTopics.getOrders());
    }

}
