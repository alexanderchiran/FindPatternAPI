/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.controller.util;

import com.gestion.coves.controller.CompraController;
import com.gestion.coves.controller.FacturarController;
import com.gestion.coves.controller.InventarioController;
import com.gestion.coves.controller.inventarioTiendaController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Alexander Chiran
 */
@Named
@RequestScoped
public class Navegacion {

    @Inject
    private FacturarController facturarController;
    @Inject
    private InventarioController inventarioController;
    @Inject
    private inventarioTiendaController inventarioTiendaController;
    @Inject
    private CompraController compraController;

    private List<String> imagenes;

    public String irACompras() {
        compraController.init();
        return "/compra/List.xhtml?faces-redirect=true";
    }

    @PostConstruct
    public void init() {
        imagenes = new ArrayList<>();
        imagenes.add("2.jpg");
        imagenes.add("1.jpg");
        imagenes.add("10.jpg");
        imagenes.add("11.jpg");
        imagenes.add("12.jpg");
        imagenes.add("13.jpg");
        imagenes.add("14.png");
        imagenes.add("15.png");
        imagenes.add("16.png");
        imagenes.add("3.jpg");
        imagenes.add("4.jpg");
        imagenes.add("5.jpg");
        imagenes.add("6.jpg");
        imagenes.add("7.jpg");
        imagenes.add("8.jpg");
        imagenes.add("9.jpg");

    }

    public String irAPrincipal() {
        return "/principal.xhtml?faces-redirect=true";
    }

    public String irAFacturar() {
        facturarController.init();
        return "/facturar/List.xhtml?faces-redirect=true";
    }

    public String irAinventario() {
        inventarioController.init();
        return "/inventario/List.xhtml?faces-redirect=true";
    }

    public String irAInventarioTienda() {
        inventarioTiendaController.init();
        return "/inventarioTienda/List.xhtml?faces-redirect=true";
    }

    /**
     * @return the imagenes
     */
    public List<String> getImagenes() {
        return imagenes;
    }

    /**
     * @param imagenes the imagenes to set
     */
    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }
}
