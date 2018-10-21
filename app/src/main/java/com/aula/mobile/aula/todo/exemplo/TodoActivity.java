package com.aula.mobile.aula.todo.exemplo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.aula.mobile.aula.R;
import com.aula.mobile.aula.login.exemplo.LoginActivity;
import com.aula.mobile.aula.login.exemplo.LoginUtil;

import java.util.LinkedList;
import java.util.List;

public class TodoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewList;
    private FloatingActionButton fabAdd;
    private List<Tarefa> list;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        getElements();
        addActions();
        Toolbar toolbar = findViewById(R.id.iToolbar);
        toolbar.setTitle("Lista");
        setSupportActionBar(toolbar);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewList.setLayoutManager(mLayoutManager);

        list = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new Tarefa("Tarefa " + i));
        }

        adapter = new TodoAdapter(this, list);

        mRecyclerViewList.setAdapter(adapter);
        notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                //Toast.makeText(getApplicationContext(), "Sair", Toast.LENGTH_SHORT).show();
                LoginUtil.remove(getApplicationContext());
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//limpa todas as activities
                //OR
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    private void addActions() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });
    }

    private void getElements() {
        mRecyclerViewList = findViewById(R.id.rvList);
        fabAdd = findViewById(R.id.fbAdd);
    }

    private void addTask() {
        final EditText etTarefa = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add Tarefa")
                .setMessage("O que deseja adicionar?")
                .setView(etTarefa)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list.add(new Tarefa(etTarefa.getText().toString()));
                        notifyDataSetChanged();
                    }
                }).setNegativeButton("Cancelar", null)
                .create();
        dialog.show();

    }

    public void onRemove(final Tarefa item) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Tarefa")
                .setMessage("Deseja remover a tarefa " + item.getTarefa().toString() + " ?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list.remove(item);
                        notifyDataSetChanged();
                    }
                }).setNegativeButton("Não", null)
                .create();
        dialog.show();

    }

    public void onEdit(final Tarefa item) {
        final EditText etTarefa = new EditText(this);
        etTarefa.setText(item.getTarefa());
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Tarefa")
                .setMessage("Editando tarefa")
                .setView(etTarefa)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        item.setTarefa(etTarefa.getText().toString());
                        notifyDataSetChanged();
                    }
                }).setNegativeButton("Cancelar", null)
                .create();
        dialog.show();

    }
}
