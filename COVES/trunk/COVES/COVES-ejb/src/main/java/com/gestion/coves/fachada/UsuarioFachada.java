/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Usuario;
import com.gestion.coves.services.UsuarioServiceLocal;
import com.gestion.coves.util.Operaciones;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class UsuarioFachada implements UsuarioFachadaLocal {
    
    @Inject
    private UsuarioServiceLocal usuarioService;
    
    @Override
    public Usuario validarUsuario(String usuario, String clave) {
        return usuarioService.validarUsuario(usuario, clave);
    }
    
    @Override
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }
    
    @Override
    public void edit(Usuario selected) {
        if (selected.getIdUsuario() != null) {
            selected.setFechaActualizacion(new Date());
            selected.setUsuarioActualizacion(Operaciones.usuarioSesion());
        } else {
            selected.setFechaCreacion(new Date());
            selected.setUsuarioCreacion(Operaciones.usuarioSesion());
        }
        usuarioService.edit(selected);
    }
    
    @Override
    public void remove(Usuario selected) {
        usuarioService.remove(selected);
    }
    
    @Override
    public Usuario find(Integer id) {
        return usuarioService.find(id);
    }
    
}
