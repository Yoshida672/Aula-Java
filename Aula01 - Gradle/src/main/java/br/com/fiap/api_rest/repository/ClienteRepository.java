package br.com.fiap.api_rest.repository;

import br.com.fiap.api_rest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNome(String nome);
    Cliente findByCpf(String cpf);
    List<Cliente> findByNomeEqualsIgnoreCase(String nome);
    List<Cliente> findTop10ByIdade(Integer idade);
    List<Cliente> findByIdadeGreaterThanEqual(Integer idade);
    List<Cliente> findByIdadeBetween(Integer min,Integer max);
    List<Cliente> findByCpfAndNome(String cpf,String nome);
    List<Cliente> findByCpfStartingWith(String prefix);
    List<Cliente> findByNomeIsNot(String nome);
    List<Cliente> findBySenhaIsNotNull();
    List<Cliente> findByEmailLike(String likePattern);
    List<Cliente> findByVipTrue();
    List<Cliente> findByDataNascimentoBefore(Date data);
    List<Cliente> findByDataNascimentoAfter(Date data);
    List<Cliente> findByNomeOrderByNome(String nome);
    List<Cliente> findByNomeOrEmail(String nome,String email);
}

