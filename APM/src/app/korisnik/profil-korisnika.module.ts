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
import { HttpClientModule } from '@angular/common/http';


import { CenovnikComponent } from './cenovnik/cenovnik.component';
import { NoviCenovnikComponent } from './cenovnik/novi-cenovnik.component';
import { CenovnikService } from './cenovnik/cenovnik.service';
import { ListaCenovnikaComponent } from './cenovnik/lista-cenovnika.component';
import { MojiZahteviComponent } from './moji-zahtevi/moji-zahtevi.component';
import { ListaMojihChatovaComponent } from './chat/lista-mojih-chatova.component';
import { ChatService } from './chat/chat.service';
import { ChatComponent } from './chat/chat.component';


@NgModule({
  declarations: [ProfilKorisnikaComponent,
    VoziloComponent,
    ListaVozilaComponent,
    DetaljiOglasaComponent,
    SearchComponent,
    ZahtevComponent, 
    CenovnikComponent,
    NoviCenovnikComponent,
    ListaCenovnikaComponent,
    MojiZahteviComponent,
    ListaMojihChatovaComponent,
    ChatComponent],

  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,

    RouterModule.forChild([
      { path: 'korisnik', component: ProfilKorisnikaComponent },
      { path: 'korisnik/vozilo', component: VoziloComponent },
      { path: 'korisnik/mojiOglasi', component: ListaVozilaComponent },
      { path: 'korisnik/search', component: SearchComponent },
      { path: 'korisnik/zahteviZaMene', component: ZahtevComponent },
      { path: 'korisnik/placanje', component: MojiZahteviComponent },
      { path: 'korisnik/novi-cenovnik', component: NoviCenovnikComponent },
      { path: 'korisnik/cenovnik/:id', component: CenovnikComponent },
      { path: 'korisnik/cenovnik', component: ListaCenovnikaComponent },
      { path: 'korisnik/mojiOglasi/:id', component: DetaljiOglasaComponent },
      { path: 'korisnik/chat/:id', component: ChatComponent },
      { path: 'korisnik/chat', component: ListaMojihChatovaComponent }
     

      //{ path: 'admin/korisnici', component: ListaKorisnikaComponent },
      //{ path: 'admin/sifrarnici', component: SifrarniciComponent },

    ]),
    FormsModule
  ],
  providers: [
    KorisnikService,
    VoziloSerivces,
    SifrarnikService,
    ZahtevSerivces,
    SearchSerivces,
    CenovnikService,
    ChatService
  ]
})
export class ProfilKorisnikaModule { }
