/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Factura;
import com.gestion.coves.dominio.entities.FacturaDetalle;
import com.gestion.coves.dto.InventarioDTO;
import com.gestion.coves.services.FacturaDetalleServiceLocal;
import com.gestion.coves.services.InventarioServiceLocal;
import com.gestion.coves.services.InventarioTiendaServiceLocal;
import com.gestion.coves.util.Global;
import com.gestion.coves.util.Mensaje;
import com.gestion.coves.util.Operaciones;
import com.gestion.coves.util.UtilidadLog;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class FacturaDetalleFachada implements FacturaDetalleFachadaLocal {

    @Inject
    private FacturaDetalleServiceLocal facturaService;
    @Inject
    private InventarioServiceLocal inventarioService;
    @Inject
    private InventarioTiendaServiceLocal inventarioTiendaService;

    @Override
    public List<FacturaDetalle> findAll() {
        return facturaService.findAll();
    }

    @Override
    public void edit(FacturaDetalle selected) {
        facturaService.edit(selected);
    }

    @Override
    public void remove(FacturaDetalle selected) {
        facturaService.remove(selected);
    }

    @Override
    public FacturaDetalle find(Integer id) {
        return facturaService.find(id);
    }

    @Override
    public List<Mensaje> insertarDetalles(List<FacturaDetalle> listaFacturaDetalle, Factura selected) {
        List<Mensaje> mensajes = new ArrayList<>();
        try {
            InventarioDTO inventarioDTO;
            for (FacturaDetalle facturaDetalle : listaFacturaDetalle) {
                try {
                    facturaDetalle.setIdFactura(selected);
                    facturaDetalle.setFechaCreacion(new Date());
                    facturaDetalle.setUsuarioCreacion(Operaciones.usuarioSesion());
                    //actualizó el inventario
                    inventarioDTO = new InventarioDTO(facturaDetalle.getIdProducto(),
                            facturaDetalle.getCantidad(), Global.UPDATE, selected.getIdTienda(), null, Global.UPDATE);
                    inventarioService.actualizarInventarioGlobal(inventarioDTO);
                    //alcualizó inventario por tienda
                    inventarioTiendaService.actualizarInventarioTienda(inventarioDTO);
                    //registro factura detalle
                    facturaService.edit(facturaDetalle);
                } catch (Exception e) {
                      mensajes.add(new Mensaje("No se pudo registrar el detalle {0}", facturaDetalle.getIdProducto().getDescripcion()));
                }

            }
        } catch (Exception e) {
            UtilidadLog.hacerLogError(e);
            mensajes.add(new Mensaje("Error en facturación", null));
        }
        return mensajes;
    }

    @Override
    public List<FacturaDetalle> findByIdFactura(Integer idFactura) {
       return facturaService.findByIdFactura(idFactura);
    }

}
