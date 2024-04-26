package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import util.DateUtils;
import util.RandomUtils;

@Entity
@Table(name = "token")
@NamedQueries({
    @NamedQuery(name = "Token.findToken", query = "SELECT t FROM Token t WHERE t.codigo = :codigo AND t.usado = false")
})
public class Token implements Serializable{
    @Id
    @Column(name= "codigo",nullable = false, length = 36)
    private String codigo;
    
    @Column(name = "data_expiracao", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataExpiracao;
    
    @Column(name = "usado")
    private boolean usado;
    
    @JoinColumn(name = "usuario", nullable = false)
    private String usuario;

    public Token() {
    }

    public Token(String usuario) {
        this.codigo = RandomUtils.getAlphaNumericString(6);
        this.usuario = usuario;
        this.dataExpiracao = DateUtils.gerarHoraToken();
        this.usado = false;
    }

    public Token(String codigo, Date dataExpiracao, String usuario, boolean usado) {
        this.codigo = codigo;
        this.dataExpiracao = dataExpiracao;
        this.usuario = usuario;
        this.usado = usado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean getUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Token other = (Token) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Token{" + "codigo=" + codigo + ", dataExpiracao=" + dataExpiracao + ", usuario=" + usuario + '}';
    }
}