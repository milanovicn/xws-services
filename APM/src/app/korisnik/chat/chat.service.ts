import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Chat } from "./Chat";
import { Message } from "./Message";

@Injectable()
export class ChatService{
  

    constructor(private http:HttpClient){
      
    }

    public vratiChatovePoKorisniku(email:string): Observable<Chat[]> {
        return this.http.get<Chat[]>("/user/chat/autor/"+email);
    }

    public vratiChat(id:number):Observable<Chat>{
        return this.http.get<Chat>("/user/chat/"+id);
    }

    public vratiPorukePoCetu(id:number): Observable<Message[]> {
        return this.http.get<Message[]>("/user/chat/message/"+id);
    }

    public posaljiPorukuNaCet(message:Message): Observable<Message> {
        return this.http.post<Message>("/user/chat/message",message);
    }

    //poziva se nakon odobrene rezervacije u listi zahteva za mene
    public kreirajChat(chat:Chat): Observable<Chat> {
        return this.http.post<Chat>("/user/chat/",chat);
    }
    

}