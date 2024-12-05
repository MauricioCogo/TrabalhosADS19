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

    // Método para listar todos os endereços
    public List<Endereco> listarTodos() throws SQLException {
        return enderecoRepository.getAllAdress();
    }

    // Método para buscar um endereço por ID
    public Endereco buscarPorId(Long id) throws SQLException {
        return enderecoRepository.getAdressById(id);
    }

    // Método para salvar um novo endereço
    public void salvar(Endereco endereco) throws SQLException {
        enderecoRepository.createAdress(endereco);
    }

    // Método para excluir um endereço
    public void excluir(Long id) throws SQLException {
        enderecoRepository.deleteAdress(id);
    }
}
