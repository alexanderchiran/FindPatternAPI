package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Cliente;
import com.gestion.coves.dominio.entities.Empresa;
import com.gestion.coves.dominio.entities.Proveedor;
import com.gestion.coves.dominio.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(TipoIdentificacion.class)
public class TipoIdentificacion_ { 

    public static volatile SingularAttribute<TipoIdentificacion, String> descripcion;
    public static volatile ListAttribute<TipoIdentificacion, Cliente> clienteList;
    public static volatile ListAttribute<TipoIdentificacion, Proveedor> proveedorList;
    public static volatile SingularAttribute<TipoIdentificacion, String> sigla;
    public static volatile ListAttribute<TipoIdentificacion, Usuario> usuarioList;
    public static volatile ListAttribute<TipoIdentificacion, Empresa> empresaList;
    public static volatile SingularAttribute<TipoIdentificacion, Integer> idTipoIdentificacion;
    public static volatile SingularAttribute<TipoIdentificacion, Date> fechaCreacion;
    public static volatile SingularAttribute<TipoIdentificacion, String> usuarioCreacion;

}