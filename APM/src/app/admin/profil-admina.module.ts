import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilAdminaComponent } from './profil-admina.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AdminService } from './profil-admina.services';
import { ListaKorisnikaComponent } from './lista-korisnika/lista-korisnika.component';
import { SifrarniciComponent } from './sifrarnici/sifrarnici.component';
import { KorisnikService } from './lista-korisnika/korisnici.services';



@NgModule({
  declarations: [ProfilAdminaComponent, ListaKorisnikaComponent, SifrarniciComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([
      { path: 'admin', component: ProfilAdminaComponent },
      { path: 'admin/korisnici', component: ListaKorisnikaComponent },
      { path: 'admin/sifrarnici', component: SifrarniciComponent },
      // { path: 'adminKlinike/lekari', component: LekarComponent },
      // { path: 'adminKlinike/sale', component: SaleComponent },
      // { path: 'adminKlinike/pregledi', component: PreglediComponent },

      // { path: 'adminKlinike/listaZahtevaZaPreglede', component: ListaZahtevaComponent },
      // { path: 'adminKlinike/listaZahtevaZaPreglede/odobrenjeZahteva', component: OdobrenjeZahtevaComponent },

      // { path: 'adminKlinike/zahteviZaOdsustvo', component: ZahteviZaOdsustvoComponent },

      // { path: 'adminKlinike/izvestajiPoslovanjaKlinike', component: IzvestajiPoslovanjaComponent },

    ]),
    FormsModule
  ],
   providers: [
     AdminService, KorisnikService,
   ]
})
export class ProfilAdminaModule { }
