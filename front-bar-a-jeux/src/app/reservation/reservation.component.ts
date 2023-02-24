import { Component } from '@angular/core';
import { Reservation } from '../model';
import { ReservationService } from './reservation.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent {
  formReservation: Reservation = null;

  constructor(private reservationService: ReservationService) {
  }

  list(): Array<Reservation> {
    return this.reservationService.findAll();
  }

  add(): void {
    this.formReservation = new Reservation();
  }

  edit(id: number): void {
    this.reservationService.findById(id).subscribe(resp => {
      this.formReservation = resp;
    });
  }

  save(): void {
    if(this.formReservation.id) { // UPDATE
      this.reservationService.update(this.formReservation);
    } else { // CREATE
      this.reservationService.create(this.formReservation);
    }

    this.cancel();
  }

  remove(id: number): void {
    this.reservationService.remove(id);
  }

  cancel(): void {
    this.formReservation = null;
  }
}
