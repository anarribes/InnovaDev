package com.example.jimi.recsimples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadProdutos extends AppCompatActivity {

    EditText edtNomeP, edtDescricaoP, edtMarcaP, edtModeloP, edtPrecoP;
    Button btnSalvarP, btnLimparP, btnExcluirP, btnFotoP;

    final BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_produtos);

        //edtCodP = view.findViewById(R.id.edtCodP);
        edtNomeP = (EditText) findViewById(R.id.edtNomeP);
        edtDescricaoP = (EditText) findViewById(R.id.edtDescricaoP);
        edtMarcaP = (EditText) findViewById(R.id.edtMarcaP);
        edtModeloP = (EditText) findViewById(R.id.edtModeloP);
        edtPrecoP = (EditText) findViewById(R.id.edtPrecoP);

        btnSalvarP = (Button) findViewById(R.id.btnAddProdutos);
        btnLimparP = (Button) findViewById(R.id.btnLimparP);
        btnExcluirP = (Button) findViewById(R.id.btnExcluirP);
        btnFotoP = (Button) findViewById(R.id.btnLimpar);

        btnLimparP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                limparCamposP();
            }
        });

        btnSalvarP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //String codigo = edtCodP.getText().toString();
                String nome = edtNomeP.getText().toString();
                String endereco = edtMarcaP.getText().toString();
                String descricao = edtDescricaoP.getText().toString();
                String modelo = edtModeloP.getText().toString();
                String preco = edtPrecoP.getText().toString();

                if(nome.isEmpty()){
                    edtNomeP.setText("Este campo é obrigatório");
                }
                else{

                    /*if(codigo.isEmpty()){*/
                        //insert
                        db.addProduto(new Produtos(nome,endereco,descricao,modelo,Integer.parseInt(preco)));
                        Toast.makeText(getBaseContext().getApplicationContext(), "Produto adicionado com sucesso", Toast.LENGTH_LONG).show();
                        limparCamposP();
                    }
                    /*else{
                        //update
                        db.addProduto(new Produtos(Integer.parseInt(codigo),nome,endereco,descricao,modelo,(Float.parseFloat(preco))));
                        Toast.makeText(getActivity().getApplicationContext(), "Produto atualizado com sucesso", Toast.LENGTH_LONG).show();
                        limparCamposP();
                    }*/

                //}


            }
        });
    }

    void limparCamposP(){
        edtNomeP.setText("");
        edtDescricaoP.setText("");
        edtMarcaP.setText("");
        edtModeloP.setText("");
        edtPrecoP.setText("");

        edtNomeP.requestFocus();
    }



}
