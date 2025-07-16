package control;

import dao.VeiculoDAO;
import java.sql.SQLException;

public class VeiculoController {
    private final VeiculoDAO veiculoDAO;

    public VeiculoController() {
        this.veiculoDAO = new VeiculoDAO();
    }

    public void adicionarVeiculo(String modelo, String placa, String marca, String ano, String cor, Integer idAlocador) {
        try {
            veiculoDAO.cadastrar(modelo, placa, marca, ano, cor, idAlocador);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarVeiculo(String placa) {
        try {
            veiculoDAO.consultar(placa);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarVeiculo(String placa, int opcao, String fieldValue, Integer idAlocador) {
        try {
            veiculoDAO.alterar(placa, opcao, fieldValue, idAlocador);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletarVeiculo(String placa) {
        try {
            veiculoDAO.excluir(placa);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarVeiculos() {
        veiculoDAO.relatorio();
    }
}