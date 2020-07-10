/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dominio.entities.Compra;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class CompraService extends AbstractService<Compra> implements CompraServicesLocal{

    @PersistenceContext(unitName = "persistence-COVES")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraService() {
        super(Compra.class);
    }
    
    @Override
    public List<Compra> findAllCompras(){
          List<Compra> list = em.createNativeQuery("SELECT * FROM  compra order by fecha_compra desc", Compra.class).getResultList();
          return list;
    }
    
}
