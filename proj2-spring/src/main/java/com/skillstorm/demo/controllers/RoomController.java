package com.skillstorm.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.demo.models.HotelRoom;
import com.skillstorm.demo.services.RoomService;

@RestController
@RequestMapping("/rooms")
@CrossOrigin
public class RoomController {

	@Autowired
	private RoomService roomService;

	@GetMapping
	public ResponseEntity<List<HotelRoom>> getAllRooms(Pageable pageable) {
		Page<HotelRoom> rooms = roomService.findAllRooms(pageable);
		return new ResponseEntity<>(rooms.getContent(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<HotelRoom> getRoomById(@PathVariable long id) {
		HotelRoom room = roomService.findRoomById(id);
		return new ResponseEntity<>(room, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HotelRoom> createRoom(@RequestBody HotelRoom room) {
		HotelRoom createdRoom = roomService.createRoom(room);
		return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<HotelRoom> updateRoom(@PathVariable long id, @RequestBody HotelRoom room) {
		HotelRoom updatedRoom = roomService.findRoomById(id);
		updatedRoom.setRoomNumber(room.getRoomNumber());
		updatedRoom.setRoomType(room.getRoomType());
		updatedRoom.setAvailabilityStatus(room.getAvailabilityStatus());

		HotelRoom savedRoom = roomService.updateRoom(updatedRoom);
		return new ResponseEntity<>(savedRoom, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRoom(@PathVariable long id) {
		roomService.deleteRoom(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
