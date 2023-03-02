import { Component, ElementRef, Input, OnDestroy, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { AuthDTO } from '../model';
import { PopupService } from './popup.service';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css']
})

export class PopupComponent implements OnInit, OnDestroy {

  @Input() id?: string;
  isOpen = false;
  private element: any;
  typeCompte : string;
  authentification : AuthDTO = new AuthDTO();
  erreur : boolean = false;

  constructor(private popupService: PopupService, private el: ElementRef, private appService : AppComponent) {
      this.element = el.nativeElement;
  }

  ngOnInit() {
      // add self (this modal instance) to the modal service so it can be opened from any component
      this.popupService.add(this);

      // move element to bottom of page (just before </body>) so it can be displayed above everything else
      document.body.appendChild(this.element);

      // close modal on background click
      this.element.addEventListener('click', (el: any) => {
          if (el.target.className === 'modal') {
              this.close();
          }
      });
  }

  ngOnDestroy() {
      // remove self from modal service
      this.popupService.remove(this);

      // remove modal element from html
      this.element.remove();
  }

  open() {
      this.element.style.display = 'block';
      document.body.classList.add('modal-open');
      this.isOpen = true;
      this.appService.popupBool = false;
  }

  close() {
      this.element.style.display = 'none';
      document.body.classList.remove('modal-open');
      this.isOpen = false;  
      this.appService.boutonConnectedPress = false;
  }

  login( ){
    if(this.typeCompte && this.authentification.isValide()){
      if(this.typeCompte == "admin"){
        this.popupService.loginAdmin(this.authentification);
      }else if(this.typeCompte == "client"){
        this.popupService.loginClient(this.authentification);
      }
    }else{
    this.erreur = true;
    }
  }


}
