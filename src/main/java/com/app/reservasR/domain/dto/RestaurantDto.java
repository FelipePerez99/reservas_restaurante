package com.app.reservasR.domain.dto;

import com.app.reservasR.domain.entity.DiningTable;
import com.app.reservasR.domain.entity.Schedule;

import java.util.List;

public record RestaurantDto(
        Integer id,
        String name,
        String address,
        List<DiningTable> diningTableList,
        List<Schedule> schedules
) {
}
