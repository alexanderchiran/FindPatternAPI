/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.Menu;
import com.gestion.coves.dominio.entities.MenuRol;
import com.gestion.coves.dominio.entities.UsuarioRol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface MenuRolServiceLocal {

    public List<MenuRol> findAll();

    public void edit(MenuRol selected);

    public void remove(MenuRol selected);
    
    public MenuRol find(Object id);

    public List<Menu> findHijos(Integer idMenu, List<UsuarioRol> usuarioRoles);
    
}
