package com.aula.mobile.aula.login.exemplo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aula.mobile.aula.R;
import com.aula.mobile.aula.todo.exemplo.TodoActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btEntrar;
    private TextInputEditText tvLogin, tvSenha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String login = LoginUtil.get(this);
        if (login != null) {
            Intent intent = new Intent(LoginActivity.this, TodoActivity.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_login);
            btEntrar = findViewById(R.id.btEntrar);
            tvLogin = findViewById(R.id.tvLogin);
            tvSenha = findViewById(R.id.tvSenha);

            init();

            btEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String login = tvLogin.getText().toString();
                    String senha = tvSenha.getText().toString();
                    if (login.equals("admin") && senha.equals("1")) {
                        LoginUtil.save(getApplicationContext(), login, senha);
                        Intent intent = new Intent(LoginActivity.this, TodoActivity.class);
                        startActivity(intent);
                        //finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Acesso bloqueado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void init() {
        String login = LoginUtil.get(this);
        if (login != null) {
            Toast.makeText(this, "Útimo login: " + login, Toast.LENGTH_LONG).show();
        }
    }
}
