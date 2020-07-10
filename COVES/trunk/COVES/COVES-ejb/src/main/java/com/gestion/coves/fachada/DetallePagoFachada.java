/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.DetallePago;
import com.gestion.coves.services.DetallePagoServiceLocal;
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
public class DetallePagoFachada implements DetallePagoFachadaLocal {
    
    @Inject
    private DetallePagoServiceLocal detallePagoService;
    
    @Override
    public List<DetallePago> findAll() {
        return detallePagoService.findAll();
    }
    
    @Override
    public void edit(DetallePago selected) {
        if (selected.getIdDetallePago() == null) {
            selected.setFechaCreacion(new Date());
            selected.setUsuarioCreacion(Operaciones.usuarioSesion());
        } else {
            selected.setFechaActualizacion(new Date());
            selected.setUsuarioActualizacion(Operaciones.usuarioSesion());
            
        }
        detallePagoService.edit(selected);
    }
    
    @Override
    public void remove(DetallePago selected) {
        detallePagoService.remove(selected);
    }
    
    @Override
    public DetallePago find(Integer id) {
        return detallePagoService.find(id);
    }
    
    @Override
    public List<DetallePago> findByIdPago(Integer idPagoProveedor) {
        return detallePagoService.findByIdPago(idPagoProveedor);
    }
}
