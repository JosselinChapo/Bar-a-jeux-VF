import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Jeu } from '../model';

@Injectable({
  providedIn: 'root'
})
export class JeuService {

  jeux: Array<Jeu> = new Array<Jeu>();

    constructor(private http : HttpClient) { 
     this.load();
    }
  
    findAll(): Array<Jeu> {
      return this.jeux;
    }
  
    findById(id: number): Observable<Jeu> {
      return this.http.get<Jeu>("http://localhost:8888/jeu/"+id);
    }
  
    create(jeu: Jeu): void {
      this.http.post<Jeu>("http://localhost:8888/jeu", jeu).subscribe(resp => {
        this.load();
      });
    }
  
    update(jeu: Jeu): void {
      this.http.put<Jeu>("http://localhost:8888/jeu/" + jeu.id, jeu).subscribe(resp => {
        this.load();
      });
    }
  
    remove(id: number): void {
      this.http.delete<void>("http://localhost:8888/jeu/" + id).subscribe(resp => {
        this.load();
      });
    }
  
    private load(): void {
      this.http.get<Array<Jeu>>("http://localhost:8888/jeu").subscribe(resp => {
        this.jeux = resp;
      });
    }
  }

