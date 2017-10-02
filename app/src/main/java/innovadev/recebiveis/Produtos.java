package innovadev.recebiveis;

/**
 * Created by jimi on 01/10/17.
 */

public class Produtos {

    int codP;
    String nomeP, marcaP, modeloP, descricaoP;
    float precoP;

    public Produtos(){

    }

    public Produtos(int _codP, String _nomeP, String _marcaP, String modeloP, String _descricaoP, float _precoP){
        this.codP = _codP;
        this.nomeP = _nomeP;
        this.marcaP = _marcaP;
        this.modeloP = modeloP;
        this.precoP = _precoP;
    }

    public Produtos(String _nomeP, String _marcaP, String modeloP, String _modeloP, float _precoP){
        this.nomeP = _nomeP;
        this.marcaP = _marcaP;
        this.modeloP = modeloP;
        this.precoP = _precoP;
    }

    //**********************************************


    public int getCodP() {
        return codP;
    }

    public void setCodP(int codP) {
        this.codP = codP;
    }

    public String getNomeP() {
        return nomeP;
    }

    public void setNomeP(String nomeP) {
        this.nomeP = nomeP;
    }

    public String getMarcaP() {
        return marcaP;
    }

    public void setMarcaP(String marcaP) {
        this.marcaP = marcaP;
    }

    public String getModeloP() {
        return modeloP;
    }

    public void setModeloP(String modeloP) {
        this.modeloP = modeloP;
    }

    public String getDescricaoP() {
        return descricaoP;
    }

    public void setDescricaoP(String descricaoP) {
        this.descricaoP = descricaoP;
    }

    public float getPrecoP() {
        return precoP;
    }

    public void setPrecoP(float precoP) {
        this.precoP = precoP;
    }
}
