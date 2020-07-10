/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.fachada;

import com.gestion.coves.dominio.entities.Cliente;
import com.gestion.coves.services.ClienteServiceLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Alexander Chiran
 */
@Stateless
public class ClienteFachada implements ClienteFachadaLocal {

    @Inject
    private ClienteServiceLocal clienteService;
    
    @Override
    public List<Cliente> findAll() {
        return clienteService.findAll();
    }

    @Override
    public void edit(Cliente selected) {
       clienteService.edit(selected); 
    }

    @Override
    public void remove(Cliente selected) {
        clienteService.remove(selected);
    }

    @Override
    public Cliente find(Integer id) {
       return clienteService.find(id);
    }
    
}
