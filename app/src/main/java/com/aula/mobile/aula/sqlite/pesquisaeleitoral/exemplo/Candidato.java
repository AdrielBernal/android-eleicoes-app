package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo;

public class Candidato {

    private int id;
    private String nome;
    private String partido;
    private int idCategoria;
    private int votos;

    public Candidato(int id, String nome, String partido, int votos) {
        this.id = id;
        this.nome = nome;
        this.partido = partido;
        this.votos = votos;
    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public String getPartido() {
        return partido;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    @Override
    public String toString() {
        return nome + " - " + partido;
    }
}
