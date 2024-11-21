package com.example.checkpoint.model;

public class Empresa {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String endereco;
    private String departamento;
    private String senha;

    public Empresa(String nomeFantasia, String razaoSocial, String cnpj, String endereco, String departamento, String senha) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.departamento = departamento;
        this.senha = senha;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getSenha() {
        return senha;
    }
}