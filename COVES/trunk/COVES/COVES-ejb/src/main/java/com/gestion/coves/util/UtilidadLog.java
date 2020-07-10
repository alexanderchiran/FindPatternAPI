/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author user
 */
public class UtilidadLog {

    private static final Logger LOGGER = LoggerFactory.getLogger("Captura de errores");

    /**
     *
     * @param e
     */
    public static void hacerLogError(Exception e) {
        String trace = getTrazaFromException(e);
        LOGGER.error(trace);
    }

    /**
     *
     * @param mensaje
     */
    public static void hacerLogError(String mensaje) {
        LOGGER.error(mensaje);
    }

    /**
     *
     * @param e
     * @return
     */
    private static String getTrazaFromException(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public static String getMessageResource(String key) {
        //El archivo .properties se llama Bundle.properties
        //Está en el paquete co.paquete
        ResourceBundle rb = ResourceBundle.getBundle("Bundle");//Sin la extensión .properties
        //te devuelve rb nulo si no existe.
        //Para obtener una clave se utiliza asi.
        String valor = rb.getString(key);
        return valor;
    }

}
