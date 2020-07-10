/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.Inventario;
import com.gestion.coves.dto.InventarioDTO;
import com.gestion.coves.exception.ExceptionControl;
import com.gestion.coves.util.Global;
import com.gestion.coves.util.Operaciones;
import java.math.BigDecimal;
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
public class InventarioService extends AbstractService<Inventario> implements InventarioServiceLocal {

    @PersistenceContext(unitName = "persistence-COVES")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventarioService() {
        super(Inventario.class);
    }

    @Override
    public void actualizarInventarioGlobal(InventarioDTO inventariodto) throws ExceptionControl{
        Inventario inventario = null;
        try {
            inventario = (Inventario) em.createNamedQuery("Inventario.findByIdProducto")
                    .setParameter("id_producto", inventariodto.getProducto().getIdProducto()).getSingleResult();
            if (inventario != null) {
                if (inventariodto.getOrigen().equals("CREATE")) {
                    if(!inventariodto.isNovedad()){
                        inventario.setCostoUnitario(inventariodto.getCosto());
                    }
                    if (Global.DEVOLUCION.equals(inventariodto.getOperacion())) {
                        inventario.setExistencia(inventario.getExistencia() + inventariodto.getCantidad());
                    } else if (Global.DAR_DE_BAJA.equals(inventariodto.getOperacion())) {
                        int cantidad = inventario.getExistencia() - inventariodto.getCantidad();
                        if (cantidad >= 0) {
                            inventario.setExistencia(cantidad);
                            inventario.setFechaActualizacion(new Date());
                            inventario.setUsuarioActualizacion(Operaciones.usuarioSesion());
                            edit(inventario);
                        } else {
                            throw new ExceptionControl("La cantidad de la novedad no corresponde con el inventario registrado");
                        }
                    }
                } else if (inventariodto.getOrigen().equals("UPDATE")) {
                    if (inventariodto.getCosto() != null) {
                        if (inventario.getCostoUnitario().compareTo(inventariodto.getCosto()) != 0) {
                            inventario.setCostoUnitario(inventariodto.getCosto());
                        }
                    }
                    if (inventariodto.getCantidad() != null) {
                        int cantidad = inventario.getExistencia() - inventariodto.getCantidad();
                        if (cantidad >= 0) {
                            inventario.setExistencia(cantidad);
                            inventario.setFechaActualizacion(new Date());
                            inventario.setUsuarioActualizacion(Operaciones.usuarioSesion());
                            edit(inventario);
                        } else {
                            throw new ExceptionControl("La cantidad de la novedad no corresponde con el inventario registrado");
                        }
                    }
                }
            }
        } catch (NoResultException e) {
            if (Global.DAR_DE_BAJA.equals(inventariodto.getOperacion())) {
                throw new ExceptionControl("La cantidad de la novedad no corresponde con el inventario registrado");
            } else {
                inventario = new Inventario();
                inventario.setCostoUnitario(inventariodto.getCosto());
                inventario.setExistencia(inventariodto.getCantidad());
                inventario.setIdProducto(inventariodto.getProducto());
                inventario.setFechaCreacion(new Date());
                inventario.setUsuarioCreacion(Operaciones.usuarioSesion());
                edit(inventario);
            }
        } catch (ExceptionControl ex) {
            throw ex;
        }catch (Exception ex) {
            try {
                throw ex;
            } catch (Exception ex1) {               
                Logger.getLogger(InventarioTiendaService.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    /*private BigDecimal actualizarCostoUnitario(Inventario inventario, InventarioDTO inventariodto) {
        try {
            Integer existenciaActual = inventario.getExistencia();
            Integer cantidad = inventariodto.getCantidad();
            BigDecimal costoUnitarioAtual = inventario.getCostoUnitario();
            BigDecimal costoActualizar = inventariodto.getCosto();
            int costoUnitarioAtualInt = costoUnitarioAtual.intValue() * existenciaActual;
            int costoActualizarInt = costoActualizar.intValue() * cantidad;
            int costoAdd = (costoUnitarioAtualInt + costoActualizarInt) / (existenciaActual + cantidad);
            BigDecimal costoTotal = new BigDecimal(costoAdd);
            return costoTotal;
        } catch (Exception ex) {
            throw ex;
        }
    }*/
}
