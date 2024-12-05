package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import entity.Endereco;
import repository.EnderecoRepository;

public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    public EnderecoService(Connection connection) {
        this.enderecoRepository = new EnderecoRepository(connection);
    }

    // M�todo para listar todos os endere�os
    public List<Endereco> listarTodos() throws SQLException {
        return enderecoRepository.getAllAdress();
    }

    // M�todo para buscar um endere�o por ID
    public Endereco buscarPorId(Long id) throws SQLException {
        return enderecoRepository.getAdressById(id);
    }

    // M�todo para salvar um novo endere�o
    public void salvar(Endereco endereco) throws SQLException {
        enderecoRepository.createAdress(endereco);
    }

    // M�todo para excluir um endere�o
    public void excluir(Long id) throws SQLException {
        enderecoRepository.deleteAdress(id);
    }
}
