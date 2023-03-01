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
  typeconso: string;
  consos : Array<Conso>;
  listBoissons : Array<Conso>;


  constructor(private consoService: ConsoService){
  }

  listBoisson(): Array<Conso> {
  this.consos = this.consoService.findAll();
    


 return this.listBoisson;
     
    
  }

  listTypeConso(): Array<string> {
    return this.consoService.findAllTypeConso();
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
