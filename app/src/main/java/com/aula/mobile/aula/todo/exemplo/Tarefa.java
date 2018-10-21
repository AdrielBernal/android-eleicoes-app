package com.aula.mobile.aula.todo.exemplo;

public class Tarefa {


    private String tarefa;

    public Tarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    @Override
    public boolean equals(Object obj) {
        Tarefa tarefa = (Tarefa) obj;
        return tarefa.getTarefa().equals(this.tarefa);
    }
}




