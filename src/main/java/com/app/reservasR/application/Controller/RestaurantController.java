package com.app.reservasR.application.Controller;

import com.app.reservasR.application.Exception.EntityNotFoundException;
import com.app.reservasR.application.service.RestaurantService;
import com.app.reservasR.domain.dto.DiningTableDto;
import com.app.reservasR.domain.dto.RestaurantDto;
import com.app.reservasR.domain.dto.ScheduleDto;
import com.app.reservasR.domain.entity.DiningTable;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurant")
public record RestaurantController(RestaurantService restaurantService) {
    @PostMapping
    public ResponseEntity<?> registerRestaurant(@RequestBody RestaurantDto restaurantDto){
        restaurantService.createRestaurant(restaurantDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findRestaurantById(@PathVariable Integer id) throws EntityNotFoundException {
        RestaurantDto restaurantDto = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurantDto, HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<?> allSRestaurant(){
        List<RestaurantDto> restaurants = restaurantService.allRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Integer id, @RequestBody RestaurantDto restaurantDto) throws EntityNotFoundException{
        RestaurantDto updateRestaurant = restaurantService.updateRestaurant(id, restaurantDto);
        return new ResponseEntity<>(updateRestaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Integer id) throws EntityNotFoundException{
        restaurantService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
