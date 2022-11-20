package com.ee.ordermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRow {

    @NotNull
    private Integer orderRowId;
    @NotNull
    private Integer itemId;
    @NotNull
    private Integer quantity;
    @NotNull
    private Double unitaryPrice;

}