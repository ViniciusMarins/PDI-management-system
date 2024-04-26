package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Area;
import model.Curso;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-04-25T21:03:37")
@StaticMetamodel(Disciplina.class)
public class Disciplina_ { 

    public static volatile SingularAttribute<Disciplina, Integer> cargaHorariaParcial;
    public static volatile SingularAttribute<Disciplina, String> sigla;
    public static volatile SingularAttribute<Disciplina, Area> idArea;
    public static volatile SingularAttribute<Disciplina, Area> idArea2;
    public static volatile SingularAttribute<Disciplina, Integer> idDisciplina;
    public static volatile SingularAttribute<Disciplina, Curso> idCurso;
    public static volatile SingularAttribute<Disciplina, String> nome;
    public static volatile SingularAttribute<Disciplina, String> semestre;
    public static volatile SingularAttribute<Disciplina, Integer> cargaHoraria;
    public static volatile SingularAttribute<Disciplina, String> regencia;
    public static volatile SingularAttribute<Disciplina, Boolean> status;

}