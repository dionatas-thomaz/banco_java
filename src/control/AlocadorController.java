package control;

import dao.AlocadorDAO;

public class AlocadorController {
    private final AlocadorDAO alocadorDAO;

    public AlocadorController() {
        this.alocadorDAO = new AlocadorDAO();
    }

    public void adicionarAlocador(Object[] dados) {
        try {
            alocadorDAO.cadastrar(dados);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarAlocador(Object[] dados) {
        try {
            alocadorDAO.consultar(dados);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarAlocador(Object[] dados) {
        try {
            alocadorDAO.alterar(dados);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletarAlocador(Object[] dados) {
        try {
            alocadorDAO.excluir(dados);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarAlocadores() {
        try {
            alocadorDAO.relatorio();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}