package com.gestion.coves.dominio.entities;

import com.gestion.coves.dominio.entities.Novedad;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:51:30")
@StaticMetamodel(TipoNoveda.class)
public class TipoNoveda_ { 

    public static volatile SingularAttribute<TipoNoveda, String> descripcion;
    public static volatile SingularAttribute<TipoNoveda, String> estado;
    public static volatile SingularAttribute<TipoNoveda, String> sigla;
    public static volatile SingularAttribute<TipoNoveda, Date> fechaCreacion;
    public static volatile ListAttribute<TipoNoveda, Novedad> novedadList;
    public static volatile SingularAttribute<TipoNoveda, Integer> idTipoNoveda;
    public static volatile SingularAttribute<TipoNoveda, String> usuarioCreacion;

}