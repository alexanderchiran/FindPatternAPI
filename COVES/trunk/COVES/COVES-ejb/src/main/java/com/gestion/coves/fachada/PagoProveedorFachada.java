/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.PagoProveedor;
import com.gestion.coves.services.DetallePagoServiceLocal;
import com.gestion.coves.services.PagoProveedorServiceLocal;
import com.gestion.coves.util.Operaciones;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class PagoProveedorFachada implements PagoProveedorFachadaLocal {

    @Inject
    private PagoProveedorServiceLocal pagoProveedorService;
    @Inject
    private DetallePagoServiceLocal detallePagoService;

    @Override
    public List<PagoProveedor> findAll() {
        return pagoProveedorService.findAll();
    }

    @Override
    public void edit(PagoProveedor selected) {
        if (selected.getIdPagoProveedor() == null) {
            selected.setFechaCreacion(new Date());
            selected.setUsuarioCreacion(Operaciones.usuarioSesion());
        } else {
            selected.setFechaActualizacion(new Date());
            selected.setUsuarioActualizacion(Operaciones.usuarioSesion());
        }
        pagoProveedorService.edit(selected);
    }

    @Override
    public void remove(PagoProveedor selected) {
        pagoProveedorService.remove(selected);
    }

    @Override
    public PagoProveedor find(Integer id) {
        return pagoProveedorService.find(id);
    }

    @Override
    public void actualizarValorPagado(PagoProveedor selected) {
        BigDecimal sumaPagos = detallePagoService.sumarPagos(selected.getIdPagoProveedor());
        selected.setValorPagado(sumaPagos);
        edit(selected);
    }

}
