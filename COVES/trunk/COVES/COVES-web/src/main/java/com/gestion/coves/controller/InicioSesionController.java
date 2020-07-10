/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.controller;

import com.gestion.coves.controller.util.Control;
import com.gestion.coves.dominio.entities.Menu;
import com.gestion.coves.dominio.entities.Usuario;
import com.gestion.coves.dominio.entities.UsuarioRol;
import com.gestion.coves.fachada.MenuFachadaLocal;
import com.gestion.coves.fachada.UsuarioFachadaLocal;
import com.gestion.coves.model.InicioSesionModel;
import com.gestion.coves.util.Global;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Alexander Chiran
 */
@Named("inicioSesion")
@SessionScoped
public class InicioSesionController extends Control implements Serializable {

    private InicioSesionModel model;
    @Inject
    private UsuarioFachadaLocal usuarioFachada;
    @Inject
    private MenuFachadaLocal menuFachada;
    private boolean esAdministrador;

    @PostConstruct
    public void init() {
        model = new InicioSesionModel();
    }

    /**
     *
     * @return
     */
    public String validarSesion() {
        try {
            Usuario usuario = usuarioFachada.validarUsuario(model.getUsuario(), model.getClave());
            if (usuario != null && usuario.getUsuario().equals(model.getUsuario())) {
                model.setNombre(usuario.getNombres().concat(" ").concat(usuario.getApellidos()));
                session().setAttribute(Global.USUARIO_SESION, usuario);
                carcarMenu(usuario.getUsuarioRolList());
                validarAdministrador(usuario.getUsuarioRolList());
                return "/faces/principal.xhtml?faces-redirect=true";
            } else {
                imprimirMensaje(getMessageResource("UsuarioIncorrecto"), FacesMessage.SEVERITY_ERROR);
                return null;
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
        return null;
    }

    private void validarAdministrador(List<UsuarioRol> usuarioRoles) {
        try {
            if (usuarioRoles != null) {
                for (UsuarioRol usuarioRol : usuarioRoles) {
                    if (usuarioRol.getIdRol().getNombre().toUpperCase().equals(Global.ADMINISTRADOR)) {
                        esAdministrador=true;
                    }
                }
            } else {
                esAdministrador = false;
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }

    /**
     *
     */
    private void carcarMenu(List<UsuarioRol> usuarioRoles) {
        try {
            MenuModel menu = new DefaultMenuModel();
            DefaultMenuItem principal = new DefaultMenuItem("Principal");
            principal.setIcon("ui-icon-home");
            principal.setOutcome("/principal.xhtml");
            menu.addElement(principal);
            Map<String, String> opciones = new HashMap<>();
            opciones.put("/COVES-web/faces/principal.xhtml", "");
            if (usuarioRoles == null) {
                imprimirMensaje(getMessageResource("usuarioSinRol"), FacesMessage.SEVERITY_ERROR);
                return;
            }

            List<Menu> listaMenu = menuFachada.findPadres();
            DefaultMenuItem hijoMenu;
            DefaultSubMenu padreMenu;
            if (listaMenu != null) {
                for (Menu padre : listaMenu) {
                    padreMenu = new DefaultSubMenu(padre.getDescripcion());
                    ponerIcono(padreMenu, padre.getDescripcion());
                    List<Menu> listaHijos = menuFachada.findHijos(padre.getIdMenu(), usuarioRoles);
                    if (listaHijos != null && !listaHijos.isEmpty()) {
                        for (Menu hijo : listaHijos) {
                            hijoMenu = new DefaultMenuItem(hijo.getDescripcion());
                            switch (hijo.getOpcion()) {
                                case "/COVES-web/faces/facturar/":
                                    hijoMenu.setCommand("#{navegacion.irAFacturar}");
                                    break;
                                case "/COVES-web/faces/inventario/":
                                    hijoMenu.setCommand("#{navegacion.irAinventario}");
                                    break;
                                case "/COVES-web/faces/inventarioTienda/":
                                    hijoMenu.setCommand("#{navegacion.irAInventarioTienda}");
                                    break;
                                 case "/COVES-web/faces/compra/":
                                    hijoMenu.setCommand("#{navegacion.irACompras}");
                                    break;
                                default:
                                    hijoMenu.setOutcome(formatearCadena(hijo.getOpcion()));
                                    break;
                            }
                            padreMenu.addElement(hijoMenu);
                            opciones.put(hijo.getOpcion(), "");
                        }
                        menu.addElement(padreMenu);
                    }

                }
            }
            model.setMenu(menu);
            session().setAttribute(Global.OPCIONES_MENU, opciones);

        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }

    public String formatearCadena(String menu) {
        try {
            menu = menu.replace("/COVES-web/faces", "");
            menu += "List.xhtml";
            return menu;
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
        return "/principal.xhtml";
    }

    /**
     *
     * @param padre
     * @param opcion
     */
    public void ponerIcono(DefaultSubMenu padre, String opcion) {
        try {
            opcion = opcion.toUpperCase();
            if (opcion.startsWith("G")) {
                padre.setIcon("fa fa-cogs");
            } else if (opcion.startsWith("P")) {
                padre.setIcon("fa fa-street-view");
            } else {
                padre.setIcon("fa fa-money");
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }

    }

    /**
     * Cierra la Sesion de la aplicacion
     *
     * @return
     */
    public HttpSession session() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            return session;

        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
        return null;
    }

    /**
     * cerrar la sesion
     *
     * @return
     */
    public String cerrarSesion() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                session.invalidate();
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
        return "/faces/index.xhtml?faces-redirect=true";
    }

    /**
     * @return the model
     */
    public InicioSesionModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(InicioSesionModel model) {
        this.model = model;
    }

    /**
     * @return the esAdministrador
     */
    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    /**
     * @param esAdministrador the esAdministrador to set
     */
    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

}
