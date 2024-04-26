package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Disciplina;
import model.Lotacao;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-04-25T21:03:37")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile SingularAttribute<Curso, String> sigla;
    public static volatile ListAttribute<Curso, Disciplina> disciplinaList;
    public static volatile SingularAttribute<Curso, Integer> idCurso;
    public static volatile SingularAttribute<Curso, String> semestre;
    public static volatile SingularAttribute<Curso, Lotacao> idLotacao;
    public static volatile SingularAttribute<Curso, String> descricao;
    public static volatile SingularAttribute<Curso, Boolean> status;

}