import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth.service';
import { Admin, AuthDTO, Client } from '../model';
import { PopupComponent } from './popup.component';

@Injectable({
  providedIn: 'root'
})
export class PopupService {

  private popup: PopupComponent[] = [];
  
  constructor(private http: HttpClient,private authService : AuthService) { }
  
    add(modal: PopupComponent) {
      // ensure component has a unique id attribute
      if (!modal.id || this.popup.find(x => x.id === modal.id)) {
          throw new Error('modal must have a unique id attribute');
      }
  
      // add modal to array of active modals
      this.popup.push(modal);
  }
  
  remove(modal: PopupComponent) {
      // remove modal from array of active modals
      this.popup = this.popup.filter(x => x === modal);
  }
  
  open(id: string) {
      // open modal specified by id
      const modal = this.popup.find(x => x.id === id);
  
      if (!modal) {
          throw new Error(`modal '${id}' not found`);
      }
  
      modal.open();
  }
  
  close() {
      // close the modal that is currently open
      const modal = this.popup.find(x => x.isOpen);
      modal?.close();
  }
 
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
