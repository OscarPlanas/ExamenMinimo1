package edu.upc.dsa.models;

public class User {
    private String password;
    private String name;
    private String surname1;
    private String surname2;
    private String mail;
    private String fecha;
    private int coins;
    private String id;


    public User(String password, String name, String surname1, String surname2, String mail, String fecha, int coins, String id) {
        this.password = password;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.mail = mail;
        this.fecha = fecha;
        this.coins = coins;
        this.id = id;

    }
    public User(){
        //Empty Constructor Initialization for second cases
        //Objects list of User is always initialized empty
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}