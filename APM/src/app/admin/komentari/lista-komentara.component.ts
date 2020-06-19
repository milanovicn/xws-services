import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Korisnik } from 'src/app/login/Korisnik';
import { LoginServces } from 'src/app/login/login.services';
import { Komentar } from './Komentar';
import { KomentariService } from './komentari.services';
@Component({

    templateUrl: './lista-komentara.html'

})


export class ListaKomentaraComponent implements OnInit {
    komentariZaPregled: Komentar[]=[];
    komentari: Komentar[] = [];
    admin: Korisnik;

    constructor(private router: Router, private loginService: LoginServces, private komentariService: KomentariService) {
        this.admin = new Korisnik();
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
                this.komentariService.getSveKomentare().subscribe({
                    next: komentari => {
                        this.komentari = komentari;

                        for (let k of this.komentari)
                            if (k.stanje == "OBJAVLJEN") {
                                this.komentariZaPregled.push(k);
                                console.log(this.komentariZaPregled);

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

odobri(komentar: Komentar) {
    this.komentariService.odobri(komentar).subscribe();
}

odbij(komentar: Komentar) {
    this.komentariService.odbij(komentar).subscribe();
}




}