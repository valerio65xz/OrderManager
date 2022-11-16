package com.ee.ordermanager.service;

import com.ee.ordermanager.random.Randomize;
import com.ee.ordermanager.configuration.KafkaTopics;
import com.ee.ordermanager.mapper.OrderMapper;
import com.ee.ordermanager.model.dto.CreateAcmeOrder;
import com.ee.ordermanager.model.dto.CreateEEOrder;
import com.ee.ordermanager.model.dto.ShipAcmeOrder;
import com.ee.ordermanager.model.dto.ShipEEOrder;
import com.ee.ordermanager.model.payload.CreateAcmeOrderPayload;
import com.ee.ordermanager.model.payload.CreateEEOrderPayload;
import com.ee.ordermanager.model.payload.ShipAcmeOrderPayload;
import com.ee.ordermanager.model.payload.ShipEEOrderPayload;
import com.ee.ordermanager.producer.OrderKafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderKafkaProducer orderKafkaProducer;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private KafkaTopics kafkaTopics;
    @InjectMocks
    private OrderService orderService;

    @Test
    void createEEOrder() {
        CreateEEOrder createEEOrder = Randomize.random(CreateEEOrder.class);

        when(orderMapper.modelToPayload(any(CreateEEOrder.class))).thenReturn(new CreateEEOrderPayload());
        when(kafkaTopics.getOrders()).thenReturn("kafkaTopics");

        orderService.createEEOrder(createEEOrder);

        verify(orderMapper).modelToPayload(createEEOrder);
        verify(kafkaTopics).getOrders();
    }

    @Test
    void createAcmeOrder() {
        CreateAcmeOrder createAcmeOrder = Randomize.random(CreateAcmeOrder.class);

        when(orderMapper.modelToPayload(any(CreateAcmeOrder.class))).thenReturn(new CreateAcmeOrderPayload());
        when(kafkaTopics.getOrders()).thenReturn("kafkaTopics");

        orderService.createAcmeOrder(createAcmeOrder);

        verify(orderMapper).modelToPayload(createAcmeOrder);
        verify(kafkaTopics).getOrders();
    }

    @Test
    void shipEEOrder() {
        ShipEEOrder shipEEOrder = Randomize.random(ShipEEOrder.class);

        when(orderMapper.modelToPayload(any(ShipEEOrder.class))).thenReturn(new ShipEEOrderPayload());
        when(kafkaTopics.getOrders()).thenReturn("kafkaTopics");

        orderService.shipEEOrder(shipEEOrder);

        verify(orderMapper).modelToPayload(shipEEOrder);
        verify(kafkaTopics).getOrders();
    }

    @Test
    void shipAcmeOrder() {
        ShipAcmeOrder shipAcmeOrder = Randomize.random(ShipAcmeOrder.class);

        when(orderMapper.modelToPayload(any(ShipAcmeOrder.class))).thenReturn(new ShipAcmeOrderPayload());
        when(kafkaTopics.getOrders()).thenReturn("kafkaTopics");

        orderService.shipAcmeOrder(shipAcmeOrder);

        verify(orderMapper).modelToPayload(shipAcmeOrder);
        verify(kafkaTopics).getOrders();
    }

}
