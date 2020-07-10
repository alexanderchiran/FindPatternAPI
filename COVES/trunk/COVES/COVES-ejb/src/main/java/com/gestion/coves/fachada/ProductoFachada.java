/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Producto;
import com.gestion.coves.services.ProductoServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class ProductoFachada implements ProductoFachadaLocal {

    @Inject
    private ProductoServiceLocal productoServices;
    
    
    @Override
    public List<Producto> findAll() {
        return productoServices.findAll();
    }

    @Override
    public void edit(Producto selected) {
        productoServices.edit(selected);
    }

    @Override
    public void remove(Producto selected) {
        productoServices.remove(selected);
    }

    @Override
    public Producto find(Integer id) {
        return productoServices.find(id);
    }

    
}
