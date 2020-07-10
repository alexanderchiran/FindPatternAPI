/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Menu;
import com.gestion.coves.dominio.entities.UsuarioRol;
import com.gestion.coves.services.MenuRolServiceLocal;
import com.gestion.coves.services.MenuServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class MenuFachada implements MenuFachadaLocal {

    @Inject
    private MenuServiceLocal menuService;
    @Inject
    private MenuRolServiceLocal menuRolService;

    @Override
    public List<Menu> findPadres() throws Exception {
        return menuService.findPadres();
    }

    @Override
    public List<Menu> findHijos(Integer idMenu, List<UsuarioRol> usuarioRoles) throws Exception {
        return menuRolService.findHijos(idMenu, usuarioRoles);
    }

    @Override
    public List<Menu> findAll() {
        return menuService.findAll();
    }

    @Override
    public void edit(Menu selected) {
        menuService.edit(selected);
    }

    @Override
    public void remove(Menu selected) {
        menuService.remove(selected);
    }

    @Override
    public Menu find(Integer id) {
        return menuService.find(id);
    }
}
