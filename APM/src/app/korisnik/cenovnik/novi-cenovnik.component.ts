import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { Cenovnik } from './Cenovnik';
import { CenovnikService } from './cenovnik.service';
import { Korisnik } from 'src/app/login/Korisnik';
import { KorisnikService } from 'src/app/admin/lista-korisnika/korisnici.services';

@Component({
    templateUrl: 'novi-cenovnik.html',
})
export class NoviCenovnikComponent {

    errorMessage = '';
    cenovnik: Cenovnik;
    id: number;
    korisnik: Korisnik;

    constructor(private route: ActivatedRoute, private router: Router, private cenovnikService: CenovnikService, private login: KorisnikService) {
        this.cenovnik = new Cenovnik();

    }


    napraviCenovnik() {

        this.login.getKorisnika().subscribe({ next: korisnik => {
                this.korisnik = korisnik;
                this.cenovnik.autor=korisnik.id.toString(10);
                this.cenovnikService.napraviCenovnik(this.cenovnik).subscribe(cenovnik => {
                    this.cenovnik = cenovnik;
                });
            }

        });

       
    }
}