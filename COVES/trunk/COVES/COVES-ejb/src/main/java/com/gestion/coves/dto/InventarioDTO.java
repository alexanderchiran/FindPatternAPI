/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.dto;

import com.gestion.coves.dominio.entities.Producto;
import com.gestion.coves.dominio.entities.Tienda;
import java.math.BigDecimal;

/**
 *
 * @author Alexander Chiran
 */
public class InventarioDTO {
    
    private Producto producto;
    private Integer cantidad;
    private String operacion;
    private Tienda tienda;
    private BigDecimal costo;
    private String origen;
    private boolean origenTienda;
    private boolean novedad;

    public InventarioDTO(Producto producto, Integer cantidad, String operacion, Tienda tienda, BigDecimal costo, String origen) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.operacion = operacion;
        this.tienda = tienda;
        this.costo = costo;
        this.origen = origen;
    }

    public InventarioDTO() {
    }
       
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public boolean isOrigenTienda() {
        return origenTienda;
    }

    public void setOrigenTienda(boolean origenTienda) {
        this.origenTienda = origenTienda;
    }

    /**
     * @return the novedad
     */
    public boolean isNovedad() {
        return novedad;
    }

    /**
     * @param novedad the novedad to set
     */
    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
    }
    
    
}
