import { Component } from '@angular/core';
import { ClientService } from '../client/client.service';
import { JeuService } from '../collection/jeu.service';
import { Admin, Client, Jeu, Reservation } from '../model';
import { ReservationService } from '../reservation/reservation.service';
import { AdminService } from './admin-service.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  formClient: Client = null;
  formReservation: Reservation = null;
  menuAdmin: string;
  creaAdmin: Admin= null;
  formJeu: Jeu = null;

  constructor(private adminService: AdminService,private reservationService: ReservationService,private jeuService: JeuService,private clientService: ClientService){
  }

  listAdmin(): Array<Admin> {
    return this.adminService.findAll();
    
  }

  add(): void {
    this.creaAdmin = new Admin();
  }

  save(): void {
     if(this.creaAdmin.id) { // UPDATE
      this.adminService.update(this.creaAdmin);
    } else { // CREATE
      this.adminService.create(this.creaAdmin);
    }
    this.cancel();
  }

  

  edit(id: number): void {
    this.adminService.findById(id).subscribe(response => {
      this.creaAdmin = response;
    });
  }


   remove(id: number): void {
    this.adminService.remove(id);
   }

  cancel(): void {
    this.creaAdmin = null;
  }

  /* Composant réservation */

  listResa(): Array<Reservation> {
    return this.reservationService.findAll();
  }

  addResa(): void {
    this.formReservation = new Reservation();
  }

  editResa(id: number): void {
    this.reservationService.findById(id).subscribe(resp => {
      this.formReservation = resp;
    });
  }

  saveResa(): void {
    if(this.formReservation.id) { // UPDATE
      this.reservationService.update(this.formReservation);
    } else { // CREATE
      this.reservationService.create(this.formReservation);
    }

    this.cancel();
  }

  removeResa(id: number): void {
    this.reservationService.remove(id);
  }

  cancelResa(): void {
    this.formReservation = null;
  }

  /* Composant jeu */

  listJeux(): Array<Jeu> {
    return this.jeuService.findAll();
  }

  addJeu(): void {
    this.formJeu= new Jeu();
  }

  editJeu(id: number): void {
    this.jeuService.findById(id).subscribe(response => {
      this.formJeu = response;
    });
  }

  saveJeu(): void {
    if(this.formJeu.id) { // UPDATE
      this.jeuService.update(this.formJeu);
    } else { // CREATE
      this.jeuService.create(this.formJeu);
    }

    this.cancelJeu();
  }

  removeJeu(id: number): void {
    this.jeuService.remove(id);
  }

  cancelJeu(): void {
    this.formJeu = null;
  }

  /* Composant client */

  listClient(): Array<Client> {
    return this.clientService.findAll();
  }

  editClient(id: number): void {
    this.clientService.findById(id).subscribe(response => {
      this.formClient = response;
    });
  }

  addClient(): void {
    this.formClient= new Client();
  }

  saveClient(): void {
    if(this.formClient.id) { // UPDATE
      this.clientService.update(this.formClient);
    } else { // CREATE
      this.clientService.create(this.formClient);
    }

    this.cancelClient();
  }

  removeClient(id: number): void {
    this.clientService.remove(id);
  }
  
  cancelClient(): void {
    this.formClient = null;
  }

 /* Fonction Menu */
  divToShow(info:string): void{
    this.menuAdmin = info;
  }

}
