/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.InventarioTienda;
import com.gestion.coves.dominio.entities.Producto;
import com.gestion.coves.dominio.entities.Tienda;
import com.gestion.coves.dto.InventarioDTO;
import com.gestion.coves.exception.ExceptionControl;
import com.gestion.coves.services.InventarioTiendaServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class InventarioTiendaFachada implements InventarioTiendaFachadaLocal {

    @Inject
    private InventarioTiendaServiceLocal inventarioTiendaService;

    @Override
    public List<InventarioTienda> findAll() {
        return inventarioTiendaService.findAll();
    }

    @Override
    public void edit(InventarioTienda selected) {
        inventarioTiendaService.edit(selected);
    }

    @Override
    public InventarioTienda find(Integer id) {
        return inventarioTiendaService.find(id);
    }

    @Override
    public void actualizarInventarioTienda(InventarioDTO inventario) throws ExceptionControl{
        inventarioTiendaService.actualizarInventarioTienda(inventario);
    }

    @Override
    public boolean validarCantidades(Tienda tienda, Integer cantidad, Producto idProducto) {
        InventarioTienda inventarioTienda = inventarioTiendaService.findByTiendaProducto(tienda.getIdTienda(), idProducto.getIdProducto());
        if (inventarioTienda != null) {
            return inventarioTienda.getExistencia() >= cantidad;
        } else {
            return false;
        }
    }

}
