/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vinicius
 */
@Entity
@Table(name = "servidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servidor.findAll", query = "SELECT s FROM Servidor s"),
    @NamedQuery(name = "Servidor.findByProntuario", query = "SELECT s FROM Servidor s WHERE s.prontuario = :prontuario"),
    @NamedQuery(name = "Servidor.findByNome", query = "SELECT s FROM Servidor s WHERE s.nome = :nome"),
    @NamedQuery(name = "Servidor.findByEmail", query = "SELECT s FROM Servidor s WHERE s.email = :email"),
    @NamedQuery(name = "Servidor.findBySenha", query = "SELECT s FROM Servidor s WHERE s.senha = :senha"),
    @NamedQuery(name = "Servidor.findByPdi", query = "SELECT s FROM Servidor s WHERE s.pdi = :pdi"),
    @NamedQuery(name = "Servidor.findByTipo", query = "SELECT s FROM Servidor s WHERE s.tipo = :tipo")})
public class Servidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "prontuario")
    private String prontuario;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @Column(name = "pdi")
    private boolean pdi;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prontuarioServidor")
    private List<Comentario> comentarioList;

    public Servidor() {
    }

     public Servidor(String prontuario) {
        this.prontuario = prontuario;
    }

    public Servidor(String prontuario, String nome, String email, boolean pdi, String tipo) {
        this.prontuario = prontuario;
        this.nome = nome;
        this.email = email;
        this.pdi = pdi;
        this.tipo = tipo;
    }
    
    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getPdi() {
        return pdi;
    }

    public void setPdi(boolean pdi) {
        this.pdi = pdi;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prontuario != null ? prontuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servidor)) {
            return false;
        }
        Servidor other = (Servidor) object;
        if ((this.prontuario == null && other.prontuario != null) || (this.prontuario != null && !this.prontuario.equals(other.prontuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Servidor[ prontuario=" + prontuario + " ]";
    }
}
