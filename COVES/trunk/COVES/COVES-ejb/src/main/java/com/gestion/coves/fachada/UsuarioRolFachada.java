/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.UsuarioRol;
import com.gestion.coves.services.UsuarioRolServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class UsuarioRolFachada implements UsuarioRolFachadaLocal {

    @Inject
    private UsuarioRolServiceLocal usuarioRolService;
    
    @Override
    public List<UsuarioRol> findAll() {
       return usuarioRolService.findAll();
    }

    @Override
    public void edit(UsuarioRol selected) {
        usuarioRolService.edit(selected);
    }

    @Override
    public void remove(UsuarioRol selected) {
        usuarioRolService.remove(selected);
    }

    @Override
    public UsuarioRol find(Integer id) {
        return usuarioRolService.find(id);
    }

    
    
}
