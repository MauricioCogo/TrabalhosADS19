package service;

import java.util.List;

import entity.Vehicle;
import repository.VehiclesRepository;

public class VehicleService {

    private final VehiclesRepository vehiclesRepository;

    public VehicleService() {
        this.vehiclesRepository = new VehiclesRepository();
    }

    public boolean createVehicle(String marca, String modelo, int ano, String cor, int quilometragem, String estado, String combustivel) {
        if (marca == null || marca.isEmpty()) {
            throw new IllegalArgumentException("A marca não pode ser nula ou vazia.");
        }

        if (modelo == null || modelo.isEmpty()) {
            throw new IllegalArgumentException("O modelo não pode ser nulo ou vazio.");
        }

        int currentYear = java.time.Year.now().getValue();
        if (ano <= 1885 || ano > currentYear) {
            throw new IllegalArgumentException("O ano deve ser maior que 1885 e não pode ser maior que o ano atual.");
        }

        if (cor == null || cor.isEmpty()) {
            throw new IllegalArgumentException("A cor não pode ser nula ou vazia.");
        }

        if (quilometragem < 0) {
            throw new IllegalArgumentException("A quilometragem não pode ser negativa.");
        }

        if (estado == null || estado.isEmpty()) {
            throw new IllegalArgumentException("O estado do veículo (Novo/Seminovo/Usado) não pode ser nulo ou vazio.");
        }

        if (combustivel == null || combustivel.isEmpty()) {
            throw new IllegalArgumentException("O tipo de combustível não pode ser nulo ou vazio.");
        }

        return vehiclesRepository.createVehicle(marca, modelo, ano, cor, quilometragem, estado, combustivel);
    }

    public List<Vehicle> getAllVehicles() {
        return vehiclesRepository.getAllVehicles();
    }

    public List<Vehicle> getVehiclesByMake(String marca) {
        if (marca == null || marca.isEmpty()) {
            throw new IllegalArgumentException("A marca não pode ser nula ou vazia.");
        }
        return vehiclesRepository.getVehiclesByMake(marca);
    }

    public List<Vehicle> getVehiclesByModel(String modelo) {
        if (modelo == null || modelo.isEmpty()) {
            throw new IllegalArgumentException("O modelo não pode ser nulo ou vazio.");
        }
        return vehiclesRepository.getVehiclesByModel(modelo);
    }

    public List<Vehicle> getVehiclesByYear(int ano) {
        int currentYear = java.time.Year.now().getValue();
        if (ano <= 1885 || ano > currentYear) {
            throw new IllegalArgumentException("O ano deve ser maior que 1885 e não pode ser maior que o ano atual.");
        }
        return vehiclesRepository.getVehiclesByYear(ano);
    }

    public Vehicle getVehicleById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID do veículo deve ser um valor positivo.");
        }
        return vehiclesRepository.getVehicleById(id);
    }

    public boolean updateVehicle(int id, String marca, String modelo, int ano, String cor, int quilometragem, String estado, String combustivel) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID do veículo deve ser um valor positivo.");
        }
        
        if (marca == null || marca.isEmpty()) {
            throw new IllegalArgumentException("A marca não pode ser nula ou vazia.");
        }

        if (modelo == null || modelo.isEmpty()) {
            throw new IllegalArgumentException("O modelo não pode ser nulo ou vazio.");
        }

        int currentYear = java.time.Year.now().getValue();
        if (ano <= 1885 || ano > currentYear) {
            throw new IllegalArgumentException("O ano deve ser maior que 1885 e não pode ser maior que o ano atual.");
        }

        if (cor == null || cor.isEmpty()) {
            throw new IllegalArgumentException("A cor não pode ser nula ou vazia.");
        }

        if (quilometragem < 0) {
            throw new IllegalArgumentException("A quilometragem não pode ser negativa.");
        }

        if (estado == null || estado.isEmpty()) {
            throw new IllegalArgumentException("O estado do veículo (Novo/Seminovo/Usado) não pode ser nulo ou vazio.");
        }

        if (combustivel == null || combustivel.isEmpty()) {
            throw new IllegalArgumentException("O tipo de combustível não pode ser nulo ou vazio.");
        }

        return vehiclesRepository.updateVehicle(id, marca, modelo, ano, cor, quilometragem, estado, combustivel);
    }

    public boolean deleteVehicle(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID do veículo deve ser um valor positivo.");
        }
        return vehiclesRepository.deleteVehicle(id);
    }
}
