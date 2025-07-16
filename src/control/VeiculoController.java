package control;
import dao.VeiculoDAO;

public class VeiculoController {
    private final VeiculoDAO veiculoDAO;

    public VeiculoController() {
        this.veiculoDAO = new VeiculoDAO();
    }

    public void adicionarVeiculo(Object[] dados) {
        try {
            veiculoDAO.cadastrar(dados);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarVeiculo(Object[] dados) {
        try {
            veiculoDAO.consultar(dados);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarVeiculo(Object[] dados) {
        try {
            veiculoDAO.alterar(dados);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletarVeiculo(Object[] dados) {
        try {
            veiculoDAO.excluir(dados);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarVeiculos() {
        try {
            veiculoDAO.relatorio();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
