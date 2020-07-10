package com.gestion.coves.controller;

import com.gestion.coves.controller.util.Control;
import com.gestion.coves.dominio.entities.Novedad;
import com.gestion.coves.controller.util.JsfUtil;
import com.gestion.coves.controller.util.JsfUtil.PersistAction;
import com.gestion.coves.dto.InventarioDTO;
import com.gestion.coves.exception.ExceptionControl;
import com.gestion.coves.fachada.InventarioFachadaLocal;
import com.gestion.coves.fachada.InventarioTiendaFachadaLocal;
import com.gestion.coves.fachada.NovedadFachadaLocal;
import com.gestion.coves.util.Global;
import com.gestion.coves.util.Operaciones;

import java.io.Serializable;
import java.math.BigDecimal;
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

@Named("novedadController")
@SessionScoped
public class NovedadController extends Control implements Serializable {

    @Inject
    private NovedadFachadaLocal ejbFacade;
    @Inject
    private InventarioFachadaLocal inventarioGlobal;
    @Inject
    private InventarioTiendaFachadaLocal inventarioTienda;
    @Inject
    private InventarioController inventarioBean;
    @Inject
    private inventarioTiendaController inventarioTiendaBean;
    private List<Novedad> items = null;
    private Novedad selected;
    private Integer cantidadActual;

    public NovedadController() {
    }

    public Novedad getSelected() {
        return selected;
    }

    public void setSelected(Novedad selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public InventarioFachadaLocal getInventarioGlobal() {
        return inventarioGlobal;
    }

    public InventarioTiendaFachadaLocal getInventarioTienda() {
        return inventarioTienda;
    }

    private NovedadFachadaLocal getFacade() {
        return ejbFacade;
    }

    public Novedad prepareCreate() {
        selected = new Novedad();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        getSelected().setFechaCreacion(new Date());
        getSelected().setUsuarioCreacion(Operaciones.usuarioSesion());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NovedadCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        getSelected().setFechaActualizacion(new Date());
        getSelected().setUsuarioActualizacion(Operaciones.usuarioSesion());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NovedadUpdated"));
    }

    public List<Novedad> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Integer cantidadActual) {
        this.cantidadActual = cantidadActual;
    }
    
    public void guardarCantidadActual() {
        setCantidadActual(selected.getCantidad());
    }
    

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                InventarioDTO inventario = createInventatioDTO(selected);
                inventario.setNovedad(true);
                if (persistAction == PersistAction.CREATE) {
                    inventario.setOrigen(PersistAction.CREATE.name());
                } else if (persistAction == PersistAction.UPDATE) {
                    if (selected.getCantidad().intValue() == getCantidadActual()) {
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
                if (persistAction == PersistAction.DELETE) {
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
            }catch (ExceptionControl ex) {
                imprimirMensaje(ex.getMessage(), ex);
            }  catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    private InventarioDTO createInventatioDTO(Novedad selected) {
        InventarioDTO inventarioDTO = new InventarioDTO();
        inventarioDTO.setCantidad(selected.getCantidad());
        inventarioDTO.setProducto(selected.getIdProducto());
        inventarioDTO.setTienda(selected.getIdTienda());
        if (Global.DEVOLUCION.equals(selected.getIdTipoNovedad().getSigla())) {
            inventarioDTO.setOperacion(Global.DEVOLUCION);
        } else if (Global.DAR_DE_BAJA.equals(selected.getIdTipoNovedad().getSigla())) {
            inventarioDTO.setOperacion(Global.DAR_DE_BAJA);
        }
        inventarioDTO.setCosto(selected.getCostoUnitario());
        return inventarioDTO;
    }

    public Novedad getNovedad(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Novedad> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Novedad> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public void calculateCostTotal() {
        Novedad novedad = getSelected();
        BigDecimal costoUnitario = novedad.getCostoUnitario();
        int cantidad = novedad.getCantidad();
        BigDecimal cantidadB = new BigDecimal(cantidad);
        BigDecimal costoTotal = costoUnitario.multiply(cantidadB);
        getSelected().setCostoTotal(costoTotal);
    }

    @FacesConverter(forClass = Novedad.class)
    public static class NovedadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NovedadController controller = (NovedadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "novedadController");
            return controller.getNovedad(getKey(value));
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
            if (object instanceof Novedad) {
                Novedad o = (Novedad) object;
                return getStringKey(o.getIdNovedad());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Novedad.class.getName()});
                return null;
            }
        }

    }

}
