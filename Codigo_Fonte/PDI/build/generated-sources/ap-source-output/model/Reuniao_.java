package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Arquivo;
import model.Comentario;
import model.Frequencia;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-04-25T21:03:37")
@StaticMetamodel(Reuniao.class)
public class Reuniao_ { 

    public static volatile CollectionAttribute<Reuniao, Frequencia> frequenciaCollection;
    public static volatile SingularAttribute<Reuniao, Date> ultimaAlteracao;
    public static volatile SingularAttribute<Reuniao, Integer> idReuniao;
    public static volatile ListAttribute<Reuniao, Comentario> comentarioList;
    public static volatile SingularAttribute<Reuniao, Date> dataTermino;
    public static volatile SingularAttribute<Reuniao, String> titulo;
    public static volatile SingularAttribute<Reuniao, Date> dataInicio;
    public static volatile ListAttribute<Reuniao, Arquivo> arquivoList;
    public static volatile SingularAttribute<Reuniao, String> descricao;

}