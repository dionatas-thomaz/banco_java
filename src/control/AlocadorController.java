package control;

import dao.AlocadorDAO;
import java.sql.SQLException;

public class AlocadorController {
    private final AlocadorDAO alocadorDAO;

    public AlocadorController() {
        this.alocadorDAO = new AlocadorDAO();
    }

    public void adicionarAlocador(String nome, String cpf, String telefone) {
        try {
            alocadorDAO.cadastrar(nome, cpf, telefone);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarAlocador(String cpf) {
        try {
            alocadorDAO.consultar(cpf);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarAlocador(String cpf, int opcao, String fieldValue) {
        try {
            alocadorDAO.alterar(cpf, opcao, fieldValue);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletarAlocador(String cpf) {
        try {
            alocadorDAO.excluir(cpf);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarAlocadores() {
        alocadorDAO.relatorio();
    }
}