import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AuthDTO } from './model';
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
  
  constructor(protected popupService: PopupService, public router: Router) { }


isAccueilRoute() {
if(this.router.url=='/'){
  return true;
}
else if (this.router.url=='/accueil'){
  return true;
}
else {return false;}
  
}

toto() {
  console.log(this.authentification);
}
}