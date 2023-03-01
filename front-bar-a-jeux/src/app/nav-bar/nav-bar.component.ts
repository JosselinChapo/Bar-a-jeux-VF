import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { PopupService } from '../popup/popup.service';

@Component({
  selector: 'nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  constructor(public popupService: PopupService, public appComponent : AppComponent,public router : Router) { }

  navigationUser(){
    if(this.appComponent.client == undefined && this.appComponent.admin == undefined){
      this.popupService.open('modal-1');
    }
    else if( this.appComponent.admin != undefined){
      this.router.navigate(["/admin"]);
    }
    else{
      this.router.navigate(["/client"]);
    }
  }

}
