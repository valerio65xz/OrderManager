package com.ee.ordermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEEOrder {

    @NotNull
    private Integer orderId;
    @NotNull
    private Integer customerId;
    @NotNull
    private String customerName;
    @NotNull
    private LocalDateTime creationDateTime;
    @NotEmpty
    private List<@Valid OrderRow> orderRows;

}
