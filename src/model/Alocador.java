package model;

public class Alocador extends Pessoa {
    public Alocador(String nome, String cpf, String telefone) {
        super(nome, cpf, telefone);
    }

    @Override
    public String toString() {
        return "Alocador [ID=" + getId() + ", Nome=" + getNome() + ", CPF=" + getCpf() + ", Telefone=" + getTelefone() + "]";
    }
}