package com.ee.ordermanager.mapper;

import com.ee.ordermanager.model.CreateAcmeOrder;
import com.ee.ordermanager.model.CreateAcmeOrderPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArchiveMapper {

    /**
     * Converts the createAcmeOrder into the avro payload
     *
     * @param order   The create-acme-order
     * @return The CreateAcmeOrderPayload
     */
    CreateAcmeOrderPayload modelToUpsertPayload(CreateAcmeOrder order);

//    /**
//     * Converts the mail server response to the archive object
//     *
//     * @param mailServerApiAggregateGetMailboxesResponse mail server response
//     * @return the archive
//     */
//    @Mapping(target = "configuration", source = ".")
//    Archive webMailApiAggregateGetMailboxesResponseToArchive(MailServerApiAggregateGetMailboxesResponse mailServerApiAggregateGetMailboxesResponse);
//
//    @Mapping(target = "size", source = "quota.computed.size")
//    @Mapping(target = "currentSpaceOccupation", source = "quota.occupation.size")
//    @Mapping(target = "validFrom", source = "lifecycle.validFrom")
//    @Mapping(target = "validTo", source = "lifecycle.validTo")
//    ArchiveConfiguration webMailApiAggregateGetResponseConfigurationToArchiveConfiguration(MailServerApiAggregateGetMailboxesResponse mailServerApiAggregateGetMailboxesResponse);
//
//    default Instant localDateTimeToInstant(LocalDateTime localDateTime) {
//        return localDateTime.toInstant(ZoneOffset.UTC);
//    }
}
