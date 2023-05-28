package com.skillstorm.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.demo.models.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long>{
    public List<Reservation> findByUserId(Long userId);
}
