import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Zauzece } from './Zauzece';
import { Vozilo } from '../vozilo/Vozilo';
import { VoziloSerivces } from '../vozilo/vozilo.services';



@Component({
  templateUrl: './detaljiOglasa.html',
})
export class DetaljiOglasaComponent implements OnInit {
  
  errorMessage = '';
  vozilo:Vozilo;
  id:number;
  _tekstKomentar:string;
  _zauzmiOd:Date;
  _zauzmiDo:Date;
  prikazanKomentre:boolean=false;
  zauzmiVozilo:boolean=false;
  zauzece:Zauzece;





  get zauzmiOd():Date{
    return this._zauzmiOd;
  }

  set zauzmiOd(value:Date){
    this._zauzmiOd=value;
  }

  get zauzmiDo():Date{
    return this._zauzmiDo;
  }

  set zauzmiDo(value:Date){
    this._zauzmiDo=value;
  }

  constructor(private route: ActivatedRoute,private router: Router,private voziloService: VoziloSerivces) {

        this.vozilo=new Vozilo();
        this.zauzece=new Zauzece();
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
  }

 
prikaziRezervaciju(){
  this.zauzmiVozilo=true;
}
rezervisi(){
  this.zauzece.od=this.zauzmiOd;
  this.zauzece.zauzetDo=this.zauzmiDo;
  this.zauzece.idVozila=this.id;
  this.voziloService.rezervisiVozilo(this.zauzece).subscribe();

}
}