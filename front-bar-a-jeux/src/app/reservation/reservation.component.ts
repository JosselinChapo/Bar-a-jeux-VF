import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, Inject, inject, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DateAdapter, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatInput } from '@angular/material/input';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { Reservation, TableBar } from '../model';
import { ReservationService } from './reservation.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css'],
  providers: [DatePipe],
})
export class ReservationComponent {
  formReservation: Reservation = new Reservation();
  tableBar: TableBar;
  myForm : FormGroup;
  idTable: number;

  table1: any = "assets/images/reservation/table4rempli.png";
  table2: any = "assets/images/reservation/table4rempli.png";
  table3: any = "assets/images/reservation/table6rempli.png";
  table4: any = "assets/images/reservation/table8rempli.png";
  table5: any = "assets/images/reservation/table6rempli.png";
  table6: any = "assets/images/reservation/table4rempli.png";
  table7: any = "assets/images/reservation/table4rempli.png";
  table8: any = "assets/images/reservation/table8rempli.png";
  seletedTable: boolean=false;
  selectedNumber: number;

  isconnected: boolean=false;

  constructor(
    private http: HttpClient,
    private reservationService: ReservationService,
    private _adapter: DateAdapter<any>, 
    @Inject(MAT_DATE_LOCALE) private _locale: string,
    public datepipe: DatePipe,
    private appComponent: AppComponent,
    private router: Router
  ) { 
    this._locale = 'fr-FR';
    this._adapter.setLocale(this._locale);
    this.isconnected=this.appComponent.connected;
    this.seletedTable=false;

  }


  list(): Array<Reservation> {
    return this.reservationService.findAll();
  }

  listHeureDisponible(): Array<string> {
    let dateTest: string = this.datepipe.transform(this.formReservation.dateRes, 'yyyy-MM-dd');
    return this.reservationService.findAllHeure(this.formReservation.nbPersonne,dateTest);
  }

  listNbPersonneDisponible(): Array<number> {
    return [4,6,8];
  }

  listTableDisponible(): Array<number> {
    let dateTest: string = this.datepipe.transform(this.formReservation.dateRes, 'yyyy-MM-dd');
    let tablesDiponibles = this.reservationService.findAllTable(this.formReservation.nbPersonne,dateTest,this.formReservation.heureRes);

    let tablesDiponiblesString=tablesDiponibles.map(String);
    if (tablesDiponibles.includes(1) && this.selectedNumber!=1){
      this.table1="assets/images/reservation/table4vide.png";
    }else if(!tablesDiponibles.includes(1) && this.selectedNumber!=1){
      this.table1="assets/images/reservation/table4rempli.png";
    }
    
    if (tablesDiponibles.includes(2) && this.seletedTable==false){
      this.table2="assets/images/reservation/table4vide.png";
    }else if(!tablesDiponibles.includes(2) && this.selectedNumber!=2){
      this.table2="assets/images/reservation/table4rempli.png";
    }
    
    if (tablesDiponibles.includes(3) && this.seletedTable==false){
      this.table3="assets/images/reservation/table6vide.png";
    }else if(!tablesDiponibles.includes(3) && this.selectedNumber!=3){
      this.table3="assets/images/reservation/table6rempli.png";
    }
    
    if (tablesDiponibles.includes(4) && this.seletedTable==false){
      this.table4="assets/images/reservation/table8vide.png";
    }else if(!tablesDiponibles.includes(4) && this.selectedNumber!=4){
      this.table4="assets/images/reservation/table8rempli.png";
    }
    
    if (tablesDiponibles.includes(5) && this.seletedTable==false){
      this.table5="assets/images/reservation/table6vide.png";
    }else if(!tablesDiponibles.includes(5) && this.selectedNumber!=5){
      this.table5="assets/images/reservation/table6rempli.png";
    }
    
    if (tablesDiponibles.includes(6) && this.seletedTable==false){
      this.table6="assets/images/reservation/table4vide.png";
    }else if(!tablesDiponibles.includes(6) && this.selectedNumber!=6){
      this.table6="assets/images/reservation/table4rempli.png";
    }
    
    if (tablesDiponibles.includes(7) && this.seletedTable==false){
      this.table7="assets/images/reservation/table4vide.png";
    }else if(!tablesDiponibles.includes(7) && this.selectedNumber!=7){
      this.table7="assets/images/reservation/table4rempli.png";
    }
    
    if (tablesDiponibles.includes(8) && this.seletedTable==false){
      this.table8="assets/images/reservation/table8vide.png";
    }else if(!tablesDiponibles.includes(8) && this.selectedNumber!=8){
      this.table8="assets/images/reservation/table8rempli.png";
    }

    return tablesDiponibles;
  }

