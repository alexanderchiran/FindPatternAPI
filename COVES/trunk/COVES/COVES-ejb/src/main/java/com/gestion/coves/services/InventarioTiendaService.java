/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.InventarioTienda;
import com.gestion.coves.dto.InventarioDTO;
import com.gestion.coves.exception.ExceptionControl;
import com.gestion.coves.util.Global;
import com.gestion.coves.util.Operaciones;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class InventarioTiendaService extends AbstractService<InventarioTienda> implements InventarioTiendaServiceLocal {

    @PersistenceContext(unitName = "persistence-COVES")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarioTiendaService() {
        super(InventarioTienda.class);
    }

    @Override
    public void actualizarInventarioTienda(InventarioDTO inventario) throws ExceptionControl {
        InventarioTienda inventarioTienda = null;
        try {

            inventarioTienda = (InventarioTienda) em.createNamedQuery("InventarioTienda.findByidProductoIdTienda")
                    .setParameter("id_producto", inventario.getProducto().getIdProducto())
                    .setParameter("idTienda", inventario.getTienda().getIdTienda()).getSingleResult();
            if (inventarioTienda != null) {

                if (inventario.getOrigen().equals("CREATE")) {
                    if (Global.DEVOLUCION.equals(inventario.getOperacion())) {
                        inventarioTienda.setExistencia(inventarioTienda.getExistencia() + inventario.getCantidad());
                    } else if (Global.DAR_DE_BAJA.equals(inventario.getOperacion())) {
                        int cantidad = inventarioTienda.getExistencia() - inventario.getCantidad();
                        if (cantidad >= 0) {
                            inventarioTienda.setExistencia(cantidad);
                            inventarioTienda.setFechaActualizacion(new Date());
                            inventarioTienda.setUsuarioActualizacion(Operaciones.usuarioSesion());
                            edit(inventarioTienda);
                        } else {
                            throw new ExceptionControl("La cantidad de la novedad no corresponde con el inventario registrado");
                        }
                    }
                } else if (inventario.getOrigen().equals("UPDATE")) {
                    if (inventario.getCantidad() != null) {
                        int cantidad = inventarioTienda.getExistencia() - inventario.getCantidad();
                        if (cantidad >= 0) {
                            inventarioTienda.setExistencia(cantidad);
                            inventarioTienda.setFechaActualizacion(new Date());
                            inventarioTienda.setUsuarioActualizacion(Operaciones.usuarioSesion());
                            edit(inventarioTienda);
                        } else {
                            throw new ExceptionControl("La cantidad de la novedad no corresponde con el inventario registrado");
                        }
                    }
                } else if (inventario.getOrigen().equals("DISTRIBUIR")) {
                    int cantidad;
                    if (inventario.isOrigenTienda()) {
                        cantidad = inventario.getCantidad();
                    } else {
                        cantidad = inventarioTienda.getExistencia() + inventario.getCantidad();
                    }
                    inventarioTienda.setExistencia(cantidad);
                    inventarioTienda.setFechaActualizacion(new Date());
                    inventarioTienda.setUsuarioActualizacion(Operaciones.usuarioSesion());
                    edit(inventarioTienda);
                }
            }
        } catch (NoResultException e) {
            if (Global.DAR_DE_BAJA.equals(inventario.getOperacion())) {
                throw new ExceptionControl("El producto no esta registrado");
            } else {

                inventarioTienda = new InventarioTienda();
                inventarioTienda.setExistencia(inventario.getCantidad());
                inventarioTienda.setIdProducto(inventario.getProducto());
                inventarioTienda.setIdTienda(inventario.getTienda());
                inventarioTienda.setFechaCreacion(new Date());
                inventarioTienda.setUsuarioCreacion(Operaciones.usuarioSesion());
                edit(inventarioTienda);
            }
        } catch (ExceptionControl ex) {
            throw ex;
        } catch (Exception ex) {
            try {
                throw ex;
            } catch (Exception ex1) {
                Logger.getLogger(InventarioTiendaService.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    @Override
    public InventarioTienda findByTiendaProducto(Integer idTienda, Integer idProducto) {
        try {
            return em.createNamedQuery("InventarioTienda.findByidProductoIdTienda", InventarioTienda.class).setParameter("id_producto", idProducto).setParameter("idTienda", idTienda).getSingleResult();
        } catch (NoResultException e) {
        }
        return null;
    }

}
