import { Options } from '@angular-slider/ngx-slider';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AchatJeu, Client, CommandeJeu, Filter, Jeu } from '../model';
import { JeuService } from './jeu.service';

import { ClientService } from '../client/client.service';
@Component({
  selector: 'app-collection',
  templateUrl: './collection.component.html',
  styleUrls: ['./collection.component.css']
})
export class CollectionComponent {

  typeJeux: Array<string>
  formFilter : Filter;
  nombreJoueur:number = 0;
  typeDeJeuDefault : string = "Type de jeu";
  minValue: number = 0;
  maxValue: number = 60;
  minPrix: number = 0;
  maxPrix: number = 100;
  options: Options = {
    floor: 0,
    ceil: 90,
    step: 5
  };
  options2: Options = {
    floor: 0,
    ceil: 100,
    step: 5
  };

  achatJeu : AchatJeu = new AchatJeu();
  commandeJeu : CommandeJeu = new CommandeJeu(1,"");
  client : Client = new Client();
  
  recherche:string;
  formJeu: Jeu = null;
  

  

  constructor(private jeuService: JeuService,public router: Router,  private clientService: ClientService) {
  } 

  search(): Array<Jeu> {
    if(this.recherche) {
      return this.jeuService.findAllByNom(this.recherche);
    } 

    return this.jeuService.findAll();
  }

  listJeux(): Array<Jeu> {
    return this.jeuService.findAll();
  }

  addJeu(): void {
    this.formJeu= new Jeu();
  }

  editJeu(id: number): void {
    this.jeuService.findById(id).subscribe(response => {
      this.formJeu = response;
    });
  }

  saveJeu(): void {
    if(this.formJeu.id) { // UPDATE
      this.jeuService.update(this.formJeu);
    } else { // CREATE
      this.jeuService.create(this.formJeu);
    }

    this.cancelJeu();
  }

  searchJeu(): void{
    if(this.typeDeJeuDefault=="Type de jeu"){
      this.formFilter = new Filter(this.nombreJoueur,this.minValue,this.maxValue,"");
    }else{
    this.formFilter = new Filter(this.nombreJoueur,this.minValue,this.maxValue,this.typeDeJeuDefault);}
    this.jeuService.filterTest(this.formFilter);
  }

  reset():void{
    this.jeuService.resetServ();
    this.nombreJoueur=0;
    this.typeDeJeuDefault = "Type de jeu";
    this.minValue = 0;
    this.maxValue  = 60;
  }

  removeJeu(id: number): void {
    this.jeuService.remove(id);
  }

  cancelJeu(): void {
    this.formJeu = null;
  }

  allTypeJeu(): Array<string>{
    return this.jeuService.findAllTypeJeu();
  }

  isBoutique() {
    if(this.router.url=='/boutique'){
      return true;
    }
    else {return false;}
    }


  addCommande(idJeu : number,idClient : number){
    if(idClient != undefined){
      this.jeuService.findById(idJeu).subscribe(resp => {
        this.achatJeu.jeu = resp;
        this.achatJeu.quantite = 1;
        
        this.clientService.findById(idClient).subscribe(resp => {
          this.commandeJeu.client = resp;
          this.commandeJeu.statut = "EnCours";
          this.achatJeu.commandeJeu = this.commandeJeu;
        });
      });

      }
    }
  }

