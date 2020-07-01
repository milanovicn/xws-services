import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vozilo } from "./Vozilo";
import { Zauzece } from '../listaKorisnikovihVozila/Zauzece';
import { ZahtevRezervacije } from '../search-oglasi/ZahtevRezervacije';



@Injectable()
export class VoziloSerivces {
  private pacijetUrl: string;

  constructor(private http: HttpClient) {
    //  this.pacijetUrl='http//localhost:8080/api/pacijenti';
  }



  public sacuvajVozilo(vozilo: Vozilo,rola:string) {
    return this.http.post<Vozilo>("/car/addVozilo/"+rola, vozilo);
  }

  public zahtevNapravi(zahtev:ZahtevRezervacije) {
    return this.http.post<ZahtevRezervacije>("/car/zahtev",zahtev);
  }

  public vratiZahtevePoKorisnikuMail(mail:string):Observable<ZahtevRezervacije[]>{
    return this.http.get<ZahtevRezervacije[]>("/car/zahtev/izdavalacMail/"+mail);
  }
  public vratiZahtevePoPodnosiocu(idKorisnika:number):Observable<ZahtevRezervacije[]>{
    return this.http.get<ZahtevRezervacije[]>("/car/zahtev/"+idKorisnika);
}

  public odobriZahtev(zahtev:ZahtevRezervacije):Observable<ZahtevRezervacije>{
    return this.http.post<ZahtevRezervacije>("/car/odobri/"+zahtev.id,zahtev);
}
public otkaziZahtev(zahtev:ZahtevRezervacije):Observable<ZahtevRezervacije>{
  return this.http.post<ZahtevRezervacije>("/car/otkazi/"+zahtev.id,zahtev);
}

public platiZahtev(zahtev:ZahtevRezervacije):Observable<ZahtevRezervacije>{
  return this.http.post<ZahtevRezervacije>("/car/plati/"+zahtev.id, zahtev);
}

  public vratiSvaVozila(): Observable<Vozilo[]> {
    return this.http.get<Vozilo[]>("/car/getAllCars");
  }
  public vratiVozilo(id: number): Observable<Vozilo> {
    return this.http.get<Vozilo>("/car/getCar/" + id);
  }
  public vratVozilaPoKorisniku(idKorisnika: number): Observable<Vozilo[]> {
    return this.http.get<Vozilo[]>("/car/vratiPoKorisniku/" + idKorisnika);
  }
  public vratVozilaPoIzdavacuMail(mail: string): Observable<Vozilo[]> {
    return this.http.get<Vozilo[]>("/car/vratiPoIzdavacu/" + mail);
  }
  public rezervisiVozilo(zauzece: Zauzece) {
    return this.http.post<Zauzece>("/car/zauzmiVozilo", zauzece);
  }

  public kreirajKomentar(tekstKomentara:string, idVozila: number) {
    return this.http.post<string>("/car/comment/"+idVozila, tekstKomentara);
  }

}


