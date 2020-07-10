/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.Factura;
import com.gestion.coves.util.UtilidadLog;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class FacturaService extends AbstractService<Factura> implements FacturaServiceLocal {

    @PersistenceContext(unitName = "persistence-COVES")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaService() {
        super(Factura.class);
    }

    @Override
    public int calcularNumeroFactura() {
        try {
            Query query = em.createQuery("Select MAX(f.idFactura) FROM Factura f");
            Integer resultado = (Integer) query.getSingleResult();
            return resultado+1;
        } catch (Exception e) {
            UtilidadLog.hacerLogError(e);
        }
        return 1;

    }

    @Override
    public void crear(Factura selected) {
        em.persist(selected);
        em.flush();
    }

}
