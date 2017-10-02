package innovadev.recebiveis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CadProdutos extends AppCompatActivity {

    EditText edtCodP, edtNomeP, edtDescricaoP, edtMarcaP, edtModeloP, edtPrecoP;
    Button btnSalvarP, btnLimparP, btnExcluirP;

    BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produtos);

        edtCodP = (EditText) findViewById(R.id.edtCodP);
        edtNomeP = (EditText) findViewById(R.id.edtNomeP);
        edtDescricaoP = (EditText) findViewById(R.id.edtDescricaoP);
        edtMarcaP = (EditText) findViewById(R.id.edtMarcaP);
        edtModeloP = (EditText) findViewById(R.id.edtModeloP);
        edtPrecoP = (EditText) findViewById(R.id.edtPrecoP);

        btnSalvarP = (Button) findViewById(R.id.btnSalvarP);
        btnLimparP = (Button) findViewById(R.id.btnLimparP);
        btnExcluirP = (Button) findViewById(R.id.btnExcluirP);

        btnLimparP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                limparCamposP();
            }
        });

        btnSalvarP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
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
                        db.addProduto(new Produtos(nome,endereco,descricao,modelo,Float.parseFloat(preco)));
                        Toast.makeText(CadProdutos.this, "Produto adicionado com sucesso", Toast.LENGTH_LONG).show();
                        limparCamposP();
                    }
                    else{
                        //update
                        db.addProduto(new Produtos(Integer.parseInt(codigo),nome,endereco,descricao,modelo,(Float.parseFloat(preco))));
                        Toast.makeText(CadProdutos.this, "Produto atualizado com sucesso", Toast.LENGTH_LONG).show();
                        limparCamposP();
                    }

                }


            }
        });

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
