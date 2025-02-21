package br.com.fiap.api_rest.model;

public enum Categoria {

    BRONZE("Básico"),
    PRATA("Prata"),
    OURO("Ouro");

    private String descricao;

    Categoria(String descricao){
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }
}
