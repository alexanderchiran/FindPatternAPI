package com.gestion.coves.controller;

import com.gestion.coves.controller.util.Control;
import com.gestion.coves.dominio.entities.DetallePago;
import com.gestion.coves.controller.util.JsfUtil;
import com.gestion.coves.controller.util.JsfUtil.PersistAction;
import com.gestion.coves.dominio.entities.Factura;
import com.gestion.coves.dominio.entities.PagoProveedor;
import com.gestion.coves.fachada.DetallePagoFachadaLocal;
import com.gestion.coves.util.Global;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("detallePagoController")
@SessionScoped
public class DetallePagoController extends Control implements Serializable {

    @Inject
    private DetallePagoFachadaLocal ejbFacade;
    private List<DetallePago> items = null;
    private DetallePago selected;
    private PagoProveedor pagoProveedorSeleccionado;

    public DetallePagoController() {
    }

    public DetallePago getSelected() {
        return selected;
    }

    public void setSelected(DetallePago selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DetallePagoFachadaLocal getFacade() {
        return ejbFacade;
    }

    public DetallePago prepareCreate() {
        selected = new DetallePago();
        selected.setIdPagoProveedor(pagoProveedorSeleccionado);
        initializeEmbeddableKey();
        return selected;
    }

    public void findByIdFactura() {
        try {
            selected=null;
            if (pagoProveedorSeleccionado == null) {
                items = null;
            } else {
                items = ejbFacade.findByIdPago(pagoProveedorSeleccionado.getIdPagoProveedor());
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }

    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DetallePagoCreated"));
        findByIdFactura();
       
    }

    public void update() {
        try {
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetallePagoUpdated"));
            findByIdFactura();
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }

    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DetallePagoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection            
        }
         findByIdFactura();
    }

    public List<DetallePago> getItems() {
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public DetallePago getDetallePago(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<DetallePago> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DetallePago> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DetallePago.class)
    public static class DetallePagoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetallePagoController controller = (DetallePagoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detallePagoController");
            return controller.getDetallePago(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetallePago) {
                DetallePago o = (DetallePago) object;
                return getStringKey(o.getIdDetallePago());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetallePago.class.getName()});
                return null;
            }
        }

    }

    /**
     * @return the pagoProveedorSeleccionado
     */
    public PagoProveedor getPagoProveedorSeleccionado() {
        return pagoProveedorSeleccionado;
    }

    /**
     * @param pagoProveedorSeleccionado the pagoProveedorSeleccionado to set
     */
    public void setPagoProveedorSeleccionado(PagoProveedor pagoProveedorSeleccionado) {
        this.pagoProveedorSeleccionado = pagoProveedorSeleccionado;
    }

}
