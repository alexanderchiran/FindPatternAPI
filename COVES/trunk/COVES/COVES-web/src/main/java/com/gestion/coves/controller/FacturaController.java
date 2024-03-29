package com.gestion.coves.controller;

import com.gestion.coves.controller.util.Control;
import static com.gestion.coves.controller.util.Control.getMessageResource;
import com.gestion.coves.controller.util.ImprimirPdf;
import com.gestion.coves.dominio.entities.Factura;
import com.gestion.coves.controller.util.JsfUtil;
import com.gestion.coves.controller.util.JsfUtil.PersistAction;
import com.gestion.coves.fachada.EmpresaFachadaLocal;
import com.gestion.coves.fachada.FacturaFachadaLocal;
import com.gestion.coves.util.Global;
import com.gestion.coves.util.Operaciones;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

@Named("facturaController")
@SessionScoped
public class FacturaController extends Control implements Serializable {

    @Inject
    private FacturaFachadaLocal ejbFacade;
    @Inject
    private EmpresaFachadaLocal empresaFachada;
    private List<Factura> items = null;
    private Factura selected;

    @Inject
    private FacturaDetalleController facturaDetalleController;

    public FacturaController() {
    }

    public Factura getSelected() {
        return selected;
    }

    public void setSelected(Factura selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private FacturaFachadaLocal getFacade() {
        return ejbFacade;
    }

    public Factura prepareCreate() {
        selected = new Factura();
        initializeEmbeddableKey();
        return selected;
    }

    public void imprimir() {
        ImprimirPdf firstPdf = new ImprimirPdf();
        consultarDetalle();
        Factura buscarFact = ejbFacade.find(selected.getIdFactura());
        String nombreA = firstPdf.crearFactura(buscarFact, empresaFachada.findAll().get(0), facturaDetalleController.getItems());
        //nombreFactura="fileName.pdf";
        RequestContext.getCurrentInstance().execute("imprimir('" + nombreA + "')");

    }

    /**
     *
     */
    public void consultarDetalle() {
        try {
            facturaDetalleController.setFacturaSeleccionada(selected);
            facturaDetalleController.findByIdFactura();
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }

    public void create() {
        getSelected().setFechaCreacion(new Date());
        getSelected().setUsuarioCreacion(Operaciones.usuarioSesion());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("FacturaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("FacturaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("FacturaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Factura> getItems() {

        items = getFacade().findAll();

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

    public Factura getFactura(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Factura> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Factura> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Factura.class)
    public static class FacturaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FacturaController controller = (FacturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "facturaController");
            return controller.getFactura(getKey(value));
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
            if (object instanceof Factura) {
                Factura o = (Factura) object;
                return getStringKey(o.getIdFactura());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Factura.class.getName()});
                return null;
            }
        }

    }

}
