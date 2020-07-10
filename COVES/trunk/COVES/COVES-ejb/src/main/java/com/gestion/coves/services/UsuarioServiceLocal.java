/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.Usuario;
import java.util.List;

/**
 *
 * @author Alexander Chiran
 */
public interface UsuarioServiceLocal {

    public Usuario validarUsuario(String usuario, String clave);

    public List<Usuario> findAll();

    public void edit(Usuario selected);

    public void remove(Usuario selected);

    public Usuario find(Object id);

}
