package model;

import java.time.LocalDateTime;

public class Alocacao {
    private int id;
    private String cpfAlocador;
    private String placaVeiculo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String status;

    public Alocacao(String cpfAlocador, String placaVeiculo, LocalDateTime dataInicio, LocalDateTime dataFim, String status) {
        this.cpfAlocador = cpfAlocador;
        this.placaVeiculo = placaVeiculo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpfAlocador() {
        return cpfAlocador;
    }

    public void setCpfAlocador(String cpfAlocador) {
        this.cpfAlocador = cpfAlocador;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Alocacao [ID=" + id + ", cpfAlocador=" + cpfAlocador + ", placaVeiculo=" + placaVeiculo +
                ", dataInicio=" + dataInicio + ", dataFim=" + (dataFim != null ? dataFim : "N/A") +
                ", status=" + status + "]";
    }
}
