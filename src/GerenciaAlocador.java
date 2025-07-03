import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaAlocador implements Crud{
    private final ArrayList<Alocador> alocadores;
    private final Scanner entrada;

    public GerenciaAlocador(ArrayList<Alocador> alocadores) {
        this.alocadores = alocadores;
        this.entrada = new Scanner(System.in);
    }

    @Override
    public void cadastrar() {
        System.out.println("Cadastro de Alocador");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();
        System.out.print("CPF: ");
        String cpf = entrada.nextLine();
        System.out.print("Telefone: ");
        String telefone = entrada.nextLine();

        Alocador alocador = new Alocador(nome, cpf, telefone);

        if (!alocadores.contains(alocador)) {
            alocadores.add(alocador);
            System.out.println("Alocador cadastrado com sucesso!");
            return;
        }
        System.out.println("Alocador já cadastrado!");
    }
    private Alocador bucarCpf(String cpf) {
        for (Alocador alocador : alocadores) {
            if (alocador.getCpf().equals(cpf)) {
                return alocador;
            }
        }
        return null;
    }

    private  Alocador buscaAlocador(String cpf) {
        return bucarCpf(cpf);
    }

    private Alocador busca() {
        System.out.println("Consultar pelo CPF");
        String cpf = entrada.nextLine();
        entrada.skip("\n");
        Alocador alocador = null;
        alocador = buscaAlocador(cpf);
        if (alocador == null) {
            System.out.println("Alocador não encontrado!");
        }
        return alocador;
    }

    @Override
    public void alterar() {
        System.out.println("Alteração de Alocador");
        System.out.println("alocadores cadastrados: ");
        System.out.println(alocadores);

        Alocador alocador =  busca();
        if (alocador == null) {
            System.out.println("Alocador não encontrado");
            return;
        }
        System.out.println(alocador);
        System.out.println("escolha o campo a ser alterado:");
        System.out.println("1 - Nome");
        System.out.println("2 - CPF");
        System.out.println("3 - Telefone");
        int opcao = entrada.nextInt();
        entrada.skip("\n");
        switch (opcao) {
            case 1 -> {
                System.out.print("Digite o novo nome: ");
                String nome = entrada.nextLine();
                alocador.setNome(nome);
            }
            case 2 -> {
                System.out.print("Digite o novo CPF: ");
                String cpf = entrada.nextLine();
                alocador.setCpf(cpf);
            }
            case 3 -> {
                System.out.print("Digite o novo telefone: ");
                String telefone = entrada.nextLine();
                alocador.setTelefone(telefone);
            }
            default -> System.out.println("Opção inválida!");
        }

    }

    @Override
    public void excluir() {
        System.out.println("Exclusão de Alocador");
        System.out.println("Alocadores cadastrados: ");
        System.out.println(alocadores);
        Alocador alocador = busca();
        if (alocador == null) {
            System.out.println("Alocador não encontrado");
            return;
        }
        alocadores.remove(alocador);
        System.out.println("Alocador excluído com sucesso!");
    }

    @Override
    public void relatorio() {
        System.out.println(alocadores);
    }

    @Override
    public void consultar() {
        System.out.println("Consultar Alocador");
        System.out.println("Alocadores cadastrados: ");
        System.out.println(alocadores);
        Alocador alocador = busca();
        if (alocador == null) {
            System.out.println("Alocador não encontrado");
            return;
        }
        System.out.println(alocador);
    }
}
