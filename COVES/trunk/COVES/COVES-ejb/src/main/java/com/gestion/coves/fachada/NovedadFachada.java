/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Novedad;
import com.gestion.coves.services.NovedadServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class NovedadFachada implements NovedadFachadaLocal {

    @Inject
    private NovedadServiceLocal servicesNovedad;
    
    @Override
    public List<Novedad> findAll() {
        return servicesNovedad.findAll();
    }

    @Override
    public void edit(Novedad selected) {
       servicesNovedad.edit(selected);
    }

    @Override
    public void remove(Novedad selected) {
        servicesNovedad.remove(selected);
    }

    @Override
    public Novedad find(Integer id) {
        return servicesNovedad.find(id);
    }

}
