package com.gestion.coves.controller;

import com.gestion.coves.controller.util.Control;
import com.gestion.coves.dominio.entities.Compra;
import com.gestion.coves.controller.util.JsfUtil;
import com.gestion.coves.controller.util.JsfUtil.PersistAction;
import com.gestion.coves.dto.InventarioDTO;
import com.gestion.coves.exception.ExceptionControl;
import com.gestion.coves.fachada.CompraFachadaLocal;
import com.gestion.coves.fachada.InventarioFachadaLocal;
import com.gestion.coves.fachada.InventarioTiendaFachadaLocal;
import com.gestion.coves.util.Global;
import com.gestion.coves.util.Operaciones;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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

@Named("compraController")
@SessionScoped
public class CompraController extends Control implements Serializable {
    
    @Inject
    private CompraFachadaLocal comprasFachada;
    @Inject
    private InventarioFachadaLocal inventarioGlobal;
    @Inject
    private InventarioTiendaFachadaLocal inventarioTienda;
    @Inject
    private InventarioController inventarioBean;
    @Inject
    private inventarioTiendaController inventarioTiendaBean;
    private List<Compra> items = null;
    private Compra selected;
    private Integer cantidadActual;
    
    public CompraController() {
    }
    
    public Compra getSelected() {
        return selected;
    }
    
    public void setSelected(Compra selected) {
        this.selected = selected;
    }
    
    protected void setEmbeddableKeys() {
    }
    
    protected void initializeEmbeddableKey() {
    }
    
    public CompraFachadaLocal getFacade() {
        return comprasFachada;
    }
    
    public InventarioFachadaLocal getInventarioGlobal() {
        return inventarioGlobal;
    }
    
    public InventarioTiendaFachadaLocal getInventarioTienda() {
        return inventarioTienda;
    }
    
    public Integer getCantidadActual() {
        return cantidadActual;
    }
    
    public void setCantidadActual(Integer cantidadActual) {
        this.cantidadActual = cantidadActual;
    }
    
    public Compra prepareCreate() {
        selected = new Compra();
        selected.setFechaCompra(new Date());
        initializeEmbeddableKey();
        return selected;
    }
    
    public void create() {
        getSelected().setFechaCreacion(new Date());
        getSelected().setUsuarioCreacion(Operaciones.usuarioSesion());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CompraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void guardarCantidadActual() {
        setCantidadActual(selected.getCantidad());
    }
    
    public void update() {
        getSelected().setFechaActualizacion(new Date());
        getSelected().setUsuarioActualizacion(Operaciones.usuarioSesion());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CompraUpdated"));
    }
    
    public List<Compra> getItems() {
        if (items == null) {
            items = getFacade().findAllCompras();
        }
        return items;
    }
    
    public void init() {
        try {
            items = getFacade().findAllCompras();
            selected=null;
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }
    
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                InventarioDTO inventario = createInventatioDTO(selected);
                 inventario.setNovedad(false);
                if (persistAction == PersistAction.CREATE) {
                    inventario.setOrigen(PersistAction.CREATE.name());
                } else if (persistAction == PersistAction.UPDATE) {
                    if (selected.getCantidad() == getCantidadActual()) {
                        inventario.setCantidad(null);
                    } else {
                        int catidadActualizar = getCantidadActual() - selected.getCantidad();
                        inventario.setCantidad(catidadActualizar);
                    }
                    inventario.setOrigen(PersistAction.UPDATE.name());
                }
                getInventarioGlobal().actualizarInventarioGlobal(inventario);
                getInventarioTienda().actualizarInventarioTienda(inventario);
                inventarioBean.init();
                inventarioTiendaBean.init();
                getFacade().edit(selected);
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
            } catch (ExceptionControl ex) {
                imprimirMensaje(ex.getMessage(), ex);
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }
    
    public Compra getCompra(java.lang.Integer id) {
        return getFacade().find(id);
    }
    
    public List<Compra> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }
    
    public List<Compra> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
    public void calculateCostTotal() {
        Compra compra = getSelected();
        BigDecimal costoUnitario = compra.getCostoUnitario();
        int cantidad = compra.getCantidad();
        BigDecimal cantidadB = new BigDecimal(cantidad);
        BigDecimal costoTotal = costoUnitario.multiply(cantidadB);
        getSelected().setCostoTotal(costoTotal);
    }
    
    private InventarioDTO createInventatioDTO(Compra selected) {
        InventarioDTO inventarioDTO = new InventarioDTO();
        inventarioDTO.setCantidad(selected.getCantidad());
        inventarioDTO.setProducto(selected.getIdProducto());
        inventarioDTO.setTienda(selected.getIdTienda());
        inventarioDTO.setOperacion(Global.DEVOLUCION);
        inventarioDTO.setCosto(selected.getCostoUnitario());
        return inventarioDTO;
    }
    
    @FacesConverter(forClass = Compra.class)
    public static class CompraControllerConverter implements Converter {
        
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CompraController controller = (CompraController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "compraController");
            return controller.getCompra(getKey(value));
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
            if (object instanceof Compra) {
                Compra o = (Compra) object;
                return getStringKey(o.getIdCompra());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Compra.class.getName()});
                return null;
            }
        }
        
    }
    
}
