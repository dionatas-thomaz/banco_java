package view;

import control.AlocadorController;
import control.VeiculoController;

import java.util.Scanner;

public class AlocacaoView {
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

    public static int subMenu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Escolha o tipo:");
        System.out.println("1 - Alocador");
        System.out.println("2 - Veículo");
        System.out.println("Opção: ");
        return entrada.nextInt();
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        AlocadorController alocadorController = new AlocadorController();
        VeiculoController veiculoController = new VeiculoController();

        int entra;
        do {
            entra = menu();
            switch (entra) {
                case 1 -> {
                    System.out.println("Cadastra alocacao");
                    int opcaoSub = subMenu();
                    if (opcaoSub == 1) {
                        alocadorController.adicionarAlocador();
                    } else if (opcaoSub == 2) {
                        veiculoController.adicionarVeiculo();
                    } else {
                        System.out.println("Opcao invalida!");
                    }
                }
                case 2 -> {
                    System.out.println("Alteracao de alocacao");
                    int opcaoSub = subMenu();
                    if (opcaoSub == 1) {
                        alocadorController.atualizarAlocador();
                    } else if (opcaoSub == 2) {
                        veiculoController.atualizarVeiculo();
                    } else {
                        System.out.println("Opção invalida!");
                    }
                }
                case 3 -> {
                    System.out.println("Exclusao de alocacao");
                    int opcaoSub = subMenu();
                    if (opcaoSub == 1) {
                        alocadorController.deletarAlocador();
                    } else if (opcaoSub == 2) {
                        veiculoController.deletarVeiculo();
                    } else {
                        System.out.println("Opcao invalida!");
                    }
                }
                case 4 -> {
                    System.out.println("Consulta alocacao");
                    int opcaoSub = subMenu();
                    if (opcaoSub == 1) {
                        alocadorController.buscarAlocador();
                    } else if (opcaoSub == 2) {
                        veiculoController.buscarVeiculo();
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