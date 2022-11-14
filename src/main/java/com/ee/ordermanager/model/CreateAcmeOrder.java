package com.ee.ordermanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAcmeOrder {

    private Integer orderId;
    private LocalDateTime creationDateTime;
    private Integer numberOfItems;
    private Double totalPrice;

}
