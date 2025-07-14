package dao;

import interfaces.Crud;
import model.Veiculo;
import model.Alocador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class VeiculoDAO implements Crud {
    @Override
    public void cadastrar() {
        System.out.println("Cadastro de carro");
        Scanner entrada = new Scanner(System.in);
        System.out.println("Modelo do veículo: ");
        String modelo = entrada.nextLine();
        System.out.println("Placa do veículo: ");
        String placa = entrada.nextLine();
        System.out.println("Marca do veículo: ");
        String marca = entrada.nextLine();
        System.out.println("Ano do veículo: ");
        String ano = entrada.nextLine();
        System.out.println("Cor do veículo: ");
        String cor = entrada.nextLine();
        System.out.println("ID do Alocador (deixe em branco se não houver): ");
        String idAlocadorStr = entrada.nextLine();
        Integer idAlocador = idAlocadorStr.isEmpty() ? null : Integer.parseInt(idAlocadorStr);

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
            System.out.println("Erro ao cadastrar carro: " + e.getMessage());
            if (e.getMessage().contains("foreign key")) {
                System.out.println("Causa: ID de alocador inválido. Verifique se o ID " + idAlocador + " existe na tabela alocador.");
            } else if (e.getMessage().contains("duplicate")) {
                System.out.println("Causa: Placa " + placa + " já foi cadastrada.");
            } else {
                System.out.println("Causa desconhecida. Detalhes: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID do alocador deve ser um número válido!");
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
                // Adicionar informações do alocador, se existir
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
    public void consultar() {
        System.out.println("Consultar Carro");
        relatorio();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Consultar pela Placa");
        String placa = entrada.nextLine();
        Veiculo carro = buscaPlaca(placa);
        if (carro == null) {
            System.out.println("Carro não encontrado");
            return;
        }
        System.out.println(carro);
    }

    @Override
    public void alterar() {
        System.out.println("Alterar Carro");
        relatorio();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Consultar pela Placa");
        String placa = entrada.nextLine();
        Veiculo carro = buscaPlaca(placa);
        if (carro == null) {
            System.out.println("Carro não encontrado");
            return;
        }
        System.out.println(carro);
        System.out.println("Escolha a opção a ser alterada: ");
        System.out.println("1 - Modelo");
        System.out.println("2 - Placa");
        System.out.println("3 - Marca");
        System.out.println("4 - Ano");
        System.out.println("5 - Cor");
        System.out.println("6 - ID do Alocador");
        int opcao = entrada.nextInt();
        entrada.nextLine(); // Limpar o buffer
        String sql = "";
        String fieldValue = "";
        switch (opcao) {
            case 1 -> {
                System.out.println("Digite o novo modelo: ");
                fieldValue = entrada.nextLine();
                sql = "UPDATE veiculo SET modelo = ? WHERE placa = ?";
            }
            case 2 -> {
                System.out.println("Digite a nova placa: ");
                fieldValue = entrada.nextLine();
                sql = "UPDATE veiculo SET placa = ? WHERE placa = ?";
            }
            case 3 -> {
                System.out.println("Digite a nova marca: ");
                fieldValue = entrada.nextLine();
                sql = "UPDATE veiculo SET marca = ? WHERE placa = ?";
            }
            case 4 -> {
                System.out.println("Digite o novo ano: ");
                fieldValue = entrada.nextLine();
                sql = "UPDATE veiculo SET ano = ? WHERE placa = ?";
            }
            case 5 -> {
                System.out.println("Digite a nova cor: ");
                fieldValue = entrada.nextLine();
                sql = "UPDATE veiculo SET cor = ? WHERE placa = ?";
            }
            case 6 -> {
                System.out.println("Digite o novo ID do Alocador (deixe em branco se não houver): ");
                String idAlocadorStr = entrada.nextLine();
                Integer idAlocador = idAlocadorStr.isEmpty() ? null : Integer.parseInt(idAlocadorStr);
                try (Connection conn = ConexaoDAO.getConnection();
                     PreparedStatement stmt = conn.prepareStatement("UPDATE veiculo SET id_alocador = ? WHERE placa = ?")) {
                    stmt.setObject(1, idAlocador);
                    stmt.setString(2, placa);
                    stmt.executeUpdate();
                    System.out.println("ID do alocador atualizado com sucesso!");
                    return;
                } catch (SQLException e) {
                    System.out.println("Erro ao atualizar ID do alocador.");
                    return;
                } catch (NumberFormatException e) {
                    System.out.println("Erro: ID do alocador deve ser um número válido!");
                    return;
                }
            }
            default -> {
                System.out.println("Opção inválida");
                return;
            }
        }
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fieldValue);
            stmt.setString(2, placa);
            stmt.executeUpdate();
            System.out.println("Carro atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar carro.");
        }
    }

    @Override
    public void excluir() {
        System.out.println("Exclusão de carro");
        relatorio();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Consultar pela Placa");
        String placa = entrada.nextLine();
        Veiculo carro = buscaPlaca(placa);
        if (carro == null) {
            System.out.println("Carro não encontrado");
            return;
        }
        String sql = "DELETE FROM veiculo WHERE placa = ?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            stmt.executeUpdate();
            System.out.println("Exclusão efetuada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir carro.");
        }
    }

    @Override
    public void relatorio() {
        System.out.println("Relatório de Carros");
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
                    System.out.println("Veículo: " + carro + " - Alocador: " + alocador);
                } else {
                    System.out.println("Veículo: " + carro + " - Sem alocador associado");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar carros: " + e.getMessage());
        }
    }
}