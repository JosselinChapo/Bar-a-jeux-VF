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

  listPlat(): Array<Conso>  {
    return this.consoService.FindAllByTypeconsoPlat();
     }

     listBoisson(): Array<Conso>  {
      return this.consoService.FindAllByTypeconsoBoisson();
       }
  
}