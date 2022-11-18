package com.ee.ordermanager.controller;

import com.ee.ordermanager.model.dto.CreateAcmeOrder;
import com.ee.ordermanager.model.dto.CreateEEOrder;
import com.ee.ordermanager.model.dto.ShipAcmeOrder;
import com.ee.ordermanager.model.dto.ShipEEOrder;
import com.ee.ordermanager.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi{

    private final OrderService orderService;

    public ResponseEntity<Void> createEEOrder(CreateEEOrder order){
        orderService.createEEOrder(order);
        return ResponseEntity.accepted().build();
    }

    public ResponseEntity<Void> createAcmeOrder(CreateAcmeOrder order){
        orderService.createAcmeOrder(order);
        return ResponseEntity.accepted().build();
    }

    public ResponseEntity<Void> shipEEOrder(ShipEEOrder order){
        orderService.shipEEOrder(order);
        return ResponseEntity.accepted().build();
    }

    public ResponseEntity<Void> shipAcmeOrder(ShipAcmeOrder order){
        orderService.shipAcmeOrder(order);
        return ResponseEntity.accepted().build();
    }

}