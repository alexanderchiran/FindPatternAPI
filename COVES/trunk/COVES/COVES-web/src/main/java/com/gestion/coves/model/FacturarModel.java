/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.model;

import com.gestion.coves.dominio.entities.Cliente;
import com.gestion.coves.dominio.entities.Empresa;
import com.gestion.coves.dominio.entities.FacturaDetalle;
import com.gestion.coves.dominio.entities.Impuesto;
import com.gestion.coves.dominio.entities.Producto;
import com.gestion.coves.dominio.entities.Tienda;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Alexander Chiran
 */
public class FacturarModel implements Serializable {

    private boolean deshabilitar;
    private List<Producto> listaProducto;
    private List<FacturaDetalle> listaFacturaDetalle;
    private FacturaDetalle facturaDetalleSel;
    private Producto productoSeleccionado;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal precioTotal;
    private BigDecimal subTotal;
    private BigDecimal iva;
    private BigDecimal total;
    private Impuesto impuesto;
   // private List<Tienda> listaTienda;
    private Tienda tiendaSeleccionada;
    private Empresa empresa;
    private List<Cliente> listaCliente;

    /**
     * @return the listaProducto
     */
    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    /**
     * @param listaProducto the listaProducto to set
     */
    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    /**
     * @return the productoSeleccionado
     */
    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    /**
     * @param productoSeleccionado the productoSeleccionado to set
     */
    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
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
     * @return the precioUnitario
     */
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * @param precioUnitario the precioUnitario to set
     */
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * @return the precioTotal
     */
    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    /**
     * @param precioTotal the precioTotal to set
     */
    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    /**
     * @return the subTotal
     */
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the subTotal to set
     */
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * @return the iva
     */
    public BigDecimal getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the listaFacturaDetalle
     */
    public List<FacturaDetalle> getListaFacturaDetalle() {
        return listaFacturaDetalle;
    }

    /**
     * @param listaFacturaDetalle the listaFacturaDetalle to set
     */
    public void setListaFacturaDetalle(List<FacturaDetalle> listaFacturaDetalle) {
        this.listaFacturaDetalle = listaFacturaDetalle;
    }

    /**
     * @return the facturaDetalleSel
     */
    public FacturaDetalle getFacturaDetalleSel() {
        return facturaDetalleSel;
    }

    /**
     * @param facturaDetalleSel the facturaDetalleSel to set
     */
    public void setFacturaDetalleSel(FacturaDetalle facturaDetalleSel) {
        this.facturaDetalleSel = facturaDetalleSel;
    }

    /**
     * @return the impuesto
     */
    public Impuesto getImpuesto() {
        return impuesto;
    }

    /**
     * @param impuesto the impuesto to set
     */
    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    /**
     * @return the tiendaSeleccionada
     */
    public Tienda getTiendaSeleccionada() {
        return tiendaSeleccionada;
    }

    /**
     * @param tiendaSeleccionada the tiendaSeleccionada to set
     */
    public void setTiendaSeleccionada(Tienda tiendaSeleccionada) {
        this.tiendaSeleccionada = tiendaSeleccionada;
    }

    /**
     * @return the listaCliente
     */
    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    /**
     * @param listaCliente the listaCliente to set
     */
    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    /**
     * @return the deshabilitar
     */
    public boolean isDeshabilitar() {
        return deshabilitar;
    }

    /**
     * @param deshabilitar the deshabilitar to set
     */
    public void setDeshabilitar(boolean deshabilitar) {
        this.deshabilitar = deshabilitar;
    }

    /**
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
