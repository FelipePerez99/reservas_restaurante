package com.app.reservasR.domain.entity.dto;

import com.app.reservasR.application.lasting.ERole;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
        Integer id,
        String firstName,
        String lastName,
        String email,
        String password,
        Boolean enable,
        ERole role
) {
}
