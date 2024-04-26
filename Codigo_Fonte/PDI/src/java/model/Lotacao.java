/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "lotacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lotacao.findAll", query = "SELECT l FROM Lotacao l"),
    @NamedQuery(name = "Lotacao.findByIdLotacao", query = "SELECT l FROM Lotacao l WHERE l.idLotacao = :idLotacao"),
    @NamedQuery(name = "Lotacao.findByDescricao", query = "SELECT l FROM Lotacao l WHERE l.descricao = :descricao")})
public class Lotacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLotacao")
    private Integer idLotacao;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "idLotacao")
    private List<ServidorSimulacao> servidorSimulacaoList;
    @OneToMany(mappedBy = "idLotacao")
    private List<Curso> cursoList;

    public Lotacao() {
    }

    public Lotacao(Integer idLotacao) {
        this.idLotacao = idLotacao;
    }

    public Lotacao(Integer idLotacao, String descricao) {
        this.idLotacao = idLotacao;
        this.descricao = descricao;
    }

    public Integer getIdLotacao() {
        return idLotacao;
    }

    public void setIdLotacao(Integer idLotacao) {
        this.idLotacao = idLotacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<ServidorSimulacao> getServidorSimulacaoList() {
        return servidorSimulacaoList;
    }

    public void setServidorSimulacaoList(List<ServidorSimulacao> servidorSimulacaoList) {
        this.servidorSimulacaoList = servidorSimulacaoList;
    }

    @XmlTransient
    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> CursoList) {
        this.cursoList = CursoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLotacao != null ? idLotacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lotacao)) {
            return false;
        }
        Lotacao other = (Lotacao) object;
        if ((this.idLotacao == null && other.idLotacao != null) || (this.idLotacao != null && !this.idLotacao.equals(other.idLotacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Lotacao[ idLotacao=" + idLotacao + " ]";
    }
    
}
