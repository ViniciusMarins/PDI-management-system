package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Area;
import model.Lotacao;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-04-25T21:03:37")
@StaticMetamodel(ServidorSimulacao.class)
public class ServidorSimulacao_ { 

    public static volatile SingularAttribute<ServidorSimulacao, String> tipo;
    public static volatile SingularAttribute<ServidorSimulacao, Area> idArea;
    public static volatile SingularAttribute<ServidorSimulacao, Integer> idProfessor;
    public static volatile SingularAttribute<ServidorSimulacao, String> nome;
    public static volatile SingularAttribute<ServidorSimulacao, Lotacao> idLotacao;
    public static volatile SingularAttribute<ServidorSimulacao, Boolean> status;

}