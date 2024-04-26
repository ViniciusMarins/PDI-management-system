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
@Table(name = "area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a order by a.descricao"),
    @NamedQuery(name = "Area.findByIdArea", query = "SELECT a FROM Area a WHERE a.idArea = :idArea"),
    @NamedQuery(name = "Area.findByDescricao", query = "SELECT a FROM Area a WHERE a.descricao = :descricao")})
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArea")
    private Integer idArea;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "idArea")
    private List<ServidorSimulacao> servidorSimulacaoList;
    
    @OneToMany(mappedBy = "idArea")
    private List<Disciplina> disciplinaList;
    
    @OneToMany(mappedBy = "idArea2")
    private List<Disciplina> disciplina2List;

    
    public Area() {
    }

    public Area(Integer idArea) {
        this.idArea = idArea;
    }

    public Area(Integer idArea, String descricao) {
        this.idArea = idArea;
        this.descricao = descricao;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
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
    public List<Disciplina> getDisciplinaList() {
        return disciplinaList;
    }

    public void setDisciplinaList(List<Disciplina> disciplinaList) {
        this.disciplinaList = disciplinaList;
    }

    public List<Disciplina> getDisciplina2List() {
        return disciplina2List;
    }

    public void setDisciplina2List(List<Disciplina> disciplina2List) {
        this.disciplina2List = disciplina2List;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArea != null ? idArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.idArea == null && other.idArea != null) || (this.idArea != null && !this.idArea.equals(other.idArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Area[ idArea=" + idArea + " ]";
    }
    
}
