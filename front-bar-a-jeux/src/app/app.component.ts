import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title ="Dé qui roule n'amasse pas mousse";
  @Input()
  mailConnexion : string;
  @Input()
  passwordConnexion : string;
  popupBool : boolean = true;
  
  constructor(protected popupService: PopupService) { }


constructor(public router: Router){}

isAccueilRoute() {
if(this.router.url=='/'){
  return true;
}
else if (this.router.url=='/accueil'){
  return true;
}
else {return false;}
  
}
}