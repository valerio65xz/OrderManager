package com.ee.ordermanager.model.dto;

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
public class ShipEEOrder {

    @NotNull
    private Integer orderId;
    @NotNull
    private LocalDateTime shippingDate;
    @NotNull
    private Integer deliveryCompanyId;
    @NotNull
    private String deliveryCompanyName;
    @NotNull
    private Double shippingCost;
    @NotNull
    private Integer warehousePosition;


}
