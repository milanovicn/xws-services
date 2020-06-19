import { Component, NgModule, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cenovnik } from './Cenovnik';
import { CenovnikService } from './cenovnik.service';
import { Korisnik } from 'src/app/login/Korisnik';
import { KorisnikService } from 'src/app/admin/lista-korisnika/korisnici.services';




@Component({

  templateUrl: './lista-cenovnika.html'

})

export class ListaCenovnikaComponent implements OnInit {

  cenovnici: Cenovnik[] = [];
  korisnik:Korisnik;


  constructor(private route: ActivatedRoute, private router: Router, private cenovnikService: CenovnikService, private login:KorisnikService) {


  }
  ngOnInit(): void {

    this.login.getKorisnika().subscribe({
      next: korisnik=>{this.korisnik=korisnik;
       this.cenovnikService.vratiCenovnikePoKorisniku(this.korisnik.id).subscribe({
           next: cenovnici => {
             this.cenovnici = cenovnici;
           }
       });
       }
      
   })



  }




}