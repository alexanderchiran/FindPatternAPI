/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.UsuarioRol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author natalia
 */
@Local
public interface UsuarioRolServiceLocal {

    public List<UsuarioRol> findAll();

    public void edit(UsuarioRol selected);

    public void remove(UsuarioRol selected);

    public UsuarioRol find(Object id);
    
}
