import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vozilo } from "./Vozilo";
import { Zauzece } from '../listaKorisnikovihVozila/Zauzece';



@Injectable()
export class VoziloSerivces{
    private pacijetUrl:string;

    constructor(private http:HttpClient){
      //  this.pacijetUrl='http//localhost:8080/api/pacijenti';
    }
  
 

    public sacuvajVozilo(vozilo:Vozilo){
        return this.http.post<Vozilo>("/car/addVozilo",vozilo);
    }
   
    public vratiSvaVozila():Observable<Vozilo[]>{
      return this.http.get<Vozilo[]>("/car/getAllCars");
  }
  public vratiVozilo(id:number):Observable<Vozilo>{
    return this.http.get<Vozilo>("/car/getCar/"+id);
}
public vratVozilaPoKorisniku(idKorisnika:number):Observable<Vozilo[]>{
      return this.http.get<Vozilo[]>("/car/vratiPoKorisniku/"+idKorisnika);
  }
  public rezervisiVozilo(zauzece:Zauzece){
    return this.http.post<Zauzece>("/car/zauzmiVozilo",zauzece);
  }
 

}


