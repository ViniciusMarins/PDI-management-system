package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Comentario;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-04-25T21:03:37")
@StaticMetamodel(Servidor.class)
public class Servidor_ { 

    public static volatile SingularAttribute<Servidor, String> senha;
    public static volatile SingularAttribute<Servidor, String> prontuario;
    public static volatile SingularAttribute<Servidor, String> tipo;
    public static volatile SingularAttribute<Servidor, Boolean> pdi;
    public static volatile ListAttribute<Servidor, Comentario> comentarioList;
    public static volatile SingularAttribute<Servidor, String> nome;
    public static volatile SingularAttribute<Servidor, String> email;

}