import { Component, ElementRef, Input, OnDestroy, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { AuthentificationService } from './authentification.service';

@Component({
  selector: 'app-authentification',
  templateUrl: './authentification.component.html',
  styleUrls: ['./authentification.component.css']
})
export class AuthentificationComponent implements OnInit, OnDestroy {
  @Input() id?: string;
  isOpen = false;
  private element: any;

  constructor(private modalService: AuthentificationService, private el: ElementRef, private appService : AppComponent) {
      this.element = el.nativeElement;
  }

  ngOnInit() {
      // add self (this modal instance) to the modal service so it can be opened from any component
      this.modalService.add(this);

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
      this.modalService.remove(this);

      // remove modal element from html
      this.element.remove();
  }

  open() {
      this.element.style.display = 'block';
      document.body.classList.add('modal-open');
      this.isOpen = true;
  }

  close() {
      this.element.style.display = 'none';
      document.body.classList.remove('modal-open');
      this.isOpen = false;
  }
}
