/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.seguridad;

import com.gestion.coves.util.Global;
import com.gestion.coves.util.RecursoPermitidoHelper;
import com.gestion.coves.util.UtilidadLog;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Alexander Chiran
 */
public class SeguridadFiltro implements Filter, Serializable {

    private FilterConfig filterConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger("Captura de errores");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        try {
            String path = ((HttpServletRequest) request).getRequestURI();
            if (path.equals("/COVES-web/faces/index.xhtml")
                    || path.equals("/COVES-web/faces/accesoNoAutorizado.xhtml")
                    || path.equals("/COVES-web/faces/error.xhtml")
                    || path.equals("/COVES-web/")) {
                filterChain.doFilter(request, response);
                return;
            }

            if (RecursoPermitidoHelper.esExtensionPermitidaPorGet(path)) {
                filterChain.doFilter(request, response);
                return;
            }

            Map<String, String> opcionesMenu = (Map<String, String>) session.getAttribute(Global.OPCIONES_MENU);
            if (opcionesMenu != null) {

                if (path.equals("/COVES-web/faces/principal.xhtml")) {
                    String opcion = opcionesMenu.get(path);
                    if (opcion != null) {
                        filterChain.doFilter(request, response);
                        return;
                    } else {
                        UtilidadLog.hacerLogError("Acceso no autorizado");
                        res.sendRedirect("/COVES-web/faces/accesoNoAutorizado.xhtml");
                        return;
                    }
                }

                path = path.substring(0, path.lastIndexOf("/")+1);
                String opcion = opcionesMenu.get(path);
                if (opcion != null) {
                    filterChain.doFilter(request, response);
                } else {
                    UtilidadLog.hacerLogError("Acceso no autorizado");
                    res.sendRedirect("/COVES-web/faces/accesoNoAutorizado.xhtml");
                }
            } else {
                UtilidadLog.hacerLogError("Acceso no autorizado");
                res.sendRedirect("/COVES-web/faces/accesoNoAutorizado.xhtml");
            }

        } catch (IOException | ServletException e) {
            hacerLogError(e);
            e.printStackTrace();
        }

    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
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
     * @param mensaje
     * @param e
     */
    private void hacerLogError(Exception e) {
        try {
            String trace = getTrazaFromException(e);
            LOGGER.error(trace);
        } catch (Exception ex) {
            filterConfig.getServletContext().log(ex.getMessage());
        }
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

}
