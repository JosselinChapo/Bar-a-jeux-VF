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
    commandeJeux : Array<CommandeJeu>;
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

    constructor(id?:number, dateRes?:string, heureRes?:string, nbPersonne?:number, tableBar?:TableBar, client?:Client){ //jeu?:Jeu
        this.id = id;
        this.dateRes = dateRes;
        this.heureRes = heureRes;
        this.nbPersonne = nbPersonne;
        this.tableBar = tableBar;
        this.client = client;
      //  this.jeu = jeu;

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
    
    export class Admin extends Compte {
        

        constructor(id?:number, mail?: string, password?:string) {
            super(id, mail, password);
           
        }

}


export class Jeu {

    id: number;
    nom: string;
    nbJoueurMin: number;
    nbJoueurMax : number;
    ageMin : number;
    duree : number;
    editeur : string;
    annee : string;
    prix : number;
    image : string;
    typeJeu : string;
    stock : number;
    description : string;

    constructor(id?:number, nom?: string, nbJoueurMin?: number,nbJoueurMax?: number,ageMin?: number,duree?: number,editeur?: string,annee ?: string,prix?: number,image?: string,typeJeu ?: string,stock?: number,description?: string){
        this.id = id;
        this.nom = nom;
        this.nbJoueurMin = nbJoueurMin;
        this.nbJoueurMax = nbJoueurMax;
        this.ageMin = ageMin;
        this.duree = duree;
        this.editeur = editeur;
        this.annee = annee;
        this.prix = prix;
        this.image = image;
        this.typeJeu = typeJeu;
        this.stock = stock;
        this.description = description;
    }
}


export class AchatJeu {

    id: number;
    dateAchat: string;
    quantite: number;
    
    jeu : Jeu;
    commandeJeu : CommandeJeu;

    constructor(id?:number, dateAchat?: string, quantite?: number){
        this.id = id;
        this.dateAchat = dateAchat;
        this.quantite = quantite;
    }
}

export class CommandeJeu {

    id: number;
    statut: string;
    
    client : Client;
    

    constructor(id?:number, statut?: string){
        this.id = id;
        this.statut = statut;
    }
}