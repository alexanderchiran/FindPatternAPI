/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.TipoNoveda;
import com.gestion.coves.services.TipoNovedaServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class TipoNovedaFachada implements TipoNovedaFachadaLocal {

    @Inject
    private  TipoNovedaServiceLocal tipoNovedadService;
        
    @Override
    public List<TipoNoveda> findAll() {
       return tipoNovedadService.findAll();
    }

    @Override
    public void edit(TipoNoveda selected) {
        tipoNovedadService.edit(selected);
    }

    @Override
    public void remove(TipoNoveda selected) {
        tipoNovedadService.remove(selected);
    }

    @Override
    public TipoNoveda find(Integer id) {
        return tipoNovedadService.find(id);
    }

    
}
