import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AccueilComponent } from './accueil/accueil.component';
import { CollectionComponent } from './collection/collection.component';
import { ReservationComponent } from './reservation/reservation.component';
import { ClientComponent } from './client/client.component';
import { AdminComponent } from './admin/admin.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { HttpClientModule }from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CustomDatePipe } from './reservation/custom-date-pipe.pipe';
import { InscriptionComponent } from './inscription/inscription.component';
import { BanniereAccueilComponent } from './banniere-accueil/banniere-accueil.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    AccueilComponent,
    CollectionComponent,
    ReservationComponent,
    ClientComponent,
    AdminComponent,
    AppComponent,
    NavBarComponent,
    CustomDatePipe,
    InscriptionComponent,
    BanniereAccueilComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
