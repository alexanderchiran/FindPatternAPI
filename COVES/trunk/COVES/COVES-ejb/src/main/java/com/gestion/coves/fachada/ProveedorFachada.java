/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Proveedor;
import com.gestion.coves.services.ProveedorServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class ProveedorFachada implements ProveedorFachadaLocal {

    
    @Inject
    private ProveedorServiceLocal proveedorService;
    
    @Override
    public List<Proveedor> findAll() {
       return proveedorService.findAll();
    }

    @Override
    public void edit(Proveedor selected) {
        proveedorService.edit(selected);
    }

    @Override
    public void remove(Proveedor selected) {
        proveedorService.remove(selected);
    }

    @Override
    public Proveedor find(Integer id) {
       return proveedorService.find(id);
    }

    
}
