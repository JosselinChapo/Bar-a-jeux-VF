import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { Admin, AuthDTO, Client } from './model';
import { PopupService } from './popup/popup.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title ="Dé qui roule n'amasse pas mousse";
  typeCompte : string;
  popupBool : boolean = true;
  authentification : AuthDTO = new AuthDTO();
  client : Client;
  admin : Admin;
  connected : boolean=false;
  boutonConnectedPress : boolean=false;

  constructor(protected popupService: PopupService, public router: Router, private authService: AuthService) { }

  isAccueilRoute() {
    if(this.router.url=='/'){
      return true;
    } else if (this.router.url=='/accueil'){
      return true;
    }else { 
      return false;
    }
  }

  connexion() {
    if(this.boutonConnectedPress){
      console.log("entrée if")
      console.log(this.boutonConnectedPress)
      if(this.authentification.mail == "admin@test.fr" ){
        console.log("admin authentification");
        this.popupService.loginAdmin(this.authentification).subscribe(resp => { 
          this.admin = resp;
          this.connected=true;
         this.authService.loginCompte(resp);
          this.popupService.close();
        });
      }
      else{
        console.log("client authentification");
        this.popupService.loginClient(this.authentification).subscribe(resp => { 
          this.client = resp;
          this.connected=true;
          console.log(resp);
          this.authService.loginCompte(resp);
          this.popupService.close();
        });
      }


      this.boutonConnectedPress=false;

    }else{
      console.log("entrée else")
      console.log(this.boutonConnectedPress)
      if(this.authentification.mail == "admin@test.fr" ){
        console.log("admin authentification");
        this.popupService.loginAdmin(this.authentification).subscribe(resp => { 
          this.admin = resp;
          this.connected=true;
       
          this.popupService.close();
        });
      }
      else{
        console.log("client authentification");
        this.popupService.loginClient(this.authentification).subscribe(resp => { 
          this.client = resp;
          this.connected=true;
          console.log(resp);
         
          this.popupService.close();
        });
      }




    }
  }

  deconnexion(){
    if(this.authentification.mail == "admin@test.fr"){
      this.admin = undefined;
      this.connected=false;
      console.log("deconnexion admin");
    } else {
      this.client = undefined;
      this.connected=false;
      console.log("deconnexion client");
    }
    this.authentification.mail = "";
    this.authentification.password = "";
  }

  resaRoute(){
        if(true){
          this.router.navigate(["/reservation"]);
        }
  }


}