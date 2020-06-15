import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServces } from './login.services';
import { Korisnik } from './Korisnik';

@Component({
	selector: 'pm-signup',
	templateUrl: './signup.component.html',
	styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

	user: Korisnik;

	constructor(private router: Router, private loginServices: LoginServces) {
		this.user = new Korisnik();
	}

	ngOnInit(): void {
	}

	onSubmit() {

		this.user.rola = "ADMIN";
		this.user.blokiran = false;
		this.user.brojObjavljenihOglasa = 0;
		this.user.brojOtkazanihOglasa = 0;
		this.loginServices.save(this.user).subscribe(result => this.finish());
		//alert("sacuvan korisnik " + this.pacijent.ime + "" + this.pacijent.prezime)
		//this.router.navigate(["/homePage"]);

	}

	finish() {
		alert("sacuvan korisnik " + this.user.ime + "" + this.user.prezime)
		this.user.ime = "";
		this.user.prezime = "";
		this.user.email = "";
		this.user.password = "";
		this.user.brojTelefona = "";
		this.router.navigate(["/homePage"]);
	}

}
