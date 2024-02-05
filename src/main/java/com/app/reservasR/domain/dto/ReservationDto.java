package com.app.reservasR.domain.dto;

import com.app.reservasR.domain.entity.DiningTable;
import com.app.reservasR.domain.entity.Restaurant;
import com.app.reservasR.domain.entity.User;

import java.util.Date;

public record ReservationDto(
        Integer id,
        User user,
        DiningTable diningTable,
        Restaurant restaurant
) {
}
