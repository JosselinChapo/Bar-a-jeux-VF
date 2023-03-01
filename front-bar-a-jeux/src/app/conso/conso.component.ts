import { Component } from '@angular/core';
import { Conso } from '../model';
import { ConsoService } from './conso.service';

@Component({
  selector: 'conso',
  templateUrl: './conso.component.html',
  styleUrls: ['./conso.component.css']
})
export class ConsoComponent {
  creaConso: Conso = null;


  constructor(private consoService: ConsoService){
  }

  list(): Array<Conso> {
    return this.consoService.findAll();
    
  }

  add(): void {
    this.creaConso = new Conso();
  }

  save(): void {
     if(this.creaConso.id) { // UPDATE
      this.consoService.update(this.creaConso);
    } else { // CREATE
      this.consoService.create(this.creaConso);
    }
    this.cancel();
  }

  edit(id: number): void {
    this.consoService.findById(id).subscribe(response => {
      this.creaConso = response;
    });
  }


   remove(id: number): void {
    this.consoService.remove(id);
   }

  cancel(): void {
    this.creaConso = null;
  }
}
