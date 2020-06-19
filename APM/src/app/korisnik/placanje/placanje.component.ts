import { Component, NgModule, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Vozilo } from '../vozilo/Vozilo';
import { VoziloSerivces } from '../vozilo/vozilo.services';

import { Korisnik } from 'src/app/login/Korisnik';
import { KorisnikService } from 'src/app/admin/lista-korisnika/korisnici.services';
import { ZahtevRezervacije } from '../search-oglasi/ZahtevRezervacije';
import { ZahtevSerivces } from '../search-oglasi/zahtev.service';









@Component({

  templateUrl: './placanje.html'

})

export class PlacanjeComponent implements OnInit {

  vozila: Vozilo[] = [];

  vozilo: Vozilo;
  prikaziKorpu: boolean = false;
  zahtevi: ZahtevRezervacije[] = [];
  zahteviZaPlacanje: ZahtevRezervacije[] = [];
  zahtev: ZahtevRezervacije;
  korisnik: Korisnik;
  tekstKomentara: string;
  zahteviZaKomentar: ZahtevRezervacije[] = [];

  constructor(private route: ActivatedRoute, private router: Router, private voziloService: VoziloSerivces, private login: KorisnikService, private zahtevService: ZahtevSerivces) {
    this.vozilo = new Vozilo();
    this.zahtev = new ZahtevRezervacije();
    this.korisnik = new Korisnik();

  }



  ngOnInit(): void {

    this.login.getKorisnika().subscribe({
      next: korisnik => {
        this.korisnik = korisnik;
        this.zahtevService.vratiZahtevePoPodnosiocu(this.korisnik.id).subscribe({
          next: zahtevi => {
            this.zahtevi = zahtevi;
            for (let z of this.zahtevi)
              if (z.stanje == "RESERVED") {
                this.zahteviZaPlacanje.push(z);
                console.log(this.zahteviZaPlacanje);

              }
          }
        });

        this.zahtevService.vratiZahtevePoPodnosiocu(this.korisnik.id).subscribe({
          next: zahtevi => {
            this.zahtevi = zahtevi;
            for (let z of this.zahtevi)
              if (z.stanje == "WAITING_REVIEW") {
                this.zahteviZaKomentar.push(z);
                console.log(this.zahteviZaKomentar);

              }
          }
        });


      }

    });



  }

  plati(zahtev: ZahtevRezervacije) {
    this.zahtevService.platiZahtev(zahtev).subscribe();
  }

  ostaviKomentar(zahtev: ZahtevRezervacije) {
    //promena statusa zahteva u zahtev service
    this.zahtevService.komentarisi(zahtev).subscribe();
    //dodavanje komentara za vozilo u vozilo service
    this.voziloService.kreirajKomentar(this.tekstKomentara, zahtev.idVozila);
  }



}