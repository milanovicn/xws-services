import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Zauzece } from './Zauzece';
import { Vozilo } from '../vozilo/Vozilo';
import { VoziloSerivces } from '../vozilo/vozilo.services';

import { HttpClient, HttpEventType } from '@angular/common/http';
import { Komentar } from 'src/app/admin/komentari/Komentar';
import { KomentariService } from 'src/app/admin/komentari/komentari.services';
import { ZahtevRezervacije } from '../search-oglasi/ZahtevRezervacije';
import { ZahtevSerivces } from '../search-oglasi/zahtev.service';
import { KorisnikService } from 'src/app/admin/lista-korisnika/korisnici.services';
import { Korisnik } from 'src/app/login/Korisnik';

@Component({
  templateUrl: './detaljiOglasa.html',
})
export class DetaljiOglasaComponent implements OnInit {

  errorMessage = '';
  vozilo: Vozilo;
  id: number;
  _tekstKomentar: string;
  _zauzmiOd: Date;
  _zauzmiDo: Date;
  prikazanKomentre: boolean = false;
  zauzmiVozilo: boolean = false;
  zauzece: ZahtevRezervacije;
  korisnik: Korisnik;


  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  ocenaVozila: number;
  komentari: Komentar[] = [];


  get zauzmiOd(): Date {
    return this._zauzmiOd;
  }

  set zauzmiOd(value: Date) {
    this._zauzmiOd = value;
  }

  get zauzmiDo(): Date {
    return this._zauzmiDo;
  }

  set zauzmiDo(value: Date) {
    this._zauzmiDo = value;
  }

  constructor(private httpClient: HttpClient, private route: ActivatedRoute,
    private router: Router, private voziloService: VoziloSerivces,
    private komentariService: KomentariService, private login: KorisnikService, private zahtevService: ZahtevSerivces) {

    this.vozilo = new Vozilo();
    this.zauzece = new ZahtevRezervacije();
    this.korisnik = new Korisnik();
  }


  ngOnInit() {
    this.login.getKorisnika().subscribe({
      next: korisnik => { this.korisnik = korisnik; }
    });
    const param = this.route.snapshot.paramMap.get('id');
    if (param) {
      this.id = +param;
      this.getProduct(this.id);



    }

  }

  getProduct(id: number) {
    this.voziloService.vratiVozilo(id).subscribe(
      vozilo => this.vozilo = vozilo,
      error => this.errorMessage = <any>error);

    this.httpClient.get('http://localhost:8080/car/getImage/' + this.id)
      .subscribe(
        res => {
          this.retrieveResonse = res;
          this.base64Data = this.retrieveResonse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;

        }

      );

    this.voziloService.getOcena(this.id).subscribe({
      next: ocenaVozila => {
        this.ocenaVozila = ocenaVozila;
      }
    });

    this.listaKomentara();
  }


  prikaziRezervaciju() {
    this.zauzmiVozilo = true;
  }
  rezervisi() {
    this.zauzece.datumOd = this.zauzmiOd;
    this.zauzece.datumDo = this.zauzmiDo;
    this.zauzece.vozila.push(this.vozilo);
    this.zauzece.izdavac = this.korisnik.id;
    this.zauzece.podnosilac = this.korisnik.id;
    this.zahtevService.napraviZahtev(this.zauzece).subscribe();

  }

  listaKomentara() {
    this.komentariService.vratiKomentareVozila(this.id).subscribe(
      komentari => {
        this.komentari = komentari;
      }
    );
  }
}