package com.example.jimi.recsimples;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

//import static com.example.jimi.recebiveis.R.id.edtCodigo;


public class ListaCliente extends AppCompatActivity {

    Button btnAdd;

    final BancoDados db = new BancoDados(this);

    public ListaCliente() {

    }

    ListView ListViewCliente;
    //ArrayAdapter<String> adapter;
    //ArrayList<String> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes);

        ListViewCliente = (ListView) findViewById(R.id.listViClientes);

        showData(db.selectAll());

        //btnAdd = (Button) findViewById(R.id.);

        listaCliente();

        /*btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //chamar o cad de clientes aqui
            }

        });*/

        /*listVCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String conteudo = (String) listVCliente.getItemAtPosition(position);

                Toast.makeText(getActivity().getApplicationContext(), "Selecionando: " + conteudo, Toast.LENGTH_LONG).show();

                String codigo = conteudo.substring(0, conteudo.indexOf("-"));

                Cliente cliente = db.selecionaCliente(Integer.parseInt(codigo));
            }
        });*/

    }

    public void showData(Cursor c) {
        while (c.moveToNext()) {
            Log.i(c.getString(1), c.getString(2));
        }
    }


    public void listaCliente() {

        Cursor data = db.selectAll();

        ArrayList<String> listaDados = new ArrayList<>();

        //adapter = new ArrayAdapter<Cliente>(getActivity(), android.R.layout.simple_list_item_1, clientes);

       //listVCliente.setAdapter(adapter);

        while (data.moveToNext()){
            listaDados.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDados);

        ListViewCliente.setAdapter(adapter);

       //ArrayAdapter<Cliente> clienteAdapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientes);
       //listVCliente.setAdapter(clienteAdapter);

       /*for (Cliente c : clientes) {
            arraylist.add(c.getCodigo() + "-" + c.getNome());
            adapter.notifyDataSetChanged();

       }*/
    }

}
