import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfilAdminaComponent } from './profil-admina.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [ProfilAdminaComponent],
  imports: [
    CommonModule,
    RouterModule.forChild([
      { path: 'adminKlinike', component: ProfilAdminaComponent },
      // { path: 'adminKlinike/profilKlinike', component: ProfilKlinikeComponent },
      // { path: 'adminKlinike/tipoviPregleda', component: TipoviPregledaComponent },
      // { path: 'adminKlinike/lekari', component: LekarComponent },
      // { path: 'adminKlinike/sale', component: SaleComponent },
      // { path: 'adminKlinike/pregledi', component: PreglediComponent },

      // { path: 'adminKlinike/listaZahtevaZaPreglede', component: ListaZahtevaComponent },
      // { path: 'adminKlinike/listaZahtevaZaPreglede/odobrenjeZahteva', component: OdobrenjeZahtevaComponent },

      // { path: 'adminKlinike/zahteviZaOdsustvo', component: ZahteviZaOdsustvoComponent },

      // { path: 'adminKlinike/izvestajiPoslovanjaKlinike', component: IzvestajiPoslovanjaComponent },

    ]),
    FormsModule
  ]
})
export class ProfilAdminaModule { }
