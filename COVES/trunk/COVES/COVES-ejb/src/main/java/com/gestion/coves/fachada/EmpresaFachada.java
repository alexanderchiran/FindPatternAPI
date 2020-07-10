/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Empresa;
import com.gestion.coves.services.EmpresaServicesLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class EmpresaFachada implements EmpresaFachadaLocal {

    @Inject
    private EmpresaServicesLocal empresaService;

    @Override
    public List<Empresa> findAll() {
        return empresaService.findAll();
    }

    @Override
    public void edit(Empresa selected) {
        empresaService.edit(selected);
    }

    @Override
    public void remove(Empresa selected) {
        empresaService.remove(selected);
    }

    @Override
    public Empresa find(Integer id) {
        return find(id);
    }

}
