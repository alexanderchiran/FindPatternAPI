package com.gestion.coves.controller;

import com.gestion.coves.controller.util.Control;
import static com.gestion.coves.controller.util.Control.getMessageResource;
import com.gestion.coves.dominio.entities.Inventario;
import com.gestion.coves.fachada.InventarioFachadaLocal;
import com.gestion.coves.util.Global;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("inventarioController")
@RequestScoped
public class InventarioController extends Control implements Serializable {

    @Inject
    private InventarioFachadaLocal ejbFacade;
    private List<Inventario> items = null;
    private Inventario selected;
    private BigDecimal total;

    public InventarioController() {
    }

    public Inventario getSelected() {
        return selected;
    }

    /**
     * Total de cada Item
     */
    private void calcularTotalItem() {
        try {
            if (items != null) {
                for (Inventario inventario : items) {
                    if (inventario.getCostoUnitario() != null && inventario.getExistencia() != null) {
                        BigDecimal totalLocal = inventario.getCostoUnitario().multiply(new BigDecimal(inventario.getExistencia()));
                        inventario.setTotalItem(totalLocal);
                    } else {
                        inventario.setTotalItem(new BigDecimal(BigInteger.ZERO));
                    }
                }
                totalDeTotales();
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }

    /**
     *
     */
    private void totalDeTotales() {
        try {
            if (items != null) {
                total = new BigDecimal(BigInteger.ZERO);
                for (Inventario inventario : items) {
                    total = total.add(inventario.getTotalItem());
                }
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }

    public void setSelected(Inventario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    private InventarioFachadaLocal getFacade() {
        return ejbFacade;
    }

    public void init() {
        items = getFacade().findAll();
    }

    public List<Inventario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
            calcularTotalItem();
        }
        return items;
    }

    public Inventario getInventario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Inventario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Inventario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Inventario.class)
    public static class InventarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InventarioController controller = (InventarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "inventarioController");
            return controller.getInventario(getKey(value));
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
            if (object instanceof Inventario) {
                Inventario o = (Inventario) object;
                return getStringKey(o.getIdInventario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Inventario.class.getName()});
                return null;
            }
        }

    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
