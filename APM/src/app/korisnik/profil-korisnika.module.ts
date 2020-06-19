import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilKorisnikaComponent } from './profil-korisnika.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { VoziloComponent } from './vozilo/dodaj-vozilo.component';
import { VoziloSerivces } from './vozilo/vozilo.services';
import { SifrarnikService } from '../admin/sifrarnici/sifrarnici.services';
import { DetaljiOglasaComponent } from './listaKorisnikovihVozila/detaljiOglasa.component';
import { ListaVozilaComponent } from './listaKorisnikovihVozila/listaMojihOglasa.component';
import { KorisnikService } from '../admin/lista-korisnika/korisnici.services';
import { SearchComponent } from './search-oglasi/listaSvihOglasa.component';
import { ZahtevComponent } from './zahtevi/zahtevi.component';
import { ZahtevSerivces } from './search-oglasi/zahtev.service';
import { SearchSerivces } from './search-oglasi/search.service';



@NgModule({
  declarations: [ProfilKorisnikaComponent,VoziloComponent,ListaVozilaComponent,DetaljiOglasaComponent,SearchComponent,ZahtevComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([
      { path: 'korisnik', component: ProfilKorisnikaComponent },
      { path: 'korisnik/vozilo', component: VoziloComponent },
      { path: 'korisnik/mojiOglasi', component: ListaVozilaComponent },
      { path: 'korisnik/search', component: SearchComponent },
      { path: 'korisnik/zahteviZaMene', component: ZahtevComponent },
      {
        path: 'korisnik/mojiOglasi/:id', component: DetaljiOglasaComponent
      },
     

      //{ path: 'admin/korisnici', component: ListaKorisnikaComponent },
      //{ path: 'admin/sifrarnici', component: SifrarniciComponent },

    ]),
    FormsModule
  ],
  providers: [
    KorisnikService,VoziloSerivces,SifrarnikService,ZahtevSerivces,SearchSerivces
  ]
})
export class ProfilKorisnikaModule { }
