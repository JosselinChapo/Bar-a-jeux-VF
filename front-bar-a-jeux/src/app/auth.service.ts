import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Compte } from './model';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  private compteConnecte : Compte = undefined;

  constructor( private router: Router) { }

  loginCompte(compteToConnect : Compte){
    if(compteToConnect){
      if(compteToConnect.type){
        this.compteConnecte = compteToConnect;
        if(compteToConnect.type == "client"){
          this.router.navigate(["/client"]);
        }else
        if(compteToConnect.type == "admin"){
          this.router.navigate(["/admin"])
        }
      }
    } 
  }

getCompte():Compte{
  console.log(this.compteConnecte);
  return this.compteConnecte;
}

disconnectCompte(){
  this.compteConnecte = undefined;
  this.router.navigate(["/accueil"]);
}

}