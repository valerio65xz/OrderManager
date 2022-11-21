package com.ee.ordermanager.mapper;

import com.ee.ordermanager.model.dto.CreateAcmeOrder;
import com.ee.ordermanager.model.dto.CreateEEOrder;
import com.ee.ordermanager.model.dto.ShipAcmeOrder;
import com.ee.ordermanager.model.dto.ShipEEOrder;
import com.ee.ordermanager.model.payload.CreateAcmeOrderPayload;
import com.ee.ordermanager.model.payload.CreateEEOrderPayload;
import com.ee.ordermanager.model.payload.ShipAcmeOrderPayload;
import com.ee.ordermanager.model.payload.ShipEEOrderPayload;
import com.ee.ordermanager.random.Randomize;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FilterMapperTest {

    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Test
    void modelToPayloadCreateEEOrder() {
        CreateEEOrder createEEOrder = Randomize.random(CreateEEOrder.class);

        CreateEEOrderPayload result = orderMapper.modelToPayload(createEEOrder);

        assertThat(createEEOrder)
                .usingRecursiveComparison()
                .ignoringFields("creationDateTime")
                .isEqualTo(result);
    }

    @Test
    void modelToPayloadCreateAcmeOrder() {
        CreateAcmeOrder createAcmeOrder = Randomize.random(CreateAcmeOrder.class);

        CreateAcmeOrderPayload result = orderMapper.modelToPayload(createAcmeOrder);

        assertThat(createAcmeOrder)
                .usingRecursiveComparison()
                .ignoringFields("creationDateTime")
                .isEqualTo(result);
    }

    @Test
    void modelToPayloadShipEEOrder() {
        ShipEEOrder shipEEOrder = Randomize.random(ShipEEOrder.class);

        ShipEEOrderPayload result = orderMapper.modelToPayload(shipEEOrder);

        assertThat(shipEEOrder)
                .usingRecursiveComparison()
                .ignoringFields("shippingDate")
                .isEqualTo(result);
    }

    @Test
    void modelToPayloadShipAcmeOrder() {
        ShipAcmeOrder shipAcmeOrder = Randomize.random(ShipAcmeOrder.class);

        ShipAcmeOrderPayload result = orderMapper.modelToPayload(shipAcmeOrder);

        assertThat(shipAcmeOrder)
                .usingRecursiveComparison()
                .ignoringFields("shippingDate")
                .isEqualTo(result);
    }

}