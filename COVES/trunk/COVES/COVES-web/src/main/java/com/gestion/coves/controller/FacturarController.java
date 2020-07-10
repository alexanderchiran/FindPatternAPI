package com.gestion.coves.controller;

import com.gestion.coves.controller.util.Control;
import com.gestion.coves.controller.util.ImprimirPdf;
import com.gestion.coves.dominio.entities.Factura;
import com.gestion.coves.controller.util.JsfUtil;
import com.gestion.coves.controller.util.JsfUtil.PersistAction;
import com.gestion.coves.dominio.entities.Cliente;
import com.gestion.coves.dominio.entities.Empresa;
import com.gestion.coves.dominio.entities.FacturaDetalle;
import com.gestion.coves.dominio.entities.Producto;
import com.gestion.coves.dominio.entities.Tienda;
import com.gestion.coves.fachada.ClienteFachadaLocal;
import com.gestion.coves.fachada.EmpresaFachadaLocal;
import com.gestion.coves.fachada.FacturaDetalleFachadaLocal;
import com.gestion.coves.fachada.FacturaFachadaLocal;
import com.gestion.coves.fachada.ImpuestoFachadaLocal;
import com.gestion.coves.fachada.InventarioTiendaFachadaLocal;
import com.gestion.coves.fachada.ProductoFachadaLocal;
import com.gestion.coves.fachada.TiendaFachadaLocal;
import com.gestion.coves.model.FacturarModel;
import com.gestion.coves.util.Global;
import com.gestion.coves.util.Mensaje;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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

@Named("facturarController")
@SessionScoped
public class FacturarController extends Control implements Serializable {

    @Inject
    private FacturaFachadaLocal facturaFachada;
    @Inject
    private FacturaDetalleFachadaLocal facturaDetalleFachada;
    @Inject
    private InventarioTiendaFachadaLocal inventarioTiendaFachada;
    @Inject
    private InicioSesionController inicioSesionController;
    @Inject
    private ProductoFachadaLocal productoFachada;
    @Inject
    private ImpuestoFachadaLocal impuestoFachada;
    @Inject
    private TiendaFachadaLocal tiendaFachada;
    @Inject
    private ClienteFachadaLocal clienteFachada;
    @Inject
    private EmpresaFachadaLocal empresaFachada;

    private String nombreFactura;
    private List<Factura> items = null;
    private Factura selected;
    private FacturarModel facturarModel;
   // private Empresa empresa;

    public FacturarController() {
    }

    @PostConstruct
    public void init() {
        try {

            facturarModel = new FacturarModel();
            facturarModel.setEmpresa(empresaFachada.findAll().get(0));
            facturarModel.setDeshabilitar(false);
            iniciarFactura();
            consultarProductos();
            getModel().setListaFacturaDetalle(new ArrayList<FacturaDetalle>());
            consultarClientes();
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }

    }

    public void nuevaFactura() {
        init();
    }

    public void consultarClientes() {
        try {
            getModel().setListaCliente(clienteFachada.findAll());
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }

    }

