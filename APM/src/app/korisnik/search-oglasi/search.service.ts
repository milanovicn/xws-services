import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


import { Pretraga } from './Pretraga';
import { AdvancedSearch } from '../vozilo/AdvancedSearch';



@Injectable()
export class SearchSerivces {
  private pacijetUrl: string;

  constructor(private http: HttpClient) {
    //  this.pacijetUrl='http//localhost:8080/api/pacijenti';
  }





  public dodaj(search: AdvancedSearch) {
    return this.http.post<AdvancedSearch>("/search/search", search);
  }

  public pretrazi(search: Pretraga): Observable<number[]> {
    return this.http.post<number[]>("/search/pretrazi", search);
  }


}