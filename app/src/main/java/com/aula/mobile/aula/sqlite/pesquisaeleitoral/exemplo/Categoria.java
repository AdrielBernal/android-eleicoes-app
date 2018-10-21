package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo;

public class Categoria {

    private int id;
    private String nome;
    private String estado;

    public Categoria(int id, String nome, String estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return nome + " - " + estado;
    }
}
