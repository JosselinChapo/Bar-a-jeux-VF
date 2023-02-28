import { Component, Input } from '@angular/core';
import { PopupService } from './popup/popup.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title ="Dé qui roule n'amasse pas mousse";
  visilibity : string;
  @Input()
  mailConnexion : string;
  @Input()
  passwordConnexion : string;
  popupBool : boolean = true;
  
  constructor(protected popupService: PopupService) { }

}
