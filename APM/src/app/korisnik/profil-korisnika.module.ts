import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilKorisnikaComponent } from './profil-korisnika.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [ProfilKorisnikaComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([
      { path: 'korisnik', component: ProfilKorisnikaComponent },
      //{ path: 'admin/korisnici', component: ListaKorisnikaComponent },
      //{ path: 'admin/sifrarnici', component: SifrarniciComponent },

    ]),
    FormsModule
  ],
  providers: [

  ]
})
export class ProfilKorisnikaModule { }
