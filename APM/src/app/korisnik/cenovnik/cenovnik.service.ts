import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cenovnik } from "./Cenovnik";



@Injectable()
export class CenovnikService{
  
    //private pacijetUrl:string;

    constructor(private http:HttpClient){
      
    }

    public vratiCenovnik(id:number):Observable<Cenovnik>{
        return this.http.get<Cenovnik>("/user/user/cenovnik/"+id);
    }

    public obrisiCenovnik(id:number){
        return this.http.delete("/user/user/cenovnik/"+id);
    }

    public napraviCenovnik(cenovnik:Cenovnik){
        return this.http.post<Cenovnik>("/user/user/cenovnik",cenovnik);
    }

    //public vratiSveCenovnike(): Observable<Cenovnik[]> {
    //    return this.http.get<Cenovnik[]>("/user/cenovnik/");
    //}

    public vratiCenovnikePoKorisniku(id:number): Observable<Cenovnik[]> {
        return this.http.get<Cenovnik[]>("/user/user/cenovnik/autor/"+id);
    }

}