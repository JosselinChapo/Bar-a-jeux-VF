import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
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
  formReservation: Reservation = new Reservation();
  myForm : FormGroup;

  constructor(
    private reservationService: ReservationService,
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

  save(): void {
    console.log(this.formReservation)
    if(this.formReservation.id) { // UPDATE
      this.reservationService.update(this.formReservation);
    } else { // CREATE
      this.reservationService.create(this.formReservation);
    }
  }

  remove(id: number): void {
    this.reservationService.remove(id);
  }

  myFilter = (d: Date | null): boolean => {
    const day = (d || new Date()).getDay();
    // Prevent Saturday and Sunday from being selected.
    let testDates: Array<Date>;

    if (!this.formReservation){
      testDates= this.reservationService.findDateDisable(0);
    }else{
      testDates= this.reservationService.findDateDisable(this.formReservation.nbPersonne);
    }
    let index: number=-1;
    if (testDates == undefined) {
      return day !== 0 && day !== 6;
    }else {
      // console.log(this.datepipe.transform(d, 'yyyy-MM-dd'));
      // console.log(this.datepipe.transform(testDates[0], 'yyyy-MM-dd'));
      // console.log(this.datepipe.transform(testDates[1], 'yyyy-MM-dd'));
      // console.log(testDates.findIndex(testDate => this.datepipe.transform(d, 'yyyy-MM-dd') == this.datepipe.transform(testDate, 'yyyy-MM-dd')));
      
      return day !== 0 && day !== 6 && testDates.findIndex(testDate => this.datepipe.transform(d, 'yyyy-MM-dd') == this.datepipe.transform(testDate, 'yyyy-MM-dd')) <0;
    };
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
