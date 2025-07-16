package interfaces;

public interface Crud<T> {
    void cadastrar(T dados) throws Exception;
    void alterar(T dados) throws Exception;
    void excluir(T dados) throws Exception;
    void relatorio() throws Exception;
    void consultar(T dados) throws Exception;
}