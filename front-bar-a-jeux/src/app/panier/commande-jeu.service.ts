import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppComponent } from '../app.component';
import { CommandeJeu } from '../model';

@Injectable({
  providedIn: 'root'
})
export class CommandeJeuService {
  commandeJeux : Array<CommandeJeu>

  constructor(private http: HttpClient) {
  
   }




  load(id : number): void {
    
    if(id == undefined){
      let cmdJeuInit =  new CommandeJeu(3,"test");
      this.commandeJeux.push(cmdJeuInit);
    }else{
    this.http.get<Array<CommandeJeu>>("http://localhost:8888/commandeJeu/client/" + id).subscribe(resp => {
      this.commandeJeux = resp;
    });
  }
  }


  findAll(): Array<CommandeJeu> {
    return this.commandeJeux;
  }

  findByIdClient(id: number): Observable<Array<CommandeJeu>> {
    return this.http.get<Array<CommandeJeu>>("http://localhost:8888/commandeJeu/client/" + id);
  }

  update(commandeJeu: CommandeJeu): void {
    this.http.put<CommandeJeu>("http://localhost:8888/commandeJeu/" + commandeJeu.id, commandeJeu).subscribe(resp => {
    });
  }

  remove(id: number): void {
    this.http.delete<void>("http://localhost:8888/commandeJeu/" + id).subscribe(resp => {
    });
  }
}
