package com.ee.ordermanager.model;

import lombok.*;

import java.util.Map;

@Builder
public record Event (
        @NonNull
        Map<String, Object> data
){}
