package br.com.cd2.sigabem.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Frete implements Serializable {
    public static final long serialVersionUID = 1L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @Column(nullable = false)
    private String nomeDestinatario;

    @Column(nullable = false, length = 8)
    private String cepOrigem;

    @Column(nullable = false, length = 8)
    private String cepDestino;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataPrevistaEntrega;

    @JsonIgnore
    private LocalDateTime dataConsulta;

    @JsonIgnore
    @Column(nullable = false)
    private Double peso;
    private BigDecimal vlTotalFrete;

    public Frete(Long id, String nomeDestinatario, String cepOrigem, String cepDestino, LocalDateTime dataPrevistaEntrega, LocalDateTime dataConsulta, Double peso, BigDecimal vlTotalFrete) {
        this.id = id;
        this.nomeDestinatario = nomeDestinatario;
        this.cepOrigem = cepOrigem;
        this.cepDestino = cepDestino;
        this.dataPrevistaEntrega = dataPrevistaEntrega;
        this.dataConsulta = dataConsulta;
        this.peso = peso;
        this.vlTotalFrete = vlTotalFrete;
    }

    public Frete() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public String getCepOrigem() {
        return cepOrigem;
    }

    public void setCepOrigem(String cepOrigem) {
        this.cepOrigem = cepOrigem;
    }

    public String getCepDestino() {
        return cepDestino;
    }

    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
    }

    public LocalDateTime getDataPrevistaEntrega() {
        return dataPrevistaEntrega;
    }

    public void setDataPrevistaEntrega(LocalDateTime dataPrevistaEntrega) {
        this.dataPrevistaEntrega = dataPrevistaEntrega;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public BigDecimal getVlTotalFrete() {
        return vlTotalFrete;
    }

    public void setVlTotalFrete(BigDecimal vlTotalFrete) {
        this.vlTotalFrete = vlTotalFrete;
    }

}
