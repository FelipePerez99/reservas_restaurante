package com.app.reservasR.application.service;

import com.app.reservasR.application.Exception.EntityNotFoundException;
import com.app.reservasR.application.lasting.EMessage;
import com.app.reservasR.domain.dto.ReservationDto;
import com.app.reservasR.domain.dto.ScheduleDto;
import com.app.reservasR.domain.entity.DiningTable;
import com.app.reservasR.domain.entity.Reservation;
import com.app.reservasR.domain.entity.Restaurant;
import com.app.reservasR.domain.entity.User;
import com.app.reservasR.domain.repository.DiningTableRepository;
import com.app.reservasR.domain.repository.ReservationRepository;
import com.app.reservasR.domain.repository.RestaurantRepository;
import com.app.reservasR.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public record ReservationService(ReservationRepository reservationRepository, UserRepository userRepository,
                                 DiningTableRepository diningTableRepository, RestaurantRepository restaurantRepository) {
    public void createReservation(ReservationDto reservationDto) throws EntityNotFoundException {
        Integer idUser = reservationDto.user().getId();
        Integer idDiningTable = reservationDto.diningTable().getId();
        Integer idRestaurant = reservationDto.restaurant().getId();


        User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException(EMessage.DATA_NOT_FOUND));

        DiningTable diningTable = diningTableRepository.findById(idDiningTable)
                .orElseThrow(() -> new EntityNotFoundException(EMessage.DATA_NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(idRestaurant)
                .orElseThrow(() -> new EntityNotFoundException(EMessage.DATA_NOT_FOUND));


        Reservation reservation = Reservation.builder()
                .user(user)
                .diningTable(diningTable)
                .restaurant(restaurant)
                .build();

        reservationRepository.save(reservation);
    }

    public List<Reservation> findReservationByUser(Integer userId) throws EntityNotFoundException {
        Optional<User>  user = userRepository.findById(userId);

        User useer = user.orElseThrow(() -> new EntityNotFoundException(EMessage.DATA_NOT_FOUND));

        return reservationRepository.findByUserId(useer.getId());
    }

    public void cancelReservationById(Integer id)throws EntityNotFoundException {
        Optional<Reservation> restaurant = reservationRepository.findById(id);
        if (restaurant.isPresent()){
            reservationRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException(EMessage.DATA_NOT_FOUND);
        }
    }
}
