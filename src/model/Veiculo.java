package model;

public class Veiculo {
    private String modelo;
    private String placa;
    private String cor;
    private String marca;
    private String ano;

    public Veiculo(String modelo, String placa, String cor, String marca, String ano) {
        this.modelo = modelo;
        this.placa = placa;
        this.cor = cor;
        this.marca = marca;
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Veiculo [Modelo=" + modelo + ", Placa=" + placa + ", Cor=" + cor + ", Marca=" + marca + ", Ano=" + ano + "]";
    }
}