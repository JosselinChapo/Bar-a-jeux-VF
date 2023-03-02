import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Conso } from '../model';

@Injectable({
  providedIn: 'root'
})
export class ConsoService {
  consos: Array< Conso> = new Array<Conso>();
  typeConsos: Array<string> = new Array<string>();

  constructor(private http: HttpClient) {
    this.load();
    this.loadTypeConsos();
  
  }

  findAll(): Array<Conso> {
    return this.consos;
  }

  findAllTypeConso(): Array<string> {
    return this.typeConsos;
  }

  FindAllByTypeconsoBoisson(): Observable<Array <Conso>>{
  
    return this.http.get<Array<Conso>>("http://localhost:8888/conso/boisson");

  }

  FindAllByTypeconsoPlat(): Observable<Array <Conso>>{
  
    return this.http.get<Array<Conso>>("http://localhost:8888/conso/plat");

  }


  findById(id: number): Observable<Conso> {
    return this.http.get<Conso>("http://localhost:8888/conso/" + id);
  }

 create(conso: Conso): void {
    
      this.http.post<Conso>("http://localhost:8888/conso", conso).subscribe(resp => {
        this.load();
      });
    }

    update(conso: Conso): void {
      this.http.put<Conso>("http://localhost:8888/conso/" + conso.id, conso).subscribe(resp => {
        this.load();
      });
    }
  
    remove(id: number): void {
      this.http.delete<void>("http://localhost:8888/conso/" + id).subscribe(resp => {
        this.load();
      });
    }
    

  private load(): void {
    this.http.get<Array<Conso>>("http://localhost:8888/conso").subscribe(resp => {
      this.consos = resp;
    });

    
  }

  private loadTypeConsos(): void {
    this.http.get<Array<string>>("http://localhost:8888/typeconsos").subscribe(resp => {
      resp.forEach(typeconso => {
        this.typeConsos = resp;
      });
    });
  }

 
}

