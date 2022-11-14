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
public class CreateAcmeOrder {

    @NotNull
    private Integer orderId;
    @NotNull
    private LocalDateTime creationDateTime;
    @NotNull
    private Integer numberOfItems;
    @NotNull
    private Double totalPrice;

}