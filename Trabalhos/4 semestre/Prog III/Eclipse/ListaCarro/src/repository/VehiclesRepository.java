package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Main.PostgresConnection;
import entity.Vehicle;

public class VehiclesRepository {

    private Connection getConnection() {
        try {
            return PostgresConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco: " + e.getMessage());
            return null;
        }
    }

    public boolean createVehicle(String marca, String modelo, int ano, String cor, int quilometragem, String estado, String tipo_combustivel) {
        String SQL = "INSERT INTO vehicles (marca, modelo, ano, cor, quilometragem, estado, tipo_combustivel) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, marca);
            preparedStatement.setString(2, modelo);
            preparedStatement.setInt(3, ano);
            preparedStatement.setString(4, cor);
            preparedStatement.setInt(5, quilometragem);
            preparedStatement.setString(6, estado);
            preparedStatement.setString(7, tipo_combustivel);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir veículo: " + e.getMessage());
            return false;
        }
    }

    public List<Vehicle> getAllVehicles() {
        String SQL = "SELECT id, marca, modelo, ano, cor, quilometragem, estado, tipo_combustivel FROM vehicles";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                int ano = resultSet.getInt("ano");
                String cor = resultSet.getString("cor");
                int quilometragem = resultSet.getInt("quilometragem");
                String estado = resultSet.getString("estado");
                String tipo_combustivel = resultSet.getString("tipo_combustivel");

                Vehicle vehicle = new Vehicle(id, marca, modelo, ano, cor, quilometragem, estado, tipo_combustivel);
                vehicles.add(vehicle);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículos: " + e.getMessage());
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByMake(String marca) {
        String SQL = "SELECT id, marca, modelo, ano, cor, quilometragem, estado, tipo_combustivel FROM vehicles WHERE marca = ?";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, marca);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String modelo = resultSet.getString("modelo");
                    int ano = resultSet.getInt("ano");
                    String cor = resultSet.getString("cor");
                    int quilometragem = resultSet.getInt("quilometragem");
                    String estado = resultSet.getString("estado");
                    String tipo_combustivel = resultSet.getString("tipo_combustivel");

                    Vehicle vehicle = new Vehicle(id, marca, modelo, ano, cor, quilometragem, estado, tipo_combustivel);
                    vehicles.add(vehicle);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículos: " + e.getMessage());
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByModel(String modelo) {
        String SQL = "SELECT id, marca, modelo, ano, cor, quilometragem, estado, tipo_combustivel FROM vehicles WHERE modelo = ?";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, modelo);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String marca = resultSet.getString("marca");
                    int ano = resultSet.getInt("ano");
                    String cor = resultSet.getString("cor");
                    int quilometragem = resultSet.getInt("quilometragem");
                    String estado = resultSet.getString("estado");
                    String tipo_combustivel = resultSet.getString("tipo_combustivel");

                    Vehicle vehicle = new Vehicle(id, marca, modelo, ano, cor, quilometragem, estado, tipo_combustivel);
                    vehicles.add(vehicle);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículos: " + e.getMessage());
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByYear(int ano) {
        String SQL = "SELECT id, marca, modelo, ano, cor, quilometragem, estado, tipo_combustivel FROM vehicles WHERE ano = ?";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, ano);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String marca = resultSet.getString("marca");
                    String modelo = resultSet.getString("modelo");
                    String cor = resultSet.getString("cor");
                    int quilometragem = resultSet.getInt("quilometragem");
                    String estado = resultSet.getString("estado");
                    String tipo_combustivel = resultSet.getString("tipo_combustivel");

                    Vehicle vehicle = new Vehicle(id, marca, modelo, ano, cor, quilometragem, estado, tipo_combustivel);
                    vehicles.add(vehicle);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículos: " + e.getMessage());
        }
        return vehicles;
    }

    public Vehicle getVehicleById(int id) {
        String SQL = "SELECT id, marca, modelo, ano, cor, quilometragem, estado, tipo_combustivel FROM vehicles WHERE id = ?";
        Vehicle vehicle = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, id);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String marca = resultSet.getString("marca");
                    String modelo = resultSet.getString("modelo");
                    int ano = resultSet.getInt("ano");
                    String cor = resultSet.getString("cor");
                    int quilometragem = resultSet.getInt("quilometragem");
                    String estado = resultSet.getString("estado");
                    String tipo_combustivel = resultSet.getString("tipo_combustivel");

                    vehicle = new Vehicle(id, marca, modelo, ano, cor, quilometragem, estado, tipo_combustivel);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículo: " + e.getMessage());
        }
        
        return vehicle;
    }

    public boolean updateVehicle(int id, String marca, String modelo, int ano, String cor, int quilometragem, String estado, String tipo_combustivel) {
        String SQL = "UPDATE vehicles SET marca = ?, modelo = ?, ano = ?, cor = ?, quilometragem = ?, estado = ?, tipo_combustivel = ? WHERE id = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, marca);
            preparedStatement.setString(2, modelo);
            preparedStatement.setInt(3, ano);
            preparedStatement.setString(4, cor);
            preparedStatement.setInt(5, quilometragem);
            preparedStatement.setString(6, estado);
            preparedStatement.setString(7, tipo_combustivel);
            preparedStatement.setInt(8, id);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar veículo: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteVehicle(int id) {
        String SQL = "DELETE FROM vehicles WHERE id = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, id);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir veículo: " + e.getMessage());
            return false;
        }
    }
}
