package com.app.reservasR.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ScheduleDto(
        Integer id,
        Date startTime,
        Date endTime
) {
}
