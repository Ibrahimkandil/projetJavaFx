package com.mycompany.atmmanagementsys.module1.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class client {
    private int id;
    private String identification_number;
    private String password;
    private String name;
    private String last_name;
    private double balance;
    private String address;
    private String email;
    private String phone;
    public client() {
    }

    public client(int id, String identification_number, String password, String name, String last_name, double balance, String address, String email, String phone) {
        this.id = id;
        this.identification_number = identification_number;
        this.password = password;
        this.name = name;
        this.last_name = last_name;
        this.balance = balance;
        this.address = address;
        this.email = email;
        this.phone = phone;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String PasswordGenarator(){
        String password = "0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = (int)(password.length() * Math.random());
            sb.append(password.charAt(index));
        }
        return sb.toString();
    }
    public String IdnetificationNumberGenarator(){
        String password = "0123456789";
        StringBuilder sb = new StringBuilder("2");
        for (int i = 0; i <3; i++) {
            int index = (int)(password.length() * Math.random());
            sb.append(password.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "client{" +
                "id=" + id +
                ", identification_number='" + identification_number + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", balance=" + balance +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String toString_Id() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String formattedDate = dateFormat.format(new Date());
        return "client{" +
                "nom et prénom=" + name+last_name +'\'' +
                ", identification_number: '" + identification_number + '\'' +
                ", password: '" + password + '\'' +
                ", Crée Le: '" + formattedDate + '\'' +
                '}'+'\n'+"PLEASE THOSE INFORMATION ARE VERY IMPORTANT FOR YOU, PLEASE KEEP THEM IN A SAFE PLACE.";
    }
}

