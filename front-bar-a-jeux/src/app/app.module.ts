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
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule} from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BanniereAccueilComponent } from './banniere-accueil/banniere-accueil.component';
import { FooterComponent } from './footer/footer.component';
import { NgxSliderModule } from '@angular-slider/ngx-slider';
import { PopupComponent } from './popup/popup.component';
import { PanierComponent } from './panier/panier.component';


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
    PopupComponent,
    BanniereAccueilComponent,
    FooterComponent, 
    InscriptionComponent, PanierComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    NgxSliderModule,
    NgbModule,
    MatInputModule 
  ],
  providers: [],
  bootstrap: [AppComponent],
  
})
export class AppModule { }
