import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from '../model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  admins: Array< Admin> = new Array<Admin>();

  constructor(private http: HttpClient) {
    this.load();
  
  }

  findAll(): Array<Admin> {
    return this.admins;
  }

  findById(id: number): Observable<Admin> {
    return this.http.get<Admin>("http://localhost:8888/admin/" + id);
  }

 create(Admin: Admin): void {
    
      this.http.post<Admin>("http://localhost:8888/admin", Admin).subscribe(resp => {
        this.load();
      });
    }

    update(Admin: Admin): void {
      this.http.put<Admin>("http://localhost:8888/admin/" + Admin.id, Admin).subscribe(resp => {
        this.load();
      });
    }
  
    remove(id: number): void {
      this.http.delete<void>("http://localhost:8888/admin/" + id).subscribe(resp => {
        this.load();
      });
    }
    

  private load(): void {
    this.http.get<Array<Admin>>("http://localhost:8888/admin").subscribe(resp => {
      this.admins = resp;
    });

    
  }
 
}
