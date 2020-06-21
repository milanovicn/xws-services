insert into ADMIN(id, ime, prezime, email, password, rola) values (1, 'Aca', 'Acic', 'a@gmail.com', 'acaacaaca', 'ADMIN')
insert into CLIENT(id, ime, prezime, email, password, rola,brojTelefona) values (1, 'Mika', 'Mikic', 'm@gmail.com', '12345678', 'CLIENT','111111111')
insert into CLIENT(id, ime, prezime, email, password, rola,brojTelefona) values (2, 'Zika', 'Zikic', 'z@gmail.com', '11111111', 'CLIENT','111111111')

insert into KLASA_VOZILA(id, naziv) values (1, 'Limunzina')
insert into KLASA_VOZILA(id, naziv) values (2, 'Karavan')
insert into KLASA_VOZILA(id, naziv) values (3, 'Van')

insert into TIP_GORIVA(id, naziv) values (1, 'Dizel')
insert into TIP_GORIVA(id, naziv) values (2, 'Benzin')
insert into TIP_GORIVA(id, naziv) values (3, 'Gas')

insert into MARKA(id, naziv) values (1, 'BMW')
insert into MARKA(id, naziv) values (2, 'Renault')

insert into MODEL(id, naziv,marka) values (1,'BMW120', 'BMW')
insert into MODEL(id, naziv,marka) values (2, 'Laguna','Renault')

insert into TIP_MENJACA(id, naziv) values (1, 'Manuelni')


insert into CENOVNIK(id, naziv,cenaDan,cenaPrekoraceniKm,cenaCDW, popustProcenat,autor) values (1, 'c1',35,10,40,30,1)
insert into CENOVNIK(id, naziv,cenaDan,cenaPrekoraceniKm,cenaCDW, popustProcenat,autor) values (2, 'c2',25,5,30,20,1)
insert into CENOVNIK(id, naziv,cenaDan,cenaPrekoraceniKm,cenaCDW, popustProcenat,autor) values (3, 'c3',20,5,25,30,2)
insert into CENOVNIK(id, naziv,cenaDan,cenaPrekoraceniKm,cenaCDW, popustProcenat,autor) values (4, 'c4',15,2,20,30,2)