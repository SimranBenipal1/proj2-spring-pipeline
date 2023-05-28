package com.skillstorm.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.demo.models.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long>{
    public List<Reservation> findByUserId(Long userId);
    
    @Query("SELECT r FROM Reservation r WHERE r.hotelRoom.id = :roomId " +
            "AND r.startDate <= :endDate AND r.endDate >= :startDate")
    List<Reservation> findConflictingReservations(
            @Param("roomId") Long roomId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
