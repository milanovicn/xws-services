import { Vozilo } from '../vozilo/Vozilo';

export class ZahtevRezervacije{
    id:number;
    vozila:Vozilo[];
   datumOd:Date;
    datumDo:Date;
    izdavac:number;
    izdavacMail:string;
    podnosilac:number;
    stanje:string;
    vremeKreiranja:Date;
    vremeOdobrenja:Date;
}