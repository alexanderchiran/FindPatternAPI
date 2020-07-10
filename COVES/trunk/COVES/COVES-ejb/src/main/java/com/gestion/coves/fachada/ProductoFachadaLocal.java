/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Producto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface ProductoFachadaLocal {
    
    public List<Producto> findAll();

    public void edit(Producto selected);

    public void remove(Producto selected);

    public Producto find(Integer id);
    
}
