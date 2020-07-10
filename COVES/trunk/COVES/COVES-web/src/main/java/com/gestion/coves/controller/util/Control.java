/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.controller.util;

import com.gestion.coves.util.Mensaje;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Paulo Alexander Chiran paulo.alexander12@gmail.com
 */
public class Control {

    private static final Logger LOGGER = LoggerFactory.getLogger("Captura de errores");

    /**
     * Método que permite imprimir mensajes, para funcionar correctamente se
     * necesita un <p:messages/> o un <p:growl/>
     *
     * @param mensaje mensaje de entrada
     * @param severity
     */
    protected void imprimirMensaje(String mensaje, FacesMessage.Severity severity) {

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, mensaje, ""));
    }

    protected void imprimirMensaje(List<Mensaje> mensajes, FacesMessage.Severity severity) {
        if (mensajes != null && !mensajes.isEmpty()) {
            for (Mensaje mensaje : mensajes) {
                if (mensaje.getParam1() == null) {
                    imprimirMensaje(mensaje.getMensaje(), severity);
                } else if (mensaje.getParam2() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, getMessageResourcParam(mensaje.getMensaje(), mensaje.getParam1()), ""));
                } else if (mensaje.getParam3() == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, getMessageResourcParam(mensaje.getMensaje(), mensaje.getParam1(), mensaje.getParam2()), ""));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, getMessageResourcParam(mensaje.getMensaje(), mensaje.getParam1(), mensaje.getParam2(), mensaje.getParam3()), ""));
                }
            }
        }
    }

    /**
     *
     * @param mensaje
     * @param e
     */
    protected void imprimirMensaje(String mensaje, Exception e) {
        String trace = getTrazaFromException(e);
        LOGGER.error(trace);
        imprimirMensaje(mensaje, FacesMessage.SEVERITY_FATAL);

    }

    /**
     *
     * @param e
     */
    public void hacerLogError(Exception e) {
        String trace = getTrazaFromException(e);
        LOGGER.error(trace);
    }

    /**
     *
     * @param mensaje
     */
    public void hacerLogError(String mensaje) {
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

    public static ResourceBundle getBundle() {
        //El archivo .properties se llama Bundle.properties
        //Está en el paquete co.paquete
        ResourceBundle rb = ResourceBundle.getBundle("Bundle");//Sin la extensión .properties
        return rb;
    }

    public static String getMessageResourcParam(String key, Object... params) {
        //El archivo .properties se llama Bundle.properties
        //Está en el paquete co.paquete
        ResourceBundle rb = ResourceBundle.getBundle("Bundle");//Sin la extensión .properties
        //te devuelve rb nulo si no existe.
        //Para obtener una clave se utiliza asi.
        return MessageFormat.format(rb.getString(key), params);
    }
}
