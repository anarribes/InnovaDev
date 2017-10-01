package innovadev.recebiveis;

import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtCodigo, edtNome, edtEndereco, edtTelefone;
    RadioButton radMasc, radFem;
    Button btnSalvar, btnLimpar, btnExcluir;
    ListView listVCliente;

    BancoDados db = new BancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtCodigo = (EditText) findViewById(R.id.edtCodigo);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);

        radMasc = (RadioButton) findViewById(R.id.radMasc);
        radFem = (RadioButton) findViewById(R.id.radFem);

        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnLimpar = (Button) findViewById(R.id.btnLimpar);

        listVCliente = (ListView) findViewById(R.id.listViClientes);

        btnLimpar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                limparCampos();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
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
                        Toast.makeText(MainActivity.this, "Cliente adicionado com sucesso", Toast.LENGTH_LONG).show();
                        limparCampos();
                        listaCliente();
                    }
                    else{
                        //update
                        db.atualizarCliente(new Cliente(Integer.parseInt(codigo), nome,telefone,endereco));
                        Toast.makeText(MainActivity.this, "Cliente atualizado com sucesso", Toast.LENGTH_LONG).show();
                        limparCampos();
                        listaCliente();
                    }

                }


            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String codigo = edtCodigo.getText().toString();

                if (codigo.isEmpty()){
                    Toast.makeText(MainActivity.this, "Nenhum cliente selecionado", Toast.LENGTH_LONG).show();
                }
                else{

                    Cliente cliente = new Cliente();
                    cliente.setCodigo(Integer.parseInt(codigo));
                    db.apagaCliente(cliente);
                    limparCampos();
                    listaCliente();
                    Toast.makeText(MainActivity.this, "Cliente excluido com sucesso", Toast.LENGTH_LONG).show();
                }
            }

        });



        listVCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String conteudo = (String) listVCliente.getItemAtPosition(position);

                //Toast.makeText(MainActivity.this, "Selecionando: " + conteudo, Toast.LENGTH_LONG).show();

                String codigo = conteudo.substring(0, conteudo.indexOf("-"));

                Cliente cliente = db.selecionaCliente(Integer.parseInt(codigo));

                edtCodigo.setText(String.valueOf(cliente.getCodigo()));
                edtNome.setText(cliente.getNome());
                edtTelefone.setText(cliente.getTelefone());
                edtEndereco.setText(cliente.getEndereco());
            }
        });

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

        arraylist = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arraylist);

        listVCliente.setAdapter(adapter);

        for (Cliente c : clientes) {
            arraylist.add(c.getCodigo() + "-" + c.getNome());
            adapter.notifyDataSetChanged();
        }
    }


    //************* teste CRUD *****//////

    // INLCUDE OK
    //db.addCliente(new Cliente("jimi", "teste", "23232" ));
    //db.addCliente(new Cliente("jimi", "teste", "23232" ));
    //db.addCliente(new Cliente("jimi", "teste", "23232" ));

    //DELETE
    //Cliente c = new Cliente();
    //c.setCodigo(2);
    //db.apagaCliente(c);

    //select
    //Cliente c = db.selecionaCLiente(4);

    //uodate
    //Cliente c = new Cliente();
    //c.setCodigo(2);
    //c.setNome("jimi");
    //c.setEndereco("sdasda");
    //c.setTelefone("433434343");
    //db.atualizarCliente(c);

    //Toast.makeText(MainActivity.this, "Salvo sucesso", Toast.LENGTH_LONG).show();

}