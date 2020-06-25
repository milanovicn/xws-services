import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginServces } from '../login/login.services';


@Component({

    templateUrl: './homePageAgent.component.html'

})
export class HomePageAgentComponent implements OnInit {

    request: Request;

    constructor(private router: Router, private loginService: LoginServces) {
    }

    ngOnInit(): void {
    }

    kraj() {
        this.router.navigate(["/welcome"]);
    }

    odjavi() {
        this.loginService.IzlogujSe(this.request).subscribe(result => this.kraj());
    }


}
