import { Component, NgModule, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Vozilo } from '../vozilo/Vozilo';
import { VoziloSerivces } from '../vozilo/vozilo.services';

import { Korisnik } from 'src/app/login/Korisnik';
import { KorisnikService } from 'src/app/admin/lista-korisnika/korisnici.services';
import { ZahtevRezervacije } from '../search-oglasi/ZahtevRezervacije';
import { ZahtevSerivces } from '../search-oglasi/zahtev.service';









@Component({

  templateUrl: './moji-zahtevi.html'

})

export class MojiZahteviComponent implements OnInit {

  vozila: Vozilo[] = [];

  vozilo: Vozilo;
  prikaziKorpu: boolean = false;
  zahtevi: ZahtevRezervacije[] = [];
  zahteviZaPlacanje: ZahtevRezervacije[] = []
  korisnik: Korisnik;
  tekstKomentara: string;
  zahteviZaKomentar: ZahtevRezervacije[] = [];
  zahtev:ZahtevRezervacije;

  constructor(private route: ActivatedRoute, private router: Router, private voziloService: VoziloSerivces, private login: KorisnikService, private zahtevService: ZahtevSerivces) {
    this.vozilo = new Vozilo();
    this.korisnik = new Korisnik();
    this.zahtev=new ZahtevRezervacije();

  }



  ngOnInit(): void {

    this.login.getKorisnika().subscribe({
      next: korisnik => {
        this.korisnik = korisnik;
        this.zahtevService.vratiZahtevePoPodnosiocu(this.korisnik.id).subscribe({
          next: zahtevi => {
            this.zahtevi = zahtevi;
             console.log(this.zahtevi);
            for (let z of this.zahtevi){
              if (z.stanje == "RESERVED") {
                this.zahteviZaPlacanje.push(z);
                
              }
              }

            for (let z of this.zahtevi){
              if (z.stanje == "PAID") {
                this.zahteviZaKomentar.push(z);
                console.log("cao" +this.zahteviZaKomentar);
              }
              }
          }
        });

       /* this.zahtevService.vratiZahtevePoPodnosiocu(this.korisnik.id).subscribe({
          next: zahtevi => {
            this.zahtevi = zahtevi;
            for (let z of this.zahtevi)
              if (z.stanje == "WAITING_REVIEW") {
                this.zahteviZaKomentar.push(z);
                console.log("cao" +this.zahteviZaKomentar);

              }
          }
        });*/


      }

    })

    console.log(this.zahteviZaPlacanje);
    console.log("cao" +this.zahteviZaKomentar);

  }

  plati(zahtev: ZahtevRezervacije) {
    this.zahtevService.platiZahtev(zahtev).subscribe();
  }

  ostaviKomentar(zahtev: ZahtevRezervacije) {
    //promena statusa zahteva u zahtev service
   // this.zahtevService.komentarisi(zahtev).subscribe({
     // next:zahtev=>{this.zahtev=zahtev;
        this.voziloService.kreirajKomentar(this.tekstKomentara, zahtev.idVozila).subscribe();
     // }
   // });
    //dodavanje komentara za vozilo u vozilo service
    
  }



}