  changeHeure(): void {
    let dateTestHeure: string = this.datepipe.transform(this.formReservation.dateRes, 'yyyy-MM-dd');
    this.reservationService.changeHeure(this.formReservation.nbPersonne,dateTestHeure);
  }

  changeTable(): void {
    let dateTestTable: string = this.datepipe.transform(this.formReservation.dateRes, 'yyyy-MM-dd');
    this.reservationService.changeTable(this.formReservation.nbPersonne,dateTestTable,this.formReservation.heureRes);
  }

  save(): void {
    console.log(this.formReservation)
    if(this.formReservation.id) { // UPDATE
      this.reservationService.update(this.formReservation);
    } else { // CREATE

      this.http.get<TableBar>("http://localhost:8888/tableBar/" + this.idTable).subscribe(resp => {
        this.tableBar = resp;
        this.formReservation.tableBar=this.tableBar;
        this.formReservation.client=this.appComponent.client;
        console.log(this.formReservation);
        this.reservationService.loadTables(undefined,undefined,undefined);
        this.reservationService.loadHeures(undefined,undefined);
        this.reservationService.create(this.formReservation);
        this.resetForm();
        this.formReservation.nbPersonne=undefined;
        this.router.navigate(["/client"]);
      });
    }
  }

  remove(id: number): void {
    this.reservationService.remove(id);
  }

  myFilter = (d: Date | null): boolean => {
    const day = (d || new Date()).getDay();
    // Prevent Saturday and Sunday from being selected.
    let testDates: Array<Date>;

    if (!this.formReservation.nbPersonne){
      testDates= this.reservationService.findDateDisable(0);
    }else{
      testDates= this.reservationService.findDateDisable(this.formReservation.nbPersonne);
    }
    let index: number=-1;
    if (testDates == undefined) {
      return day !== 0 && day !== 1;
    }else {
      return day !== 0 && day !== 1 && testDates.findIndex(testDate => this.datepipe.transform(d, 'yyyy-MM-dd') == this.datepipe.transform(testDate, 'yyyy-MM-dd')) <0;
    };
  };

  @ViewChild('fromInput', {read: MatInput})fromInput: MatInput;

  resetForm() {
    this.fromInput.value='';
    this.formReservation.heureRes=undefined;
    this.idTable=undefined;
    this.formReservation.dateRes=undefined;
    this.seletedTable=false;
    this.selectedNumber=undefined;
  }

  resetHeure(){
    
  }

  test(event: any): void {
    console.log(event);
    let event1: Date;
    // let dateForm: string = event.toISOString();
    // console.log(dateForm);
    // let dateForm1: string = event.toString();
    // console.log(dateForm1);
    let dateForm2 =this.datepipe.transform(event, 'yyyy-MM-dd');
    console.log(dateForm2);

    this.formReservation.dateRes=dateForm2;
    this.reservationService.changeHeure(this.formReservation.nbPersonne,dateForm2);
    this.reservationService.changeTable(this.formReservation.nbPersonne,dateForm2,this.formReservation.heureRes);

  } 
  test2(): void {
    this.myFilter;

  } 

