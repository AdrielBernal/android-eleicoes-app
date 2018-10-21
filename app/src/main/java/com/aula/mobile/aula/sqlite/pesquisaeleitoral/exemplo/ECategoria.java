package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo;

public enum ECategoria {

    PRESIDENTE(1, "Presidente", "BR"),
    GOVERNADOR_SP(2, "Governador", "SP"),
    GOVERNADOR_PR(3, "Governador", "PR");

    private int id;
    private String nome;
    private String estado;

    ECategoria(int id, String nome, String estado) {
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

    public static ECategoria getById(int id) {
        for (ECategoria e : values()) {
            if (e.getId() == id) return e;
        }
        return null;
    }
}
