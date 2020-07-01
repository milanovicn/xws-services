import {Component,NgModule, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Vozilo } from '../vozilo/Vozilo';
import { VoziloSerivces } from '../vozilo/vozilo.services';
import { ZahtevRezervacije } from './ZahtevRezervacije';
import { Korisnik } from 'src/app/login/Korisnik';
import { KorisnikService } from 'src/app/admin/lista-korisnika/korisnici.services';
import { ZahtevSerivces } from './zahtev.service';
import { SifrarnikService } from 'src/app/admin/sifrarnici/sifrarnici.services';
import { Model } from 'src/app/admin/sifrarnici/Model';
import { Marka } from 'src/app/admin/sifrarnici/Marka';
import { TipGoriva } from 'src/app/admin/sifrarnici/TipGoriva';
import { TipMenjaca } from 'src/app/admin/sifrarnici/TipMenjaca';
import { Pretraga } from './Pretraga';
import { SearchSerivces } from './search.service';








@Component({  
  templateUrl : './listaSvihVozila.html',
})

export class SearchComponent implements OnInit{
    
    vozila:Vozilo[]=[];
    korpa:Vozilo[]=[];
    isBundle:boolean=false;
    idAutora:number=0;
    bundleZahtevi:ZahtevRezervacije[]=[];
    idVozila:number[]=[];
    pretrazenaVozila:Vozilo[]=[];
    vracenoVozilo:Vozilo;

   vozilo:Vozilo;
   prikaziKorpu:boolean=false;
   _zauzmiOd:Date;
   _zauzmiDo:Date;
    _mesto:string;
    _sedista:number;
    _protection:boolean;
   zahtev:ZahtevRezervacije;
   korisnik:Korisnik;
   pretraga:Pretraga;

   modeliVozila:Model[]=[];
    markeVozila:Marka[]=[];
    tipoviGoriva:TipGoriva[]=[];
    tipoviMenjaca:TipMenjaca[]=[];

    nazivModeliVozila:string[]=[];
    nazivmarkeVozila:string[]=[];
    nazivtipoviGoriva:string[]=[];
    nazivtipoviMenjaca:string[]=[];

    izabranoGorivo:TipGoriva;
    izabraniMenjac:TipMenjaca;
    izabranaMarka:Marka;
    izabraniModel:Model;
    sve:string="sve";

    
    constructor(private route:ActivatedRoute,private router:Router,private voziloService:VoziloSerivces,private login:KorisnikService,
      private zahtevService:ZahtevSerivces,private sifarniciService:SifrarnikService,private searchService:SearchSerivces){
      this.vozilo=new Vozilo();
      this.vracenoVozilo=new Vozilo();
      this.zahtev=new ZahtevRezervacije();
      this.korisnik=new Korisnik();
      this.pretraga=new Pretraga();
       
    }

     ngOnInit(): void {

      this.login.getKorisnika().subscribe({
        next: korisnik=>{this.korisnik=korisnik;
         
         }

        
     });
        this.voziloService.vratiSvaVozila().subscribe({
            next: vozila => {
              this.vozila = vozila;}
            });

         
       this.ucitajmodele();
       this.ucitajMarke();
       this.ucitajGoriva();
       this.ucitajMenjace();
        
        
         
          }

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

    get mesto():string{
      return this.mesto;
    }
  
    set mesto(value:string){
      this._mesto=value;
    }

    get sedista():number{
      return this._sedista;
    }
  
    set sedista(value:number){
      this._sedista=value;
    }
    get protection():boolean{
      return this._protection;
    }
  
    set protection(value:boolean){
      this._protection=value;
    }
   
       
        ucitajmodele(){
          this.sifarniciService.getSveModele().subscribe({
            next: modeli => {
                this.modeliVozila = modeli;
           
              /*  for(let naziv of this.modeliVozila){
                    this.nazivModeliVozila.push(naziv.naziv);}*/
                     }
          });
        }
        ucitajMarke(){
           this.sifarniciService.getMarkeVozila().subscribe({
            next: marke => {
                this.markeVozila = marke;
            
               /* for(let naziv of this.markeVozila){
                    this.nazivmarkeVozila.push(naziv.naziv);}*/
                }
                  });
                
        }
        ucitajMenjace(){
          this.sifarniciService.getTipveMenjaca().subscribe({
            next: menjaci => {
                this.tipoviMenjaca = menjaci;
         
                /*for(let naziv of this.tipoviMenjaca){
                    this.nazivtipoviMenjaca.push(naziv.naziv);}*/
                  }
                });
        }
        
        ucitajGoriva(){
          this.sifarniciService.getTipoveGoriva().subscribe({
            next: goriva => {
                this.tipoviGoriva = goriva;
            
               /* for(let naziv of this.tipoviGoriva){
                    this.nazivtipoviGoriva.push(naziv.naziv);}*/
                  }
                });
                }
        

