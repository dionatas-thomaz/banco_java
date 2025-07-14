package control;
import dao.VeiculoDAO;
import model.Veiculo;
import java.util.ArrayList;

public class VeiculoController  {
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    public void adicionarVeiculo() {
         veiculoDAO.cadastrar();
    }

    public void buscarVeiculo() {
        veiculoDAO.consultar();
    }

    public void listarVeiculos() {
      veiculoDAO.relatorio();
    }

    public void atualizarVeiculo() {
       veiculoDAO.alterar();

    }
    public void deletarVeiculo() {
        veiculoDAO.excluir();
    }
}
