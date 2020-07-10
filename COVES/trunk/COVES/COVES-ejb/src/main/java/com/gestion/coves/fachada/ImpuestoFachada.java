/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Impuesto;
import com.gestion.coves.services.ImpuestoServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class ImpuestoFachada implements ImpuestoFachadaLocal {

   @Inject
   private ImpuestoServiceLocal impuestoService;

    @Override
    public List<Impuesto> findAll() {
        return impuestoService.findAll();
    }

    @Override
    public void edit(Impuesto selected) {
        impuestoService.edit(selected);
    }

    @Override
    public void remove(Impuesto selected) {
        impuestoService.remove(selected);
    }

    @Override
    public Impuesto find(Integer id) {
        return impuestoService.find(id);
    }
   
   
}
