import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GerenciaVeiculo implements Crud {

    private final ArrayList<Veiculo> carros;
    private final Scanner entrada;

    public GerenciaVeiculo(ArrayList<Veiculo> carros, Scanner entrada) {
        this.carros = carros;
        this.entrada = new Scanner(System.in);
    }

    @Override
    public void cadastrar() {
        System.out.println("Cadastro de carro");
        System.out.println("Modelo do veiculo: ");
        String modelo = entrada.nextLine();
        System.out.println("placa do veiculo: ");
        String placa = entrada.nextLine();
        System.out.println("marca do veiculo: ");
        String marca = entrada.nextLine();
        System.out.println("ano do veiculo: ");
        String ano = entrada.nextLine();
        System.out.println("cor do veiculo: ");
        String cor = entrada.nextLine();

        Veiculo carro = new Veiculo(modelo,placa,marca,ano,cor);

        if(!carros.contains(carro)){
            carros.add(carro);
            return;
        }
        System.out.println("carro ja cadastrado!");
    }

    private Veiculo buscaPlaca(String placa) {
        for (Veiculo carro : carros) {
            if (Objects.equals(carro.getPlaca(), placa)) {
                return carro;
            }
        }
        return null;
    }

    private Veiculo buscaCarro(String placa) {
        return buscaPlaca(placa);
    }

    private Veiculo busca(){
        System.out.println("Consultar pela Placa");
        String placa = entrada.nextLine();
        entrada.skip("\n");
        Veiculo carro = null;
        carro = buscaCarro(placa);
        return carro;
    }

    @Override
    public void alterar() {
        System.out.println("Alterar Carro");
        System.out.println(carros);
        Veiculo carro = busca();

        if (carro == null) {
            System.out.println("Carro nao encontrado");
            return;
        }
        System.out.println(carro);
        System.out.println("Escolha a opcao a ser alterada: ");
        System.out.println("1 - Modelo");
        System.out.println("2 - Placa");
        System.out.println("3 - Marca");
        System.out.println("4 - Ano");
        System.out.println("5 - Cor");
        int opcao = entrada.nextInt();
        entrada.skip("\n");
        switch (opcao) {
            case 1 -> {
                System.out.println("Digite o novo modelo: ");
                String modelo = entrada.nextLine();
                carro.setModelo(modelo);
            }
            case 2 -> {
                System.out.println("Digite a nova placa: ");
                String placa = entrada.nextLine();
                carro.setPlaca(placa);
            }
            case 3 -> {
                System.out.println("Digite a nova marca: ");
                String marca = entrada.nextLine();
                carro.setMarca(marca);
            }
            case 4 -> {
                System.out.println("Digite o novo ano: ");
                String ano = entrada.nextLine();
                carro.setAno(ano);
            }
            case 5 -> {
                System.out.println("Digite a nova cor: ");
                String cor = entrada.nextLine();
                carro.setCor(cor);
            }
            default -> System.out.println("Opcao invalida");
        }

    }

    @Override
    public void excluir() {
        System.out.println("Cadastro de carro");
        System.out.println(carros);
        Veiculo carro = busca();
        if (carro == null) {
            System.out.println("Carro nao encontrado");
            return;
        }
        carros.remove(carro);
        System.out.println("Exclusão efetuada com sucesso!");
    }

    @Override
    public void relatorio() {
        System.out.println("Relatório de Carros");
        System.out.println(carros);
    }

    @Override
    public void consultar() {
        System.out.println("Consultar Carro");
        System.out.println(carros);
        Veiculo carro = busca();
        if (carro == null) {
            System.out.println("Carro nao encontrado");
            return;
        }
        System.out.println(carro);
    }
}
