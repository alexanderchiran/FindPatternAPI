/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.dto;

import java.util.Date;

/**
 *
 * @author Alexander Chiran
 */
public class ConsultaDtoRequest {
    
    private Date fechaInicio;
    private Date fechaFin;
    private Integer codProducto;
    private Integer codTienda;

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(Integer codProducto) {
        this.codProducto = codProducto;
    }

    public Integer getCodTienda() {
        return codTienda;
    }

    public void setCodTienda(Integer codTienda) {
        this.codTienda = codTienda;
    }
    
}
