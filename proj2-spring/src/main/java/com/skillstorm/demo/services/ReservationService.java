package com.skillstorm.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillstorm.demo.models.HotelRoom;
import com.skillstorm.demo.models.Reservation;
import com.skillstorm.demo.models.User;
import com.skillstorm.demo.repositories.ReservationRepo;
import com.skillstorm.demo.repositories.RoomRepo;
import com.skillstorm.demo.repositories.UserRepo;

@Service
@Transactional
public class ReservationService {

	@Autowired
	private ReservationRepo reservationRepo;
	
    @Autowired
    private UserRepo userRepo;
    
	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private EmailService emailService;

	public List<Reservation> findAllReservations() {
		return reservationRepo.findAll();
	}

	public Reservation findReservationById(long id) {
		return reservationRepo.findById(id).orElseThrow();
	}

	public Reservation createReservation(Reservation reservation) {
        User user = userRepo.findById(reservation.getUser().getId()).orElseThrow();
        HotelRoom room = roomRepo.findById(reservation.getHotelRoom().getId()).orElseThrow();
        
        List<Reservation> conflictingReservations = reservationRepo.findConflictingReservations(room.getId(), reservation.getStartDate(), reservation.getEndDate());

        System.out.println(conflictingReservations.size());
        if (conflictingReservations.size() > 0) {
        	throw new RuntimeException("There is a conflict in reservation time");
        }
		
        String toEmail = user.getEmail();
		String subject = "Reservation Created";
		String message =
				"Dear " + user.getName() + ",\n\n"
                + "You have reserved the following room:\n"
                + "Room Number: " + room.getRoomNumber() + "\n"
                + "Room Type: " + room.getRoomType() + "\n"
                + "Start Date: " + reservation.getStartDate() + "\n"
                + "End Date: " + reservation.getEndDate() + "\n\n"
                + "Thank you for your reservation.";
		
		System.out.println(toEmail);
		System.out.println(subject);
		System.out.println(message);
		
		emailService.sendMail(toEmail, subject, message);
		
		System.out.println("email sent");
		
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
		
        User user = userRepo.findById(reservation.getUser().getId()).orElseThrow();
        HotelRoom room = roomRepo.findById(reservation.getHotelRoom().getId()).orElseThrow();
        
        List<Reservation> conflictingReservations = reservationRepo.findConflictingReservations(room.getId(), reservation.getStartDate(), reservation.getEndDate());

        System.out.println(conflictingReservations.size());
        if (conflictingReservations.size() > 1) {
        	throw new RuntimeException("There is a conflict in reservation time");
        }
		
        String toEmail = user.getEmail();
		String subject = "Reservation Updated";
		String message =
				"Dear " + user.getName() + ",\n\n"
                + "You have updated your reservation to the following room:\n"
                + "Room Number: " + room.getRoomNumber() + "\n"
                + "Room Type: " + room.getRoomType() + "\n"
                + "Start Date: " + reservation.getStartDate() + "\n"
                + "End Date: " + reservation.getEndDate() + "\n\n"
                + "Thank you for your reservation.";
		
		System.out.println(toEmail);
		System.out.println(subject);
		System.out.println(message);
		
		//emailService.sendMail(toEmail, subject, message);
		return reservationRepo.save(updatedReservation);
	}
	
    public List<Reservation> findReservationsByUserId(long userId) {
        return reservationRepo.findByUserId(userId);
    }
}
