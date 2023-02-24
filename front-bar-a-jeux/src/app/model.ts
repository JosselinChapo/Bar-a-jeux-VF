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
    reservations : Array<Reservations>;
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




