package dao;

import interfaces.Crud;
import model.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VeiculoDAO implements Crud<Object[]> {
    @Override
    public void cadastrar(Object[] dados) throws Exception {
        String modelo = (String) dados[0];
        String placa = (String) dados[1];
        String marca = (String) dados[2];
        String ano = (String) dados[3];
        String cor = (String) dados[4];
        String sql = "INSERT INTO veiculo (placa, modelo, marca, ano, cor) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            stmt.setString(2, modelo);
            stmt.setString(3, marca);
            stmt.setString(4, ano);
            stmt.setString(5, cor);
            stmt.executeUpdate();
            System.out.println("Carro cadastrado com sucesso!");
        } catch (SQLException e) {
            String mensagemErro = "Erro ao cadastrar carro: " + e.getMessage();
            if (e.getMessage().contains("duplicate")) {
                mensagemErro = "Causa: Placa " + placa + " ja foi cadastrada.";
            }
            throw new SQLException(mensagemErro);
        }
    }

    private Veiculo buscaPlaca(String placa) throws SQLException {
        String sql = "SELECT * FROM veiculo WHERE placa = ?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Veiculo(
                        rs.getString("modelo"),
                        rs.getString("placa"),
                        rs.getString("cor"),
                        rs.getString("marca"),
                        rs.getString("ano")
                );
            }
        }
        return null;
    }

    @Override
    public void consultar(Object[] dados) throws Exception {
        String placa = (String) dados[0];
        Veiculo carro = buscaPlaca(placa);
        if (carro == null) {
            throw new Exception("Carro nao encontrado");
        }
        System.out.println(carro);
    }

    @Override
    public void alterar(Object[] dados) throws Exception {
        String placa = (String) dados[0];
        int opcao = (int) dados[1];
        String fieldValue = (String) dados[2];
        String sql = "";
        switch (opcao) {
            case 1: sql = "UPDATE veiculo SET modelo = ? WHERE placa = ?"; break;
            case 2: sql = "UPDATE veiculo SET placa = ? WHERE placa = ?"; break;
            case 3: sql = "UPDATE veiculo SET marca = ? WHERE placa = ?"; break;
            case 4: sql = "UPDATE veiculo SET ano = ? WHERE placa = ?"; break;
            case 5: sql = "UPDATE veiculo SET cor = ? WHERE placa = ?"; break;
            default: throw new Exception("Opcao invalida");
        }
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fieldValue);
            stmt.setString(2, placa);
            stmt.executeUpdate();
            System.out.println("Carro atualizado com sucesso!");
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar carro: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Object[] dados) throws Exception {
        String placa = (String) dados[0];
        Veiculo carro = buscaPlaca(placa);
        if (carro == null) {
            throw new Exception("Carro nao encontrado");
        }
        String sql = "DELETE FROM veiculo WHERE placa = ?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            stmt.executeUpdate();
            System.out.println("Exclusao efetuada com sucesso!");
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir carro: " + e.getMessage());
        }
    }

    @Override
    public void relatorio() throws Exception {
        System.out.println("Relatorio de Carros");
        String sql = "SELECT * FROM veiculo";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ArrayList<Veiculo> veiculos = new ArrayList<>();
            while (rs.next()) {
                Veiculo carro = new Veiculo(
                        rs.getString("modelo"),
                        rs.getString("placa"),
                        rs.getString("cor"),
                        rs.getString("marca"),
                        rs.getString("ano")
                );
                veiculos.add(carro);
                System.out.println("Veiculo: " + carro);
            }
            if (veiculos.isEmpty()) {
                System.out.println("Nenhum carro cadastrado.");
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar carros: " + e.getMessage());
        }
    }
}