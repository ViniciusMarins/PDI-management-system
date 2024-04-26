/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vinicius
 */
@Entity
@Table(name = "disciplina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disciplina.findAll", query = "SELECT d FROM Disciplina d"),
    @NamedQuery(name = "Disciplina.findByIdDisciplina", query = "SELECT d FROM Disciplina d WHERE d.idDisciplina = :idDisciplina"),
    @NamedQuery(name = "Disciplina.findBySigla", query = "SELECT d FROM Disciplina d WHERE d.sigla = :sigla"),
    @NamedQuery(name = "Disciplina.findByCargaHoraria", query = "SELECT d FROM Disciplina d WHERE d.cargaHoraria = :cargaHoraria"),
    @NamedQuery(name = "Disciplina.findBySemestre", query = "SELECT d FROM Disciplina d WHERE d.semestre = :semestre"),
    @NamedQuery(name = "Disciplina.findByNome", query = "SELECT d FROM Disciplina d WHERE d.nome = :nome"),
    @NamedQuery(name = "Disciplina.findByStatus", query = "SELECT d FROM Disciplina d WHERE d.status = :status")})
public class Disciplina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDisciplina")
    private Integer idDisciplina;
    @Basic(optional = false)
    @Column(name = "sigla")
    private String sigla;
    @Basic(optional = false)
    @Column(name = "cargaHoraria")
    private int cargaHoraria;
    @Basic(optional = false)
    @Column(name = "semestre")
    private String semestre;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "regencia")
    private String regencia;
    @Basic(optional = false)
    @Column(name = "cargaHorariaParcial")
    private int cargaHorariaParcial;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;

    @JoinColumn(name = "idArea", referencedColumnName = "idArea")
    @ManyToOne(optional = false)
    private Area idArea;

    @JoinColumn(name = "idArea2", referencedColumnName = "idArea")
    @ManyToOne(optional = false)
    private Area idArea2;

    @JoinColumn(name = "idCurso", referencedColumnName = "idCurso")
    @ManyToOne(optional = false)
    private Curso idCurso;

    public Disciplina() {
    }

    public Disciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public Disciplina(Integer idDisciplina, String sigla, int cargaHoraria, String semestre, String nome, boolean status) {
        this.idDisciplina = idDisciplina;
        this.sigla = sigla;
        this.cargaHoraria = cargaHoraria;
        this.semestre = semestre;
        this.nome = nome;
        this.status = status;
    }

    public Disciplina(Integer idDisciplina, String sigla, int cargaHoraria, String semestre, String nome, String regencia, int cargaHorariaParcial, boolean status) {
        this.idDisciplina = idDisciplina;
        this.sigla = sigla;
        this.cargaHoraria = cargaHoraria;
        this.semestre = semestre;
        this.nome = nome;
        this.regencia = regencia;
        this.cargaHorariaParcial = cargaHorariaParcial;
        this.status = status;
    }

    public String getLog(Disciplina d) {
        int flag = 0;
        String log;

        log = "[DISCIPLINA] " + sigla + "(" + idDisciplina + "): <br>";

        if (d.status != status) {
            log = log + "Status alterado de " + d.status + " para " + status + ".<br>";
            flag = 1;
        }

        if (d.cargaHoraria != cargaHoraria) {
            log = log + "Carga Horária alterada de " + d.cargaHoraria + " para " + cargaHoraria + ".<br>";
            flag = 1;
        }

        if (d.cargaHorariaParcial != cargaHorariaParcial) {
            log = log + "Carga Horária parcial alterada de " + d.cargaHorariaParcial + " para " + cargaHorariaParcial + ".<br>";
            flag = 1;
        }

        if (!d.regencia.equals(regencia)) {
            log = log + "Regência alterada de " + d.regencia + " para " + regencia + ".<br>";
            flag = 1;
        }

        if (!d.semestre.equals(semestre)) {
            log = log + "Semestre alterado de " + d.semestre + " para " + semestre + ".<br>";
            flag = 1;
        }

        if (flag == 1) {
            return log;
        }

        return "";
    }

    public String getLog() {
        String log;

        log = "[DISCIPLINA] " + sigla + " criada: <br>";

        log = log + "Nome: " + nome + ".<br>";
        log = log + "Curso: " + idCurso.getDescricao() + ".<br>";
        log = log + "Semestre: " + semestre + ".<br>";
        log = log + "Status: " + status + ".<br>";
        log = log + "Carga Horária: " + cargaHoraria + ".<br>";
        log = log + "Carga Horária Parcial: " + cargaHorariaParcial + ".<br>";
        log = log + "Área de Docente 1: " + idArea.getDescricao() + ".<br>";
        if (idArea2 != null) {
            log = log + "Área de Docente 2: " + idArea2.getDescricao() + ".<br>";
        }else{
            log = log + "Área de Docente 2: " + ".<br>";
        }

        return log;
    }

    public Integer getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public int getCargaHorariaParcial() {
        return cargaHorariaParcial;
    }

    public void setCargaHorariaParcial(int cargaHorariaParcial) {
        this.cargaHorariaParcial = cargaHorariaParcial;
    }

    public String getRegencia() {
        return regencia;
    }

    public void setRegencia(String regencia) {
        this.regencia = regencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDisciplina != null ? idDisciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disciplina)) {
            return false;
        }
        Disciplina other = (Disciplina) object;
        if ((this.idDisciplina == null && other.idDisciplina != null) || (this.idDisciplina != null && !this.idDisciplina.equals(other.idDisciplina))) {
            return false;
        }
        return true;
    }

    public Area getIdArea2() {
        return idArea2;
    }

    public void setIdArea2(Area idArea2) {
        this.idArea2 = idArea2;
    }

    @Override
    public String toString() {
        return "model.Disciplina[ idDisciplina=" + idDisciplina + " ]";
    }

}
