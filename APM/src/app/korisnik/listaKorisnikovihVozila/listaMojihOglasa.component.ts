import {Component,NgModule, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Vozilo } from '../vozilo/Vozilo';
import { VoziloSerivces } from '../vozilo/vozilo.services';
import { LoginServces } from 'src/app/login/login.services';
import { Korisnik } from 'src/app/login/Korisnik';
import { KorisnikService } from 'src/app/admin/lista-korisnika/korisnici.services';







@Component({

    templateUrl : './listaMojihVozila.html'

})

export class ListaVozilaComponent implements OnInit{
    
    vozila:Vozilo[]=[];
    korisnik:Korisnik;

    
    constructor(private route:ActivatedRoute,private router:Router,private voziloService:VoziloSerivces,private login:KorisnikService){
      
       
    }
    ngOnInit(): void {
            this.login.getKorisnika().subscribe({
               next: korisnik=>{this.korisnik=korisnik;
                this.voziloService.vratVozilaPoKorisniku(this.korisnik.id).subscribe({
                    next: vozila => {
                      this.vozila = vozila;
                    }
                });
                }
               
            });
            
        }

    

}