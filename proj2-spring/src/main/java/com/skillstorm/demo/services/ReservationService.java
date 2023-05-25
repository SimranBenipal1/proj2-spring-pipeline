package com.skillstorm.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillstorm.demo.models.Reservation;
import com.skillstorm.demo.repositories.ReservationRepo;

@Service
@Transactional
public class ReservationService {

	@Autowired
	private ReservationRepo reservationRepo;

	public List<Reservation> findAllReservations() {
		return reservationRepo.findAll();
	}

	public Reservation findReservationById(long id) {
		return reservationRepo.findById(id).orElseThrow();
	}

	public Reservation createReservation(Reservation reservation) {
		return reservationRepo.save(reservation);
	}

	public void deleteReservation(long id) {
		reservationRepo.deleteById(id);
	}

	public Reservation updateReservation(Reservation reservation) {
		Reservation updatedReservation = new Reservation(
				reservation.getId(),
				reservation.getUser(),
				reservation.getHotelRoom(),
				reservation.getReservationStatus(),
				reservation.getReservationTime(),
				reservation.getStartDate(),
				reservation.getEndDate());
		return reservationRepo.save(updatedReservation);
	}
}
