import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Zauzece } from './Zauzece';
import { Vozilo } from '../vozilo/Vozilo';
import { VoziloSerivces } from '../vozilo/vozilo.services';

import { HttpClient, HttpEventType } from '@angular/common/http';

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
  zauzece: Zauzece;


  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;


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

  constructor(private httpClient: HttpClient, private route: ActivatedRoute, private router: Router, private voziloService: VoziloSerivces) {

    this.vozilo = new Vozilo();
    this.zauzece = new Zauzece();
  }


  ngOnInit() {
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
  }


  prikaziRezervaciju() {
    this.zauzmiVozilo = true;
  }
  rezervisi() {
    this.zauzece.od = this.zauzmiOd;
    this.zauzece.zauzetDo = this.zauzmiDo;
    this.zauzece.idVozila = this.id;
    this.voziloService.rezervisiVozilo(this.zauzece).subscribe();

  }
}