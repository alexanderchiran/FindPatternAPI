/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Tienda;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface TiendaFachadaLocal {
    
    public List<Tienda> findAll();

    public void edit(Tienda selected);

    public void remove(Tienda selected);

    public Tienda find(Integer id);
    
}
