import { Component } from '@angular/core';
import { AppComponent } from '../app.component';
import { CommandeJeu } from '../model';
import { CommandeJeuService } from './commande-jeu.service';

@Component({
  selector: 'panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent {
  commandeJeu : CommandeJeu;
  commandeJeux : Array<CommandeJeu> = new Array
  

  constructor(private commandeService: CommandeJeuService, private appComponent : AppComponent) {
  }

  list(): Array<CommandeJeu> {
    return this.commandeService.findAll();
  }

  refreshPanier(){
    this.commandeService.load(this.appComponent.client.id);
  }

  removeCmd(id: number): void {
    this.commandeService.remove(id);
    this.refreshPanier();
  }

  validateCmd():void{
    this.commandeService.commandeJeux.forEach(commandeJeu => { 
      commandeJeu.statut = "Validee";
      this.commandeService.update(commandeJeu);
      this.refreshPanier();
    });
  }
}
