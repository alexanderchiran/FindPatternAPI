package com.gestion.coves.controller;

import com.gestion.coves.dominio.entities.UsuarioRol;
import com.gestion.coves.controller.util.JsfUtil;
import com.gestion.coves.controller.util.JsfUtil.PersistAction;
import com.gestion.coves.fachada.UsuarioRolFachadaLocal;
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

@Named("usuarioRolController")
@SessionScoped
public class UsuarioRolController implements Serializable {
    
    @Inject
    private UsuarioRolFachadaLocal ejbFacade;
    private List<UsuarioRol> items = null;
    private UsuarioRol selected;
    
    public UsuarioRolController() {
    }
    
    public UsuarioRol getSelected() {
        return selected;
    }
    
    public void setSelected(UsuarioRol selected) {
        this.selected = selected;
    }
    
    protected void setEmbeddableKeys() {
    }
    
    protected void initializeEmbeddableKey() {
    }
    
    private UsuarioRolFachadaLocal getFacade() {
        return ejbFacade;
    }
    
    public UsuarioRol prepareCreate() {
        selected = new UsuarioRol();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void create() {
        getSelected().setFechaCreacion(new Date());
        getSelected().setUsuarioCreacion(Operaciones.usuarioSesion());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioRolCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void update() {
        getSelected().setFechaActualizacion(new Date());
        getSelected().setUsuarioActualizacion(Operaciones.usuarioSesion());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioRolUpdated"));
    }
    
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioRolDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public List<UsuarioRol> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
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
    
    public UsuarioRol getUsuarioRol(java.lang.Integer id) {
        return getFacade().find(id);
    }
    
    public List<UsuarioRol> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }
    
    public List<UsuarioRol> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    @FacesConverter(forClass = UsuarioRol.class)
    public static class UsuarioRolControllerConverter implements Converter {
        
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioRolController controller = (UsuarioRolController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioRolController");
            return controller.getUsuarioRol(getKey(value));
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
            if (object instanceof UsuarioRol) {
                UsuarioRol o = (UsuarioRol) object;
                return getStringKey(o.getIdUsuarioRol());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UsuarioRol.class.getName()});
                return null;
            }
        }
        
    }
    
}
