package com.example.jimi.recebiveis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CadProdutos extends Fragment {


    BancoDados db = new BancoDados(getActivity());

    EditText edtCodP, edtNomeP, edtDescricaoP, edtMarcaP, edtModeloP, edtPrecoP;
    Button btnSalvarP, btnLimparP, btnExcluirP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cad_produtos, null);

        edtCodP = view.findViewById(R.id.edtCodP);
        edtNomeP = view.findViewById(R.id.edtNomeP);
        edtDescricaoP = view.findViewById(R.id.edtDescricaoP);
        edtMarcaP = view.findViewById(R.id.edtMarcaP);
        edtModeloP = view.findViewById(R.id.edtModeloP);
        edtPrecoP = view.findViewById(R.id.edtPrecoP);

        btnSalvarP = view.findViewById(R.id.btnSalvarP);
        btnLimparP = view.findViewById(R.id.btnLimparP);
        btnExcluirP = view.findViewById(R.id.btnExcluirP);

        btnLimparP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                limparCamposP();
            }
        });

        btnSalvarP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String codigo = edtCodP.getText().toString();
                String nome = edtNomeP.getText().toString();
                String endereco = edtMarcaP.getText().toString();
                String descricao = edtDescricaoP.getText().toString();
                String modelo = edtModeloP.getText().toString();
                String preco = edtPrecoP.getText().toString();

                if(nome.isEmpty()){
                    edtNomeP.setText("Este campo é obrigatório");
                }
                else{

                    if(codigo.isEmpty()){
                        //insert
                        db.addProduto(new Produtos(nome,endereco,descricao,modelo, Float.parseFloat(preco)));
                        Toast.makeText(getActivity().getApplicationContext(), "Produto adicionado com sucesso", Toast.LENGTH_LONG).show();
                        limparCamposP();
                    }
                    else{
                        //update
                        db.addProduto(new Produtos(Integer.parseInt(codigo),nome,endereco,descricao,modelo,(Float.parseFloat(preco))));
                        Toast.makeText(getActivity().getApplicationContext(), "Produto atualizado com sucesso", Toast.LENGTH_LONG).show();
                        limparCamposP();
                    }

                }


            }
        });

        return view;
    }

    void limparCamposP(){
        edtCodP.setText("");
        edtNomeP.setText("");
        edtDescricaoP.setText("");
        edtMarcaP.setText("");
        edtModeloP.setText("");
        edtPrecoP.setText("");

        edtNomeP.requestFocus();
    }



}
