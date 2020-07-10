/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Factura;
import com.gestion.coves.services.FacturaServiceLocal;
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
public class FacturaFachada implements FacturaFachadaLocal {

    @Inject
    private FacturaServiceLocal facturaService;

    @Override
    public List<Factura> findAll() {
        return facturaService.findAll();
    }

    @Override
    public void edit(Factura selected) {
            if(selected.getIdFactura()==null){
                 selected.setFechaCreacion(new Date());
                 selected.setUsuarioCreacion(Operaciones.usuarioSesion());
            }
            
            facturaService.edit(selected);
    }

    @Override
    public void remove(Factura selected) {
        facturaService.remove(selected);
    }

    @Override
    public Factura find(Integer id) {
        return facturaService.find(id);
    }

    @Override
    public int calcularNumeroFactura() {
        return facturaService.calcularNumeroFactura();
    }

    @Override
    public void crear(Factura selected) {
         if(selected.getIdFactura()==null){
                 selected.setFechaCreacion(new Date());
                 selected.setUsuarioCreacion(Operaciones.usuarioSesion());
            }
        facturaService.crear(selected);
    }
    
    
}
