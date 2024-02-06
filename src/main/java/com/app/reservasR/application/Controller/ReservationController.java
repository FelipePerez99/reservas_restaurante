package com.app.reservasR.application.Controller;

import com.app.reservasR.application.Exception.EntityNotFoundException;
import com.app.reservasR.application.service.ReservationService;
import com.app.reservasR.domain.dto.ReservationDto;
import com.app.reservasR.domain.dto.RestaurantDto;
import com.app.reservasR.domain.entity.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public record ReservationController(ReservationService reservationService) {

    @PostMapping
    public ResponseEntity<?> registerReservation(@RequestBody ReservationDto reservationDto) throws EntityNotFoundException {
        reservationService.createReservation(reservationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Integer id) throws EntityNotFoundException{
        reservationService.cancelReservationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
