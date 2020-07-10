/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.services;

import com.gestion.coves.dto.ConsultaDtoRequest;
import com.gestion.coves.dto.ConsultaDtoResponse;
import com.gestion.coves.exception.ExceptionControl;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexander Chiran
 */
@Local
public interface ConsultaVentasServiceLocal {
    
    /**
     *
     * @param dto
     * @return
     * @throws com.gestion.coves.exception.ExceptionControl
     */
    public  List<ConsultaDtoResponse> consultarVentas(ConsultaDtoRequest dto) throws ExceptionControl;
    
}
