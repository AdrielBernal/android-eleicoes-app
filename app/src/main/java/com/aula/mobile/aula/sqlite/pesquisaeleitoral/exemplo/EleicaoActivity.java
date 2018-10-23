package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.aula.mobile.aula.R;

import java.util.ArrayList;
import java.util.List;

public class EleicaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleicao);

        /*********BANCO DE DADOS**************/
        final CandidatoHelper candidatoHelper = new CandidatoHelper(this);
        if (candidatoHelper.countCategoria() == 0) {
            candidatoHelper.addCategoria();
        }
        if (candidatoHelper.countCandidatos() == 0)
            candidatoHelper.add();
        /***********************/

        final Spinner spCategoria = findViewById(R.id.spCategoria);
        Button btIniciar = findViewById(R.id.btIniciar);
        Button btResultado = findViewById(R.id.btResultado);

        List<Categoria> categorias = new ArrayList<>();
        categorias = candidatoHelper.getListCategoria();
        configureSpinner(spCategoria, categorias);

        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Categoria categoria = (Categoria) spCategoria.getSelectedItem();
                Intent intent = new Intent(EleicaoActivity.this, EleicaoVotoActivity.class);
                intent.putExtra("id", categoria.getId());
                startActivity(intent);
            }
        });

        btResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Categoria categoria = (Categoria) spCategoria.getSelectedItem();
                List<Candidato> list = candidatoHelper.getList(categoria.getId());
                String resultado = "Resultados \n";
                for (Candidato c : list) {
                    resultado = resultado.concat(c.getNome() + "-" + c.getPartido() + "Votos= " + c.getVotos() + "\n");
                }
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configureSpinner(Spinner spCategoria, List<Categoria> categorias) {
        //toString candidato
        ArrayAdapter<Categoria> dataAdapter =
                new ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCategoria.setAdapter(dataAdapter);
    }
}
