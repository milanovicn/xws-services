import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Zauzece } from '../listaKorisnikovihVozila/Zauzece';
import { ZahtevRezervacije } from './ZahtevRezervacije';



@Injectable()
export class ZahtevSerivces{
    private pacijetUrl:string;

    constructor(private http:HttpClient){
      //  this.pacijetUrl='http//localhost:8080/api/pacijenti';
    }
  
 

    public napraviZahtev(zahtev:ZahtevRezervacije){
        return this.http.post<ZahtevRezervacije>("/zahtev/zahtev",zahtev);
    }
   

public vratiZahtevePoKorisniku(idKorisnika:number):Observable<ZahtevRezervacije[]>{
      return this.http.get<ZahtevRezervacije[]>("/zahtev/zahtev/izdavalac/"+idKorisnika);
  }
  public vratiZahtevePoPodnosiocu(idKorisnika:number):Observable<ZahtevRezervacije[]>{
    return this.http.get<ZahtevRezervacije[]>("/zahtev/zahtev/"+idKorisnika);
}
  public odobriZahtev(zahtev:ZahtevRezervacije):Observable<ZahtevRezervacije>{
    return this.http.post<ZahtevRezervacije>("/zahtev/odobri/"+zahtev.id,zahtev);
}
public otkaziZahtev(zahtev:ZahtevRezervacije):Observable<ZahtevRezervacije>{
    return this.http.post<ZahtevRezervacije>("/zahtev/otkazi/"+zahtev.id,zahtev);
}

}