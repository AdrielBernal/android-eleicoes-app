package com.aula.mobile.aula.retrofit.exemplo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.aula.mobile.aula.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitRepositoriesActivity extends AppCompatActivity {

    private ListView listView;
    private EditText tvText;
    private ImageView ivSearch;
    private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_repositories);

        final IGitService service = RetrofitUtil.build().create(IGitService.class);

        listView = findViewById(R.id.list);
        tvText = findViewById(R.id.tvText);
        ivSearch = findViewById(R.id.ivSearch);

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = tvText.getText().toString();
                if (user != null) {
                    progress = new ProgressDialog(GitRepositoriesActivity.this);
                    progress.setTitle("Buscando");
                    progress.setMessage("Carregando respositórios...");
                    progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                    progress.show();
                    getGit(service, user.trim());
                }
            }
        });
    }

    private void getGit(IGitService service, String user) {
        Call<List<Repo>> repos = service.listRepos(user);
        //enfileirar
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> list = response.body();
                setListView(list);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                //Erro
                // To dismiss the dialog
                progress.dismiss();
            }
        });
    }

    private void setListView(List<Repo> list) {
        if (list != null) {
            String[] values = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                values[i] = list.get(i).getName();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, values);

            listView.setAdapter(adapter);
        }
        progress.dismiss();
        hideKeyboard();
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
