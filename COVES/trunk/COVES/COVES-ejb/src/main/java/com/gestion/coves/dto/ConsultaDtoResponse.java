/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.dto;

import com.gestion.coves.dominio.entities.Producto;
import java.math.BigDecimal;

/**
 *
 * @author Alexander Chiran
 */
public class ConsultaDtoResponse {

    private Producto producto;
    private Integer cantidad;
    private BigDecimal costoUnitario;
    private BigDecimal costoTotal;
    private BigDecimal precioPromedioVenta;
    private BigDecimal precioVentaTotal;
    private BigDecimal gananciaUnitario;

    /**
     * @return the producto
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the costoUnitario
     */
    public BigDecimal getCostoUnitario() {
        return costoUnitario;
    }

    /**
     * @param costoUnitario the costoUnitario to set
     */
    public void setCostoUnitario(BigDecimal costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    /**
     * @return the costoTotal
     */
    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    /**
     * @param costoTotal the costoTotal to set
     */
    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    /**
     * @return the precioPromedioVenta
     */
    public BigDecimal getPrecioPromedioVenta() {
        return precioPromedioVenta;
    }

    /**
     * @param precioPromedioVenta the precioPromedioVenta to set
     */
    public void setPrecioPromedioVenta(BigDecimal precioPromedioVenta) {
        this.precioPromedioVenta = precioPromedioVenta;
    }

    /**
     * @return the precioVentaTotal
     */
    public BigDecimal getPrecioVentaTotal() {
        return precioVentaTotal;
    }

    /**
     * @param precioVentaTotal the precioVentaTotal to set
     */
    public void setPrecioVentaTotal(BigDecimal precioVentaTotal) {
        this.precioVentaTotal = precioVentaTotal;
    }

    /**
     * @return the gananciaUnitario
     */
    public BigDecimal getGananciaUnitario() {
        return gananciaUnitario;
    }

    /**
     * @param gananciaUnitario the gananciaUnitario to set
     */
    public void setGananciaUnitario(BigDecimal gananciaUnitario) {
        this.gananciaUnitario = gananciaUnitario;
    }


}
