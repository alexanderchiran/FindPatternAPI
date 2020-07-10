/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.InventarioTienda;
import com.gestion.coves.dto.InventarioDTO;
import com.gestion.coves.exception.ExceptionControl;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface InventarioTiendaServiceLocal {

    public List<InventarioTienda> findAll();

    public void edit(InventarioTienda selected);
    
    public InventarioTienda find(Object id);

    public void actualizarInventarioTienda(InventarioDTO inventario) throws ExceptionControl;

    public InventarioTienda findByTiendaProducto(Integer idTienda, Integer idProducto);
    
}
