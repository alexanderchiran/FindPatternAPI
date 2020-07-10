package com.gestion.coves.controller;

import com.gestion.coves.controller.util.Control;
import com.gestion.coves.dominio.entities.InventarioTienda;
import com.gestion.coves.controller.util.JsfUtil;
import com.gestion.coves.controller.util.JsfUtil.PersistAction;
import com.gestion.coves.dominio.entities.Tienda;
import com.gestion.coves.dto.InventarioDTO;
import com.gestion.coves.dto.TiendaDto;
import com.gestion.coves.exception.ExceptionControl;
import com.gestion.coves.fachada.InventarioFachadaLocal;
import com.gestion.coves.fachada.InventarioTiendaFachadaLocal;
import com.gestion.coves.fachada.TiendaFachadaLocal;
import com.gestion.coves.util.Global;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

@Named("inventarioTiendaController")
@SessionScoped
public class inventarioTiendaController extends Control implements Serializable {

    @Inject
    private InventarioTiendaFachadaLocal ejbFacade;
    @Inject
    private InventarioFachadaLocal inventarioGlobal;
    @Inject
    private InventarioController inventarioBean;
    @Inject
    private TiendaFachadaLocal ejbFacadeTienda;
    private List<InventarioTienda> items = null;
    private List<TiendaDto> itemsTiendaDto = null;
    private InventarioTienda selected;
    private Integer cantidadActual;
    private TiendaDto tienda;
    private boolean resultado;

    public inventarioTiendaController() {
    }

    public InventarioTienda getSelected() {
        return selected;
    }

    public void setSelected(InventarioTienda selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InventarioTiendaFachadaLocal getFacade() {
        return ejbFacade;
    }

    public InventarioTienda prepareCreate() {
        selected = new InventarioTienda();
        initializeEmbeddableKey();
        return selected;
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("InventarioTiendaUpdated"));
    }

    public List<InventarioTienda> getItems() {
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
        setCantidadActual(selected.getExistencia());
    }

    public void init() {
        items = getFacade().findAll();
    }

    public InventarioFachadaLocal getInventarioGlobal() {
        return inventarioGlobal;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                InventarioDTO inventario = new InventarioDTO();
                inventario.setProducto(selected.getIdProducto());
                if (persistAction == PersistAction.UPDATE) {
                    if (selected.getExistencia() == getCantidadActual()) {
                        inventario.setCantidad(null);
                    } else {
                        int catidadActualizar = getCantidadActual() - selected.getExistencia();
                        inventario.setCantidad(catidadActualizar);
                    }
                    inventario.setOrigen(PersistAction.UPDATE.name());
                    getInventarioGlobal().actualizarInventarioGlobal(inventario);
                    inventarioBean.init();
                    getFacade().edit(selected);
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
            } catch (ExceptionControl ex) {
                imprimirMensaje(ex.getMessage(), ex);
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public InventarioTienda getInventarioTienda(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<InventarioTienda> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<InventarioTienda> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = InventarioTienda.class)
    public static class inventarioTiendaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            inventarioTiendaController controller = (inventarioTiendaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "inventarioTiendaController");
            return controller.getInventarioTienda(getKey(value));
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
            if (object instanceof InventarioTienda) {
                InventarioTienda o = (InventarioTienda) object;
                return getStringKey(o.getIdInventarioTienda());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), InventarioTienda.class.getName()});
                return null;
            }
        }

    }

    public List<TiendaDto> getItemsTienda() {
        if (itemsTiendaDto == null) {
            itemsTiendaDto = new ArrayList<>();
            List<Tienda> findAll = getEjbFacadeTienda().findAll();
            for (Tienda tiendas : findAll) {
                tienda = new TiendaDto();
                tienda.setTienda(tiendas);
                itemsTiendaDto.add(tienda);
            }
        }
        return itemsTiendaDto;
    }

    public TiendaFachadaLocal getEjbFacadeTienda() {
        return ejbFacadeTienda;
    }

    public void distribuirEnTiendasInventario() {
        List<TiendaDto> itemsTiendaDto1 = getItemsTienda();
        Integer existencia = getSelected().getExistencia();
        Integer sumDistribuir = 0;
        InventarioDTO inventario = null;
        for (TiendaDto item : itemsTiendaDto1) {
            sumDistribuir += item.getCantidad();
        }
        if (!Objects.equals(sumDistribuir, existencia)) {
            setResultado(false);
            imprimirMensaje("La cantidad a distribuir no es consistente con la existencia", FacesMessage.SEVERITY_ERROR);
        } else {
            for (TiendaDto item : itemsTiendaDto1) {
                try {
                    inventario = new InventarioDTO();
                    inventario.setProducto(getSelected().getIdProducto());
                    inventario.setTienda(item.getTienda());
                    inventario.setCantidad(item.getCantidad());
                    inventario.setOperacion(Global.DEVOLUCION);
                    inventario.setOrigen(Global.DISTRIBUIR);
                    if (item.getTienda().getIdTienda().equals(getSelected().getIdTienda().getIdTienda())) {
                        inventario.setOrigenTienda(true);
                    } else {
                        inventario.setOrigenTienda(false);
                    }
                    ejbFacade.actualizarInventarioTienda(inventario);
                    init();
                    item.setCantidad(null);
                } catch (ExceptionControl ex) {
                    imprimirMensaje(ex.getMessage(), ex);
                }
            }
            setResultado(true);
            RequestContext.getCurrentInstance().execute("valida(" + resultado + ", 'DistribuirDialog')");
        }
    }

    /**
     * @return the resultado
     */
    public boolean isResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }
}
