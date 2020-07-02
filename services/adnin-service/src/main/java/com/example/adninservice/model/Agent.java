package com.example.adninservice.model;

import javax.persistence.*;

@Entity
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NazivFirme", nullable = false)
    private String nazivFirme;


    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;


    @Column(name = "Rola", nullable = false)
    private String rola;

    @Column(name = "Adresa", nullable = false)
    private String adresa;

    @Column(name = "Odobren", nullable = false)
    private boolean odobren;

    /*@Column(name = "SaltValue", nullable = false)
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
    public Agent() {
    }

    public Agent(String nazivFirme, String email, String password, String adresa) {
        this.nazivFirme = nazivFirme;
        this.email = email;
        this.password = password;
        this.adresa = adresa;
        this.rola= "AGENT";
        this.odobren=false;
       // this.saltValue="";
    //    this.hashedPassAndSalt = new byte[16];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivFirme() {
        return nazivFirme;
    }

    public void setNazivFirme(String nazivFirme) {
        this.nazivFirme = nazivFirme;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

   // public byte[] getHashedPassAndSalt() {
    //    return hashedPassAndSalt;
   // }

   // public void setHashedPassAndSalt(byte[] hashedPassAndSalt) {
   //     this.hashedPassAndSalt = hashedPassAndSalt;
  //  }

    public boolean isOdobren() {
        return odobren;
    }

    public void setOdobren(boolean odobren) {
        this.odobren = odobren;
    }
}
