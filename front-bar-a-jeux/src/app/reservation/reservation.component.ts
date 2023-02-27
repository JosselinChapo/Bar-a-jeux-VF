import { DatePipe } from '@angular/common';
import { Component, Inject, inject } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DateAdapter, MAT_DATE_LOCALE } from '@angular/material/core';
import { Reservation } from '../model';
import { ReservationService } from './reservation.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css'],
  providers: [DatePipe],
})
export class ReservationComponent {
  formReservation: Reservation = null;
  
  myForm : FormGroup;

  constructor(private reservationService: ReservationService,
    private _adapter: DateAdapter<any>, 
    @Inject(MAT_DATE_LOCALE) private _locale: string,
    public datepipe: DatePipe
  ) { 
    this._locale = 'fr-FR';
    this._adapter.setLocale(this._locale);

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
    console.log(this.formReservation)
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

  myFilter = (d: Date | null): boolean => {
    const day = (d || new Date()).getDay();
    // Prevent Saturday and Sunday from being selected.

    const testDates: Array<Date> = [
      new Date('2023-03-07'),
      new Date('2023-03-02'),
      new Date('2023-05-04'),
      new Date('2023-03-30')
    ]
    return day !== 0 && day !== 6 && testDates.findIndex(testDate => d?.toDateString() === testDate.toDateString()) < 0;
  };

  test(event: any) {
    console.log(event);
    let event1: Date;
    // let dateForm: string = event.toISOString();
    // console.log(dateForm);
    // let dateForm1: string = event.toString();
    // console.log(dateForm1);
    let dateForm2 =this.datepipe.transform(event, 'yyyy-MM-dd');
    console.log(dateForm2);

    this.formReservation.dateRes=dateForm2;

  } 
}
