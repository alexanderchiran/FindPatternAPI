/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.dominio.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexander Chiran
 */
@Entity
@Table(name = "tienda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tienda.findAll", query = "SELECT t FROM Tienda t")
    , @NamedQuery(name = "Tienda.findByIdTienda", query = "SELECT t FROM Tienda t WHERE t.idTienda = :idTienda")})
public class Tienda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tienda")
    private Integer idTienda;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @OneToMany(mappedBy = "idTienda", fetch = FetchType.LAZY)
    private List<Compra> compraList;
    @OneToMany(mappedBy = "idTienda", fetch = FetchType.LAZY)
    private List<Novedad> novedadList;
    @OneToMany(mappedBy = "idTienda", fetch = FetchType.LAZY)
    private List<InventarioTienda> InventarioTiendaList;

    public Tienda() {
    }

    public Tienda(Integer idTienda) {
        this.idTienda = idTienda;
    }

    public Integer getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(Integer idTienda) {
        this.idTienda = idTienda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    @XmlTransient
    public List<Novedad> getNovedadList() {
        return novedadList;
    }

    public void setNovedadList(List<Novedad> novedadList) {
        this.novedadList = novedadList;
    }

    @XmlTransient
    public List<InventarioTienda> getInventarioTiendaList() {
        return InventarioTiendaList;
    }

    public void setInventarioTiendaList(List<InventarioTienda> InventarioTiendaList) {
        this.InventarioTiendaList = InventarioTiendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTienda != null ? idTienda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tienda)) {
            return false;
        }
        Tienda other = (Tienda) object;
        if ((this.idTienda == null && other.idTienda != null) || (this.idTienda != null && !this.idTienda.equals(other.idTienda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestion.coves.dominio.entites.Tienda[ idTienda=" + idTienda + " ]";
    }
    
}
