import { Component } from '@angular/core';
import { Admin } from '../model';
import { AdminService } from './admin-service.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  creaAdmin: Admin= null;

  constructor(private adminService: AdminService){
  }

  listAdmin(): Array<Admin> {
    return this.adminService.findAll();
    
  }

  add(): void {
    this.creaAdmin = new Admin();
  }

  save(): void {
    // if(this.creaAdmin.id) { // UPDATE
    //   this.adminService.update(this.creaAdmin);
    // } else { // CREATE
      this.adminService.create(this.creaAdmin);
    this.cancel();
  }

  // remove(id: number): void {
  //   this.adminService.remove(id);
  // }

  cancel(): void {
    this.creaAdmin = null;
  }

}
