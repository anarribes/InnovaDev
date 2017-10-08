package com.example.jimi.recebiveis;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CadCliente extends Fragment {


    public CadCliente() {

    }

    EditText edtCodigo, edtNome, edtEndereco, edtTelefone;
    RadioButton radMasc, radFem;
    Button btnSalvar, btnLimpar, btnExcluir;
    ListView listVCliente;

    BancoDados db = new BancoDados(getActivity());

    ArrayAdapter<String> adapter;
    ArrayList<String> arraylist;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cad_cliente, null);

        edtCodigo = view.findViewById(R.id.edtCodigo);
        edtNome = view.findViewById(R.id.edtNome);
        edtEndereco = view.findViewById(R.id.edtEndereco);
        edtTelefone = view.findViewById(R.id.edtTelefone);

        radMasc = view.findViewById(R.id.radMasc);
        radFem = view.findViewById(R.id.radFem);

        btnExcluir = view.findViewById(R.id.btnExcluir);
        btnSalvar = view.findViewById(R.id.btnSalvar);
        btnLimpar = view.findViewById(R.id.btnLimpar);

        listVCliente = view.findViewById(R.id.listViClientes);

        btnLimpar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                limparCampos();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String codigo = edtCodigo.getText().toString();
                String nome = edtNome.getText().toString();
                String endereco = edtEndereco.getText().toString();
                String telefone = edtTelefone.getText().toString();

                if(nome.isEmpty()){
                    edtNome.setText("Este campo é obrigatório");
                }
                else{

                    if(codigo.isEmpty()){
                        //insert
                        db.addCliente(new Cliente(nome,telefone,endereco));
                        Toast.makeText(getActivity().getApplicationContext(), "Cliente adicionado com sucesso", Toast.LENGTH_LONG).show();
                        limparCampos();
                        listaCliente();
                    }
                    else{
                        //update
                       // db.atualizarCliente(new Cliente(Integer.parseInt(codigo), nome,telefone,endereco));
                        Toast.makeText(getActivity().getApplicationContext(), "Cliente atualizado com sucesso", Toast.LENGTH_LONG).show();
                        limparCampos();
                        //listaCliente();
                    }

                }


            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener(){
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
                    listaCliente();
                    Toast.makeText(getActivity().getApplicationContext(), "Cliente excluido com sucesso", Toast.LENGTH_LONG).show();
                }
            }

        });



        listVCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String conteudo = (String) listVCliente.getItemAtPosition(position);

                Toast.makeText(getActivity().getApplicationContext(), "Selecionando: " + conteudo, Toast.LENGTH_LONG).show();

                String codigo = conteudo.substring(0, conteudo.indexOf("-"));

                Cliente cliente = db.selecionaCliente(Integer.parseInt(codigo));

                edtCodigo.setText(String.valueOf(cliente.getCodigo()));
                edtNome.setText(cliente.getNome());
                edtTelefone.setText(cliente.getTelefone());
                edtEndereco.setText(cliente.getEndereco());
            }
        });

        return view;
    }

    void limparCampos(){
        edtCodigo.setText("");
        edtEndereco.setText("");
        edtTelefone.setText("");
        edtNome.setText("");

        edtNome.requestFocus();
    }

   public void listaCliente() {

        List<Cliente> clientes = db.listaCliente();

        arraylist = new ArrayList<>();

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arraylist);

        listVCliente.setAdapter(adapter);

        for (Cliente c : clientes) {
            arraylist.add(c.getCodigo() + "-" + c.getNome());
            adapter.notifyDataSetChanged();
        }
    }

}
