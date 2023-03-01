import { Component, Input } from '@angular/core';
import { ClientService } from '../client/client.service';
import { Client } from '../model';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})

export class InscriptionComponent {

  formClientInscription: Client = new Client();

  constructor(private clientService: ClientService) {
  }
  
  listCivilites(): Array<string> {
    return this.clientService.findAllCivilite();
  }
  
  save(): void {
    if(this.formClientInscription.id) { // UPDATE
      this.clientService.update(this.formClientInscription);
    } else { // CREATE
      this.clientService.create(this.formClientInscription);
    }
  }

}
