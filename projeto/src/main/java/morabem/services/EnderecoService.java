package morabem.services;

import morabem.domain.Endereco;
import morabem.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> buscarPorLogradouros(String termo) {
        return enderecoRepository.buscarProLogradouro(termo.toLowerCase());
    }

    public List<Endereco> buscarPorBairro(String termo) {
        return enderecoRepository.buscarProBairro(termo.toLowerCase());

    }

    public List<Endereco> buscarPorCidade(String termo) {
        return enderecoRepository.buscarProCidade(termo.toLowerCase());

    }
}
