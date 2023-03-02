import { Component } from '@angular/core';
import { Observable } from 'rxjs';
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
  listPlats : Array<Conso>;

  constructor(private consoService: ConsoService){
  }

  listPlat(): void {
    this.consoService.FindAllByTypeconsoPlat().subscribe(resp => {
      this.listPlats = resp;
    })
     }

     listBoisson (): void {
      this.consoService.FindAllByTypeconsoBoisson().subscribe(resp => {
        this.listBoissons = resp;
      })
       }

  listTypeConso(): Array<string> {

    return this.consoService.findAllTypeConso();
  }

  AffichePlat() : Array<Conso> {
    this.listPlat();
    return this.listPlats;
  }

  AfficheBoisson() : Array<Conso> {
    this.listBoisson();
    return this.listBoissons;
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
