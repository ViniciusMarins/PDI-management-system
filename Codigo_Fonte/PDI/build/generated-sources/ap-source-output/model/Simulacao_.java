package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.DadosSimulacao;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-04-25T21:03:37")
@StaticMetamodel(Simulacao.class)
public class Simulacao_ { 

    public static volatile ListAttribute<Simulacao, DadosSimulacao> dadosSimulacaoList;
    public static volatile SingularAttribute<Simulacao, Integer> idSimulacao;
    public static volatile SingularAttribute<Simulacao, String> titulo;
    public static volatile SingularAttribute<Simulacao, String> logs;
    public static volatile SingularAttribute<Simulacao, String> descricao;

}