/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.Menu;
import com.gestion.coves.dominio.entities.UsuarioRol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface MenuServiceLocal {

    public List<Menu> findPadres() throws Exception;

    public List<Menu> findAll();

    public void edit(Menu selected);

    public void remove(Menu selected);

    public Menu find(Object id);

}
