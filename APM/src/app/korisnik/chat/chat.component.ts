import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { Chat } from './Chat';
import { Message } from './Message';
import { ChatService } from './chat.service';
import { Korisnik } from 'src/app/login/Korisnik';
import { KorisnikService } from 'src/app/admin/lista-korisnika/korisnici.services';
import { ThrowStmt } from '@angular/compiler';

@Component({
    templateUrl: './chat.html',
})
export class ChatComponent implements OnInit {

    errorMessage = '';
    chat: Chat;
    id: number;
    korisnik: Korisnik;
    poruke: Message[] = [];
    novaPoruka:Message;

    tekstPoruke: string;

    constructor(private httpClient: HttpClient, private route: ActivatedRoute,
        private router: Router, private chatService: ChatService, private login: KorisnikService) {
        this.chat = new Chat();
        this.korisnik = new Korisnik();
        this.novaPoruka = new Message();
    }


    ngOnInit(): void {
        const param = this.route.snapshot.paramMap.get('id');
        if (param) {
            this.id = +param;
            this.getProduct(this.id);

        }


        this.login.getKorisnika().subscribe({
            next: korisnik => { this.korisnik = korisnik; }
        });

        

    }

    getProduct(id: number) {
        this.chatService.vratiChat(id).subscribe(
            chat => {this.chat = chat;
                this.chatService.vratiPorukePoCetu(this.chat.id).subscribe({
                    next: poruke => {
                      this.poruke = poruke;
                    }
                  });
            },
            error => this.errorMessage = <any>error
        );
    }

    posaljiPoruku() {

        this.novaPoruka.content=this.tekstPoruke;
        this.novaPoruka.senderEmail=this.korisnik.email;
        this.novaPoruka.timestamp = new Date();
        this.novaPoruka.chatId = this.chat.id;

        this.chatService.posaljiPorukuNaCet(this.novaPoruka).subscribe({
            next: povratna=>{
                let poruka=povratna;
                if(poruka!=null){
                    window.location.reload();
                }
            }
        });
       
        
    }

}