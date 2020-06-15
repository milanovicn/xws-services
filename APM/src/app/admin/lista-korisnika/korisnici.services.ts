import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Korisnik } from 'src/app/login/Korisnik';

@Injectable()
export class KorisnikService {

    constructor(private _http: HttpClient) { }

    getKorisnika(): Observable<Korisnik> {
        return this._http.get<Korisnik>("/user/login");
    }

    getKorisnikeSve(): Observable<Korisnik[]> {
        return this._http.get<Korisnik[]>("/user/getAllClients")
    }

}
