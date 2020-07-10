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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexander Chiran
 */
@Entity
@Table(name = "tipo_noveda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNoveda.findAll", query = "SELECT t FROM TipoNoveda t")
    , @NamedQuery(name = "TipoNoveda.findByIdTipoNoveda", query = "SELECT t FROM TipoNoveda t WHERE t.idTipoNoveda = :idTipoNoveda")
    , @NamedQuery(name = "TipoNoveda.findByDescripcion", query = "SELECT t FROM TipoNoveda t WHERE t.descripcion = :descripcion")})
public class TipoNoveda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_noveda")
    private Integer idTipoNoveda;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "sigla")
    private String sigla;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 50)
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @OneToMany(mappedBy = "idTipoNovedad", fetch = FetchType.LAZY)
    private List<Novedad> novedadList;

    public TipoNoveda() {
    }

    public TipoNoveda(Integer idTipoNoveda) {
        this.idTipoNoveda = idTipoNoveda;
    }

    public Integer getIdTipoNoveda() {
        return idTipoNoveda;
    }

    public void setIdTipoNoveda(Integer idTipoNoveda) {
        this.idTipoNoveda = idTipoNoveda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    public List<Novedad> getNovedadList() {
        return novedadList;
    }

    public void setNovedadList(List<Novedad> novedadList) {
        this.novedadList = novedadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoNoveda != null ? idTipoNoveda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoNoveda)) {
            return false;
        }
        TipoNoveda other = (TipoNoveda) object;
        if ((this.idTipoNoveda == null && other.idTipoNoveda != null) || (this.idTipoNoveda != null && !this.idTipoNoveda.equals(other.idTipoNoveda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestion.coves.dominio.entities.TipoNoveda[ idTipoNoveda=" + idTipoNoveda + " ]";
    }
    
}
