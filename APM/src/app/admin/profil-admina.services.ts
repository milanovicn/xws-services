import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Korisnik } from '../login/Korisnik';


@Injectable()
export class AdminService {

    constructor(private _http: HttpClient) { }

    getAdmina(): Observable<Korisnik> {
        return this._http.get<Korisnik>("/user/login");
    }

}
