package com.skillstorm.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.skillstorm.demo.models.Reservation;
import com.skillstorm.demo.services.ReservationService;

@RestController
@RequestMapping("/reservations")
@CrossOrigin
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@GetMapping
	public ResponseEntity<List<Reservation>> getAllReservations() {
		List<Reservation> reservations = reservationService.findAllReservations();
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Reservation> getReservationById(@PathVariable long id) {
		Reservation reservation = reservationService.findReservationById(id);
		return new ResponseEntity<>(reservation, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
		Reservation createdReservation = reservationService.createReservation(reservation);
		return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Reservation> updateReservation(@PathVariable long id, @RequestBody Reservation reservation) {
		Reservation updatedReservation = reservationService.findReservationById(id);
		updatedReservation.setUser(reservation.getUser());
		updatedReservation.setHotelRoom(reservation.getHotelRoom());
		updatedReservation.setReservationStatus(reservation.getReservationStatus());
		updatedReservation.setReservationTime(reservation.getReservationTime());
		updatedReservation.setStartDate(reservation.getStartDate());
		updatedReservation.setEndDate(reservation.getEndDate());

		Reservation savedReservation = reservationService.updateReservation(updatedReservation);
		return new ResponseEntity<>(savedReservation, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
		reservationService.deleteReservation(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
