package com.example.jimi.recsimples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadCliente extends AppCompatActivity {

    EditText edtNome, edtEndereco, edtTelefone, edtCpf;
    Button btnSalvar, btnLimpar, btnFoto;

    final BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_cliente);


        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtCpf = (EditText) findViewById(R.id.edtCpf);

        btnFoto = (Button) findViewById(R.id.btnLimpar);
        btnSalvar = (Button) findViewById(R.id.btnCad);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);

        //listVCliente = view.findViewById(R.id.listViClientes);

        btnLimpar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                limparCampos();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String nome = edtNome.getText().toString();
                String endereco = edtEndereco.getText().toString();
                String telefone = edtTelefone.getText().toString();
                String cpf = edtCpf.getText().toString();

                if(nome.isEmpty()){
                    edtNome.setText("Este campo é obrigatório");
                }
                else{
                        try {
                            db.addCliente(new Cliente(nome, telefone, endereco, cpf));
                            Toast.makeText(getApplicationContext(), "Novo cliente cadastrado com sucesso: " + nome, Toast.LENGTH_LONG).show();
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

    void limparCampos(){
        edtEndereco.setText("");
        edtTelefone.setText("");
        edtNome.setText("");
        edtCpf.setText("");

        edtNome.requestFocus();
    }

}
