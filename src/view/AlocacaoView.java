package view;

import control.AlocadorController;
import control.VeiculoController;
import java.util.Scanner;

public class AlocacaoView {
    private static final Scanner entrada = new Scanner(System.in);
    private static final AlocadorController alocadorController = new AlocadorController();
    private static final VeiculoController veiculoController = new VeiculoController();

    public static int menu() {
        System.out.println("1 - Cadastrar ");
        System.out.println("2 - Alterar ");
        System.out.println("3 - Excluir ");
        System.out.println("4 - Consultar ");
        System.out.println("5 - Relatorio ");
        System.out.println("0 - Voltar ao menu principal");
        System.out.println("Opcao: ");
        return entrada.nextInt();
    }

    public static int subMenu() {
        System.out.println("Escolha o tipo:");
        System.out.println("1 - Alocador");
        System.out.println("2 - Veiculo");
        System.out.println("Opcao: ");
        return entrada.nextInt();
    }

    public static void cadastrarAlocador() {
        entrada.nextLine(); // Limpar o buffer
        System.out.println("Cadastro de Alocador");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        System.out.print("CPF: ");
        String cpf = entrada.nextLine();
        System.out.print("Telefone: ");
        String telefone = entrada.nextLine();
        alocadorController.adicionarAlocador(nome, cpf, telefone);
    }

    public static void consultarAlocador() {
        entrada.nextLine(); // Limpar o buffer
        System.out.println("Consultar Alocador");
        System.out.println("Alocadores cadastrados: ");
        alocadorController.listarAlocadores();
        System.out.println("Consultar pelo CPF");
        String cpf = entrada.nextLine();
        alocadorController.buscarAlocador(cpf);
    }

    public static void alterarAlocador() {
        entrada.nextLine(); // Limpar o buffer
        System.out.println("Alteracao de Alocador");
        System.out.println("Alocadores cadastrados: ");
        alocadorController.listarAlocadores();
        System.out.println("Consultar pelo CPF");
        String cpf = entrada.nextLine();
        System.out.println("Escolha o campo a ser alterado:");
        System.out.println("1 - Nome");
        System.out.println("2 - CPF");
        System.out.println("3 - Telefone");
        int opcao = entrada.nextInt();
        entrada.nextLine(); // Limpar o buffer
        String fieldValue = "";
        switch (opcao) {
            case 1:
                System.out.print("Digite o novo nome: ");
                fieldValue = entrada.nextLine();
                break;
            case 2:
                System.out.print("Digite o novo CPF: ");
                fieldValue = entrada.nextLine();
                break;
            case 3:
                System.out.print("Digite o novo telefone: ");
                fieldValue = entrada.nextLine();
                break;
            default:
                System.out.println("Opcao invalida!");
                return;
        }
        alocadorController.atualizarAlocador(cpf, opcao, fieldValue);
    }

    public static void excluirAlocador() {
        entrada.nextLine(); // Limpar o buffer
        System.out.println("Exclusao de Alocador");
        System.out.println("Alocadores cadastrados: ");
        alocadorController.listarAlocadores();
        System.out.println("Consultar pelo CPF");
        String cpf = entrada.nextLine();
        alocadorController.deletarAlocador(cpf);
    }

    public static void cadastrarVeiculo() {
        entrada.nextLine(); // Limpar o buffer
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
        System.out.println("ID do Alocador (deixe em branco se nao houver): ");
        String idAlocadorStr = entrada.nextLine();
        Integer idAlocador = idAlocadorStr.isEmpty() ? null : Integer.parseInt(idAlocadorStr);
        try {
            veiculoController.adicionarVeiculo(modelo, placa, marca, ano, cor, idAlocador);
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID do alocador deve ser um numero valido!");
        }
    }

    public static void consultarVeiculo() {
        entrada.nextLine(); // Limpar o buffer
        System.out.println("Consultar Carro");
        veiculoController.listarVeiculos();
        System.out.println("Consultar pela Placa");
        String placa = entrada.nextLine();
        veiculoController.buscarVeiculo(placa);
    }

    public static void alterarVeiculo() {
        entrada.nextLine(); // Limpar o buffer
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
        System.out.println("6 - ID do Alocador");
        int opcao = entrada.nextInt();
        entrada.nextLine(); // Limpar o buffer
        String fieldValue = "";
        Integer idAlocador = null;
        switch (opcao) {
            case 1:
                System.out.println("Digite o novo modelo: ");
                fieldValue = entrada.nextLine();
                break;
            case 2:
                System.out.println("Digite a nova placa: ");
                fieldValue = entrada.nextLine();
                break;
            case 3:
                System.out.println("Digite a nova marca: ");
                fieldValue = entrada.nextLine();
                break;
            case 4:
                System.out.println("Digite o novo ano: ");
                fieldValue = entrada.nextLine();
                break;
            case 5:
                System.out.println("Digite a nova cor: ");
                fieldValue = entrada.nextLine();
                break;
            case 6:
                System.out.println("Digite o novo ID do Alocador (deixe em branco se nao houver): ");
                String idAlocadorStr = entrada.nextLine();
                try {
                    idAlocador = idAlocadorStr.isEmpty() ? null : Integer.parseInt(idAlocadorStr);
                } catch (NumberFormatException e) {
                    System.out.println("Erro: ID do alocador deve ser um numero valido!");
                    return;
                }
                break;
            default:
                System.out.println("Opcao invalida");
                return;
        }
        veiculoController.atualizarVeiculo(placa, opcao, fieldValue, idAlocador);
    }

    public static void excluirVeiculo() {
        entrada.nextLine(); // Limpar o buffer
        System.out.println("Exclusao de Carro");
        veiculoController.listarVeiculos();
        System.out.println("Consultar pela Placa");
        String placa = entrada.nextLine();
        veiculoController.deletarVeiculo(placa);
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