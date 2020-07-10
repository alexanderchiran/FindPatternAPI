/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.dominio.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexander Chiran
 */
@Entity
@Table(name = "factura_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacturaDetalle.findAll", query = "SELECT f FROM FacturaDetalle f")
    ,@NamedQuery(name = "FacturaDetalle.findByIdFactura", query = "SELECT f FROM FacturaDetalle f where f.idFactura.idFactura = :idFactura")
    , @NamedQuery(name = "FacturaDetalle.findByIdFacturaDetalle", query = "SELECT f FROM FacturaDetalle f WHERE f.idFacturaDetalle = :idFacturaDetalle")})
public class FacturaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_factura_detalle")
    private Integer idFacturaDetalle;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "precio_total")
    private BigDecimal precioTotal;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 50)
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @JoinColumn(name = "id_factura", referencedColumnName = "id_factura")
    @ManyToOne(fetch = FetchType.LAZY)
    private Factura idFactura;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto idProducto;

    public FacturaDetalle() {
    }

    @Transient
    private int idTemp;

    public FacturaDetalle(Integer idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    public Integer getIdFacturaDetalle() {
        return idFacturaDetalle;
    }

    public void setIdFacturaDetalle(Integer idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacturaDetalle != null ? idFacturaDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaDetalle)) {
            return false;
        }
        FacturaDetalle other = (FacturaDetalle) object;
        if ((this.idFacturaDetalle == null && other.idFacturaDetalle != null) || (this.idFacturaDetalle != null && !this.idFacturaDetalle.equals(other.idFacturaDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestion.coves.dominio.entities.FacturaDetalle[ idFacturaDetalle=" + idFacturaDetalle + " ]";
    }

    /**
     * @return the idTemp
     */
    public int getIdTemp() {
//        if (idTemp == 0) {
//            Random randomGenerator = new Random();
//            idTemp = randomGenerator.nextInt(1000);
//        }
        return idTemp;
    }

    /**
     * @param idTemp the idTemp to set
     */
    public void setIdTemp(int idTemp) {
        this.idTemp = idTemp;
    }

}
