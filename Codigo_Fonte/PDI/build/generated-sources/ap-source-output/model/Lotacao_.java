package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Curso;
import model.ServidorSimulacao;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-04-25T21:03:37")
@StaticMetamodel(Lotacao.class)
public class Lotacao_ { 

    public static volatile SingularAttribute<Lotacao, Integer> idLotacao;
    public static volatile ListAttribute<Lotacao, ServidorSimulacao> servidorSimulacaoList;
    public static volatile ListAttribute<Lotacao, Curso> cursoList;
    public static volatile SingularAttribute<Lotacao, String> descricao;

}