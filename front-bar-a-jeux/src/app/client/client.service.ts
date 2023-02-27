import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client, Reservation } from '../model';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  clients: Array<Client> = new Array<Client>();
  civilites: Array<string> = new Array<string>();


  constructor(private http: HttpClient) { 
    this.load();
    this.loadCivilites();
  }

  findAllCivilite(): Array<string> {
    return this.civilites;
  }

  findAll(): Array<Client> {
    return this.clients;
  }

  findById(id: number): Observable<Client> {
    return this.http.get<Client>("http://localhost:8888/client/" + id);
  }

  create(client: Client): void {
    this.http.post<Client>("http://localhost:8888/client", client).subscribe(resp => {
      this.load();
    });
  }

  update(client: Client): void {
    this.http.put<Client>("http://localhost:8888/client/" + client.id, client).subscribe(resp => {
      this.load();
    });
  }

  remove(id: number): void {
    this.http.delete<void>("http://localhost:8888/client/" + id).subscribe(resp => {
      this.load();
    });
  }

  findAllReservationByIdClient(id? : number): Observable<Array<Reservation>> {
    return this.http.get<Array<Reservation>>("http://localhost:8888/reservation/client/" + (id?id:""));
  }

  removeResa(id: number): void {
    this.http.delete<void>("http://localhost:8888/reservation/" + id).subscribe(resp => {
  });
  }

  updateResa(reservation: Reservation): void {
    this.http.put<Reservation>("http://localhost:8888/reservation/" + reservation.id, reservation).subscribe(resp => {
      this.load();
    });
  }

  private load(): void {
    this.http.get<Array<Client>>("http://localhost:8888/client").subscribe(resp => {
      this.clients = resp;
    });
  }

  private loadCivilites(): void {
    this.http.get<Array<string>>("http://localhost:8888/civilites").subscribe(resp => {
      resp.forEach(civ => {
        this.civilites = resp;
      });
    });
  }



  
}
