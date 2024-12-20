package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Endereco;

public class EnderecoRepository {

    private Connection connection;

    public EnderecoRepository(Connection connection) {
        this.connection = connection;
    }

    // M�todo para salvar um novo endere�o
    public void createAdress(Endereco endereco) throws SQLException {
        String sql = "INSERT INTO endereco (pais, cep, estado, cidade, rua, numero, complemento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, endereco.getPais());
            stmt.setString(2, endereco.getCep());
            stmt.setString(3, endereco.getEstado());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getRua());
            stmt.setString(6, endereco.getNumero());
            stmt.setString(7, endereco.getComplemento());
            stmt.executeUpdate();
        }
    }

    public List<Endereco> getAllAdress() throws SQLException {
        String sql = "SELECT * FROM endereco";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            List<Endereco> enderecos = new ArrayList<>();
            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setPais(rs.getString("pais"));
                endereco.setCep(rs.getString("cep"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                enderecos.add(endereco);
            }
            return enderecos;
        }
    }

    public Endereco getAdressById(Long id) throws SQLException {
        String sql = "SELECT * FROM endereco WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Endereco endereco = new Endereco();
                    endereco.setId(rs.getInt("id"));
                    endereco.setPais(rs.getString("pais"));
                    endereco.setCep(rs.getString("cep"));
                    endereco.setEstado(rs.getString("estado"));
                    endereco.setCidade(rs.getString("cidade"));
                    endereco.setRua(rs.getString("rua"));
                    endereco.setNumero(rs.getString("numero"));
                    endereco.setComplemento(rs.getString("complemento"));
                    return endereco;
                } else {
                    return null;
                }
            }
        }
    }

    // M�todo para excluir um endere�o pelo ID
    public void deleteAdress(Long id) throws SQLException {
        String sql = "DELETE FROM endereco WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
