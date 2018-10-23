package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo;

public class Categoria {

    private int id;
    private String descricao;
    private String estado;

    public Categoria(int id, String descricao, String estado) {
        this.id = id;
        this.descricao = descricao;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return descricao + " - " + estado;
    }
}
