package com.ee.ordermanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRow {

    private Integer orderRowId;
    private Integer itemId;
    private Integer quantity;
    private Double unitaryPrice;

}
