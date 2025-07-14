package dao;

import interfaces.Crud;
import model.Alocador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AlocadorDAO implements Crud {
    @Override
    public void cadastrar() {
        System.out.println("Cadastro de Alocador");
        Scanner entrada = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        System.out.print("CPF: ");
        String cpf = entrada.nextLine();
        System.out.print("Telefone: ");
        String telefone = entrada.nextLine();

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
            System.out.println("Erro ao cadastrar alocador. O CPF: " + cpf + " já foi cadastrado!");
        }
    }

    private Alocador buscarCpf(String cpf) {
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
    public void consultar() {
        System.out.println("Consultar Alocador");
        System.out.println("Alocadores cadastrados: ");
        relatorio();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Consultar pelo CPF");
        String cpf = entrada.nextLine();
        Alocador alocador = buscarCpf(cpf);
        if (alocador == null) {
            System.out.println("Alocador não encontrado!");
            return;
        }
        System.out.println(alocador);
    }

    @Override
    public void alterar() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Alteração de Alocador");
        System.out.println("Alocadores cadastrados: ");
        relatorio();
        System.out.println("Consultar pelo CPF");
        String cpf = entrada.nextLine();
        Alocador alocador = buscarCpf(cpf);
        if (alocador == null) {
            System.out.println("Alocador não encontrado!");
            return;
        }
        System.out.println(alocador);
        System.out.println("Escolha o campo a ser alterado:");
        System.out.println("1 - Nome");
        System.out.println("2 - CPF");
        System.out.println("3 - Telefone");
        int opcao = entrada.nextInt();
        entrada.nextLine(); // Limpar o buffer
        String sql = "";
        String fieldValue = "";
        switch (opcao) {
            case 1:
                System.out.print("Digite o novo nome: ");
                fieldValue = entrada.nextLine();
                sql = "UPDATE alocador SET nome = ? WHERE cpf = ?";
                break;
            case 2:
                System.out.print("Digite o novo CPF: ");
                fieldValue = entrada.nextLine();
                sql = "UPDATE alocador SET cpf = ? WHERE cpf = ?";
                break;
            case 3:
                System.out.print("Digite o novo telefone: ");
                fieldValue = entrada.nextLine();
                sql = "UPDATE alocador SET telefone = ? WHERE cpf = ?";
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fieldValue);
            stmt.setString(2, cpf);
            stmt.executeUpdate();
            System.out.println("Alocador atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar alocador.");
        }
    }

    @Override
    public void excluir() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Exclusão de Alocador");
        System.out.println("Alocadores cadastrados: ");
        relatorio();
        System.out.println("Consultar pelo CPF");
        String cpf = entrada.nextLine();
        Alocador alocador = buscarCpf(cpf);
        if (alocador == null) {
            System.out.println("Alocador não encontrado!");
            return;
        }
        String sql = "DELETE FROM alocador WHERE cpf = ?";
        try (Connection conn = ConexaoDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            System.out.println("Alocador excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao remover alocador.");
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
                alocador.setId(rs.getInt("id")); // Recuperar e definir o id
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