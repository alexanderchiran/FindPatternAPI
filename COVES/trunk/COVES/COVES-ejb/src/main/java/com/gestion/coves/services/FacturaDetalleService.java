/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.FacturaDetalle;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class FacturaDetalleService extends AbstractService<FacturaDetalle> implements FacturaDetalleServiceLocal{

    @PersistenceContext(unitName = "persistence-COVES")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaDetalleService() {
        super(FacturaDetalle.class);
    }    

    @Override
    public List<FacturaDetalle> findByIdFactura(Integer idFactura) {
        return em.createNamedQuery("FacturaDetalle.findByIdFactura").setParameter("idFactura", idFactura).getResultList();
    }
}
