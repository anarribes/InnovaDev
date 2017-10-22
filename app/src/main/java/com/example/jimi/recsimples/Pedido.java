package com.example.jimi.recsimples;

/**
 * Created by jimi on 10/22/17.
 */

public class Pedido {

    int codigo;
    String cliente, produto;
    float total;

    public Pedido(){

    }

    public Pedido(int _codigo, String _cliente, String _produto, float _total){
        this.codigo = _codigo;
        this.cliente = _cliente;
        this.produto = _produto;
        this.total = _total;
    }

    public Pedido(String _cliente, String _produto, float _total){
        this.cliente = _cliente;
        this.produto = _produto;
        this.total = _total;
    }


    //********************


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
