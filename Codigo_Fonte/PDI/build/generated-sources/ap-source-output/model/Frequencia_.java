package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.FrequenciaPK;
import model.Reuniao;
import model.Servidor;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-04-25T21:03:37")
@StaticMetamodel(Frequencia.class)
public class Frequencia_ { 

    public static volatile SingularAttribute<Frequencia, Servidor> servidor;
    public static volatile SingularAttribute<Frequencia, FrequenciaPK> frequenciaPK;
    public static volatile SingularAttribute<Frequencia, Boolean> frequencia;
    public static volatile SingularAttribute<Frequencia, Reuniao> reuniao;
    public static volatile SingularAttribute<Frequencia, String> descricao;

}