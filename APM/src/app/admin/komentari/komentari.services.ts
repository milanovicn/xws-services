import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Komentar} from './Komentar'


@Injectable()
export class KomentariService {

    constructor(private _http: HttpClient) { 
    }

    //metoda za kreiranje komentara je u vozilo-service
    //metoda za izmenu stanja zahteva u REVIEWED je u zahtev services


    public getSveKomentare(): Observable<Komentar[]> {
        return this._http.get<Komentar[]>("/car/comment/")
    }


    public odobri(komentar:Komentar){
        return this._http.post<Komentar>("/car/comment/approve/"+komentar.id, komentar);
    }

    public odbij(komentar:Komentar){
        return this._http.post<Komentar>("/car/comment/reject/"+komentar.id, komentar);
    }

    //vraca samo odobrene komentare
    public vratiKomentareVozila(idVozila: number): Observable<Komentar[]> {
        return this._http.get<Komentar[]>("/car/comment/" + idVozila);
    }



}