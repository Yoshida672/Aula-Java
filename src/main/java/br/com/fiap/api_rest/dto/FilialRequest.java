package br.com.fiap.api_rest.dto;

import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class FilialRequest {
    @NotBlank(message = "O nome é obrigatório")
        private String nome;
    @NotBlank(message = "O endereço é obrigatório")
        private Endereco endereco;

    public FilialRequest(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public FilialRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