        dodajUkorpu(voziloId:number){
          if(this.zauzmiOd==null && this.zauzmiDo==null){
            alert("Polja za datume su obavezna za uneti");
          }else{
              this.voziloService.vratiVozilo(voziloId).subscribe({
                next:vozilo => {
                  this.vozilo = vozilo;
                  this.korpa.push(this.vozilo);
                }
              })
        }
      }

        prikazKorpe(){
          this.prikaziKorpu=true;
        }

        zahtevNapravi(vozilo:Vozilo){
          let pomocna:Vozilo;
          pomocna=new Vozilo();
          this.zahtev.vozila=[];
          this.zahtev.izdavac=vozilo.iznajmljivacId;
          this.zahtev.izdavacMail=vozilo.iznajmljivacMail;
          this.zahtev.datumOd=this.zauzmiOd;
          this.zahtev.datumDo=this.zauzmiDo;
          this.zahtev.podnosilac=this.korisnik.id;
          pomocna=vozilo;
          this.zahtev.vozila.push(pomocna);
          this.voziloService.zahtevNapravi(this.zahtev).subscribe();
        }
        pretrazi(){
                  this.pretraga.datumOd=this.zauzmiOd;
                  this.pretraga.datumDo=this.zauzmiDo;
                  
                  if(this.izabranaMarka==undefined){
                    this.pretraga.marka="sve";
                  }else{
                    this.pretraga.marka=this.izabranaMarka.naziv;
                  }

                  if(this.izabraniModel==undefined){
                    this.pretraga.model="sve";
                  }else{
                    this.pretraga.model=this.izabraniModel.naziv;
                  }

                  if(this.izabraniMenjac==undefined){
                    this.pretraga.tipMenjaca="sve";
                  }else{
                    this.pretraga.tipMenjaca=this.izabraniMenjac.naziv;
                  }

                  if(this.izabranoGorivo==undefined){
                    this.pretraga.tipGoriva="sve";
                  }else{
                    this.pretraga.tipGoriva=this.izabranoGorivo.naziv;
                  }
                 
                  

                  
                  
               /*  this.pretraga.mesto=this.mesto;
                  this.pretraga.brojSedistaZaDecu=this.sedista;*/

                  if(this.protection==true)
                    this.pretraga.CDWProtection="DA";
                  else
                    this.pretraga.CDWProtection="NE";
                    console.log(this.pretraga);
                    this.vozila=[];
                    this.searchService.pretrazi(this.pretraga).subscribe({
                      next: idovi => {
                          this.idVozila = idovi;
                          for(let id of this.idVozila)
                              this.voziloService.vratiVozilo(id).subscribe({
                                next: idovi => {
                                  this.vracenoVozilo = idovi;
                                  this.vozila.push(this.vracenoVozilo);
                                  console.log(this.vozila);
                              }

                              })
                      }
                  });
            }

            napraviBundle(){
              this.isBundle=true;
              this.zahtev.vozila=[];
              
            }

            zahtevBundleNapravi(vozilo:Vozilo){
              if(this.idAutora==0){
                let pomocna:Vozilo;
                pomocna=new Vozilo();
                //this.zahtev.vozila=[];
                this.idAutora=vozilo.iznajmljivacId;
                this.zahtev.izdavac=vozilo.iznajmljivacId;
                this.zahtev.izdavacMail=vozilo.iznajmljivacMail;
                this.zahtev.datumOd=this.zauzmiOd;
                this.zahtev.datumDo=this.zauzmiDo;
                this.zahtev.podnosilac=this.korisnik.id;
                pomocna=vozilo;
                this.zahtev.vozila.push(pomocna);
                //this.zahtev.idVozila=vozilo.id;;
                this.bundleZahtevi.push(this.zahtev);
              }else if(this.idAutora!=vozilo.iznajmljivacId && this.idAutora!=0){
                alert("U bundle zahtevu sva vozila moraju biti od istog izdavaca");
              }else{
                let pomocna:Vozilo;
                pomocna=new Vozilo();
              //  this.zahtev.vozila=[];
              this.zahtev.izdavac=vozilo.iznajmljivacId;
              this.zahtev.izdavacMail=vozilo.iznajmljivacMail;
              this.zahtev.datumOd=this.zauzmiOd;
              this.zahtev.datumDo=this.zauzmiDo;
              this.zahtev.podnosilac=this.korisnik.id;
              //this.zahtev.idVozila=vozilo.id;
              pomocna=vozilo;
                this.zahtev.vozila.push(pomocna);
              this.bundleZahtevi.push(this.zahtev);
              }
            }
            posaljiBundleZahtev(){
             // this.zahtevService.napraviBundleZahtev(this.bundleZahtevi).subscribe();
             this.voziloService.zahtevNapravi(this.zahtev).subscribe()
            }
        }

    

