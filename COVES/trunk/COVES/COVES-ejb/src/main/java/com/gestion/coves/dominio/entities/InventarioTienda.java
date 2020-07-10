/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.dominio.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexander Chiran
 */
@Entity
@Cacheable(false)
@Table(name = "inventario_tienda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioTienda.findAll", query = "SELECT i FROM InventarioTienda i")
    , @NamedQuery(name = "InventarioTienda.findByIdInventarioTienda", query = "SELECT i FROM InventarioTienda i WHERE i.idInventarioTienda = :idInventarioTienda")
    , @NamedQuery(name = "InventarioTienda.findByExistencia", query = "SELECT i FROM InventarioTienda i WHERE i.existencia = :existencia")
    , @NamedQuery(name = "InventarioTienda.findByidProductoIdTienda", query = "SELECT i FROM InventarioTienda i WHERE i.idProducto.idProducto = :id_producto AND i.idTienda.idTienda = :idTienda")})
public class InventarioTienda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_inventario_tienda")
    private Integer idInventarioTienda;
    @Column(name = "existencia")
    private Integer existencia;
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
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto idProducto;
    @JoinColumn(name = "id_tienda", referencedColumnName = "id_tienda")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tienda idTienda;

    public InventarioTienda() {
    }

    public InventarioTienda(Integer idInventarioTienda) {
        this.idInventarioTienda = idInventarioTienda;
    }

    public Integer getIdInventarioTienda() {
        return idInventarioTienda;
    }

    public void setIdInventarioTienda(Integer idInventarioTienda) {
        this.idInventarioTienda = idInventarioTienda;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
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

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Tienda getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(Tienda idTienda) {
        this.idTienda = idTienda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInventarioTienda != null ? idInventarioTienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventarioTienda)) {
            return false;
        }
        InventarioTienda other = (InventarioTienda) object;
        if ((this.idInventarioTienda == null && other.idInventarioTienda != null) || (this.idInventarioTienda != null && !this.idInventarioTienda.equals(other.idInventarioTienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestion.coves.dominio.entities.InventarioTienda[ idInventarioTienda=" + idInventarioTienda + " ]";
    }
    
}
