package dao;

import interfaces.Crud;
import model.Veiculo;
import model.Alocador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VeiculoDAO implements Crud {
    @Override
    public void cadastrar(String modelo, String placa, String marca, String ano, String cor, Integer idAlocador) throws SQLException {
        String sql = "INSERT INTO veiculo (placa, modelo, marca, ano, cor, id_alocador) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            stmt.setString(2, modelo);
            stmt.setString(3, marca);
            stmt.setString(4, ano);
            stmt.setString(5, cor);
            stmt.setObject(6, idAlocador);
            stmt.executeUpdate();
            System.out.println("Carro cadastrado com sucesso!");
        } catch (SQLException e) {
            String mensagemErro = "Erro ao cadastrar carro: " + e.getMessage();
            if (e.getMessage().contains("foreign key")) {
                mensagemErro = "Causa: ID de alocador invalido. Verifique se o ID " + idAlocador + " existe na tabela alocador.";
            } else if (e.getMessage().contains("duplicate")) {
                mensagemErro = "Causa: Placa " + placa + " ja foi cadastrada.";
            } else {
                mensagemErro = "Causa desconhecida. Detalhes: " + e.getMessage();
            }
            throw new SQLException(mensagemErro);
        }
    }

    private Veiculo buscaPlaca(String placa) {
        String sql = "SELECT v.*, a.nome AS alocador_nome, a.cpf AS alocador_cpf, a.telefone AS alocador_telefone " +
                "FROM veiculo v " +
                "LEFT JOIN alocador a ON v.id_alocador = a.id " +
                "WHERE v.placa = ?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Veiculo carro = new Veiculo(
                        rs.getString("modelo"),
                        rs.getString("placa"),
                        rs.getString("cor"),
                        rs.getString("marca"),
                        rs.getString("ano")
                ) {{
                    setIdAlocador(rs.getObject("id_alocador", Integer.class));
                }};
                if (rs.getObject("alocador_nome") != null) {
                    Alocador alocador = new Alocador(
                            rs.getString("alocador_nome"),
                            rs.getString("alocador_cpf"),
                            rs.getString("alocador_telefone")
                    );
                    alocador.setId(rs.getInt("id_alocador"));
                    System.out.println("Alocador associado: " + alocador);
                }
                return carro;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void consultar(String placa) throws SQLException {
        Veiculo carro = buscaPlaca(placa);
        if (carro == null) {
            throw new SQLException("Carro nao encontrado");
        }
        System.out.println(carro);
    }

    @Override
    public void alterar(String placa, int opcao, String fieldValue, Integer idAlocador) throws SQLException {
        String sql = "";
        if (opcao == 6) {
            try (Connection conn = ConexaoDAO.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("UPDATE veiculo SET id_alocador = ? WHERE placa = ?")) {
                stmt.setObject(1, idAlocador);
                stmt.setString(2, placa);
                stmt.executeUpdate();
                System.out.println("ID do alocador atualizado com sucesso!");
                return;
            } catch (SQLException e) {
                throw new SQLException("Erro ao atualizar ID do alocador.");
            }
        }
        switch (opcao) {
            case 1:
                sql = "UPDATE veiculo SET modelo = ? WHERE placa = ?";
                break;
            case 2:
                sql = "UPDATE veiculo SET placa = ? WHERE placa = ?";
                break;
            case 3:
                sql = "UPDATE veiculo SET marca = ? WHERE placa = ?";
                break;
            case 4:
                sql = "UPDATE veiculo SET ano = ? WHERE placa = ?";
                break;
            case 5:
                sql = "UPDATE veiculo SET cor = ? WHERE placa = ?";
                break;
            default:
                throw new SQLException("Opcao invalida");
        }
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fieldValue);
            stmt.setString(2, placa);
            stmt.executeUpdate();
            System.out.println("Carro atualizado com sucesso!");
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar carro.");
        }
    }

    @Override
    public void excluir(String placa) throws SQLException {
        Veiculo carro = buscaPlaca(placa);
        if (carro == null) {
            throw new SQLException("Carro nao encontrado");
        }
        String sql = "DELETE FROM veiculo WHERE placa = ?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            stmt.executeUpdate();
            System.out.println("Exclusao efetuada com sucesso!");
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir carro.");
        }
    }

    @Override
    public void relatorio() {
        System.out.println("Relatorio de Carros");
        String sql = "SELECT v.*, a.nome AS alocador_nome, a.cpf AS alocador_cpf, a.telefone AS alocador_telefone " +
                "FROM veiculo v " +
                "LEFT JOIN alocador a ON v.id_alocador = a.id";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Veiculo carro = new Veiculo(
                        rs.getString("modelo"),
                        rs.getString("placa"),
                        rs.getString("cor"),
                        rs.getString("marca"),
                        rs.getString("ano")
                ) {{
                    setIdAlocador(rs.getObject("id_alocador", Integer.class));
                }};
                if (rs.getObject("alocador_nome") != null) {
                    Alocador alocador = new Alocador(
                            rs.getString("alocador_nome"),
                            rs.getString("alocador_cpf"),
                            rs.getString("alocador_telefone")
                    );
                    alocador.setId(rs.getInt("id_alocador"));
                    System.out.println("Veiculo: " + carro + " - Alocador: " + alocador);
                } else {
                    System.out.println("Veiculo: " + carro + " - Sem alocador associado");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar carros: " + e.getMessage());
        }
    }
}