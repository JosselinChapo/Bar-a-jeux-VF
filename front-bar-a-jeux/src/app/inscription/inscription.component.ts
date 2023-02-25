import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ClientService } from '../client/client.service';
import { Client } from '../model';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})

export class InscriptionComponent {
  // @Input("client")
  currentClient : Client;
  formClientInscription: Client = null;

  constructor(private router: Router, private clientService: ClientService) {
  }
  

  listCivilites(): Array<string> {
    return this.clientService.findAllCivilite();
  }
  
  save(): void {
    console.log(this.currentClient.id);
    if(this.formClientInscription.id) { // UPDATE
      this.clientService.update(this.formClientInscription);
    } else { // CREATE
      this.clientService.create(this.formClientInscription);
    }

    // this.cancel();
  }

  // cancel(): void {
  //   this.formClientInscription = null;
  // }
}
