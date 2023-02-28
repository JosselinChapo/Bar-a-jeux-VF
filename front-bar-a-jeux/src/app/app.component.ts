import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title ="Dé qui roule n'amasse pas mousse";

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