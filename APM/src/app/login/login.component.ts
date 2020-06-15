
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Login } from './login';
import { LoginServces } from './login.services';
import { Korisnik } from './Korisnik';
import { Observable } from 'rxjs';



@Component({

    //selector: 'login-component',
    templateUrl: './login.html',
    //styleUrls: []

})


export class LoginComponent implements OnInit {
    korisnik: Korisnik;
    respnse: Response;
    odgovor: boolean = false;

    loginZahtev: Login;

    constructor(private router: Router, private loginService: LoginServces) {
        this.loginZahtev = new Login();
        this.korisnik = new Korisnik();

    }

    ngOnInit() {
        this.vratiKorisnika();

    }


    login() {
        this.odgovor = false;
        this.loginService.ulogujSe(this.loginZahtev).subscribe(result => this.vratiKorisnika(),
            err => this.odgovor = true
        );

    }

    vratiKorisnika() {

        this.loginService.getKorisnika().subscribe({
            next: korisnik => {
                this.korisnik = korisnik;

                console.log(this.korisnik.rola);

                if (this.korisnik.rola == "ADMIN") {
                    this.router.navigate(["/admin"]);
                }
                /*else if (this.korisnik.rola == "ADMINKLINIKE") {
                    this.router.navigate(["/adminKlinike"]);
                }
                else if (this.korisnik.rola == "LEKAR") {
                    this.router.navigate(["/lekar"]);
                }*/
                else {
                    this.router.navigate(["/signup"]);
                }


            }

        });

    }



}