package innovadev.recebiveis;

/**
 * Created by jimi on 01/10/17.
 */

public class Cliente {

    int codigo;
    String nome, telefone, endereco;

    public Cliente(){

    }

    public Cliente(int _codigo, String _nome, String _telefone, String _endereco){
        this.codigo = _codigo;
        this.nome = _nome;
        this.telefone = _telefone;
        this.endereco = _endereco;
    }

    public Cliente(String _nome, String _telefone, String _endereco){
        this.nome = _nome;
        this.telefone = _telefone;
        this.endereco = _endereco;
    }

    //**********************************************

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
