import java.util.ArrayList;
import java.util.Scanner;

public class Alocação {

    public static int menu() {
        Scanner entrada = new Scanner(System.in);

        System.out.println("1 - Cadastrar ");
        System.out.println("2 - Alterar ");
        System.out.println("3 - Excluir ");
        System.out.println("4 - Consultar ");
        System.out.println("5 - Relatório ");
        System.out.println("0 - Voltar ao menu principal");
        System.out.println("Opção: ");

        return entrada.nextInt();
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArrayList<Alocador> alocadores = new ArrayList<>();
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        GerenciaAlocador gerenciaAlocador = new GerenciaAlocador(alocadores);
        GerenciaVeiculo gerenciaVeiculo = new GerenciaVeiculo(veiculos);
        int entra;
        do {
             entra = menu();
            switch (entra)
            {
                case 1 -> {
                    System.out.println("Cadastro de Alocador");
                    gerenciaAlocador.cadastrar();
                    gerenciaVeiculo.cadastrar();
                }
                case 2 -> {
                    System.out.println("Alteração de Alocador");
                }
                case 3 -> {
                    System.out.println("Exclusão de Alocador");
                }
                case 4 -> {
                    System.out.println("Consulta de Alocador");
                    gerenciaVeiculo.consultar();
                }
                case 5 -> {
                    System.out.println("Relatório de Alocadores");
                    gerenciaVeiculo.relatorio();
                    gerenciaAlocador.relatorio();
                }
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }

        } while (entra != 0);

    }
}
