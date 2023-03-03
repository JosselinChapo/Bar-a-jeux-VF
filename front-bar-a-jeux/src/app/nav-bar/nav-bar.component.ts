import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { CommandeJeuService } from '../panier/commande-jeu.service';
import { PopupService } from '../popup/popup.service';

@Component({
  selector: 'nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  resaconnected: boolean= false;

  constructor(public popupService: PopupService, public appComponent : AppComponent,public router : Router,private commandeService : CommandeJeuService) {
    
   }

  navigationUser(resa?: string){
    if(resa=="reservation" && this.appComponent.connected==false && this.resaconnected==false){
      console.log("je suis la");
      this.resaconnected=true;
      this.popupService.open('modal-1');
    }
    else if(this.appComponent.client == undefined && this.appComponent.admin == undefined){
      console.log("je suis undefinie");
      this.appComponent.boutonConnectedPress = true;
      this.popupService.open('modal-1');
    }
    else if( this.appComponent.admin != undefined){
      console.log("je suis dans la admin");
      this.router.navigate(["/admin"]);
    }
    else if(resa=="reservation" && this.appComponent.connected){
      console.log("je suis dans la resa");
      this.router.navigate(["/reservation"]);
    }
    else{
      console.log(this.resaconnected);
      console.log("je suis dans la client");
      this.router.navigate(["/client"]);
    }

  }

  refreshPanier(){
    this.commandeService.load(this.appComponent.client.id);
  }
}
