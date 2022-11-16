package com.ee.ordermanager.mapper;

import com.ee.ordermanager.model.dto.CreateAcmeOrder;
import com.ee.ordermanager.model.dto.CreateEEOrder;
import com.ee.ordermanager.model.dto.ShipAcmeOrder;
import com.ee.ordermanager.model.dto.ShipEEOrder;
import com.ee.ordermanager.model.payload.CreateAcmeOrderPayload;
import com.ee.ordermanager.model.payload.CreateEEOrderPayload;
import com.ee.ordermanager.model.payload.ShipAcmeOrderPayload;
import com.ee.ordermanager.model.payload.ShipEEOrderPayload;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface OrderMapper {

    /**
     * Converts the createAcmeOrder into the avro payload
     *
     * @param order   The create-acme-order
     * @return The CreateAcmeOrderPayload
     */
    CreateAcmeOrderPayload modelToPayload(CreateAcmeOrder order);

    /**
     * Converts the createEEOrder into the avro payload
     *
     * @param order   The create-ee-order
     * @return The CreateEEOrderPayload
     */
    CreateEEOrderPayload modelToPayload(CreateEEOrder order);

    /**
     * Converts the shipEEOrder into the avro payload
     *
     * @param order   The ship-ee-order
     * @return The ShipEeOrderPayload
     */
    ShipEEOrderPayload modelToPayload(ShipEEOrder order);

    /**
     * Converts the shipAcmeOrder into the avro payload
     *
     * @param order   The ship-acme-order
     * @return The ShipAcmeOrderPayload
     */
    ShipAcmeOrderPayload modelToPayload(ShipAcmeOrder order);

    default Instant localDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC);
    }

}
