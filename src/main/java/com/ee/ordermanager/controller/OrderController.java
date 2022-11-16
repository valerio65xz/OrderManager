package com.ee.ordermanager.controller;

import com.ee.ordermanager.model.dto.CreateAcmeOrder;
import com.ee.ordermanager.model.dto.CreateEEOrder;
import com.ee.ordermanager.model.dto.ShipAcmeOrder;
import com.ee.ordermanager.model.dto.ShipEEOrder;
import com.ee.ordermanager.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create/ee")
    public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateEEOrder createEEOrder){
        orderService.createEEOrder(createEEOrder);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/create/acme")
    public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateAcmeOrder createAcmeOrder){
        orderService.createAcmeOrder(createAcmeOrder);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/ship/ee")
    public ResponseEntity<Void> shipOrder(@RequestBody @Valid ShipEEOrder shipEEOrder){
        orderService.shipEEOrder(shipEEOrder);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/ship/acme")
    public ResponseEntity<Void> shipOrder(@RequestBody @Valid ShipAcmeOrder shipAcmeOrder){
        orderService.shipAcmeOrder(shipAcmeOrder);
        return ResponseEntity.accepted().build();
    }

}