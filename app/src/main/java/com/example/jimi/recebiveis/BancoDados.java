package com.example.jimi.recebiveis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimi on 01/10/17.
 */

public class BancoDados extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_CLIENTE = "bd_cliente";

    private static final String TABELA_CLIENTE = "tb_cliente";
    private static final String TABELA_PRODUTO = "tb_produto";

    private static final String COLUNA_CODIGO = "codigo";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_ENDERECO = "endereco";
    private static final String COLUNA_TELEFONE = "telefone";

    private static final String COLUNA_CODIGOP = "codP";
    private static final String COLUNA_NOMEP = "nomeP";
    private static final String COLUNA_MARCAP = "marcaP";
    private static final String COLUNA_MODELOP = "modeloP";
    private static final String COLUNA_DESCRICAOP = "descricaoP";
    private static final String COLUNA_PRECOP = "precoP";


    public BancoDados(Context context) {
        super(context, BANCO_CLIENTE, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String cliente = "CREATE TABLE tb_cliente (codigo INTEGER PRIMARY KEY, nome TEXT, endereco TEXT, telefone TEXT )";

        String produto = "CREATE TABLE tb_produto (codP INTEGER PRIMARY KEY, nomeP TEXT, marcaP TEXT, modeloP TEXT, descricaoP TEXT, precoP TEXT)";

        db.execSQL(cliente);
        db.execSQL(produto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    //********ADD

    void addCliente(Cliente cliente){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, cliente.getNome());
        values.put(COLUNA_ENDERECO, cliente.getEndereco());
        values.put(COLUNA_TELEFONE, cliente.getTelefone());

        db.insert(TABELA_CLIENTE, null, values);
        db.close();
    }

    void addProduto(Produtos produto){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOMEP, produto.getNomeP());
        values.put(COLUNA_MARCAP, produto.getMarcaP());
        values.put(COLUNA_MODELOP, produto.getModeloP());
        values.put(COLUNA_DESCRICAOP, produto.getDescricaoP());
        values.put(COLUNA_PRECOP, produto.getPrecoP());

        db.insert(TABELA_PRODUTO, null, values);
        db.close();
    }


    //******** Apaga

    void apagaCliente(Cliente cliente){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_CLIENTE, COLUNA_CODIGO + " = ?", new String[] { String.valueOf(cliente.getCodigo())});

        db.close();
    }

    Cliente selecionaCliente(int codigo){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_CLIENTE, new String[] {COLUNA_CODIGO, COLUNA_NOME, COLUNA_ENDERECO, COLUNA_TELEFONE}, COLUNA_CODIGO
                + " = ?", new String[] {String.valueOf(codigo)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Cliente cliente = new Cliente(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return cliente;
    }

    void atualizarCliente(Cliente cliente){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, cliente.getNome());
        values.put(COLUNA_ENDERECO, cliente.getNome());
        values.put(COLUNA_TELEFONE, cliente.getNome());

        db.update(TABELA_CLIENTE, values, COLUNA_CODIGO + " = ?", new String[] { String.valueOf(cliente.getCodigo())});

    }

    public List<Cliente> listaCliente(){
        List<Cliente> listaCliente = new ArrayList<>();

        String query = "SELECT * FROM cliente";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()){

            do{
                Cliente cliente = new Cliente();
                cliente.setCodigo(Integer.parseInt(c.getString(0)));
                cliente.setNome(c.getString(1));
                cliente.setTelefone(c.getString(2));
                cliente.setEndereco(c.getString(3));

                listaCliente.add(cliente);
            }while (c.moveToNext());
        }

        return listaCliente;
    }




}
