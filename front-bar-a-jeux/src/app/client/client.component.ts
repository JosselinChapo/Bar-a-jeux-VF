import { Component } from '@angular/core';
import { Client } from '../model';
import { ClientService } from './client.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent {
  formClient: Client = null;

  constructor(private clientService: ClientService) {
  }

  list(): Array<Client> {
    return this.clientService.findAll();
  }

  listCivilites(): Array<string> {
    return this.clientService.findAllCivilite();
  }

  add(): void {
    this.formClient = new Client();

  }

  edit(id: number): void {
    this.clientService.findById(id).subscribe(response => {
      this.formClient = response;
    });
  }

  save(): void {
    if(this.formClient.id) { // UPDATE
      this.clientService.update(this.formClient);
    } else { // CREATE
      this.clientService.create(this.formClient);
    }

    this.cancel();
  }

  remove(id: number): void {
    this.clientService.remove(id);
  }

  cancel(): void {
    this.formClient = null;
  }
  
}
