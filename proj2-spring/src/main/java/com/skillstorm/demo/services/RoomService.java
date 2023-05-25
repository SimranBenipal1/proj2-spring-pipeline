package com.skillstorm.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillstorm.demo.models.HotelRoom;
import com.skillstorm.demo.repositories.RoomRepo;

@Service
@Transactional
public class RoomService {

	@Autowired
	private RoomRepo roomRepo;

	public List<HotelRoom> findAllRooms() {
		return roomRepo.findAll();
	}

	public HotelRoom findRoomById(long id) {
		return roomRepo.findById(id).orElseThrow();
	}

	public HotelRoom createRoom(HotelRoom room) {
		return roomRepo.save(room);
	}

	public void deleteRoom(long id) {
		roomRepo.deleteById(id);
	}

	public HotelRoom updateRoom(HotelRoom room) {
		HotelRoom updatedRoom = new HotelRoom(
				room.getId(),
				room.getRoomNumber(),
				room.getRoomType(),
				room.getAvailabilityStatus());
		return roomRepo.save(updatedRoom);
	}
}
