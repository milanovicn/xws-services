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
  pom: Vozilo[] = [];

  vozilo: Vozilo;
  prikaziKorpu: boolean = false;
  zahtevi: ZahtevRezervacije[] = [];
  zahteviZaPlacanje: ZahtevRezervacije[] = []
  korisnik: Korisnik;
  tekstKomentara: string;
  ocena: number;
  zahteviZaKomentar: ZahtevRezervacije[] = [];
  zahteviZavrseni: ZahtevRezervacije[] = [];
  zahteviPodneti: ZahtevRezervacije[] = [];
  zahteviPonisteni: ZahtevRezervacije[] = [];
  zahtev: ZahtevRezervacije;

  constructor(private route: ActivatedRoute, private router: Router, private voziloService: VoziloSerivces, private login: KorisnikService, private zahtevService: ZahtevSerivces) {
    this.vozilo = new Vozilo();
    this.korisnik = new Korisnik();
    this.zahtev = new ZahtevRezervacije();

  }



  ngOnInit(): void {

    this.login.getKorisnika().subscribe({
      next: korisnik => {
        this.korisnik = korisnik;
        this.voziloService.vratiZahtevePoPodnosiocu(this.korisnik.id).subscribe({
          next: zahtevi => {
            this.zahtevi = zahtevi;
            console.log(this.zahtevi);
            for (let z of this.zahtevi) {
              if (z.stanje == "RESERVED") {
                this.zahteviZaPlacanje.push(z);
                for (let v of z.vozila) {
                  this.vozila.push(v);
                }
              }
            }

            for (let z of this.zahtevi) {
              if (z.stanje == "PAID") {
                this.zahteviZaKomentar.push(z);
                for (let v of z.vozila) {
                  this.pom.push(v);
                }
                console.log("cao" + this.zahteviZaKomentar);
              }
            }

            for (let z of this.zahtevi) {
              if (z.stanje == "REVIEWED") {
                this.zahteviZavrseni.push(z);
                for (let v of z.vozila) {
                  this.pom.push(v);
                }
                console.log("cao" + this.zahteviZavrseni);
              }
            }

            for (let z of this.zahtevi) {
              if (z.stanje == "PENDING") {
                this.zahteviPodneti.push(z);
                for (let v of z.vozila) {
                  this.pom.push(v);
                }
                console.log("cao" + this.zahteviPodneti);
              }
            }

            for (let z of this.zahtevi) {
              if (z.stanje == "CANCELED") {
                this.zahteviPonisteni.push(z);
                for (let v of z.vozila) {
                  this.pom.push(v);
                }
                console.log("cao" + this.zahteviPonisteni);
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
    console.log("cao" + this.zahteviZaKomentar);

  }

  plati(zahtev: ZahtevRezervacije) {
    this.voziloService.platiZahtev(zahtev).subscribe();
  }
  otkazi(zahtev: ZahtevRezervacije) {
    this.voziloService.otkaziZahtev(zahtev).subscribe();
  }

  ostaviKomentar(zahtev: ZahtevRezervacije) {
    //promena statusa zahteva u zahtev service
    // this.zahtevService.komentarisi(zahtev).subscribe({
    // next:zahtev=>{this.zahtev=zahtev;
    for (let v of zahtev.vozila) {
      this.voziloService.kreirajKomentar(this.tekstKomentara, v.id).subscribe();
      // }
      // });
      //dodavanje komentara za vozilo u vozilo service
    }

    this.voziloService.komentarisi(zahtev).subscribe();

  }
  oceni(zahtev: ZahtevRezervacije) {
    //promena statusa zahteva u zahtev service
    // this.zahtevService.komentarisi(zahtev).subscribe({
    // next:zahtev=>{this.zahtev=zahtev;
    for (let v of zahtev.vozila) {
      this.voziloService.oceni(this.ocena, v.id).subscribe();
      // }
      // });
      //dodavanje komentara za vozilo u vozilo service
    }
  }



}