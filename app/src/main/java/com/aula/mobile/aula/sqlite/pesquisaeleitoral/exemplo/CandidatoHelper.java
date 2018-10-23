package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class CandidatoHelper {

    private DbHelper dbHelper;
    public static final String TABLE_CANDIDATO = "candidato";
    public static final String TABLE_CATEGORIA = "categoria";

    public CandidatoHelper(Context context) {
        dbHelper = new DbHelper(context);
    }

    public List<Candidato> getList(int idCategoria) {
        List<Candidato> list = new ArrayList<>(0);
        Cursor cursor = dbHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM candidato WHERE idCategoria = " + idCategoria + " ORDER BY nome ASC", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(cursor.getColumnIndex("id"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String partido = cursor.getString(cursor.getColumnIndex("partido"));
                int votos = cursor.getInt(cursor.getColumnIndex("votos"));
                list.add(new Candidato(ID, nome, partido, votos));
                cursor.moveToNext();
            }
        }
        return list;
    }

    public List<Categoria> getListCategoria() {
        List<Categoria> list = new ArrayList<>(0);
        Cursor cursor = dbHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM categoria ", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(cursor.getColumnIndex("id"));
                String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
                String estado = cursor.getString(cursor.getColumnIndex("estado"));
                list.add(new Categoria(ID, descricao, estado));
                cursor.moveToNext();
            }
        }
        return list;
    }

    public void add() {
        insertCandidato("Jair Bolsonaro", "PSL", 1, 0);
        insertCandidato("Fernando Haddad", "PT", 1, 0);
        insertCandidato("Ciro Gomes", "PDT", 1, 0);
        insertCandidato("Geraldo Alckmin", "PSBD", 1, 0);
        //
        insertCandidato("Ratinho JR", "PSD", 3, 0);
        insertCandidato("Cida", "PP", 3, 0);
        insertCandidato("João Arruda", "MDB", 3, 0);
    }

    public void addCategoria() {
        insertCategoria("Presidente", "BR");
        insertCategoria("Governador", "SP");
        insertCategoria("Governador", "PR");
    }

    private void insertCategoria(String descricao, String estado) {
        ContentValues values = new ContentValues();
        values.put("descricao", descricao);
        values.put("estado", estado);
        dbHelper.getWritableDatabase().insert(TABLE_CATEGORIA, null, values);
    }

    private void insertCandidato(String nome, String partido, int idCategoria, int votos) {
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("partido", partido);
        values.put("idCategoria", idCategoria);
        values.put("votos", votos);
        dbHelper.getWritableDatabase().insert(TABLE_CANDIDATO, null, values);
    }

    public void addVoto(Candidato item) {
        ContentValues values = new ContentValues();
        values.put("votos", item.getVotos());
        dbHelper.getWritableDatabase().update(TABLE_CANDIDATO, values, "id=" + item.getId(), null);
    }

    public int countCandidatos() {
        Cursor mCount = dbHelper.getReadableDatabase()
                .rawQuery("select count(*) from " + TABLE_CANDIDATO, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }

    public final int countCategoria() {
        Cursor mCount = dbHelper.getReadableDatabase()
                .rawQuery("select count(*) from " + TABLE_CATEGORIA, null);
        mCount.moveToFirst();
        final int count = mCount.getInt(0);
        mCount.close();
        return count;
    }

    public Categoria getCategoriaById(int id) {
        for (Categoria c : getListCategoria()) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}
