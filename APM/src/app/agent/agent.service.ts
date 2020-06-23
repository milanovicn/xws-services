import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Agent } from "./Agent";



@Injectable()
export class AgentSerivces {
  private pacijetUrl: string;

  constructor(private http: HttpClient) {
    //  this.pacijetUrl='http//localhost:8080/api/pacijenti';
  }



  public sacuvaj(agent: Agent) {
    return this.http.post<Agent>("/user/agent", agent);
  }

  public vratiSveAgente(): Observable<Agent[]> {
    return this.http.get<Agent[]>("/user/agent");
  }
  public vratiAgenta(id: number): Observable<Agent> {
    return this.http.get<Agent>("/user/agent/" + id);
  }

  public odobri(komentar:Agent){
    return this.http.post<Agent>("/user/agent/odobri/"+komentar.id, komentar);
}

public odbij(komentar:Agent){
    return this.http.post<Agent>("/user/agent/odbaci/"+komentar.id, komentar);
}
  
}