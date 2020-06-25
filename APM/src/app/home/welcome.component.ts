import { Component, OnInit } from '@angular/core';
import { Korisnik } from '../login/Korisnik';
import { Router } from '@angular/router';
import { LoginServces } from '../login/login.services';

@Component({
  templateUrl: './welcome.component.html'
})
export class WelcomeComponent implements OnInit{
  public pageTitle = 'Welcome';

  request: Request;
  user: Korisnik;

  constructor(private _router: Router, private loginService: LoginServces) {
    this.user = new Korisnik();
  }

  ngOnInit(): void {
    this.loginService.getKorisnika().subscribe({
      next: admin => {
        this.user = admin;
        console.log('Korisnik', this.user);
        if (this.user == null) {
          this._router.navigate(["/welcome"]);
        }
        if (this.user != null && this.user.rola == "ADMIN") {
          //this.loginService.IzlogujSe(this.request).subscribe(result => this.kraj());
          this._router.navigate(["/admin"]);
        }
        if (this.user != null && this.user.rola == "CLIENT") {
          //this.loginService.IzlogujSe(this.request).subscribe(result => this.kraj());
          this._router.navigate(["/korisnik"]);
        }
        if (this.user != null && this.user.rola == "AGENT") {
          //this.loginService.IzlogujSe(this.request).subscribe(result => this.kraj());
          window.open("http://localhost:4201");
          this._router.navigate(["/homeAgent"]);
        }
      }
    });
    
  }

}
