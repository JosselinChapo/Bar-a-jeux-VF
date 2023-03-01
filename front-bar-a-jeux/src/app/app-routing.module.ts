import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { AdminComponent } from './admin/admin.component';
import { ClientComponent } from './client/client.component';
import { CollectionComponent } from './collection/collection.component';
import { ConsoComponent } from './conso/conso.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { ReservationComponent } from './reservation/reservation.component';

const routes: Routes = [
  {path: "", component: AccueilComponent, pathMatch: 'full'},
  {path: "accueil", component: AccueilComponent},
  {path: "reservation", component: ReservationComponent},
  {path: "collection", component: CollectionComponent},
  {path: "client", component: ClientComponent},
  {path: "admin", component: AdminComponent}, 
  {path: "inscription", component: InscriptionComponent},
  {path: "boutique", component: CollectionComponent},
  {path: "conso",component:ConsoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
