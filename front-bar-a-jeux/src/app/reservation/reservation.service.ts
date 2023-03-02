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
  heuresDispo: Array<string>;
  heureAChanger: boolean=false;
  tablesDispo: Array<number>;
  tableAChanger: boolean=false;

  constructor(private http: HttpClient, private clientSrv: ClientService) {
    this.load();
    this.loadHeures(undefined,undefined);
    this.loadTables(undefined,undefined,undefined);
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
    return this.datesDisable;
  }

  findAllHeure(nbPersonneheure: number, datetest: string): Array<string> {
    if (this.heureAChanger) {
      this.loadHeures(nbPersonneheure,datetest);
      this.heureAChanger=false;
    }
    return this.heuresDispo;
  }

  findAllTable(nbPersonnetable: number, datetable: string, heuretable: string): Array<number> {
    if (this.tableAChanger) {
      this.loadTables(nbPersonnetable,datetable,heuretable);
      this.tableAChanger=false;
    }
    return this.tablesDispo;
  }

  changeHeure() {
    this.heureAChanger=true;
  }

  changeTable() {
    this.tableAChanger=true;
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

  loadHeures(nbPersonneHeure: number, dateHeure: string): void {
    if (nbPersonneHeure!== undefined && dateHeure!== undefined){
      this.http.get<Array<string>>("http://localhost:8888/reservation/heures/" + nbPersonneHeure + ":" + dateHeure).subscribe(resp => {
        this.heuresDispo = resp;
      });
    }else {
      this.heuresDispo=["18:00:00","19:00:00","20:00:00","21:00:00","22:00:00","23:00:00"];
    }  
  }

  loadTables(nbPersonneTable: number, datetest: string, heureTable: string): void {
    if (nbPersonneTable!== undefined && datetest!== undefined && heureTable!== undefined){
      this.http.get<Array<number>>("http://localhost:8888/reservation/tables/" + nbPersonneTable + "/" + datetest + "/" + heureTable).subscribe(resp => {
        this.tablesDispo = resp;
      });
    }else {
      this.tablesDispo=[1,2,3,4,5,6,7,8];
    }  
  }

}
