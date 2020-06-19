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
import { ListaKomentaraComponent } from './komentari/lista-komentara.component';
import { KomentariService } from './komentari/komentari.services';

@NgModule({
  declarations: [ProfilAdminaComponent, ListaKorisnikaComponent, SifrarniciComponent, ListaKomentaraComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([
      { path: 'admin', component: ProfilAdminaComponent },
      { path: 'admin/korisnici', component: ListaKorisnikaComponent },
      { path: 'admin/sifrarnici', component: SifrarniciComponent },
      { path: 'admin/komentari', component: ListaKomentaraComponent }
    ]),
    FormsModule
  ],
   providers: [
     AdminService, KorisnikService, SifrarnikService, KomentariService
   ]
})
export class ProfilAdminaModule { }
