package com.ee.ordermanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipAcmeOrder {

    @NotNull
    private Integer orderId;
    @NotNull
    private LocalDateTime shippingDate;
    @NotNull
    private String deliveryCompanyName;
    @NotNull
    private Double shippingCost;


}