    public void setCliente(Cliente cliente) {
        try {
            selected.setIdCliente(cliente);
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }

    /**
     *
     */
    public void iniciarFactura() {
        try {
            selected = new Factura();
            selected.setVendedor(inicioSesionController.getModel().getNombre());
            selected.setFechaVenta(new Date());
            selected.setFechaVencimiento(new Date());
            selected.setTipoPago("Efectivo");
            selected.setSubtotal(BigDecimal.ZERO);
            selected.setIva(BigDecimal.ZERO);
            selected.setTotal(BigDecimal.ZERO);
            selected.setNumeroFactura(numeroFactura());
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }

    /**
     *
     * @return
     */
    public String numeroFactura() {
        try {
            return "" + facturaFachada.calcularNumeroFactura();
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
        return "1";
    }

    /**
     *
     */
    public void quitarProducto() {
        try {
            FacturaDetalle facturaDetalle = getModel().getFacturaDetalleSel();
            if (facturaDetalle != null) {
                int val=0;
                for(FacturaDetalle facturaDetalleInt:   getModel().getListaFacturaDetalle()){
                    if(facturaDetalle.getIdTemp()==facturaDetalleInt.getIdTemp()){
                         getModel().getListaFacturaDetalle().remove(val);
                         break;
                    }
                    val++;
                }
                calcularTotal(Global.RESTA, facturaDetalle.getPrecioTotal());
                imprimirMensaje("Producto borrado correctamente", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }

    /**
     *
     */
    private void consultarProductos() {
        try {
            facturarModel.setListaProducto(productoFachada.findAll());
            getModel().setImpuesto(impuestoFachada.findAll().get(0));
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
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
        return facturaFachada;
    }

    public Factura prepareCreate() {
        selected = new Factura();
        initializeEmbeddableKey();
        return selected;
    }

    public void facturar() {
        try {
            if (selected.getIdCliente() == null) {
                imprimirMensaje("Seleccione cliente", FacesMessage.SEVERITY_ERROR);
            } else if (getModel().getTiendaSeleccionada() == null) {
                imprimirMensaje("Seleccione tienda", FacesMessage.SEVERITY_ERROR);
            } else if (getModel().getListaFacturaDetalle() == null || getModel().getListaFacturaDetalle().isEmpty()) {
                imprimirMensaje("Debe agregar productos", FacesMessage.SEVERITY_ERROR);
            } else if (selected.getEfectivo() == null) {
                imprimirMensaje("Ingrese valor del efectivo", FacesMessage.SEVERITY_ERROR);
            } else if (selected.getEfectivo().compareTo(selected.getTotal()) < 0) {
                imprimirMensaje("El valor del efectivo es menor que el total", FacesMessage.SEVERITY_ERROR);
            } else {
                if (validarCantidades()) {
                    //Crear factura
                    selected.setIdTienda(getModel().getTiendaSeleccionada());
                    facturaFachada.crear(selected);
                    if (selected.getIdFactura() != null) {
                        getModel().setDeshabilitar(true);
                        facturaDetalleFachada.insertarDetalles(getModel().getListaFacturaDetalle(), selected);
                        imprimirMensaje("Venta realizada exitosamente !!", FacesMessage.SEVERITY_INFO);
                    } else {
                        imprimirMensaje("La factura no fue creada correctamente", FacesMessage.SEVERITY_ERROR);
                    }
                }
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }

    }
    
     public void imprimir() {
        ImprimirPdf firstPdf = new ImprimirPdf();
        String nombreA = firstPdf.crearFactura(selected, getModel().getEmpresa(), getModel().getListaFacturaDetalle());
        //nombreFactura="fileName.pdf";
        RequestContext.getCurrentInstance().execute("imprimir('" + nombreA + "')");

    }

    private boolean validarCantidades() {
        boolean estado = true;
        try {
            List<FacturaDetalle> listaFacturaDetalle = getModel().getListaFacturaDetalle();
            Tienda tienda = getModel().getTiendaSeleccionada();
            List<Mensaje> listaMensaje = new ArrayList<>();

            if (listaFacturaDetalle != null) {
                for (FacturaDetalle facturaDetalle : listaFacturaDetalle) {
                    boolean existeCantidad = inventarioTiendaFachada.validarCantidades(tienda, facturaDetalle.getCantidad(), facturaDetalle.getIdProducto());
                    if (!existeCantidad) {
                        listaMensaje.add(new Mensaje("sinCantidades", facturaDetalle.getIdProducto().getDescripcion(), tienda.getDescripcion()));
                        estado = false;
                    }
                }

                if (!listaMensaje.isEmpty()) {
                    imprimirMensaje(listaMensaje, FacesMessage.SEVERITY_ERROR);
                }
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
        return estado;
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

    public void reglaFactura() {
        try {
            Producto producto = getModel().getProductoSeleccionado();
            if (producto != null) {
                getModel().setPrecioUnitario(producto.getPrecio1());
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }

    /**
     *
     */
    public void agregarProducto() {
        try {
            if (getModel().getProductoSeleccionado() == null) {
                imprimirMensaje("Debe seleccionar un producto", FacesMessage.SEVERITY_ERROR);
                return;
            }
            if (getModel().getCantidad() == null) {
                imprimirMensaje("Debe ingresar cantidad", FacesMessage.SEVERITY_ERROR);
                return;
            }
            if (getModel().getPrecioUnitario() == null) {
                imprimirMensaje("Debe ingresar precio unitario", FacesMessage.SEVERITY_ERROR);
            } else {
                FacturaDetalle facturaDetalle = new FacturaDetalle();
                facturaDetalle.setIdProducto(getModel().getProductoSeleccionado());
                facturaDetalle.setDescripcion(getModel().getProductoSeleccionado().getDescripcion());
                facturaDetalle.setCantidad(getModel().getCantidad());
                facturaDetalle.setPrecioUnitario(getModel().getPrecioUnitario());
                facturaDetalle.setPrecioTotal(getModel().getPrecioTotal());
                if (getModel().getListaFacturaDetalle().isEmpty()) {
                    facturaDetalle.setIdTemp(1);
                } else {
                    int tam = getModel().getListaFacturaDetalle().size();
                    facturaDetalle.setIdTemp(1 + tam);
                }
                getModel().getListaFacturaDetalle().add(facturaDetalle);
                calcularTotal(Global.SUMA, getModel().getPrecioTotal());
                limpiarCampos();
                calcularCambio();
                imprimirMensaje("Producto agregado con exito", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }

    private void limpiarCampos() {
        try {
            getModel().setProductoSeleccionado(null);
            getModel().setCantidad(null);
            getModel().setPrecioUnitario(null);
            getModel().setPrecioTotal(null);
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }

    /**
     *
     * @param operador
     * @param valor
     */
    private void calcularTotal(String operador, BigDecimal valor) {
        try {
            BigDecimal total;
            if (operador.equals(Global.SUMA)) {
                total = selected.getTotal();
                total = total.add(valor);
                selected.setTotal(total);

            } else {
                total = selected.getTotal();
                valor = valor.multiply(new BigDecimal(-1));
                total = total.add(valor);
                selected.setTotal(total);

            }
            //IVA
            BigDecimal valorB = new BigDecimal(getModel().getImpuesto().getValor());
            BigDecimal iva = (valorB.divide(new BigDecimal(100), 4, RoundingMode.HALF_UP)).add(new BigDecimal(1));
            BigDecimal subTotal = total.divide(iva, 0, RoundingMode.HALF_UP);
            selected.setSubtotal(subTotal);
            //subtotal
            selected.setIva(total.add(subTotal.multiply(new BigDecimal(-1))));
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }

    public void calcularCambio() {
        try {
            if (selected.getEfectivo() != null) {
                BigDecimal cambio = selected.getEfectivo().add(selected.getTotal().multiply(new BigDecimal(-1)));
                selected.setCambio(cambio);
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }

    }

    public void calcular() {
        try {
            if (getModel().getProductoSeleccionado() != null && getModel().getPrecioUnitario() != null) {
                getModel().setPrecioTotal(getModel().getPrecioUnitario().multiply(new BigDecimal(getModel().getCantidad())));
            }
        } catch (Exception e) {
            imprimirMensaje(getMessageResource(Global.ERROR_INTERNO), e);
        }
    }

    public List<Factura> getItems() {
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
            FacturarController controller = (FacturarController) facesContext.getApplication().getELResolver().
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

    /**
     * @return the facturarModel
     */
    public FacturarModel getModel() {
        return facturarModel;
    }

    /**
     * @param facturarModel the facturarModel to set
     */
    public void setFacturarModel(FacturarModel facturarModel) {
        this.facturarModel = facturarModel;
    }

    @FacesConverter(forClass = Producto.class)
    public static class ProductoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductoController controller = (ProductoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productoController");
            return controller.getProducto(getKey(value));
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
            if (object instanceof Producto) {
                Producto o = (Producto) object;
                return getStringKey(o.getIdProducto());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Producto.class.getName()});
                return null;
            }
        }

    }

    /**
     * @return the nombreFactura
     */
    public String getNombreFactura() {
        return nombreFactura;
    }

    /**
     * @param nombreFactura the nombreFactura to set
     */
    public void setNombreFactura(String nombreFactura) {
        this.nombreFactura = nombreFactura;
    }

}
