import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Korisnik } from 'src/app/login/Korisnik';
import { LoginServces } from 'src/app/login/login.services';
import { KorisnikService } from './korisnici.services';

@Component({
  selector: 'pm-lista-korisnika',
  templateUrl: './lista-korisnika.component.html',
  styleUrls: ['./lista-korisnika.component.css']
})
export class ListaKorisnikaComponent implements OnInit {

  korisnici: Korisnik[] = [];
  admin: Korisnik;

  constructor(private router:Router, private loginService: LoginServces, private korisniciServis: KorisnikService ) { }

  ngOnInit(): void {
    this.loginService.getKorisnika().subscribe({
      next: admin => {
        this.admin = admin;
        console.log('Admin', this.admin);
        if (this.admin == null) {
          this.router.navigate(["/welcome"]);
        }
        if (this.admin != null && this.admin.rola != "ADMIN") {
          this.router.navigate(["/welcome"]);
        }
        this.korisniciServis.getKorisnikeSve().subscribe({
          next: korisnici => {
            this.korisnici = korisnici;
          }
        });
      }
    });
  }

  onBack(): void {
    this.router.navigate(['/admin']);
  }

  blokiraj(korisnik: Korisnik) {
    // TODO: asdadsad
  }

  ukloni(korisnik: Korisnik) {
    // TODO: asdadsad
  }

}
