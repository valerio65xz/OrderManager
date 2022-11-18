package com.ee.ordermanager.controller;

import com.ee.ordermanager.model.dto.CreateAcmeOrder;
import com.ee.ordermanager.model.dto.CreateEEOrder;
import com.ee.ordermanager.model.dto.ShipAcmeOrder;
import com.ee.ordermanager.model.dto.ShipEEOrder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@Validated
@Tag(name = "Orders", description = "The set of API to make orders between EverythingEverywhere and ACME")
public interface OrderApi {

    /**
     * POST /order/create/ee : Creates an EverythingEverywhere's order
     *
     * @param order the order's data
     * @return Request accepted (status code 202)
     */
    @Operation(
            operationId = "createEEOrder",
            summary = "Creates an EverythingEverywhere's order",
            tags = {"Orders"},
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Creation succesfully done"
                    )
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/order/create/ee",
            consumes = {"application/json"}
    )
    ResponseEntity<Void> createEEOrder(
            @Valid
            @Parameter(
                    name = "order",
                    description = "The order's data",
                    required = true)
            @RequestBody CreateEEOrder order
    );

    /**
     * POST /order/create/acme : Creates an Acme's order
     *
     * @param order the order's data
     * @return Request accepted (status code 202)
     */
    @Operation(
            operationId = "createAcmeOrder",
            summary = "Creates an Acme's order",
            tags = {"Orders"},
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Creation succesfully done"
                    )
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/order/create/acme",
            consumes = {"application/json"}
    )
    ResponseEntity<Void> createAcmeOrder(
            @Valid
            @Parameter(
                    name = "order",
                    description = "The order's data",
                    required = true)
            @RequestBody CreateAcmeOrder order
    );

    /**
     * POST /order/ship/ee : Ships an EverythingEverywhere's order
     *
     * @param order the order's data
     * @return Request accepted (status code 202)
     */
    @Operation(
            operationId = "shipEEOrder",
            summary = "Ships an EverythingEverywhere's order",
            tags = {"Orders"},
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Ship succesfully done"
                    )
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/order/ship/ee",
            consumes = {"application/json"}
    )
    ResponseEntity<Void> shipEEOrder(
            @Valid
            @Parameter(
                    name = "order",
                    description = "The order's data",
                    required = true)
            @RequestBody ShipEEOrder order
    );

    /**
     * POST /order/ship/acme : Ships an Acme's order
     *
     * @param order the order's data
     * @return Request accepted (status code 202)
     */
    @Operation(
            operationId = "shipAcmeOrder",
            summary = "Ships an Acme's order",
            tags = {"Orders"},
            responses = {
                    @ApiResponse(
                            responseCode = "202",
                            description = "Ship succesfully done"
                    )
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/order/ship/acme",
            consumes = {"application/json"}
    )
    ResponseEntity<Void> shipAcmeOrder(
            @Valid
            @Parameter(
                    name = "order",
                    description = "The order's data",
                    required = true)
            @RequestBody ShipAcmeOrder order
    );

}