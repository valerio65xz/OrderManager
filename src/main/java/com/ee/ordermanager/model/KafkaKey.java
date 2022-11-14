package com.ee.ordermanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KafkaKey {

    private Integer orderId;
    private Company company;
    private EventName eventName;

}