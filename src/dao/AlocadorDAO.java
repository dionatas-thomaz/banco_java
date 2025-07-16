package dao;

import interfaces.Crud;
import model.Alocador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlocadorDAO implements Crud {
    @Override
    public void cadastrar(String nome, String cpf, String telefone) throws SQLException {
        String sql = "INSERT INTO alocador (cpf, nome, telefone) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setString(3, telefone);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                System.out.println("Alocador cadastrado com sucesso! ID: " + id);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar alocador. O CPF: " + cpf + " ja foi cadastrado!");
        }
    }

    public Alocador buscarCpf(String cpf) {
        String sql = "SELECT * FROM alocador WHERE cpf = ?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Alocador alocador = new Alocador(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                );
                alocador.setId(rs.getInt("id"));
                return alocador;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void consultar(String cpf) throws SQLException {
        Alocador alocador = buscarCpf(cpf);
        if (alocador == null) {
            throw new SQLException("Alocador nao encontrado!");
        }
        System.out.println(alocador);
    }

    @Override
    public void alterar(String cpf, int opcao, String fieldValue) throws SQLException {
        String sql = "";
        switch (opcao) {
            case 1:
                sql = "UPDATE alocador SET nome = ? WHERE cpf = ?";
                break;
            case 2:
                sql = "UPDATE alocador SET cpf = ? WHERE cpf = ?";
                break;
            case 3:
                sql = "UPDATE alocador SET telefone = ? WHERE cpf = ?";
                break;
            default:
                throw new SQLException("Opcao invalida!");
        }
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fieldValue);
            stmt.setString(2, cpf);
            stmt.executeUpdate();
            System.out.println("Alocador atualizado com sucesso!");
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar alocador.");
        }
    }

    @Override
    public void excluir(String cpf) throws SQLException {
        Alocador alocador = buscarCpf(cpf);
        if (alocador == null) {
            throw new SQLException("Alocador nao encontrado!");
        }
        String sql = "DELETE FROM alocador WHERE cpf = ?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            System.out.println("Alocador excluido com sucesso!");
        } catch (SQLException e) {
            throw new SQLException("Erro ao remover alocador.");
        }
    }

    @Override
    public void relatorio() {
        String sql = "SELECT * FROM alocador";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ArrayList<Alocador> alocadores = new ArrayList<>();
            while (rs.next()) {
                Alocador alocador = new Alocador(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                );
                alocador.setId(rs.getInt("id"));
                alocadores.add(alocador);
            }
            if (alocadores.isEmpty()) {
                System.out.println("Nenhum alocador cadastrado.");
            } else {
                System.out.println(alocadores);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar alocadores: " + e.getMessage());
        }
    }
}