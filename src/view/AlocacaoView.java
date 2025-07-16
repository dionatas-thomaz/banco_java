package view;

import control.AlocadorController;
import control.VeiculoController;
import control.AlocacaoController;
import java.time.LocalDateTime;
import java.util.Scanner;

public class AlocacaoView {
    private static final Scanner entrada = new Scanner(System.in);
    private static final AlocadorController alocadorController = new AlocadorController();
    private static final VeiculoController veiculoController = new VeiculoController();
    private static final AlocacaoController alocacaoController = new AlocacaoController();

    public static int menu() {
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Alterar");
        System.out.println("3 - Excluir");
        System.out.println("4 - Consultar");
        System.out.println("5 - Relatorio");
        System.out.println("0 - Voltar ao menu principal");
        System.out.println("Opcao: ");
        return entrada.nextInt();
    }

    public static int subMenu() {
        System.out.println("Escolha o tipo:");
        System.out.println("1 - Alocador");
        System.out.println("2 - Veiculo");
        System.out.println("3 - Alocacao");
        System.out.println("Opcao: ");
        return entrada.nextInt();
    }

    public static void cadastrarAlocador() {
        entrada.nextLine();
        System.out.println("Cadastro de Alocador");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        System.out.print("CPF: ");
        String cpf = entrada.nextLine();
        System.out.print("Telefone: ");
        String telefone = entrada.nextLine();
        Object[] dados = {nome, cpf, telefone};
        alocadorController.adicionarAlocador(dados);
    }

    public static void consultarAlocador() {
        entrada.nextLine();
        System.out.println("Consultar Alocador");
        alocadorController.listarAlocadores();
        System.out.println("Consultar pelo CPF");
        String cpf = entrada.nextLine();
        Object[] dados = {cpf};
        alocadorController.buscarAlocador(dados);
    }

    public static void alterarAlocador() {
        entrada.nextLine();
        System.out.println("Alteracao de Alocador");
        alocadorController.listarAlocadores();
        System.out.println("Consultar pelo CPF");
        String cpf = entrada.nextLine();
        System.out.println("Escolha o campo a ser alterado:");
        System.out.println("1 - Nome");
        System.out.println("2 - CPF");
        System.out.println("3 - Telefone");
        int opcao = entrada.nextInt();
        entrada.nextLine();
        System.out.print("Digite o novo valor: ");
        String fieldValue = entrada.nextLine();
        Object[] dados = {cpf, opcao, fieldValue};
        alocadorController.atualizarAlocador(dados);
    }

    public static void excluirAlocador() {
        entrada.nextLine();
        System.out.println("Exclusao de Alocador");
        alocadorController.listarAlocadores();
        System.out.println("Consultar pelo CPF");
        String cpf = entrada.nextLine();
        Object[] dados = {cpf};
        alocadorController.deletarAlocador(dados);
    }

    public static void cadastrarVeiculo() {
        entrada.nextLine();
        System.out.println("Cadastro de Carro");
        System.out.println("Modelo do veiculo: ");
        String modelo = entrada.nextLine();
        System.out.println("Placa do veiculo: ");
        String placa = entrada.nextLine();
        System.out.println("Marca do veiculo: ");
        String marca = entrada.nextLine();
        System.out.println("Ano do veiculo: ");
        String ano = entrada.nextLine();
        System.out.println("Cor do veiculo: ");
        String cor = entrada.nextLine();
        Object[] dados = {modelo, placa, marca, ano, cor};
        veiculoController.adicionarVeiculo(dados);
    }

    public static void consultarVeiculo() {
        entrada.nextLine();
        System.out.println("Consultar Carro");
        veiculoController.listarVeiculos();
        System.out.println("Consultar pela Placa");
        String placa = entrada.nextLine();
        Object[] dados = {placa};
        veiculoController.buscarVeiculo(dados);
    }

    public static void alterarVeiculo() {
        entrada.nextLine();
        System.out.println("Alteracao de Carro");
        veiculoController.listarVeiculos();
        System.out.println("Consultar pela Placa");
        String placa = entrada.nextLine();
        System.out.println("Escolha a opcao a ser alterada: ");
        System.out.println("1 - Modelo");
        System.out.println("2 - Placa");
        System.out.println("3 - Marca");
        System.out.println("4 - Ano");
        System.out.println("5 - Cor");
        int opcao = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Digite o novo valor: ");
        String fieldValue = entrada.nextLine();
        Object[] dados = {placa, opcao, fieldValue};
        veiculoController.atualizarVeiculo(dados);
    }

    public static void excluirVeiculo() {
        entrada.nextLine();
        System.out.println("Exclusao de Carro");
        veiculoController.listarVeiculos();
        System.out.println("Consultar pela Placa");
        String placa = entrada.nextLine();
        Object[] dados = {placa};
        veiculoController.deletarVeiculo(dados);
    }

    public static void cadastrarAlocacao() {
        entrada.nextLine();
        System.out.println("Cadastro de Alocacao");
        System.out.println("Alocadores cadastrados: ");
        alocadorController.listarAlocadores();
        System.out.println("Veiculos cadastrados: ");
        veiculoController.listarVeiculos();
        System.out.print("CPF do alocador: ");
        String cpfAlocador = entrada.nextLine();
        System.out.print("Placa do veiculo: ");
        String placaVeiculo = entrada.nextLine();
        System.out.print("Status (ativa/encerrada): ");
        String status = entrada.nextLine();
        Object[] dados = {cpfAlocador, placaVeiculo, LocalDateTime.now(), null, status};
        alocacaoController.adicionarAlocacao(dados);
    }

