import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth.service';
import { Admin, AuthDTO, Client } from '../model';

@Injectable({
  providedIn: 'root'
})
export class SeConnecterService {
  
  constructor(private http: HttpClient,private authService : AuthService) { }
 
loginAdmin(dto : AuthDTO){
  this.http.post<Admin>("http://localhost:8888/admin/auth",dto).subscribe(resp => { 
    resp.type = "admin";
    this.authService.loginCompte(resp)
  });
}

loginClient(dto : AuthDTO){

  this.http.post<Client>("http://localhost:8888/client/auth",dto).subscribe(resp => { 
    resp.type = "client";
    this.authService.loginCompte(resp)
  });
}

}