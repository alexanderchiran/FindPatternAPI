/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Rol;
import com.gestion.coves.services.RolServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class RolFachada implements RolFachadaLocal {

    @Inject
    private RolServiceLocal rolService;

    @Override
    public List<Rol> findAll() {
        return rolService.findAll();
    }

    @Override
    public void edit(Rol selected) {
        rolService.edit(selected);
    }

    @Override
    public void remove(Rol selected) {
        rolService.remove(selected);
    }

    @Override
    public Rol find(Integer id) {
        return rolService.find(id);
    }

}