    public static void consultarAlocacao() {
        entrada.nextLine();
        System.out.println("Consultar Alocacao");
        alocacaoController.listarAlocacoes();
        System.out.println("Consultar por CPF do alocador e Placa do veiculo");
        System.out.print("CPF do alocador: ");
        String cpfAlocador = entrada.nextLine();
        System.out.print("Placa do veiculo: ");
        String placaVeiculo = entrada.nextLine();
        Object[] dados = {cpfAlocador, placaVeiculo};
        alocacaoController.buscarAlocacao(dados);
    }

    public static void alterarAlocacao() {
        entrada.nextLine();
        System.out.println("Alteracao de Alocacao");
        alocacaoController.listarAlocacoes();
        System.out.println("Consultar por CPF do alocador e Placa do veiculo");
        System.out.print("CPF do alocador: ");
        String cpfAlocador = entrada.nextLine();
        System.out.print("Placa do veiculo: ");
        String placaVeiculo = entrada.nextLine();
        System.out.println("Escolha o campo a ser alterado:");
        System.out.println("1 - Data de fim");
        System.out.println("2 - Status");
        int opcao = entrada.nextInt();
        entrada.nextLine();
        String fieldValue = "";
        LocalDateTime dataFim = null;
        switch (opcao) {
            case 1:
                System.out.print("Digite a data de fim (YYYY-MM-DD HH:MM:SS, deixe em branco para nulo): ");
                String dataFimStr = entrada.nextLine();
                if (!dataFimStr.isEmpty()) {
                    dataFim = LocalDateTime.parse(dataFimStr.replace(" ", "T"));
                }
                break;
            case 2:
                System.out.print("Digite o novo status (ativa/encerrada): ");
                fieldValue = entrada.nextLine();
                break;
            default:
                System.out.println("Opcao invalida!");
                return;
        }
        Object[] dados = {cpfAlocador, placaVeiculo, opcao, fieldValue, dataFim};
        alocacaoController.atualizarAlocacao(dados);
    }

    public static void excluirAlocacao() {
        entrada.nextLine();
        System.out.println("Exclusao de Alocacao");
        alocacaoController.listarAlocacoes();
        System.out.println("Consultar por CPF do alocador e Placa do veiculo");
        System.out.print("CPF do alocador: ");
        String cpfAlocador = entrada.nextLine();
        System.out.print("Placa do veiculo: ");
        String placaVeiculo = entrada.nextLine();
        Object[] dados = {cpfAlocador, placaVeiculo};
        alocacaoController.deletarAlocacao(dados);
    }

    public static void main(String[] args) {
        int entra;
        do {
            entra = menu();
            switch (entra) {
                case 1 -> {
                    System.out.println("Cadastra alocacao");
                    int opcaoSub = subMenu();
                    if (opcaoSub == 1) {
                        cadastrarAlocador();
                    } else if (opcaoSub == 2) {
                        cadastrarVeiculo();
                    } else if (opcaoSub == 3) {
                        cadastrarAlocacao();
                    } else {
                        System.out.println("Opcao invalida!");
                    }
                }
                case 2 -> {
                    System.out.println("Alteracao de alocacao");
                    int opcaoSub = subMenu();
                    if (opcaoSub == 1) {
                        alterarAlocador();
                    } else if (opcaoSub == 2) {
                        alterarVeiculo();
                    } else if (opcaoSub == 3) {
                        alterarAlocacao();
                    } else {
                        System.out.println("Opcao invalida!");
                    }
                }
                case 3 -> {
                    System.out.println("Exclusao de alocacao");
                    int opcaoSub = subMenu();
                    if (opcaoSub == 1) {
                        excluirAlocador();
                    } else if (opcaoSub == 2) {
                        excluirVeiculo();
                    } else if (opcaoSub == 3) {
                        excluirAlocacao();
                    } else {
                        System.out.println("Opcao invalida!");
                    }
                }
                case 4 -> {
                    System.out.println("Consulta alocacao");
                    int opcaoSub = subMenu();
                    if (opcaoSub == 1) {
                        consultarAlocador();
                    } else if (opcaoSub == 2) {
                        consultarVeiculo();
                    } else if (opcaoSub == 3) {
                        consultarAlocacao();
                    } else {
                        System.out.println("Opcao invalida!");
                    }
                }
                case 5 -> {
                    System.out.println("Relatorio de Alocacao");
                    int opcaoSub = subMenu();
                    if (opcaoSub == 1) {
                        alocadorController.listarAlocadores();
                    } else if (opcaoSub == 2) {
                        veiculoController.listarVeiculos();
                    } else if (opcaoSub == 3) {
                        alocacaoController.listarAlocacoes();
                    } else {
                        System.out.println("Opcao invalida!");
                    }
                }
                case 0 -> System.out.println("Finalizando o programa...");
                default -> System.out.println("Opcao invalida!");
            }
        } while (entra != 0);
    }
}