  changeImage(idImage: number) {

    if (idImage==1 && this.table1!="assets/images/reservation/table4rempli.png"){
      if (this.table1=="assets/images/reservation/table4select.png"){
        this.table1="assets/images/reservation/table4vide.png";
        this.seletedTable=false;
        this.selectedNumber=undefined;
      }else {
        if(this.seletedTable==true){
          this.changeImage(this.selectedNumber);
        }
        this.seletedTable=true;
        this.selectedNumber=idImage;
        this.idTable=1;
        this.table1="assets/images/reservation/table4select.png";
      }
    }
    else if(idImage==2 && this.table2!="assets/images/reservation/table4rempli.png"){
      if (this.table2=="assets/images/reservation/table4select.png"){
        this.table2="assets/images/reservation/table4vide.png";
        this.seletedTable=false;
        this.selectedNumber=undefined;
      }else {
        if(this.seletedTable==true){
          this.changeImage(this.selectedNumber);
        }
        this.seletedTable=true;
        this.selectedNumber=idImage;
        this.idTable=2;
        this.table2="assets/images/reservation/table4select.png";
      }
    }
    else if(idImage==3 && this.table3!="assets/images/reservation/table6rempli.png"){
      if (this.table3=="assets/images/reservation/table6select.png"){
        this.table3="assets/images/reservation/table6vide.png";
        this.seletedTable=false;
        this.selectedNumber=undefined;
      }else {
        if(this.seletedTable==true){
          this.changeImage(this.selectedNumber);
        }
        this.seletedTable=true;
        this.selectedNumber=idImage;
        this.idTable=3;
        this.table3="assets/images/reservation/table6select.png";
      }
    }
    else if(idImage==4 && this.table4!="assets/images/reservation/table8rempli.png"){
      if (this.table4=="assets/images/reservation/table8select.png"){
        this.table4="assets/images/reservation/table8vide.png";
        this.seletedTable=false;
        this.selectedNumber=undefined;
      }else {
        if(this.seletedTable==true){
          this.changeImage(this.selectedNumber);
        }
        this.seletedTable=true;
        this.selectedNumber=idImage;
        this.idTable=4;
        this.table4="assets/images/reservation/table8select.png";
      }
    }
    else if(idImage==5 && this.table5!="assets/images/reservation/table6rempli.png"){
      if (this.table5=="assets/images/reservation/table6select.png"){
        this.table5="assets/images/reservation/table6vide.png";
        this.seletedTable=false;
        this.selectedNumber=undefined;
      }else {
        if(this.seletedTable==true){
          this.changeImage(this.selectedNumber);
        }
        this.seletedTable=true;
        this.selectedNumber=idImage;
        this.idTable=5;
        this.table5="assets/images/reservation/table6select.png";
      }
    }
    else if(idImage==6 && this.table6!="assets/images/reservation/table4rempli.png"){
      if (this.table6=="assets/images/reservation/table4select.png"){
        this.table6="assets/images/reservation/table4vide.png";
        this.seletedTable=false;
        this.selectedNumber=undefined;
      }else {
        if(this.seletedTable==true){
          this.changeImage(this.selectedNumber);
        }
        this.seletedTable=true;
        this.selectedNumber=idImage;
        this.idTable=6;
        this.table6="assets/images/reservation/table4select.png";
      }
    }
    else if(idImage==7 && this.table7!="assets/images/reservation/table4rempli.png"){
      if (this.table7=="assets/images/reservation/table4select.png"){
        this.table7="assets/images/reservation/table4vide.png";
        this.seletedTable=false;
        this.selectedNumber=undefined;
      }else {
        if(this.seletedTable==true){
          this.changeImage(this.selectedNumber);
        }
        this.seletedTable=true;
        this.selectedNumber=idImage;
        this.idTable=7;
        this.table7="assets/images/reservation/table4select.png";
      }
    }
    else if(idImage==8 && this.table8!="assets/images/reservation/table8rempli.png"){
      if (this.table8=="assets/images/reservation/table8select.png"){
        this.table8="assets/images/reservation/table8vide.png";
        this.seletedTable=false;
        this.selectedNumber=undefined;
      }else {
        if(this.seletedTable==true){
          this.changeImage(this.selectedNumber);
        }
        this.seletedTable=true;
        this.selectedNumber=idImage;
        this.idTable=8;
        this.table8="assets/images/reservation/table8select.png";
      }
    }

  }


}
