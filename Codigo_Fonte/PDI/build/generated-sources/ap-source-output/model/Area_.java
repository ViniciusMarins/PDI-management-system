package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Disciplina;
import model.ServidorSimulacao;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-04-25T21:03:37")
@StaticMetamodel(Area.class)
public class Area_ { 

    public static volatile SingularAttribute<Area, Integer> idArea;
    public static volatile ListAttribute<Area, Disciplina> disciplinaList;
    public static volatile ListAttribute<Area, Disciplina> disciplina2List;
    public static volatile ListAttribute<Area, ServidorSimulacao> servidorSimulacaoList;
    public static volatile SingularAttribute<Area, String> descricao;

}