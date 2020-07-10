package com.example.adninservice.model;

import javax.persistence.*;

@Entity
public class Client  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ime", nullable = false)
    private String ime;

    @Column(name = "Prezime", nullable = false)
    private String prezime;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;


    @Column(name = "Rola", nullable = false)
    private String rola;

    @Column(name = "BrojTelefona", nullable = false)
    private String brojTelefona;

    @Column(name = "BrojObjavljenihOglasa", nullable = false)
    private int brojObjavljenihOglasa;


    @Column(name = "Blokiran", nullable = false)
    private boolean blokiran;


    @Column(name = "BrojOtkazanihOglasa", nullable = false)
    private int brojOtkazanihOglasa;

   /* @Column(name = "SaltValue", nullable = false)
    private String saltValue;

    @Column(name = "HashedPassAndSalt", nullable = false)
    private byte[] hashedPassAndSalt;

    public String getSaltValue() {
        return saltValue;
    }

    public void setSaltValue(String saltValue) {
        this.saltValue = saltValue;
    }
*/

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rola='" + rola + '\'' +
                ", brojTelefona='" + brojTelefona + '\'' +
                ", brojObjavljenihOglasa=" + brojObjavljenihOglasa +
                ", blokiran=" + blokiran +
                ", brojOtkazanihOglasa=" + brojOtkazanihOglasa +
                '}';
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public int getBrojObjavljenihOglasa() {
        return brojObjavljenihOglasa;
    }

    public void setBrojObjavljenihOglasa(int brojObjavljenihOglasa) {
        this.brojObjavljenihOglasa = brojObjavljenihOglasa;
    }

    public boolean isBlokiran() {
        return blokiran;
    }

    public void setBlokiran(boolean blokiran) {
        this.blokiran = blokiran;
    }

    public int getBrojOtkazanihOglasa() {
        return brojOtkazanihOglasa;
    }

    public void setBrojOtkazanihOglasa(int brojOtkazanihOglasa) {
        this.brojOtkazanihOglasa = brojOtkazanihOglasa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public Client(String ime, String prezime, String email, String password, String rola, String brojTelefona) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.password = password;
        this.rola = rola;
        this.brojTelefona = brojTelefona;
        this.brojObjavljenihOglasa = 0;
        this.blokiran = false;
        this.brojOtkazanihOglasa = 0;
     //   this.saltValue="";
      //  this.hashedPassAndSalt = new byte[16];
    }
/*
    public byte[] getHashedPassAndSalt() {
        return hashedPassAndSalt;
    }

    public void setHashedPassAndSalt(byte[] hashedPassAndSalt) {
        this.hashedPassAndSalt = hashedPassAndSalt;
    }
*/
    public Client() {
    }
}
