package com.example.homebankads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class movimentos {

    int idmov;
    int idusr;
    String tipo_opera;
    double valor;
    String destino;
    Date dataopera;

    public movimentos() {
    }

    public movimentos(int idmov, int idusr, String tipo_opera, double valor, String destino, Date dataopera) {
        this.idmov = idmov;
        this.idusr = idusr;
        this.tipo_opera = tipo_opera;
        this.valor = valor;
        this.destino = destino;
        this.dataopera = dataopera;
    }

    public int getIdmov() {
        return idmov;
    }

    public void setIdmov(int idmov) {
        this.idmov = idmov;
    }

    public int getIdusr() {
        return idusr;
    }

    public void setIdusr(int idusr) {
        this.idusr = idusr;
    }

    public String getTipo_opera() {
        return tipo_opera;
    }

    public void setTipo_opera(String tipo_opera) {
        this.tipo_opera = tipo_opera;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getDataopera() {
        return dataopera;
    }

    public void setDataopera(Date dataopera) {
        this.dataopera = dataopera;
    }

    @Override
    public String toString() {
        // Formata a data como "dd/MM/yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return "Tipo: " + tipo_opera + ", Valor: R$ " + valor + ", Data: " + dateFormat.format(dataopera);
    }
}
