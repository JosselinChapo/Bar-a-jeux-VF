import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AchatJeu, CommandeJeu, Filter, Jeu } from '../model';

@Injectable({
  providedIn: 'root'
})
export class JeuService {

  jeux: Array<Jeu> = new Array<Jeu>();
  typeJeux: Array<string>;
  achatJeux : AchatJeu;
  commandeJeu : CommandeJeu;

    constructor(private http : HttpClient) { 
     this.load();
     this.getAllTypeJeu()
    }
  
    findAll(): Array<Jeu> {
      return this.jeux;
    }

    resetServ():void{
      this.load();
    }
  
    findById(id: number): Observable<Jeu> {
      return this.http.get<Jeu>("http://localhost:8888/jeu/"+id);
    }
  
    create(jeu: Jeu): void {
      this.http.post<Jeu>("http://localhost:8888/jeu", jeu).subscribe(resp => {
        this.load();
      });
    }
  
    update(jeu: Jeu): void {
      this.http.put<Jeu>("http://localhost:8888/jeu/" + jeu.id, jeu).subscribe(resp => {
        this.load();
      });
    }
  
    remove(id: number): void {
      this.http.delete<void>("http://localhost:8888/jeu/" + id).subscribe(resp => {
        this.load();
      });
    }
  
    private load(): void {
      this.http.get<Array<Jeu>>("http://localhost:8888/jeu").subscribe(resp => {
        this.jeux = resp;
      });
    }

    findAllByNom(recherche :string): Array<Jeu> {
      return this.jeux.filter(jeu => jeu.nom.toUpperCase().indexOf(recherche.toUpperCase()) != -1);
    }

    getAllTypeJeu():Array<string>{
      this.http.get<Array<string>>("http://localhost:8888/jeu/typeJeu").subscribe(resp => {
        this.typeJeux = resp;
      })
      return this.typeJeux;;
    }

    findAllTypeJeu(): Array<string> {
      return this.typeJeux;
    }

    filterTest(filter: Filter): void {
      this.http.post<Array<Jeu>>("http://localhost:8888/jeu/filtre", filter).subscribe(resp => {
        this.jeux = resp;
      });
    }

    insertCommandeJeu(commandeJeu: CommandeJeu): Observable<CommandeJeu> {
      return this.http.post<CommandeJeu>("http://localhost:8888/commandeJeu", commandeJeu)
    }

    insertAchatJeu(achatJeu: AchatJeu): Observable<AchatJeu> {
      return this.http.post<AchatJeu>("http://localhost:8888/achatJeu", achatJeu)
    }

  }

