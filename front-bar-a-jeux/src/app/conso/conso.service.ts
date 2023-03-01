import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Conso } from '../model';

@Injectable({
  providedIn: 'root'
})
export class ConsoService {
  Consos: Array< Conso> = new Array<Conso>();

  constructor(private http: HttpClient) {
    this.load();
  
  }

  findAll(): Array<Conso> {
    return this.Consos;
  }

  findById(id: number): Observable<Conso> {
    return this.http.get<Conso>("http://localhost:8888/Conso/" + id);
  }

 create(Conso: Conso): void {
    
      this.http.post<Conso>("http://localhost:8888/Conso", Conso).subscribe(resp => {
        this.load();
      });
    }

    update(Conso: Conso): void {
      this.http.put<Conso>("http://localhost:8888/Conso/" + Conso.id, Conso).subscribe(resp => {
        this.load();
      });
    }
  
    remove(id: number): void {
      this.http.delete<void>("http://localhost:8888/Conso/" + id).subscribe(resp => {
        this.load();
      });
    }
    

  private load(): void {
    this.http.get<Array<Conso>>("http://localhost:8888/Conso").subscribe(resp => {
      this.Consos = resp;
    });

    
  }
 
}

