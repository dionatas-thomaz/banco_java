package dao;
import interfaces.Crud;
import model.Alocador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlocadorDAO implements Crud<Object[]> {
    @Override
    public void cadastrar(Object[] dados) throws Exception {
        String nome = (String) dados[0];
        String cpf = (String) dados[1];
        String telefone = (String) dados[2];
        String sql = "INSERT INTO alocador (cpf, nome, telefone) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setString(3, telefone);
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("Alocador cadastrado com sucesso! ID: " + generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            String mensagemErro = "Erro ao cadastrar alocador: " + e.getMessage();
            if (e.getMessage().contains("duplicate")) {
                mensagemErro = "Causa: CPF " + cpf + " ja foi cadastrado.";
            }
            throw new SQLException(mensagemErro);
        }
    }

    private Alocador buscaCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM alocador WHERE cpf = ?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Alocador alocador = new Alocador(rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"));
                alocador.setId(rs.getInt("id"));
                return alocador;
            }
        }
        return null;
    }

    @Override
    public void consultar(Object[] dados) throws Exception {
        String cpf = (String) dados[0];
        Alocador alocador = buscaCpf(cpf);
        if (alocador == null) {
            throw new Exception("Alocador nao encontrado");
        }
        System.out.println(alocador);
    }

    @Override
    public void alterar(Object[] dados) throws Exception {
        String cpf = (String) dados[0];
        int opcao = (int) dados[1];
        String fieldValue = (String) dados[2];
        String sql = "";
        switch (opcao) {
            case 1: sql = "UPDATE alocador SET nome = ? WHERE cpf = ?"; break;
            case 2: sql = "UPDATE alocador SET cpf = ? WHERE cpf = ?"; break;
            case 3: sql = "UPDATE alocador SET telefone = ? WHERE cpf = ?"; break;
            default: throw new Exception("Opcao invalida");
        }
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fieldValue);
            stmt.setString(2, cpf);
            stmt.executeUpdate();
            System.out.println("Alocador atualizado com sucesso!");
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar alocador: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Object[] dados) throws Exception {
        String cpf = (String) dados[0];
        Alocador alocador = buscaCpf(cpf);
        if (alocador == null) {
            throw new Exception("Alocador nao encontrado");
        }
        String sql = "DELETE FROM alocador WHERE cpf = ?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            System.out.println("Exclusao efetuada com sucesso!");
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir alocador: " + e.getMessage());
        }
    }

    @Override
    public void relatorio() throws Exception {
        System.out.println("Relatorio de Alocadores");
        String sql = "SELECT * FROM alocador";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Alocador alocador = new Alocador(rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"));
                alocador.setId(rs.getInt("id"));
                System.out.println(alocador);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar alocadores: " + e.getMessage());
        }
    }
}