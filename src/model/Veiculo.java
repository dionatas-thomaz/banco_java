package model;

import java.util.Objects;

public class Veiculo {
    protected String modelo;
    protected  String placa;
    protected  String cor;
    protected  String marca;
    protected  String ano;
    private Integer idAlocador;

    public Veiculo(String modelo, String placa, String cor, String marca, String ano) {
        this.modelo = modelo;
        this.placa = placa;
        this.cor = cor;
        this.marca = marca;
        this.ano = ano;
        this.idAlocador = null;
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
    public void setIdAlocador(Integer idAlocador) { this.idAlocador = idAlocador; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Veiculo veiculo)) return false;
        return Objects.equals(placa, veiculo.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa);
    }

    @Override
    public String toString() {
        return "modelos.Veiculo{" +
                "modelo='" + modelo + '\'' +
                ", placa='" + placa + '\'' +
                ", cor='" + cor + '\'' +
                ", marca='" + marca + '\'' +
                ", ano='" + ano + '\'' +
                ", idAlocador=" + idAlocador +
                '}';
    }
}
