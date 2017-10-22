package com.example.jimi.recsimples;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCadCliente, btnListaCliente, btnCadProduto, btnListaProd, btnVendas, btnApagar, btnAreceber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnApagar = (Button) findViewById(R.id.btnApagar);
        btnCadCliente = (Button) findViewById(R.id.btnCadCliente);
        btnListaCliente = (Button) findViewById(R.id.btnListaCliente);
        btnCadProduto = (Button) findViewById(R.id.btnCadProd);
        btnListaProd = (Button) findViewById(R.id.btnListaProd);
        btnVendas = (Button) findViewById(R.id.btnVendas);
        btnAreceber = (Button) findViewById(R.id.btnAreceber);

        btnCadCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadCliente.class);
                startActivity(intent);
            }

        });

        btnListaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListaCliente.class);
                startActivity(intent);
            }

        });

        btnCadProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadProdutos.class);
                startActivity(intent);
            }

        });

        btnListaProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListaProduto.class);
                startActivity(intent);
            }

        });

        btnVendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadPedido.class);
                startActivity(intent);
            }

        });

    }



}
