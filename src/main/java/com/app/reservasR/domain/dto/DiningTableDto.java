package com.app.reservasR.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DiningTableDto(
        Integer Id,
        int capacity
) {

}
