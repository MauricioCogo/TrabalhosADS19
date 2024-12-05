package com.example.tarfas.Entity;

import androidx.annotation.NonNull;

import java.util.List;

public class Tarefa {

    private String id;  // Atributo ID para identificar a tarefa
    private String titulo;
    private String descricao;
    private String dataVencimento;
    private String prioridade;
    private List<Subtarefa> subtarefas;
    private String status;  // Atributo para representar o status da tarefa (Concluída ou Pendente)
    private String categoria;  // Categoria da tarefa (pode ser uma categoria textual)
    private String nomeCategoria;  // Novo atributo para o nome da categoria
    private String corCategoria;  // Novo atributo para a cor da categoria

    // Construtor
    public Tarefa(String id, String titulo, String descricao, String dataVencimento, String prioridade,
                  List<Subtarefa> subtarefas, String status, String nomeCategoria, String corCategoria) {
        this.id = id;  // Inicializando o id
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.prioridade = prioridade;
        this.subtarefas = subtarefas;
        this.status = status;  // Inicializando o status
        this.nomeCategoria = nomeCategoria;  // Inicializando o nomeCategoria
        this.corCategoria = corCategoria;  // Inicializando a corCategoria
    }

    public Tarefa(String titulo, String descricao, String dataVencimento, String prioridade,
                  List<Subtarefa> subtarefas, String status, String nomeCategoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.prioridade = prioridade;
        this.subtarefas = subtarefas;
        this.status = status;  // Inicializando o status
        this.nomeCategoria = nomeCategoria;  // Inicializando o nomeCategoria
        this.corCategoria = corCategoria;  // Inicializando a corCategoria
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public List<Subtarefa> getSubtarefas() {
        return subtarefas;
    }

    public void setSubtarefas(List<Subtarefa> subtarefas) {
        this.subtarefas = subtarefas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getCorCategoria() {
        return corCategoria;
    }

    public void setCorCategoria(String corCategoria) {
        this.corCategoria = corCategoria;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID: ").append(id).append("\n");  // Exibindo o id da tarefa
        sb.append("Tarefa: ").append(titulo).append("\n");
        sb.append("Descrição: ").append(descricao).append("\n");
        sb.append("Data de Vencimento: ").append(dataVencimento).append("\n");
        sb.append("Prioridade: ").append(prioridade).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Categoria: ").append(categoria).append("\n");
        sb.append("Nome da Categoria: ").append(nomeCategoria).append("\n");  // Exibindo nome da categoria
        sb.append("Cor da Categoria: ").append(corCategoria).append("\n");  // Exibindo cor da categoria

        // Adicionando as subtarefas
        if (subtarefas != null && !subtarefas.isEmpty()) {
            sb.append("Subtarefas: \n");
            for (Subtarefa subtarefa : subtarefas) {
                sb.append("  - ").append(subtarefa.getTitulo())
                        .append(" (Status: ").append(subtarefa.getStatus()).append(")\n");
            }
        } else {
            sb.append("Nenhuma subtarefa adicionada.\n");
        }

        return sb.toString();
    }
}
