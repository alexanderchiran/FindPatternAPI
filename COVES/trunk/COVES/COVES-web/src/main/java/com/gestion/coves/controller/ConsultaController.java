/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.controller;

import com.gestion.coves.controller.util.Control;
import com.gestion.coves.dto.ConsultaDtoRequest;
import com.gestion.coves.dto.ConsultaDtoResponse;
import com.gestion.coves.dto.ConsultaDtoResponseTotales;
import com.gestion.coves.exception.ExceptionControl;
import com.gestion.coves.fachada.ConsultaVentasLocal;
import com.gestion.coves.fachada.ProductoFachadaLocal;
import com.gestion.coves.util.Global;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Named(value = "consultaController")
@SessionScoped
public class ConsultaController extends Control implements Serializable {
    
    @Inject
    private ConsultaVentasLocal cosultaFachada;
    @Inject
    private ProductoFachadaLocal productoFachada;
    private ConsultaDtoRequest consulta;
    private List<ConsultaDtoResponse> items;
    private ConsultaDtoResponseTotales totales;
    
    @PostConstruct
    public void init() {
        consulta = new ConsultaDtoRequest();
    }
    
    public ConsultaDtoRequest getConsulta() {
        return consulta;
    }
    
    public void setConsulta(ConsultaDtoRequest consulta) {
        this.consulta = consulta;
        items = new ArrayList<>();
    }
    
    private ProductoFachadaLocal getFacade() {
        return productoFachada;
    }
    
    public List<ConsultaDtoResponse> getItems() {
        return items;
    }
    
    public void setItems(List<ConsultaDtoResponse> items) {
        this.items = items;
    }
    
    public ConsultaVentasLocal getCosultaFachada() {
        return cosultaFachada;
    }
    
    public void setCosultaFachada(ConsultaVentasLocal cosultaFachada) {
        this.cosultaFachada = cosultaFachada;
    }
    
    public ConsultaDtoResponseTotales getTotales() {
        return totales;
    }
    
    public void setTotales(ConsultaDtoResponseTotales totales) {
        this.totales = totales;
    }
    
    public void consultarVentas() {
        try {
            if (consulta.getFechaInicio() != null) {
                if (consulta.getFechaFin() == null) {
                    imprimirMensaje("Seleccione la Fecha Fin", FacesMessage.SEVERITY_INFO);
                } else if (consulta.getCodProducto() == null) {
                    imprimirMensaje("Seleccione Productos", FacesMessage.SEVERITY_INFO);
                } else if (consulta.getCodTienda() == null) {
                    imprimirMensaje("Seleccione Productos", FacesMessage.SEVERITY_INFO);
                } else {
                    Integer totalCostoSum = 0;
                    Integer totalVentaSum = 0;
                    Integer totalGananciaSum = 0;
                    try {
                        this.totales = new ConsultaDtoResponseTotales();
                        items = cosultaFachada.consultarVentas(consulta);
                        
                        if (items != null) {
                            for (ConsultaDtoResponse dto : items) {
                                totalCostoSum += dto.getCostoTotal().intValue();
                                totalVentaSum += dto.getPrecioVentaTotal().intValue();
                                totalGananciaSum += dto.getGananciaUnitario().intValue();
                            }
                            
                            getTotales().setCostoTotal(new BigDecimal(totalCostoSum));
                            getTotales().setGananciaTotal(new BigDecimal(totalGananciaSum));
                            getTotales().setVentaTotal(new BigDecimal(totalVentaSum));
                        }
                    } catch (ExceptionControl ex) {
                        imprimirMensaje(ex.getMessage(), ex);
                        
                    }
                    
                }
            } else {
                imprimirMensaje("Seleccione la Fecha Inicio", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
        
    }
}
