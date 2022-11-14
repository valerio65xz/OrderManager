package com.ee.ordermanager.controller;

import com.ee.ordermanager.model.CreateAcmeOrder;
import com.ee.ordermanager.service.processor.OrderService;
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

    @PostMapping("/create")
    public ResponseEntity<Void> createOrder(@RequestBody @Valid CreateAcmeOrder createAcmeOrder){
        orderService.createAcmeOrder(createAcmeOrder);
        return ResponseEntity.accepted().build();
    }

}