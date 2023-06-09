package com.skillstorm.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.demo.models.HotelRoom;

@Repository
public interface RoomRepo extends JpaRepository<HotelRoom, Long>{

}
