import { Injectable } from '@angular/core';
import { Client } from '../model';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  clients: Array<Client> = new Array<Client>();
  civilites: Map<string, string> = new Map<string,string>();

  constructor(private http: HttpClient) { 
    this.load();
    this.loadCivilites();
  }






  private load(): void {
    this.http.get<Array<Stagiaire>>("http://localhost:8888/stagiaire").subscribe(resp => {
      this.stagiaires = resp;
    });
  }

  private loadCivilites(): void {
    this.http.get<Array<string>>("http://localhost:8888/civilites").subscribe(resp => {
      resp.forEach(civ => {
        this.civilites.set(civ, civ);
      });
    });
  }

}
