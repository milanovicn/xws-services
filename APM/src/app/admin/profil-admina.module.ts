import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilAdminaComponent } from './profil-admina.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AdminService } from './profil-admina.services';
import { ListaKorisnikaComponent } from './lista-korisnika/lista-korisnika.component';
import { SifrarniciComponent } from './sifrarnici/sifrarnici.component';
import { KorisnikService } from './lista-korisnika/korisnici.services';
import { SifrarnikService } from './sifrarnici/sifrarnici.services';



@NgModule({
  declarations: [ProfilAdminaComponent, ListaKorisnikaComponent, SifrarniciComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([
      { path: 'admin', component: ProfilAdminaComponent },
      { path: 'admin/korisnici', component: ListaKorisnikaComponent },
      { path: 'admin/sifrarnici', component: SifrarniciComponent },

    ]),
    FormsModule
  ],
   providers: [
     AdminService, KorisnikService, SifrarnikService,
   ]
})
export class ProfilAdminaModule { }
