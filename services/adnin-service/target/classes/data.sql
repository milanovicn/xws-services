--insert into admin(id, ime, prezime, email, password, rola) values (1, 'Aca', 'Acic', 'a@gmail.com', 'acaacaaca', 'ADMIN')
--insert into client(id, ime, prezime, email, password, rola, broj_telefona, blokiran, broj_objavljenih_oglasa, broj_otkazanih_oglasa) values (1, 'Mika', 'Mikic', 'm@gmail.com', '12345678', 'CLIENT','111111111', 0, 0, 0)
--insert into client(id, ime, prezime, email, password, rola, broj_telefona, blokiran, broj_objavljenih_oglasa, broj_otkazanih_oglasa) values (2, 'Zika', 'Zikic', 'z@gmail.com', '11111111', 'CLIENT','111111111', 0, 0, 0)

insert into klasa_vozila(id, naziv) values (1, 'Limunzina')
insert into klasa_vozila(id, naziv) values (2, 'Karavan')
insert into klasa_vozila(id, naziv) values (3, 'Van')

insert into tip_goriva(id, naziv) values (1, 'Dizel')
insert into tip_goriva(id, naziv) values (2, 'Benzin')
insert into tip_goriva(id, naziv) values (3, 'Gas')

insert into marka(id, naziv) values (1, 'BMW')
insert into marka(id, naziv) values (2, 'Renault')

insert into model(id, naziv,marka) values (1,'BMW120', 'BMW')
insert into model(id, naziv,marka) values (2, 'Laguna','Renault')

insert into tip_menjaca(id, naziv) values (1, 'Manuelni')


insert into cenovnik(id, naziv,cena_dan, cena_prekoraceni_km, cenacdw, popust_procenat, autor) values (1, 'c1',35,10,40,30,1)
insert into cenovnik(id, naziv,cena_dan, cena_prekoraceni_km, cenacdw, popust_procenat, autor) values (2, 'c2',25,5,30,20,1)
insert into cenovnik(id, naziv,cena_dan, cena_prekoraceni_km, cenacdw, popust_procenat, autor) values (3, 'c3',20,5,25,30,2)
insert into cenovnik(id, naziv,cena_dan, cena_prekoraceni_km, cenacdw, popust_procenat, autor) values (4, 'c4',15,2,20,30,2)
