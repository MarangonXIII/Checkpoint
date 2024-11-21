package com.example.checkpoint.model;

public class Departamento {
    private String nome;
    private String empresa;
    private String usuario;

    // Construtor
    public Departamento(String nome, String empresa, String usuario) {
        this.nome = nome;
        this.empresa = empresa;
        this.usuario = usuario;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}