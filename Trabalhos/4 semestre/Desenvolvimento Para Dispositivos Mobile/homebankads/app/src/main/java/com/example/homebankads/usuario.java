package com.example.homebankads;

public class usuario {

    private int id;
    private String login;
    private String senha;
    private double saldo;

    public usuario() {

    }

    public usuario(int id,String login, String senha, double saldo) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.saldo = saldo;
    }
    public usuario(String login, String senha, double saldo) {
        this.login = login;
        this.senha = senha;
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}