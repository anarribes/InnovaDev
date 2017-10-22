package com.example.jimi.recsimples;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CadPedido extends AppCompatActivity {

    EditText edtTotal;
    Button btnCad, btnLimpar;
    Spinner spiCliente;
    Spinner spiProduto;

    final BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_cliente);

        spiCliente = (Spinner) findViewById(R.id.spiCliente);
        spiProduto = (Spinner) findViewById(R.id.spiProduto);

        edtTotal = (EditText) findViewById(R.id.edtTotal);

        btnCad = (Button) findViewById(R.id.btnCad);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);

        listaCliente();
        listaProduto();

        //listVCliente = view.findViewById(R.id.listViClientes);

        btnLimpar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                limparCampos();
            }
        });

        btnCad.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String cliente = spiCliente.toString();
                String produto = spiProduto.toString();
                String total = edtTotal.getText().toString();

                if(cliente.isEmpty() || produto.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Os campos cliente e produto não podem estar vazios ", Toast.LENGTH_LONG).show();
                }
                else{
                        try {
                            db.addPedido(new Pedido(cliente, produto, Float.parseFloat(total)));
                            Toast.makeText(getApplicationContext(), "Novo pedido cadastrado com sucesso: " + cliente, Toast.LENGTH_LONG).show();
                        }catch (Throwable e) {
                            e.printStackTrace();
                        }

                        limparCampos();

                    }
                    /*else{
                        //update
                        //db.atualizarCliente(new Cliente(Integer.parseInt(codigo), nome,telefone,endereco));
                        //Toast.makeText(getActivity().getApplicationContext(), "Cliente atualizado com sucesso, dados: " + codigo + nome + endereco + telefone, Toast.LENGTH_LONG).show();
                        //limparCampos();
                        //listaCliente();
                    }*/


            }
        });

        /*btnExcluir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String codigo = edtCodigo.getText().toString();

                if (codigo.isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(), "Nenhum cliente selecionado", Toast.LENGTH_LONG).show();
                }
                else{

                    Cliente cliente = new Cliente();
                    cliente.setCodigo(Integer.parseInt(codigo));
                    db.apagaCliente(cliente);
                    limparCampos();
                    Toast.makeText(getActivity().getApplicationContext(), "Cliente excluido com sucesso", Toast.LENGTH_LONG).show();
                }
            }

        });*/


    }

    public void listaCliente() {

        Cursor data = db.selectAll();

        ArrayList<String> listaDados = new ArrayList<>();

        while (data.moveToNext()) {
            listaDados.add(data.getString(1));
        }

        ListAdapter adapterCli = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaDados);

        spiCliente.setAdapter(SpinnerAdapter);

    }

    public void listaProduto() {

        Cursor data = db.selectAllProd();

        ArrayList<String> listaDados = new ArrayList<>();

        while (data.moveToNext()) {
            listaDados.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaDados);

        spiCliente.setAdapter((SpinnerAdapter) adapter);

    }

    void limparCampos(){

    }

}
