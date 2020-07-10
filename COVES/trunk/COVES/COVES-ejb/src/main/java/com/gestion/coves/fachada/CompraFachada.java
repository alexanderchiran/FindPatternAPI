/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Compra;
import com.gestion.coves.services.AbstractService;
import com.gestion.coves.services.CompraServicesLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class CompraFachada implements CompraFachadaLocal {

    @Inject
    private CompraServicesLocal  service;
    
    @Override
    public List<Compra> findAll() {        
       return service.findAll();
    }

    @Override
    public void edit(Compra selected) {
        service.edit(selected);
    }

    @Override
    public Compra find(Integer id) {
        return service.find(id);
    }

    @Override
    public List<Compra> findAllCompras() {
        return service.findAllCompras();
    }


}
