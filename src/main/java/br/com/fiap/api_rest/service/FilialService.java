package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.dto.ClienteRequest;
import br.com.fiap.api_rest.dto.FilialRequest;
import br.com.fiap.api_rest.dto.FilialResponse;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.model.Endereco;
import br.com.fiap.api_rest.model.Filial;
import br.com.fiap.api_rest.repository.EnderecoRepository;
import br.com.fiap.api_rest.repository.FilialRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class FilialService {
    @Autowired
    FilialRepository filialRepository;

    @Autowired
    EnderecoRepository enderecoRepository;


    public Cliente requestToFilial(FilialRequest filialRequest){
        Filial filial = new Filial(null,filialRequest.nome(),filialRequest.endereco(),filialRequest.cliente());

        return new Filial(
                null,filialRequest.nome(),filialRequest.endereco(),filialRequest.cliente()
        );
    }

    public FilialResponse create(FilialRequest filialRequest){
        Endereco endereco = new Endereco();
        if(filialRequest.endereco().equals(null)){
            endereco = enderecoRepository.save(filialRequest.endereco());
        }
        Filial filial = new Filial(filialRequest.nome(),endereco);
        BeanUtils.copyProperties(filialRequest,filial);
        filial = filialRepository.save(filial);
        return new FilialResponse(filial.getId(),filial.getNome(),filial.getEndereco());
    }
}
