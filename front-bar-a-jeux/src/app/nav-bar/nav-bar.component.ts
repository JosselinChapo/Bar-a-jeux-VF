import { Component } from '@angular/core';
import { AuthentificationService } from '../authentification/authentification.service';

@Component({
  selector: 'nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  constructor(protected modalService: AuthentificationService) { }


}
