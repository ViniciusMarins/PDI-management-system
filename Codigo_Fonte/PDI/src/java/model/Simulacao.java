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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@Table(name = "simulacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Simulacao.findAll", query = "SELECT s FROM Simulacao s"),
    @NamedQuery(name = "Simulacao.findByIdSimulacao", query = "SELECT s FROM Simulacao s WHERE s.idSimulacao = :idSimulacao"),
    @NamedQuery(name = "Simulacao.findByTitulo", query = "SELECT s FROM Simulacao s WHERE s.titulo = :titulo")
})
        

public class Simulacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSimulacao")
    private Integer idSimulacao;
    
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    
    @Basic(optional = false)
    @Column(name = "logs", columnDefinition = "TEXT")
    private String logs;
    
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "idSimulacao")
    @OrderBy("area ASC")
    private List<DadosSimulacao> dadosSimulacaoList;
    
    public Simulacao() {
    }
    
    public Integer getIdSimulacao() {
        return idSimulacao;
    }

    public void setIdSimulacao(Integer idSimulacao) {
        this.idSimulacao = idSimulacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<DadosSimulacao> getDadosSimulacaoList() {
        return dadosSimulacaoList;
    }

    public void setDadosSimulacaoList(List<DadosSimulacao> dadosSimulacaoList) {
        this.dadosSimulacaoList = dadosSimulacaoList;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.idSimulacao);
        return hash;
    }

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
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
        final Simulacao other = (Simulacao) obj;
        return Objects.equals(this.idSimulacao, other.idSimulacao);
    }
    
    
}
