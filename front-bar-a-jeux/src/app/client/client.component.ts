import { Component, Input } from '@angular/core';
import { Client, Reservation } from '../model';
import { ReservationService } from '../reservation/reservation.service';
import { ClientService } from './client.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent {
  formClient: Client = null;
  currentClient : Client;
  @Input('id')
  id : number;
  listResa : Array<Reservation>;
  menuClient : string;
  formReservation: Reservation = null;

  constructor(private clientService: ClientService, private resaService : ReservationService) {
  }

  list(): Array<Client> {
    return this.clientService.findAll();
  }

   listResaByIdClient() : Array<Reservation>{
   return this.listResa;
  }

  edit(id: number): void {
    this.clientService.findById(id).subscribe(response => {
      this.formClient = response;
    });
  }

  removeClient(id: number): void {
    this.clientService.remove(id);
  }

  clientPage() : void {
    this.clientService.findById(this.id).subscribe(response => {
  this.currentClient = response;
    });
  }

  ViewReservation(id: number): void {
    this.clientService.findAllReservationByIdClient(this.id).subscribe(response => {
      this.listResa = response;
    });
    this.menuClient = "reservation";
  }
    ViewInformationClient() : void {
      this.menuClient = "information";
    }
  
    // editResa(id: number): void {
    //   this.resaService.findById(id).subscribe(resp => {
    //     this.formReservation = resp;
    //   });
    // }

    removeResa(id: number): void {
      this.resaService.removeId(id);
    }

    
}


