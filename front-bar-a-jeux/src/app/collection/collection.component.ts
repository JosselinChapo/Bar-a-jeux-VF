import { Component } from '@angular/core';
import { Jeu } from '../model';
import { JeuService } from './jeu.service';

@Component({
  selector: 'app-collection',
  templateUrl: './collection.component.html',
  styleUrls: ['./collection.component.css']
})
export class CollectionComponent {
  recherche:string;
  formJeu: Jeu = null;

  constructor(private jeuService: JeuService) {
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

  removeJeu(id: number): void {
    this.jeuService.remove(id);
  }

  cancelJeu(): void {
    this.formJeu = null;
  }
}
