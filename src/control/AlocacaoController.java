package control;

import dao.AlocacaoDAO;

public class AlocacaoController {
    private final AlocacaoDAO alocacaoDAO;

    public AlocacaoController() {
        this.alocacaoDAO = new AlocacaoDAO();
    }

    public void adicionarAlocacao(Object[] dados) {
        try {
            alocacaoDAO.cadastrar(dados);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarAlocacao(Object[] dados) {
        try {
            alocacaoDAO.consultar(dados);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarAlocacao(Object[] dados) {
        try {
            alocacaoDAO.alterar(dados);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletarAlocacao(Object[] dados) {
        try {
            alocacaoDAO.excluir(dados);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarAlocacoes() {
        try {
            alocacaoDAO.relatorio();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}