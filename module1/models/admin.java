package com.mycompany.atmmanagementsys.module1.models;

public class admin {
    private int id ;
    private String identification_number;
    private String password;
    private String name;
    private String last_name;
    private String mail;


    public admin(int id, String identification_number, String password, String name, String last_name, String mail) {
        this.id = id;
        this.identification_number = identification_number;
        this.password = password;
        this.name = name;
        this.last_name = last_name;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentification_number() {
        return identification_number;
    }

    public void setIdentification_number(String identification_number) {
        this.identification_number = identification_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public String toString() {
        return "admin{" +
                "id=" + id +
                ", identification_number='" + identification_number + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
