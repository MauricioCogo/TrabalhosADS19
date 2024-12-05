package com.example.tarfas.Entity;

public class Subtarefa {
    private String id;  // Atributo ID para identificar a subtarefa
    private String titulo;
    private String status;

    // Construtor
    public Subtarefa(String id, String titulo, String status) {
        this.id = id;  // Inicializando o id
        this.titulo = titulo;
        this.status = status;
    }

    public Subtarefa( String titulo, String status) {
        this.titulo = titulo;
        this.status = status;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Sobrescrevendo o toString() para exibir o título e o id da subtarefa
    @Override
    public String toString() {
        return "ID: " + id + "\n" + "Título: " + titulo + "\n";  // Exibindo o ID junto com o título
    }
}
