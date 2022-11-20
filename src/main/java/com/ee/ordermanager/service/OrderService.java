package com.ee.ordermanager.service;

import com.ee.ordermanager.configuration.KafkaTopics;
import com.ee.ordermanager.mapper.OrderMapper;
import com.ee.ordermanager.model.*;
import com.ee.ordermanager.model.dto.CreateAcmeOrder;
import com.ee.ordermanager.model.dto.CreateEEOrder;
import com.ee.ordermanager.model.dto.ShipAcmeOrder;
import com.ee.ordermanager.model.dto.ShipEEOrder;
import com.ee.ordermanager.model.payload.CreateAcmeOrderPayload;
import com.ee.ordermanager.model.payload.CreateEEOrderPayload;
import com.ee.ordermanager.model.payload.ShipAcmeOrderPayload;
import com.ee.ordermanager.model.payload.ShipEEOrderPayload;
import com.ee.ordermanager.producer.OrderKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The base service that produces the orders from the controller. For all the 4 possible operations,
 * it creates a kafka key with the orderId, the company name and the event type (created or shipped)
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderKafkaProducer orderKafkaProducer;
    private final OrderMapper orderMapper;
    private final KafkaTopics kafkaTopics;

    public void createEEOrder(CreateEEOrder createEEOrder){
        CreateEEOrderPayload payload = orderMapper.modelToPayload(createEEOrder);

        KafkaKey key = buildKafkaKey(createEEOrder.getOrderId(), Company.EE, EventName.CREATED);

        orderKafkaProducer.sendMessage(key, payload, kafkaTopics.getOrders());
    }

    public void createAcmeOrder(CreateAcmeOrder createAcmeOrder){
        CreateAcmeOrderPayload payload = orderMapper.modelToPayload(createAcmeOrder);

        KafkaKey key = buildKafkaKey(createAcmeOrder.getOrderId(), Company.ACME, EventName.CREATED);

        orderKafkaProducer.sendMessage(key, payload, kafkaTopics.getOrders());
    }

    public void shipEEOrder(ShipEEOrder shipEEOrder){
        ShipEEOrderPayload payload = orderMapper.modelToPayload(shipEEOrder);

        KafkaKey key = buildKafkaKey(shipEEOrder.getOrderId(), Company.EE, EventName.SHIPPED);

        orderKafkaProducer.sendMessage(key, payload, kafkaTopics.getOrders());
    }

    public void shipAcmeOrder(ShipAcmeOrder shipAcmeOrder){
        ShipAcmeOrderPayload payload = orderMapper.modelToPayload(shipAcmeOrder);

        KafkaKey key = buildKafkaKey(shipAcmeOrder.getOrderId(), Company.ACME, EventName.SHIPPED);

        orderKafkaProducer.sendMessage(key, payload, kafkaTopics.getOrders());
    }

    private KafkaKey buildKafkaKey(Integer orderId, Company company, EventName eventName){
        return KafkaKey.builder()
                .orderId(orderId)
                .company(company)
                .eventName(eventName)
                .build();
    }

}