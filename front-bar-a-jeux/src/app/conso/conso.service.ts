import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Conso } from '../model';

@Injectable({
  providedIn: 'root'
})
export class ConsoService {

  listBoissons: Array<Conso> = new Array<Conso>();
  listPlats: Array<Conso> = new Array<Conso>();


  constructor(private http: HttpClient) {
    this.loadBoisson();
    this.loadPlat();

  }


  FindAllByTypeconsoBoisson(): Array<Conso> {
    return this.listBoissons;
  }

   FindAllByTypeconsoPlat(): Array <Conso>{
     return this.listPlats;
   }


  private loadBoisson(): void {
    this.http.get<Array<Conso>>("http://localhost:8888/conso/boisson").subscribe(resp => {
      resp.forEach(typeconso => {
        this.listBoissons = resp;
      });
    });
  }

  private loadPlat(): void {
    this.http.get<Array<Conso>>("http://localhost:8888/conso/plat").subscribe(resp => {
      resp.forEach(typeconso => {
        this.listPlats = resp;
      });
    });
  }

}

