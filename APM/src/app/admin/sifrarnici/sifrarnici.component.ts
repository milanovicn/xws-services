import { Component, OnInit } from '@angular/core';
import { KorisnikService } from '../lista-korisnika/korisnici.services';
import { LoginServces } from 'src/app/login/login.services';
import { Router } from '@angular/router';
import { Korisnik } from 'src/app/login/Korisnik';
import { Marka } from './Marka';
import { Model } from './Model';
import { KlasaVozila } from './KlasaVozila';
import { TipMenjaca } from './TipMenjaca';
import { TipGoriva } from './TipGoriva';
import { SifrarnikService } from './sifrarnici.services';

@Component({
  selector: 'pm-sifrarnici',
  templateUrl: './sifrarnici.component.html',
  styleUrls: ['./sifrarnici.component.css']
})
export class SifrarniciComponent implements OnInit {

  admin: Korisnik;
  markeVozila: Marka[] = [];
  sviModeliVozila: Model[] = [];
  klaseVozila: KlasaVozila[] = [];
  tipoviMenjaca: TipMenjaca[] = [];
  tipoviGoriva: TipGoriva[] = [];

  novaMarka: Marka;
  noviModel: Model;
  novaKlasaVozila: KlasaVozila;
  noviTipMenjaca: TipMenjaca;
  noviTipGoriva: TipGoriva;

  constructor(private router: Router, private loginService: LoginServces, private sifrarniciService: SifrarnikService) {
    this.admin = new Korisnik();
    this.novaMarka = new Marka();
    this.noviModel = new Model();
    this.novaKlasaVozila = new KlasaVozila();
    this.noviTipMenjaca = new TipMenjaca();
    this.noviTipGoriva = new TipGoriva();

  }

  ngOnInit(): void {
    this.loginService.getKorisnika().subscribe({
      next: admin => {
        this.admin = admin;
        console.log('Admin', this.admin);
        if (this.admin == null) {
          this.router.navigate(["/welcome"]);
        }
        if (this.admin != null && this.admin.rola != "ADMIN") {
          this.router.navigate(["/welcome"]);
        }

        if (this.admin != null && this.admin.rola == "ADMIN") {
          this.sifrarniciService.getMarkeVozila().subscribe({
            next: ret => {
              this.markeVozila = ret;
            }
          })

          this.sifrarniciService.getSveModele().subscribe({
            next: ret => {
              this.sviModeliVozila = ret;
            }
          })

          this.sifrarniciService.getKlaseVozila().subscribe({
            next: ret => {
              this.klaseVozila = ret;
            }
          })

          this.sifrarniciService.getTipveMenjaca().subscribe({
            next: ret => {
              this.tipoviMenjaca = ret;
            }
          })

          this.sifrarniciService.getTipoveGoriva().subscribe({
            next: ret => {
              this.tipoviGoriva = ret;
            }
          })

        }
      }
    });
  }


  onBack(): void {
    this.router.navigate(['/admin']);
  }



  onSubmitMarka() {
    if (this.novaMarka.naziv.replace(/\s/g, '').length) {
      this.sifrarniciService.dodajMarku(this.novaMarka).subscribe();
      this.refresh();
    }
    else {
      alert('Uneti su samo prazni stringovi(spaces, tabs)\nUnesite ispravan string!');
      console.log('string only contains whitespace (ie. spaces, tabs or line breaks)');
      this.novaMarka.naziv = "";
    }
  }

  obrisiMarku(idMarke: number) {
    this.sifrarniciService.obrisiMarku(idMarke).subscribe();
    this.refresh();
  }


  onSubmitModel() {
    if (this.noviModel.naziv.replace(/\s/g, '').length && this.noviModel.marka.replace(/\s/g, '').length) {
      this.sifrarniciService.dodajModel(this.noviModel).subscribe();
      this.refresh();
    }
    else {
      alert('Uneti su samo prazni stringovi(spaces, tabs)\nUnesite ispravan string!');
      console.log('string only contains whitespace (ie. spaces, tabs or line breaks)');
      this.noviModel.naziv = "";
      this.noviModel.marka = "";
    }
  }

  obrisiModel(idModela: number) {
    this.sifrarniciService.obrisiModel(idModela).subscribe();
    this.refresh();
  }


  onSubmitKlasa() {
    if (this.novaKlasaVozila.naziv.replace(/\s/g, '').length) {
      this.sifrarniciService.dodajKlasuVozila(this.novaKlasaVozila).subscribe();
      this.refresh();
    }
    else {
      alert('Uneti su samo prazni stringovi(spaces, tabs)\nUnesite ispravan string!');
      console.log('string only contains whitespace (ie. spaces, tabs or line breaks)');
      this.novaKlasaVozila.naziv = "";
    }
  }

  obrisiKlasu(idKlase: number) {
    this.sifrarniciService.obrisiKlasuVozila(idKlase).subscribe();
    this.refresh();
  }


  onSubmitTipMenjaca() {
    if (this.noviTipMenjaca.naziv.replace(/\s/g, '').length) {
      this.sifrarniciService.dodajTipMenjaca(this.noviTipMenjaca).subscribe();
      this.refresh();
    }
    else {
      alert('Uneti su samo prazni stringovi(spaces, tabs)\nUnesite ispravan string!');
      console.log('string only contains whitespace (ie. spaces, tabs or line breaks)');
      this.noviTipMenjaca.naziv = "";
    }
  }

  obrisiTipMenjaca(idTipMenjaca: number) {
    this.sifrarniciService.obrisiTipMenjaca(idTipMenjaca).subscribe();
    this.refresh();
  }


  onSubmitTipGoriva() {
    if (this.noviTipGoriva.naziv.replace(/\s/g, '').length) {
      this.sifrarniciService.dodajTipGoriva(this.noviTipGoriva).subscribe();
      this.refresh();
    }
    else {
      alert('Uneti su samo prazni stringovi(spaces, tabs)\nUnesite ispravan string!');
      console.log('string only contains whitespace (ie. spaces, tabs or line breaks)');
      this.noviTipGoriva.naziv = "";
    }
  }

  obrisiTipGoriva(idTipGoriva: number) {
    this.sifrarniciService.obrisiTipGoriva(idTipGoriva).subscribe();
    this.refresh();
  }


  refresh() {
    window.location.reload();
  }

}
