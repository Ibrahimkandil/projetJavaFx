package com.mycompany.atmmanagementsys.module1.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class employee {


    private int id;
    private String name;
    private String mail;
    private double salaire;
    private String address;
    private String cin;
    private String Date_ajout;
    private String password;
    private int added_by;
    private String last_name;
    private String identification_number;
    public employee() {
    }

    public employee(int id, String name, String mail, double salaire, String address, String cin, String date_ajout, String password, int added_by, String last_name, String identification_number) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.salaire = salaire;
        this.address = address;
        this.cin = cin;
        Date_ajout = date_ajout;
        this.password = password;
        this.added_by = added_by;
        this.last_name = last_name;
        this.identification_number = identification_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getDate_ajout() {
        return Date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        Date_ajout = date_ajout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdded_by() {
        return added_by;
    }

    public void setAdded_by(int added_by) {
        this.added_by = added_by;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getIdentification_number() {
        return identification_number;
    }

    public void setIdentification_number(String identification_number) {
        this.identification_number = identification_number;
    }

    @Override
    public String toString() {
        return "employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", salaire=" + salaire +
                ", address='" + address + '\'' +
                ", cin='" + cin + '\'' +
                ", Date_ajout='" + Date_ajout + '\'' +
                ", password='" + password + '\'' +
                ", added_by=" + added_by +
                ", last_name='" + last_name + '\'' +
                ", identification_number='" + identification_number + '\'' +
                '}';
    }
    public String toString_Id() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String formattedDate = dateFormat.format(new Date());
        return "Employee{" +
                "nom et prénom=" + name+last_name +'\'' +
                ", identification_number: '" + identification_number + '\'' +
                ", password: '" + password + '\'' +
                ", Crée Le: '" + formattedDate + '\'' +
                '}'+'\n'+"PLEASE THOSE INFORMATION ARE VERY IMPORTANT FOR YOU, PLEASE KEEP THEM IN A SAFE PLACE.";
    }
}
