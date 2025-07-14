package control;

import dao.AlocadorDAO;
import interfaces.Crud;

public class AlocadorController {
    private final AlocadorDAO alocadorDAO;

    public AlocadorController() {
        this.alocadorDAO = new AlocadorDAO();
    }

    public void adicionarAlocador() {
        alocadorDAO.cadastrar();
    }

    public void buscarAlocador() {
        alocadorDAO.consultar();
    }

    public void listarAlocadores() {
        alocadorDAO.relatorio();
    }

    public void atualizarAlocador() {
        alocadorDAO.alterar();
    }

    public void deletarAlocador() {
        alocadorDAO.excluir();
    }
}