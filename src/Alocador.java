import java.util.ArrayList;
import java.util.List;

public class Alocador extends Pessoa{

    public Alocador(String nome, String cpf, String telefone) {
        super(nome, cpf, telefone);
    }

    @Override
    public String toString() {
        return "Alocador{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
