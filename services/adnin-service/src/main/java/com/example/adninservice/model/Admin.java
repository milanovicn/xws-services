package com.example.adninservice.model;


import javax.persistence.*;

@Entity
public class Admin{


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

    public byte[] getHashedPassAndSalt() {
        return hashedPassAndSalt;
    }

    public void setHashedPassAndSalt(byte[] hashedPassAndSalt) {
        this.hashedPassAndSalt = hashedPassAndSalt;
    }
*/
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

    public Admin(String ime, String prezime, String email, String password, String rola) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.password = password;
        this.rola = rola;
      //  this.saltValue="";
        //this.hashedPassAndSalt = new byte[16];
    }

    public Admin() {
    }
}
