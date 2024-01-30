package com.app.reservasR.domain.repository;

import com.app.reservasR.domain.entity.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiningTableRepository extends JpaRepository<DiningTable, Integer> {
}
