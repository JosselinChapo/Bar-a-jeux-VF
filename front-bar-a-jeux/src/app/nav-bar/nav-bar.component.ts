import { Component } from '@angular/core';
import { AppComponent } from '../app.component';
import { PopupService } from '../popup/popup.service';

@Component({
  selector: 'nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  constructor(public popupService: PopupService, public appComponent : AppComponent) { }


}
