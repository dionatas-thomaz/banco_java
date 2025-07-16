package dao;

import interfaces.Crud;
import model.Alocacao;
import model.Alocador;
import model.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AlocacaoDAO implements Crud<Object[]> {
    @Override
    public void cadastrar(Object[] dados) throws Exception {
        String cpfAlocador = (String) dados[0];
        String placaVeiculo = (String) dados[1];
        LocalDateTime dataInicio = (LocalDateTime) dados[2];
        LocalDateTime dataFim = (LocalDateTime) dados[3];
        String status = (String) dados[4];
        String sql = "INSERT INTO alocacao (cpf_alocador, placa_veiculo, data_inicio, data_fim, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cpfAlocador);
            stmt.setString(2, placaVeiculo);
            stmt.setTimestamp(3, dataInicio != null ? Timestamp.valueOf(dataInicio) : null);
            stmt.setTimestamp(4, dataFim != null ? Timestamp.valueOf(dataFim) : null);
            stmt.setString(5, status);
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("Alocacao cadastrada com sucesso! ID: " + generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            String mensagemErro = "Erro ao cadastrar alocacao: " + e.getMessage();
            if (e.getMessage().contains("foreign key")) {
                mensagemErro = "Causa: CPF " + cpfAlocador + " ou placa " + placaVeiculo + " invalido(s). Verifique se existem no sistema.";
            } else if (e.getMessage().contains("duplicate")) {
                mensagemErro = "Causa: Alocacao com CPF " + cpfAlocador + " e placa " + placaVeiculo + " ja existe.";
            }
            throw new SQLException(mensagemErro);
        }
    }

    private Alocacao buscarPorCpfEPlaca(String cpfAlocador, String placaVeiculo) throws SQLException {
        String sql = "SELECT a.*, al.nome AS alocador_nome, al.telefone AS alocador_telefone, " +
                "v.modelo AS veiculo_modelo, v.marca AS veiculo_marca, v.ano AS veiculo_ano, v.cor AS veiculo_cor " +
                "FROM alocacao a " +
                "LEFT JOIN alocador al ON a.cpf_alocador = al.cpf " +
                "LEFT JOIN veiculo v ON a.placa_veiculo = v.placa " +
                "WHERE a.cpf_alocador = ? AND a.placa_veiculo = ?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpfAlocador);
            stmt.setString(2, placaVeiculo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Alocacao alocacao = new Alocacao(
                        rs.getString("cpf_alocador"),
                        rs.getString("placa_veiculo"),
                        rs.getTimestamp("data_inicio") != null ? rs.getTimestamp("data_inicio").toLocalDateTime() : null,
                        rs.getTimestamp("data_fim") != null ? rs.getTimestamp("data_fim").toLocalDateTime() : null,
                        rs.getString("status")
                );
                alocacao.setId(rs.getInt("id"));
                if (rs.getString("alocador_nome") != null) {
                    System.out.println("Alocador: " + new Alocador(
                            rs.getString("alocador_nome"),
                            rs.getString("cpf_alocador"),
                            rs.getString("alocador_telefone")
                    ));
                }
                if (rs.getString("veiculo_modelo") != null) {
                    System.out.println("Veiculo: " + new Veiculo(
                            rs.getString("veiculo_modelo"),
                            rs.getString("placa_veiculo"),
                            rs.getString("veiculo_cor"),
                            rs.getString("veiculo_marca"),
                            rs.getString("veiculo_ano")
                    ));
                }
                return alocacao;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar alocacao: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void consultar(Object[] dados) throws Exception {
        String cpfAlocador = (String) dados[0];
        String placaVeiculo = (String) dados[1];
        Alocacao alocacao = buscarPorCpfEPlaca(cpfAlocador, placaVeiculo);
        if (alocacao == null) {
            throw new Exception("Alocacao nao encontrada!");
        }
        System.out.println(alocacao);
    }

    @Override
    public void alterar(Object[] dados) throws Exception {
        String cpfAlocador = (String) dados[0];
        String placaVeiculo = (String) dados[1];
        int opcao = (int) dados[2];
        String fieldValue = (String) dados[3];
        LocalDateTime dataFim = (LocalDateTime) dados[4];
        String sql = "";
        switch (opcao) {
            case 1: sql = "UPDATE alocacao SET data_fim = ? WHERE cpf_alocador = ? AND placa_veiculo = ?"; break;
            case 2: sql = "UPDATE alocacao SET status = ? WHERE cpf_alocador = ? AND placa_veiculo = ?"; break;
            default: throw new Exception("Opcao invalida!");
        }
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (opcao == 1) {
                stmt.setTimestamp(1, dataFim != null ? Timestamp.valueOf(dataFim) : null);
            } else {
                stmt.setString(1, fieldValue);
            }
            stmt.setString(2, cpfAlocador);
            stmt.setString(3, placaVeiculo);
            stmt.executeUpdate();
            System.out.println("Alocacao atualizada com sucesso!");
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar alocacao: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Object[] dados) throws Exception {
        String cpfAlocador = (String) dados[0];
        String placaVeiculo = (String) dados[1];
        Alocacao alocacao = buscarPorCpfEPlaca(cpfAlocador, placaVeiculo);
        if (alocacao == null) {
            throw new Exception("Alocacao nao encontrada!");
        }
        String sql = "DELETE FROM alocacao WHERE cpf_alocador = ? AND placa_veiculo = ?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpfAlocador);
            stmt.setString(2, placaVeiculo);
            stmt.executeUpdate();
            System.out.println("Alocacao excluida com sucesso!");
        } catch (SQLException e) {
            throw new SQLException("Erro ao excluir alocacao: " + e.getMessage());
        }
    }

    @Override
    public void relatorio() throws Exception {
        System.out.println("Relatorio de Alocacoes");
        String sql = "SELECT a.*, al.nome AS alocador_nome, al.telefone AS alocador_telefone, " +
                "v.modelo AS veiculo_modelo, v.marca AS veiculo_marca, v.ano AS veiculo_ano, v.cor AS veiculo_cor " +
                "FROM alocacao a " +
                "LEFT JOIN alocador al ON a.cpf_alocador = al.cpf " +
                "LEFT JOIN veiculo v ON a.placa_veiculo = v.placa";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            ArrayList<Alocacao> alocacoes = new ArrayList<>();
            while (rs.next()) {
                Alocacao alocacao = new Alocacao(
                        rs.getString("cpf_alocador"),
                        rs.getString("placa_veiculo"),
                        rs.getTimestamp("data_inicio") != null ? rs.getTimestamp("data_inicio").toLocalDateTime() : null,
                        rs.getTimestamp("data_fim") != null ? rs.getTimestamp("data_fim").toLocalDateTime() : null,
                        rs.getString("status")
                );
                alocacao.setId(rs.getInt("id"));
                alocacoes.add(alocacao);
                System.out.println(alocacao + " - Alocador: " + rs.getString("alocador_nome") +
                        ", Veiculo: " + rs.getString("veiculo_modelo"));
            }
            if (alocacoes.isEmpty()) {
                System.out.println("Nenhuma alocacao cadastrada.");
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar alocacoes: " + e.getMessage());
        }
    }
}