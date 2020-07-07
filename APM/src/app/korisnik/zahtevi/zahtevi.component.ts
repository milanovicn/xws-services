import {Component,NgModule, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Vozilo } from '../vozilo/Vozilo';
import { VoziloSerivces } from '../vozilo/vozilo.services';

import { Korisnik } from 'src/app/login/Korisnik';
import { KorisnikService } from 'src/app/admin/lista-korisnika/korisnici.services';
import { ZahtevRezervacije } from '../search-oglasi/ZahtevRezervacije';
import { ZahtevSerivces } from '../search-oglasi/zahtev.service';








@Component({

    templateUrl : './zahtevi.html'

})

export class ZahtevComponent implements OnInit{
    
    vozila:Vozilo[]=[];
    korpa:Vozilo[]=[];
   vozilo:Vozilo;
   prikaziKorpu:boolean=false;
   zahtevi:ZahtevRezervacije[]=[];
   zahtev:ZahtevRezervacije;
   korisnik:Korisnik;
    
    constructor(private route:ActivatedRoute,private router:Router,private voziloService:VoziloSerivces,private login:KorisnikService,private zahtevService:ZahtevSerivces){
      this.vozilo=new Vozilo();
      this.zahtev=new ZahtevRezervacije();
      this.korisnik=new Korisnik();
       
    }

    

    ngOnInit(): void {

      this.login.getKorisnika().subscribe({
        next: korisnik=>{this.korisnik=korisnik;
            this.voziloService.vratiZahtevePoKorisnikuMail(this.korisnik.email).subscribe(
                zahtevi => {
                  this.zahtevi = zahtevi;
                  for(let z of this.zahtevi){
                    for(let v of z.vozila){
                      this.vozila.push(v);
                    }
                  }

                }
              );
         }
        
     })
       
        }

        odobriZahtev(zahtev:ZahtevRezervacije){
            this.voziloService.odobriZahtev(zahtev).subscribe();
        }
        ponistiZahtev(zahtev:ZahtevRezervacije){
            this.voziloService.odbaciZahtev(zahtev).subscribe();
        }
            
        }