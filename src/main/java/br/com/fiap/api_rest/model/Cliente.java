package br.com.fiap.api_rest.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;
    private String email;
    private String senha;
    private String cpf;
    private Categoria categoria;
    private Date dataNascimento;
    private boolean vip;
    @ManyToOne
    @JoinColumn(name="id_filial")
    private Filial filial;
    @ManyToMany
    @JoinTable(name="grupo_cliente",
            joinColumns =
            @JoinColumn(name="id_grupo",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_cliente",referencedColumnName = "id")
    )
    private List<Grupo> grupos;

    public Cliente() {
    }

    public Cliente(Long id, String nome, Integer idade, String email, String senha, String cpf, Categoria categoria,Filial filial, List<Grupo> grupos) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.categoria = categoria;
        this.filial = filial;
        this.grupos = grupos;
    }

    public Date getDataNacimento() {
        return dataNascimento;
    }

    public void setDataNacimento(Date dataNacimento) {
        this.dataNascimento = dataNacimento;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
}
