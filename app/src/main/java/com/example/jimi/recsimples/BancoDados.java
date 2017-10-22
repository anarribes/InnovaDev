package com.example.jimi.recsimples;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimi on 01/10/17.
 */

public class BancoDados extends SQLiteOpenHelper {

    public BancoDados(Context context) {
        super(context, BANCO_CLIENTE, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String cliente = "CREATE TABLE tb_cliente (codigo INTEGER PRIMARY KEY, nome TEXT, endereco TEXT, telefone TEXT, cpf TEXT )";

        String produto = "CREATE TABLE tb_produto (codP INTEGER PRIMARY KEY, nomeP TEXT, marcaP TEXT, modeloP TEXT, descricaoP TEXT, precoP TEXT)";

        db.execSQL(cliente);
        db.execSQL(produto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_CLIENTE = "bd_cliente";

    private static final String TABELA_CLIENTE = "tb_cliente";
    private static final String TABELA_PRODUTO = "tb_produto";
    private static final String TABELA_PEDIDO = "tb_pedido";

    private static final String COLUNA_CODIGO = "codigo";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_ENDERECO = "endereco";
    private static final String COLUNA_TELEFONE = "telefone";
    private static final String COLUNA_CPF = "cpf";

    private static final String COLUNA_CODIGOP = "codP";
    private static final String COLUNA_NOMEP = "nomeP";
    private static final String COLUNA_MARCAP = "marcaP";
    private static final String COLUNA_MODELOP = "modeloP";
    private static final String COLUNA_DESCRICAOP = "descricaoP";
    private static final String COLUNA_PRECOP = "precoP";

    private static final String COLUNA_CODIGOPED = "codPED";
    private static final String COLUNA_CLIENTEPED = "clientePED";
    private static final String COLUNA_PRODUTOPED = "produtoPED";
    private static final String COLUNA_TOTALPED = "totalPED";

    private SQLiteDatabase db;


    //********ADD

    void addCliente(Cliente cliente){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, cliente.getNome());
        values.put(COLUNA_ENDERECO, cliente.getEndereco());
        values.put(COLUNA_TELEFONE, cliente.getTelefone());
        values.put(COLUNA_CPF, cliente.getCpf());

        db.insert(TABELA_CLIENTE, null, values);
        Log.d("minhatag", "deu add no banco");
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

    void addPedido(Pedido pedido){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_CLIENTEPED, pedido.getCliente());
        values.put(COLUNA_PRODUTOPED, pedido.getProduto());
        values.put(COLUNA_TOTALPED, pedido.getTotal());

        db.insert(TABELA_PEDIDO, null, values);
        Log.d("minhatag", "deu add no banco PEDIDO");
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

        Cliente cliente = new Cliente(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

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

    // Get all the records
    public Cursor selectAll() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * from tb_cliente", null);

        return c;

    }

    public Cursor selectAllProd() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * from tb_produto", null);

        return c;

    }

    // Find a specific record
    public Cursor searchName(String name) {

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COLUNA_CODIGO + ", " + COLUNA_NOME + " from " + TABELA_CLIENTE + " WHERE " + COLUNA_NOME + " = '" + name + "';";

        Log.i("searchName() = ", query);
        Cursor c = db.rawQuery(query, null);

        return c;
    }






    /*public List<Cliente> listaCliente(){
        List<Cliente> listaCliente = new ArrayList<>();

        String query = "SELECT * FROM tb_cliente";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(query, null);

        Log.d("minhatag", "antes try");

        try {
                do {
                    Cliente cliente = new Cliente();

                    cliente.setCodigo(0);
                    Log.d("minhatag", "************************ o caralho comeca ai ******************");

                    cliente.setNome("2");
                    cliente.setTelefone("1");

                    Log.i(c.getString(1), c.getString(2));
                    c.moveToNext();


                    //cliente.setEndereco(toString(c.getColumnName(3)));
                    //cliente.setCpf(c.getColumnName(4));

                    listaCliente.add(cliente);
                    Log.d("minhatag", "************************ passou uma vez ******************");

                } while (c.moveToNext());
            }catch (Throwable e) {
            e.printStackTrace();
        }

        Log.d("minhatag", "nem passou pelo try");
        return listaCliente;
    }*/




}
