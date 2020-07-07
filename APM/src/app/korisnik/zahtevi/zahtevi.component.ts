import { Component, NgModule, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Vozilo } from '../vozilo/Vozilo';
import { VoziloSerivces } from '../vozilo/vozilo.services';

import { Korisnik } from 'src/app/login/Korisnik';
import { KorisnikService } from 'src/app/admin/lista-korisnika/korisnici.services';
import { ZahtevRezervacije } from '../search-oglasi/ZahtevRezervacije';
import { ZahtevSerivces } from '../search-oglasi/zahtev.service';


import { ChatService } from 'src/app/korisnik/chat/chat.service';
import { Chat } from '../chat/Chat';


@Component({

  templateUrl: './zahtevi.html'

})

export class ZahtevComponent implements OnInit {

  vozila: Vozilo[] = [];
  korpa: Vozilo[] = [];
  vozilo: Vozilo;
  prikaziKorpu: boolean = false;
  zahtevi: ZahtevRezervacije[] = [];
  zahtev: ZahtevRezervacije;
  korisnik: Korisnik;

  podnosilac: Korisnik;
  korisnici: Korisnik[] = [];


  constructor(private route: ActivatedRoute, private router: Router, private voziloService: VoziloSerivces, 
    private login: KorisnikService, private zahtevService: ZahtevSerivces, private chatService:ChatService) {
    this.vozilo = new Vozilo();
    this.zahtev = new ZahtevRezervacije();
    this.korisnik = new Korisnik();
    this.podnosilac = new Korisnik();
  }


      
 

  ngOnInit(): void {

    this.login.getKorisnika().subscribe({
      next: korisnik => {
        this.korisnik = korisnik;
        this.voziloService.vratiZahtevePoKorisnikuMail(this.korisnik.email).subscribe(
          zahtevi => {
            this.zahtevi = zahtevi;
            for (let z of this.zahtevi) {
              for (let v of z.vozila) {
                this.vozila.push(v);
              }
            }

          }
        );
      }

    })

  }

  odobriZahtev(zahtev: ZahtevRezervacije) {
    this.kreirajChatZaZahtev(zahtev);
    this.voziloService.odobriZahtev(zahtev).subscribe();  
  }


  ponistiZahtev(zahtev: ZahtevRezervacije) {
    this.voziloService.odbaciZahtev(zahtev).subscribe();
  }


  kreirajChatZaZahtev(zahtev: ZahtevRezervacije) {

    this.login.getKorisnikeSve().subscribe({
      next: korisnici => {
        this.korisnici = korisnici;

        for (let i = 0; i < this.korisnici.length; i++) {
          if (this.korisnici[i].id == zahtev.podnosilac) {
            this.podnosilac = this.korisnici[i];
          }
        }
        let chat = new Chat();
        chat.user2 = zahtev.izdavacMail;
        chat.user1 = this.podnosilac.email;
        console.log(chat);
        this.chatService.kreirajChat(chat).subscribe();
        
      }
    });
  }

}