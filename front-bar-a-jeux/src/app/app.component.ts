import { Component } from '@angular/core';
import { AuthentificationService } from './authentification/authentification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title ="Dé qui roule n'amasse pas mousse";
  visilibity : string;
  
  constructor(protected modalService: AuthentificationService) { }



}
