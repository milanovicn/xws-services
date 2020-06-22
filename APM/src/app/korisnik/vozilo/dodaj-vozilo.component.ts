import { Component, NgModule, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Vozilo } from './Vozilo';
import { VoziloSerivces } from './vozilo.services';
import { LoginServces } from 'src/app/login/login.services';
import { Korisnik } from 'src/app/login/Korisnik';
import { SifrarnikService } from 'src/app/admin/sifrarnici/sifrarnici.services';
import { Model } from 'src/app/admin/sifrarnici/Model';
import { Marka } from 'src/app/admin/sifrarnici/Marka';
import { TipGoriva } from 'src/app/admin/sifrarnici/TipGoriva';
import { TipMenjaca } from 'src/app/admin/sifrarnici/TipMenjaca';
import { KlasaVozila } from 'src/app/admin/sifrarnici/KlasaVozila';
import { KorisnikService } from 'src/app/admin/lista-korisnika/korisnici.services';
import { SearchSerivces } from '../search-oglasi/search.service';
import { AdvancedSearch } from './AdvancedSearch';

import { Cenovnik } from '../cenovnik/Cenovnik';
import { CenovnikService } from '../cenovnik/cenovnik.service';
import { CenovnikComponent } from '../cenovnik/cenovnik.component';
import { HttpClient, HttpEventType } from '@angular/common/http';




@Component({

    templateUrl: './dodaj-vozilo.html'

})

export class VoziloComponent implements OnInit {


    vozilo: Vozilo;
    vozilo1: Vozilo;
    korisnik: Korisnik;

    modeliVozila: Model[] = [];
    markeVozila: Marka[] = [];
    tipoviGoriva: TipGoriva[] = [];
    tipoviMenjaca: TipMenjaca[] = [];
    klaseVozila: KlasaVozila[] = [];

    izabranaKlasa: KlasaVozila;
    izabranoGorivo: TipGoriva;
    izabraniMenjac: TipMenjaca;
    izabranaMarka: Marka;
    izabraniModel: Model;
    advancedSearch: AdvancedSearch;

    cenovnici: Cenovnik[] = [];
    izabraniCenovnik:Cenovnik;




    selectedFiles: File[] = [];
    urls = new Array<string>();
    uploadedDatas: FormData[] = [];

    selectedFile: File;
    retrievedImage: any;
    message: string;

    constructor(private httpClient: HttpClient, private route: ActivatedRoute, private router: Router, private voziloService: VoziloSerivces, private loginService: KorisnikService,
        private searchService: SearchSerivces, private sifarniciService: SifrarnikService, private cenovnikService: CenovnikService) {

        this.vozilo = new Vozilo();
        this.korisnik = new Korisnik();
        this.izabranaKlasa = new KlasaVozila();
        this.izabranoGorivo = new TipGoriva();
        this.izabraniMenjac = new TipMenjaca();
        this.izabranaMarka = new Marka();
        this.izabraniModel = new Model();
        this.advancedSearch = new AdvancedSearch();
        this.izabraniCenovnik=new Cenovnik();


    }

    ngOnInit(): void {
        this.loginService.getKorisnika().subscribe({
            next: korisnik => {
                this.korisnik = korisnik;
                console.log(this.korisnik);
                this.cenovnikService.vratiCenovnikePoKorisniku(this.korisnik.id).subscribe({
                    next: cenovnici => {
                        this.cenovnici = cenovnici;
                    }
                });
            }
        });

        
        console.log(this.cenovnici);

        this.sifarniciService.getKlaseVozila().subscribe({
            next: klaseVozila => {
                this.klaseVozila = klaseVozila;
            }
        });

        this.sifarniciService.getMarkeVozila().subscribe({
            next: marke => {
                this.markeVozila = marke;
            }
        });

        this.sifarniciService.getSveModele().subscribe({
            next: modeli => {
                this.modeliVozila = modeli;
            }
        });

        this.sifarniciService.getTipoveGoriva().subscribe({
            next: goriva => {
                this.tipoviGoriva = goriva;
            }
        });

        this.sifarniciService.getTipveMenjaca().subscribe({
            next: menjaci => {
                this.tipoviMenjaca = menjaci;
            }
        });

    }

    napraviOglas() {


        this.vozilo.iznajmljivacId = this.korisnik.id;
        this.vozilo.marka = this.izabranaMarka.naziv;
        this.vozilo.model = this.izabraniModel.naziv;
        this.vozilo.klasaVozila = this.izabranaKlasa.naziv;
        this.vozilo.tipGoriva = this.izabranoGorivo.naziv;
        this.vozilo.tipMenjaca = this.izabraniMenjac.naziv;
        this.vozilo.cenovnikId=this.izabraniCenovnik.naziv;

        this.voziloService.sacuvajVozilo(this.vozilo,this.korisnik.rola).subscribe(vozilo => {
            this.vozilo = vozilo;
            this.napraviSearch();
            // this.slika.idVozila=this.vozilo.id;
            /// this.slika.putanja=this.urls;
            //  this.voziloService.sacuvajSliku( this.slika).subscribe();

            console.log(this.selectedFile);
            console.log("vozilo id: " + this.vozilo.id);
            const uploadImageData = new FormData();
            console.log("usao");
            console.log(this.selectedFile);
            uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name.concat("_vozilo" + this.vozilo.id.toString(10)));
            console.log(this.selectedFile);
            this.httpClient.post('http://localhost:8080/car/uploadImage', uploadImageData, { observe: 'response' })

                .subscribe((response) => {

                    if (response.status === 200) {

                        this.message = 'Image uploaded successfully';

                    } else {

                        this.message = 'Image not uploaded successfully';
                    }
                }
                );


        });

    }

    public onFileChanged(event) {

        this.selectedFile = event.target.files[0];

    }



    napraviSearch() {
        this.advancedSearch.mesto = this.vozilo.mesto;
        this.advancedSearch.model = this.vozilo.model;
        this.advancedSearch.marka = this.vozilo.marka;
        this.advancedSearch.tipGoriva = this.vozilo.tipGoriva;
        this.advancedSearch.tipMenjaca = this.vozilo.tipMenjaca;
        this.advancedSearch.brojSedistaZaDecu = this.vozilo.brojSedistaDeca;
        this.advancedSearch.CDWProtection = this.vozilo.CDWProtection;
        this.advancedSearch.datumOd = this.vozilo.vaziOd;
        this.advancedSearch.datumDo = this.vozilo.vaziDo;
        this.advancedSearch.idVozila = this.vozilo.id;
        this.searchService.dodaj(this.advancedSearch).subscribe();


        // console.log("vozilo uspesno dodato");


    }

    detectFiles(event) {
        this.urls = [];

        this.selectedFiles = event.target.files;
        /*for(let file of this.selectedFiles){
             const uploadImageData = new FormData();
             uploadImageData.append('imgs',file);
            this.uploadedDatas.push(uploadImageData);
           }
         */
        console.log(this.selectedFiles);
        if (this.selectedFiles) {
            for (let file of this.selectedFiles) {
                let reader = new FileReader();
                reader.onload = (e: any) => {
                    this.urls.push(e.target.result);
                }
                reader.readAsDataURL(file);
            }
        }
    }

}