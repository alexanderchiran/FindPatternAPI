/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Tienda;
import com.gestion.coves.services.TiendaServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class TiendaFachada implements TiendaFachadaLocal {

    @Inject
    private TiendaServiceLocal tiendaServices;
    
    @Override
    public List<Tienda> findAll() {
        return tiendaServices.findAll();
    }

    @Override
    public void edit(Tienda selected) {
       tiendaServices.edit(selected);
    }

    @Override
    public void remove(Tienda selected) {
        tiendaServices.remove(selected);
    }

    @Override
    public Tienda find(Integer id) {
       return tiendaServices.find(id);
    }

   
}
