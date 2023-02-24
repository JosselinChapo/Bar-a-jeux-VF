export abstract class Compte {
    id : number;
    mail : string;
    password : string;

    constructor(id?:number, mail?: string, password?:string){
        this.id = id; 
        this.mail = mail;
        this.password = password;
    } 
}

export class Client extends Compte {
    nom: string;
    prenom: string;
    tel : string;
    dateNaissance : string;
    commandeJeux : Array<CommandeJeux>;
    civilite : string;

    constructor(id?:number, mail?: string, password?:string, nom?: string, prenom?: string, tel?: string, dateNaissance?: string, civilite?: string) {
        super(id, mail, password);
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.dateNaissance = dateNaissance;
        this.civilite = civilite;
    }
}

export class Reservation {
    id: number;
    dateRes: string;
    heureRes: string; 
    nbPersonne: number;
    tableBar: TableBar;
    client: Client;
    jeu: Jeu;

    constructor(id?:number, dateRes?:string, heureRes?:string, nbPersonne?:number, tableBar?:TableBar, client?:Client, jeu?:Jeu){
        this.id = id;
        this.dateRes = dateRes;
        this.heureRes = heureRes;
        this.nbPersonne = nbPersonne;
        this.tableBar = tableBar;
        this.client = client;
        this.jeu = jeu;

    }

}

export class TableBar {

    id: number;
    nbPersonne: number;
    idTable: number;

    constructor(id?:number, nbPersonne?:number, idTable?:number){
        this.id = id;
        this.nbPersonne = nbPersonne;
        this.idTable = idTable;
    }

}




