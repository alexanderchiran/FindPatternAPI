/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.coves.dominio.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexander Chiran
 */
@Entity
@Table(name = "impuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Impuesto.findAll", query = "SELECT i FROM Impuesto i")
    , @NamedQuery(name = "Impuesto.findByIdImpuesto", query = "SELECT i FROM Impuesto i WHERE i.idImpuesto = :idImpuesto")})
public class Impuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_impuesto")
    private Integer idImpuesto;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "valor")
    private Integer valor;
    @Size(max = 10)
    @Column(name = "sigla")
    private String sigla;

    public Impuesto() {
    }

    public Impuesto(Integer idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public Integer getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(Integer idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImpuesto != null ? idImpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impuesto)) {
            return false;
        }
        Impuesto other = (Impuesto) object;
        if ((this.idImpuesto == null && other.idImpuesto != null) || (this.idImpuesto != null && !this.idImpuesto.equals(other.idImpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gestion.coves.dominio.entities.Impuesto[ idImpuesto=" + idImpuesto + " ]";
    }
    
}
