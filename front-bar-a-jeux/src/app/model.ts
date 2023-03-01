export abstract class Compte {
    id : number;
    mail : string;
    password : string;
    type : string;

    constructor(id?:number, mail?: string, password?:string, type? : string){
        this.id = id; 
        this.mail = mail;
        this.password = password;
        this.type = type;
    } 
}

export class Client extends Compte {
    nom: string;
    prenom: string;
    tel : string;
    dateNaissance : string;
    // commandeJeux : Array<CommandeJeux>;
    civilite : string;

    constructor(id?:number, mail?: string, password?:string, type? : string, nom?: string, prenom?: string, tel?: string, dateNaissance?: string, civilite?: string) {
        super(id, mail, password, type);
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.dateNaissance = dateNaissance;
        this.civilite = civilite;
    }
}

export class AuthDTO {
    mail: string;
    password: string;
  
    constructor(mail?: string, password?: string) {
      this.mail = mail;
      this.password = password;
    }

    isValide():boolean{
        return(Boolean(this.mail) && Boolean(this.password));
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

    constructor(id?:number, dateRes?:string, heureRes?:string, nbPersonne?:number, tableBar?:TableBar, client?:Client, jeu?:Jeu){ //
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
    
export class Admin extends Compte {
        
    constructor(id?:number, mail?: string, password?:string, type? : string) {
        super(id, mail, password, type);  
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

export class Filter {

    nbJoueur : number;
    dureeMin : number;
    dureeMax : number;
    typeJeu : string;

    constructor(nbJoueur?:number,dureeMin?: number,dureeMax?: number, typeJeu?: string){
        this.nbJoueur = nbJoueur;
        this.dureeMin = dureeMin;
        this.dureeMax = dureeMax;
        this.typeJeu = typeJeu;
    }

}

export class Conso {

    id: number; 
    nom : String;
    prix: number;
   
    

    constructor(id?:number, nom?: string, prix?: number){
        this.id = id;
        this.nom= nom;
        this.prix = prix;
        
    }
}

