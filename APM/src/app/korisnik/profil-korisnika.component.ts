import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServces } from '../login/login.services';

@Component({
  selector: 'pm-profil-korisnika',
  templateUrl: './profil-korisnika.component.html',
  styleUrls: ['./profil-korisnika.component.css']
})
export class ProfilKorisnikaComponent implements OnInit {

  constructor(private _router: Router,private loginService:LoginServces) { }

  request:Request;
  
  ngOnInit(): void {
  }

  kraj() {
    this._router.navigate(["/welcome"]);
  }

  odjaviSe() {
    this.loginService.IzlogujSe(this.request).subscribe(result => this.kraj());
  }
}
