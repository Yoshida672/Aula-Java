package br.com.fiap.api_rest.dto;

import br.com.fiap.api_rest.model.Filial;
import jakarta.validation.constraints.NotNull;

public class EnderecoRequest{
    @NotNull(message = "Obrigatório uma localização")
    private String localizacao;
    @NotNull(message = "Filial Obrigatória")
    private Filial filial;

    public EnderecoRequest() {

    }

    public EnderecoRequest(String localizacao, Filial filial) {
        this.localizacao = localizacao;
        this.filial = filial;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }
}
