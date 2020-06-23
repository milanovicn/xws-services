import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Agent } from './Agent';
import { AgentSerivces } from './agent.service';


@Component({
	
	templateUrl: './agent.html'
	
})
export class AgentComponent implements OnInit {

	agent: Agent;

	constructor(private router: Router, private agentServices: AgentSerivces) {
		this.agent = new Agent();
	}

	ngOnInit(): void {
	}

	onSubmit() {

		
		this.agentServices.sacuvaj(this.agent).subscribe(result => this.finish());
		//alert("sacuvan korisnik " + this.pacijent.ime + "" + this.pacijent.prezime)
		//this.router.navigate(["/homePage"]);

	}

	finish() {
		alert("sacuvan korisnik " + this.agent.nazivFirme);
		this.agent.nazivFirme = "";
		this.agent.adresa = "";
		this.agent.email = "";
		this.agent.password = "";
		
		this.router.navigate(["/homePage"]);
	}

}
