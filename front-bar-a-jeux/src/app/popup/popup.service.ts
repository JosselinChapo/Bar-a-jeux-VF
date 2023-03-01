import { Injectable } from '@angular/core';
import { PopupComponent } from './popup.component';

@Injectable({
  providedIn: 'root'
})
export class PopupService {

  private popup: PopupComponent[] = [];
  
  constructor() { }
  
    add(modal: PopupComponent) {
      // ensure component has a unique id attribute
      if (!modal.id || this.popup.find(x => x.id === modal.id)) {
          throw new Error('modal must have a unique id attribute');
      }
  
      // add modal to array of active modals
      this.popup.push(modal);
  }
  
  remove(modal: PopupComponent) {
      // remove modal from array of active modals
      this.popup = this.popup.filter(x => x === modal);
  }
  
  open(id: string) {
      // open modal specified by id
      const modal = this.popup.find(x => x.id === id);
  
      if (!modal) {
          throw new Error(`modal '${id}' not found`);
      }
  
      modal.open();
  }
  
  close() {
      // close the modal that is currently open
      const modal = this.popup.find(x => x.isOpen);
      modal?.close();
  }
}
