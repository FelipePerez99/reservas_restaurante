package com.app.reservasR.application.service;

import com.app.reservasR.application.Exception.EntityNotFoundException;
import com.app.reservasR.application.lasting.EMessage;
import com.app.reservasR.domain.dto.RestaurantDto;
import com.app.reservasR.domain.entity.DiningTable;
import com.app.reservasR.domain.entity.Restaurant;
import com.app.reservasR.domain.entity.Schedule;
import com.app.reservasR.domain.repository.DiningTableRepository;
import com.app.reservasR.domain.repository.RestaurantRepository;
import com.app.reservasR.domain.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public record RestaurantService(RestaurantRepository restaurantRepository, DiningTableRepository diningTableRepository, ScheduleRepository scheduleRepository) {

    public void createRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDto.name())
                .address(restaurantDto.address())
                .build();

        restaurantRepository.save(restaurant);

        List<DiningTable> diningTables = restaurantDto.diningTableList().stream()
                .map(diningTableDto -> {
                    DiningTable diningTable = DiningTable.builder()
                            .capacity(diningTableDto.getCapacity())
                            .restaurant(restaurant)
                            .build();
                    diningTableRepository.save(diningTable);
                    return diningTable;
                })
                .collect(Collectors.toList());

        restaurant.setDiningTableList(diningTables);

        List<Schedule> schedules = restaurantDto.schedules().stream()
                .map(scheduleDto -> {
                    Schedule schedule = Schedule.builder()
                            .startTime(scheduleDto.getStartTime())
                            .endTime(scheduleDto.getEndTime())
                            .restaurant(restaurant)
                            .build();
                    scheduleRepository.save(schedule);
                    return schedule;
                })
                .collect(Collectors.toList());

        restaurant.setSchedules(schedules);

        restaurantRepository.save(restaurant);

    }

    public RestaurantDto findRestaurantById(Integer id) throws EntityNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EMessage.DATA_NOT_FOUND));

        List<DiningTable> diningTables = restaurant.getDiningTableList().stream()
                .map(diningTable -> {
                    DiningTable dt = new DiningTable();
                    dt.setId(diningTable.getId());
                    dt.setCapacity(diningTable.getCapacity());
                    return dt;
                })
                .collect(Collectors.toList());

        List<Schedule> schedules = restaurant.getSchedules().stream()
                .map(schedule -> {
                    Schedule sch = new Schedule();
                    sch.setId(schedule.getId());
                    sch.setStartTime(schedule.getStartTime());
                    sch.setEndTime(schedule.getEndTime());
                    return sch;
                })
                .collect(Collectors.toList());


        return new RestaurantDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                diningTables,
                schedules
        );
    }

    public RestaurantDto updateRestaurant(Integer id, RestaurantDto restaurantDto) throws EntityNotFoundException{
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(EMessage.DATA_NOT_FOUND));

        restaurant.setName(restaurantDto.name());
        restaurant.setAddress(restaurantDto.address());

        updateDiningTableList(restaurant, restaurantDto.diningTableList());

        updateSchedules(restaurant, restaurantDto.schedules());

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        return new RestaurantDto(
                updatedRestaurant.getId(),
                updatedRestaurant.getName(),
                updatedRestaurant.getAddress(),
                updatedRestaurant.getDiningTableList(),
                updatedRestaurant.getSchedules()
        );
    }

    public List<RestaurantDto> allRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurant -> {
                    List<DiningTable> diningTables = restaurant.getDiningTableList().stream()
                            .map(diningTable -> {
                                DiningTable dt = new DiningTable();
                                dt.setId(diningTable.getId());
                                dt.setCapacity(diningTable.getCapacity());
                                return dt;
                            })
                            .collect(Collectors.toList());

                    List<Schedule> schedules = restaurant.getSchedules().stream()
                            .map(schedule -> {
                                Schedule sch = new Schedule();
                                sch.setId(schedule.getId());
                                sch.setStartTime(schedule.getStartTime());
                                sch.setEndTime(schedule.getEndTime());
                                return sch;
                            })
                            .collect(Collectors.toList());

                    return new RestaurantDto(
                            restaurant.getId(),
                            restaurant.getName(),
                            restaurant.getAddress(),
                            diningTables,
                            schedules
                    );
                })
                .collect(Collectors.toList());
    }


    public void deleteById(Integer id)throws EntityNotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()){
            restaurantRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException(EMessage.DATA_NOT_FOUND);
        }
    }


    private void updateDiningTableList(Restaurant restaurant, List<DiningTable> diningTableList) {
        restaurant.getDiningTableList().clear();

        if (diningTableList != null) {
            diningTableList.forEach(diningTable -> {
                diningTable.setRestaurant(restaurant); // Establecer la relación inversa
                restaurant.getDiningTableList().add(diningTable);
            });
        }
    }

    private void updateSchedules(Restaurant restaurant, List<Schedule> schedules) {
        restaurant.getSchedules().clear();

        if (schedules != null) {
            schedules.forEach(schedule -> {
                schedule.setRestaurant(restaurant); // Establecer la relación inversa
                restaurant.getSchedules().add(schedule);
            });
        }
    }
}
