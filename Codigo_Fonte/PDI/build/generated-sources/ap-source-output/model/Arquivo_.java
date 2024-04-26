package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Reuniao;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-04-25T21:03:37")
@StaticMetamodel(Arquivo.class)
public class Arquivo_ { 

    public static volatile SingularAttribute<Arquivo, Integer> idArquivo;
    public static volatile SingularAttribute<Arquivo, String> nomeUpload;
    public static volatile SingularAttribute<Arquivo, Reuniao> idReuniao;
    public static volatile SingularAttribute<Arquivo, String> nomePasta;
    public static volatile SingularAttribute<Arquivo, String> descricao;

}