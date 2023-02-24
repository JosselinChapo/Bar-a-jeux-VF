package bar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import bar.model.Reservation;
import bar.model.Views;
import bar.repository.IReservationRepository;
import bar.service.ReservationService;

@RestController
@RequestMapping("/reservation")
@CrossOrigin("*")
public class ReservationRestController {
	@Autowired
	private ReservationService reservationSrv;
	@Autowired
	private IReservationRepository reservationRep;

	@GetMapping("")
	@JsonView(Views.ViewReservation.class)
	public List<Reservation> findAll() {
		List<Reservation> reservations = reservationSrv.findAll();

		return reservations;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewReservation.class)
	public Reservation findById(@PathVariable Integer id) {
		Reservation reservation = reservationSrv.findById(id);

//		if(optReservation.isEmpty()) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		}
		
		return reservation;
	}
	
	@PostMapping("")
	@JsonView(Views.ViewReservation.class)
	public Reservation create(@RequestBody Reservation reservation) {
		reservation = reservationSrv.create(reservation);
		
		return reservation;
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.ViewReservation.class)
	public Reservation update(@RequestBody Reservation reservation, @PathVariable Integer id) {
		if(id != reservation.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		if(!reservationRep.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		reservation = reservationSrv.update(reservation);
		
		return reservation;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		reservationSrv.delete(id);
	}
	
}
