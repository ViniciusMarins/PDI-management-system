package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-04-25T21:03:37")
@StaticMetamodel(Token.class)
public class Token_ { 

    public static volatile SingularAttribute<Token, Date> dataExpiracao;
    public static volatile SingularAttribute<Token, String> codigo;
    public static volatile SingularAttribute<Token, Boolean> usado;
    public static volatile SingularAttribute<Token, String> usuario;

}