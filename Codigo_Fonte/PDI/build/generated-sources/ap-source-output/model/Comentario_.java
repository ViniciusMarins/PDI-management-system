package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Reuniao;
import model.Servidor;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-04-25T21:03:37")
@StaticMetamodel(Comentario.class)
public class Comentario_ { 

    public static volatile SingularAttribute<Comentario, Reuniao> idReuniao;
    public static volatile SingularAttribute<Comentario, Date> data;
    public static volatile SingularAttribute<Comentario, Integer> idComentario;
    public static volatile SingularAttribute<Comentario, String> descricao;
    public static volatile SingularAttribute<Comentario, Servidor> prontuarioServidor;

}