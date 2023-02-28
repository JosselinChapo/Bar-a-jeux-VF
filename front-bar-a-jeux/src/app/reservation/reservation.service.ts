import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClientService } from '../client/client.service';
import { Reservation } from '../model';
import { ReservationComponent } from './reservation.component';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  reservations: Array<Reservation> = new Array<Reservation>();
  
  datesDisable: Array<Date>;
  heuresDisable: Array<string>;
  heureAChanger: boolean=false;

  constructor(private http: HttpClient, private clientSrv: ClientService) {
    this.load();
    this.loadHeures(undefined,undefined);
  }

  findAll(): Array<Reservation> {
    return this.reservations;
  }

  findById(id: number): Observable<Reservation> {
    return this.http.get<Reservation>("http://localhost:8888/reservation/" + id);
  }

  findDateDisable(nbPersonne: number): Array<Date> {
    if (nbPersonne!== undefined){
      this.http.get<Array<Date>>("http://localhost:8888/reservation/dates/" + nbPersonne).subscribe(resp => {
        this.datesDisable = resp;
      });
    }else {
      this.datesDisable=[];
    }
    return this.datesDisable
  }

  findAllHeure(nbPersonne: number, datetest: string): Array<string> {
    if (this.heureAChanger) {
      this.loadHeures(nbPersonne,datetest);
      this.heureAChanger=false;
    }
    return this.heuresDisable
  }

  changeHeure() {
    this.heureAChanger=true;
  }

  create(reservation: Reservation): void {
    this.http.post<Reservation>("http://localhost:8888/reservation", reservation).subscribe(resp => {
      this.load();
    });
  }

  update(reservation: Reservation): void {
    this.http.put<Reservation>("http://localhost:8888/reservation/" + reservation.id, reservation).subscribe(resp => {
      this.load();
    });
  }

  remove(id: number): void {
    this.http.delete<void>("http://localhost:8888/reservation/" + id).subscribe(resp => {
      this.load();
    });
  }

  removeId(id: number): void {
    this.http.delete<void>("http://localhost:8888/reservation/" + id).subscribe(resp => {
    });
  }

  private load(): void {
    this.http.get<Array<Reservation>>("http://localhost:8888/reservation").subscribe(resp => {
      this.reservations = resp;
    });
  }

  private loadHeures(nbPersonne: number, datetest: string): void {
    if (nbPersonne!== undefined && datetest!== undefined){
      this.http.get<Array<string>>("http://localhost:8888/reservation/heures/" + nbPersonne + ":" + datetest).subscribe(resp => {
        this.heuresDisable = resp;
      });
    }else {
      this.heuresDisable=["10:00:00","11:00:00","14:00:00","15:00:00"];
    }  
  }

}
