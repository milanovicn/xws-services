import { Component, NgModule, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Chat } from './Chat';
import { ChatPrikazLista } from './ChatPrikazLista';
import { ChatService } from './chat.service';
import { Korisnik } from 'src/app/login/Korisnik';
import { KorisnikService } from 'src/app/admin/lista-korisnika/korisnici.services';

@Component({

  templateUrl: './lista-mojih-chatova.html'

})

export class ListaMojihChatovaComponent implements OnInit {

  myChats: Chat[] = [];
  myChatsPrikaz: ChatPrikazLista[] = [];
  korisnik: Korisnik;


  constructor(private route: ActivatedRoute, private router: Router, private chatService: ChatService, private login: KorisnikService) {


  }

  ngOnInit(): void {

    this.login.getKorisnika().subscribe({
      next: korisnik => {
        this.korisnik = korisnik;
        this.chatService.vratiChatovePoKorisniku(this.korisnik.email).subscribe({
          next: chats => {
            this.myChats = chats;

            this.myChats.forEach(element => {
              let userEmail;

              if (element.user1.valueOf() == this.korisnik.email.valueOf()) {
                userEmail = element.user2;
              } else {
                userEmail = element.user1;
              }

              let c = new ChatPrikazLista(element.id, userEmail);
              this.myChatsPrikaz.push(c);
            })
             console.log(this.myChatsPrikaz);
          }
         
        });

      }


    });
  }

}