/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "curso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByIdCurso", query = "SELECT c FROM Curso c WHERE c.idCurso = :idCurso"),
    @NamedQuery(name = "Curso.findBySigla", query = "SELECT c FROM Curso c WHERE c.sigla = :sigla"),
    @NamedQuery(name = "Curso.findByDescricao", query = "SELECT c FROM Curso c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Curso.findBySemestre", query = "SELECT c FROM Curso c WHERE c.semestre = :semestre"),
    @NamedQuery(name = "Curso.findByStatus", query = "SELECT c FROM Curso c WHERE c.status = :status")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCurso")
    private Integer idCurso;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "sigla")
    private String sigla;
    @Basic(optional = false)
    @Column(name = "semestre")
    private String semestre;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private List<Disciplina> disciplinaList;

    @JoinColumn(name = "idLotacao", referencedColumnName = "idLotacao")
    @ManyToOne
    private Lotacao idLotacao;

    public Curso() {
    }

    public Curso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Curso(Integer idCurso, String descricao, String sigla, String semestre, boolean status) {
        this.idCurso = idCurso;
        this.descricao = descricao;
        this.semestre = semestre;
        this.status = status;
        this.sigla = sigla;

    }
    
    public String getLog(Curso c) {
        int flag = 0;
        String log; 
        
        log = "[CURSO] " + sigla + "(" + idCurso + "): <br>";

        if(c.status != status) {
            log = log + "Status alterado de " + c.status + " para " + status + ".<br>";
            flag = 1;
        }

        if(flag==1) {
            return log;
        }
        
        return "";
    }

    public String getLog() {
        String log;

        log = "[CURSO] " + descricao + " criado: <br>";
        
        log = log + "Sigla: " + sigla + ".<br>"; 
        log = log + "Semestre: " + semestre + ".<br>";
        log = log + "Status: " + status + ".<br>";
        log = log + "Área de Lotação: " + idLotacao.getDescricao() + ".<br>";    

        return log;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Lotacao getIdLotacao() {
        return idLotacao;
    }

    public void setIdLotacao(Lotacao idLotacao) {
        this.idLotacao = idLotacao;
    }

    @XmlTransient
    public List<Disciplina> getDisciplinaList() {
        return disciplinaList;
    }

    public void setDisciplinaList(List<Disciplina> disciplinaList) {
        this.disciplinaList = disciplinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Curso[ idCurso=" + idCurso + " ]";
    }

}
