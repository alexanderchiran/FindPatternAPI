/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.dominio.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexander Chiran
 */
@Entity
@Table(name = "detalle_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallePago.findAll", query = "SELECT d FROM DetallePago d")
    ,@NamedQuery(name = "DetallePago.findByIdPago", query = "SELECT d FROM DetallePago d where d.idPagoProveedor.idPagoProveedor = :idPagoProveedor")
    , @NamedQuery(name = "DetallePago.findByIdDetallePago", query = "SELECT d FROM DetallePago d WHERE d.idDetallePago = :idDetallePago")})
public class DetallePago implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_pago")
    private Integer idDetallePago;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_abono")
    private BigDecimal valorAbono;
    @Column(name = "fecha_abono")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAbono;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "usuario_actualizacion")
    private String usuarioActualizacion;
    @JoinColumn(name = "id_pago_proveedor", referencedColumnName = "id_pago_proveedor")
    @ManyToOne(fetch = FetchType.LAZY)
    private PagoProveedor idPagoProveedor;

    public DetallePago() {
    }

    public DetallePago(Integer idDetallePago) {
        this.idDetallePago = idDetallePago;
    }

    public Integer getIdDetallePago() {
        return idDetallePago;
    }

    public void setIdDetallePago(Integer idDetallePago) {
        this.idDetallePago = idDetallePago;
    }

    public BigDecimal getValorAbono() {
        return valorAbono;
    }

    public void setValorAbono(BigDecimal valorAbono) {
        this.valorAbono = valorAbono;
    }

    public Date getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(Date fechaAbono) {
        this.fechaAbono = fechaAbono;
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

    public PagoProveedor getIdPagoProveedor() {
        return idPagoProveedor;
    }

    public void setIdPagoProveedor(PagoProveedor idPagoProveedor) {
        this.idPagoProveedor = idPagoProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetallePago != null ? idDetallePago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallePago)) {
            return false;
        }
        DetallePago other = (DetallePago) object;
        if ((this.idDetallePago == null && other.idDetallePago != null) || (this.idDetallePago != null && !this.idDetallePago.equals(other.idDetallePago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestion.coves.dominio.entities.DetallePago[ idDetallePago=" + idDetallePago + " ]";
    }

}
