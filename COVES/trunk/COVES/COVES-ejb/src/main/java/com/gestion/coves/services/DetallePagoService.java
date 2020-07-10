/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.DetallePago;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class DetallePagoService extends AbstractService<DetallePago> implements DetallePagoServiceLocal {

    @PersistenceContext(unitName = "persistence-COVES")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallePagoService() {
        super(DetallePago.class);
    }

    @Override
    public List<DetallePago> findByIdPago(Integer idPagoProveedor) {
        try {
            return em.createNamedQuery("DetallePago.findByIdPago").setParameter("idPagoProveedor", idPagoProveedor).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public BigDecimal sumarPagos(Integer idPagoProveedor) {
        Query query;
        query = em.createQuery("select SUM(d.valorAbono) from DetallePago d where d.idPagoProveedor.idPagoProveedor = :idPagoProveedor");
        query.setParameter("idPagoProveedor", idPagoProveedor);
        BigDecimal resultado = (BigDecimal) query.getSingleResult();
        return resultado;
    }

}
