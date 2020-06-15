import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Marka } from './Marka';
import { Model } from './Model';
import { KlasaVozila } from './KlasaVozila';
import { TipMenjaca } from './TipMenjaca';
import { TipGoriva } from './TipGoriva';


@Injectable()
export class SifrarnikService {

    constructor(private _http: HttpClient) { }

    getMarkeVozila(): Observable<Marka[]> {
        return this._http.get<Marka[]>("/user/getAllMarka")
    }

    getSveModele(): Observable<Model[]> {
        return this._http.get<Model[]>("/user/getAllModel")
    }

    getKlaseVozila(): Observable<KlasaVozila[]> {
        return this._http.get<KlasaVozila[]>("/user/klasaVozila")
    }

    getTipveMenjaca(): Observable<TipMenjaca[]> {
        return this._http.get<TipMenjaca[]>("/user/getAllTipMenjaca")
    }

    getTipoveGoriva(): Observable<TipGoriva[]> {
        return this._http.get<TipGoriva[]>("/user/getAllTipGoriva")
    }



    public dodajMarku(marka: Marka) {
        return this._http.post<Marka>("/user/addMarka", marka);
    }
    
    public obrisiMarku(id: number) {
        return this._http.delete("/user/deleteMarka/" + id);
    }

    public dodajModel(model: Model) {
        return this._http.post<Model>("/user/addModel", model);
    }
    
    public obrisiModel(id: number) {
        return this._http.delete("/user/deleteModel/" + id);
    }

    public dodajKlasuVozila(klasa: KlasaVozila) {
        return this._http.post<KlasaVozila>("/user/klasaVozila", klasa);
    }
    
    public obrisiKlasuVozila(id: number) {
        return this._http.delete("/user/klasaVozila/" + id);
    }

    public dodajTipMenjaca(tipMenjaca: TipMenjaca) {
        return this._http.post<TipMenjaca>("/user/addTipMenjaca", tipMenjaca);
    }
    
    public obrisiTipMenjaca(id: number) {
        return this._http.delete("/user/deleteTipMenjaca/" + id);
    }

    public dodajTipGoriva(tipGoriva: TipGoriva) {
        return this._http.post<TipGoriva>("/user/addTipGoriva", tipGoriva);
    }
    
    public obrisiTipGoriva(id: number) {
        return this._http.delete("/user/deleteTipGoriva/" + id);
    }

}
