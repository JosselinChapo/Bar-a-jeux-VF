import { Component } from '@angular/core';
import { Admin, AuthDTO } from '../model';
import { SeConnecterService } from './se-connecter.service';


@Component({
  selector: 'app-se-connecter',
  templateUrl: './se-connecter.component.html',
  styleUrls: ['./se-connecter.component.css']
})
export class SeConnecterComponent {

  typeCompte : string;
  authentification : AuthDTO = new AuthDTO();
  erreur : boolean = false;

  constructor(private  connectionService: SeConnecterService){

  }

login( ){
  if(this.typeCompte && this.authentification.isValide()){

    if(this.typeCompte == "admin"){

      this.connectionService.loginAdmin(this.authentification);

    }else if(this.typeCompte == "client"){

      this.connectionService.loginClient(this.authentification);

    }
  }else{
   this.erreur = true;
    
  }

}


}