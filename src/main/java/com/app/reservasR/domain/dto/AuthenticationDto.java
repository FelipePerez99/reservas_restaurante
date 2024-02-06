package com.app.reservasR.domain.dto;

public record AuthenticationDto(
        String email,
        String password
) {
}
