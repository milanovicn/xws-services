import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Agent } from '../../agent/Agent';
import { AgentSerivces } from '../../agent/agent.service';
import { LoginServces } from 'src/app/login/login.services';
import { Korisnik } from 'src/app/login/Korisnik';
@Component({

    templateUrl: './lista-agenata.html'

})


export class ListaAgenataComponent implements OnInit {
    agenti: Agent[]=[];
    agentiZaRegistraciju: Agent[]=[];
    admin:Korisnik;
  

    constructor(private router: Router, private loginService: LoginServces, private agentService: AgentSerivces) {
        this.admin=new Korisnik();
    }



    ngOnInit(): void {
        this.loginService.getKorisnika().subscribe({
            next: admin => {
                this.admin = admin;
                console.log('Admin', this.admin);
                if (this.admin == null) {
                    this.router.navigate(["/welcome"]);
                }
                if (this.admin != null && this.admin.rola != "ADMIN") {
                    this.router.navigate(["/welcome"]);
                }
                this.agentService.vratiSveAgente().subscribe({
                    next: komentari => {
                        this.agenti = komentari;

                        for (let k of this.agenti)
                            if (k.odobren == false) {
                                this.agentiZaRegistraciju.push(k);
                                console.log(this.agentiZaRegistraciju);

                            }
                    }

                }
                );


    }
});


    }


onBack(): void {
    this.router.navigate(['/admin']);
}

odobri(komentar: Agent) {
    this.agentService.odobri(komentar).subscribe();
}

odbij(komentar: Agent) {
    this.agentService.odbij(komentar).subscribe();
}




}