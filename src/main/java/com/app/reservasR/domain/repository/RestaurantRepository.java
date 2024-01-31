package com.app.reservasR.domain.repository;

import com.app.reservasR.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
