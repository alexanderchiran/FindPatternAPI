/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.dominio.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexander Chiran
 */
@Entity
@Table(name = "pago_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoProveedor.findAll", query = "SELECT p FROM PagoProveedor p")
    , @NamedQuery(name = "PagoProveedor.findByIdPagoProveedor", query = "SELECT p FROM PagoProveedor p WHERE p.idPagoProveedor = :idPagoProveedor")})
public class PagoProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pago_proveedor")
    private Integer idPagoProveedor;
    @Size(max = 50)
    @Column(name = "numero_factura")
    private String numeroFactura;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "valor_pagado")
    private BigDecimal valorPagado;
    @Column(name = "fecha_factura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFactura;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 50)
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Size(max = 50)
    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne(fetch = FetchType.LAZY)
    private Proveedor idProveedor;
    @OneToMany(mappedBy = "idPagoProveedor", fetch = FetchType.LAZY)
    private List<DetallePago> detallePagoList;

    public PagoProveedor() {
    }

    public PagoProveedor(Integer idPagoProveedor) {
        this.idPagoProveedor = idPagoProveedor;
    }

    public Integer getIdPagoProveedor() {
        return idPagoProveedor;
    }

    public void setIdPagoProveedor(Integer idPagoProveedor) {
        this.idPagoProveedor = idPagoProveedor;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
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

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    @XmlTransient
    public List<DetallePago> getDetallePagoList() {
        return detallePagoList;
    }

    public void setDetallePagoList(List<DetallePago> detallePagoList) {
        this.detallePagoList = detallePagoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPagoProveedor != null ? idPagoProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoProveedor)) {
            return false;
        }
        PagoProveedor other = (PagoProveedor) object;
        if ((this.idPagoProveedor == null && other.idPagoProveedor != null) || (this.idPagoProveedor != null && !this.idPagoProveedor.equals(other.idPagoProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestion.coves.dominio.entities.PagoProveedor[ idPagoProveedor=" + idPagoProveedor + " ]";
    }
    
}
