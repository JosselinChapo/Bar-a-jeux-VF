import { Component, Input } from '@angular/core';
import { Client } from '../model';
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

  constructor(private clientService: ClientService) {
  }

  list(): Array<Client> {
    return this.clientService.findAll();
  }

  edit(id: number): void {
    this.clientService.findById(id).subscribe(response => {
      this.formClient = response;
    });
  }

  remove(id: number): void {
    this.clientService.remove(id);
  }
  
clientPage() : void {
  this.clientService.findById(this.id).subscribe(response => {
this.currentClient = response;
  });
}

}
