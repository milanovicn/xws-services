import { Component, OnInit } from '@angular/core';
import { Korisnik } from '../login/Korisnik';
import { Router } from '@angular/router';
import { LoginServces } from '../login/login.services';

@Component({
  selector: 'pm-profil-admina',
  templateUrl: './profil-admina.component.html',
  styleUrls: ['./profil-admina.component.css']
})
export class ProfilAdminaComponent implements OnInit {

  request: Request;
  admin: Korisnik;

  constructor(private _router: Router, private loginService: LoginServces) {
    this.admin = new Korisnik();
  }

  ngOnInit(): void {
    this.loginService.getKorisnika().subscribe({
      next: admin => {
        this.admin = admin;
        console.log('Admin', this.admin);
        if (this.admin == null) {
          this._router.navigate(["/welcome"]);
        }
        if (this.admin != null && this.admin.rola != "ADMIN") {
          this.loginService.IzlogujSe(this.request).subscribe(result => this.kraj());
        }
      }
    });
  }

  kraj() {
    this._router.navigate(["/welcome"]);
  }

  odjaviSe() {
    this.loginService.IzlogujSe(this.request).subscribe(result => this.kraj());
  }

}
