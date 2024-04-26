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
@Table(name = "servidor_simulacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServidorSimulacao.findAll", query = "SELECT s FROM ServidorSimulacao s"),
    @NamedQuery(name = "ServidorSimulacao.findById", query = "SELECT s FROM ServidorSimulacao s WHERE s.idProfessor = :idProfessor"),
    @NamedQuery(name = "ServidorSimulacao.findByNome", query = "SELECT s FROM ServidorSimulacao s WHERE s.nome = :nome"),
    @NamedQuery(name = "ServidorSimulacao.findByStatus", query = "SELECT s FROM ServidorSimulacao s WHERE s.status = :status"),
    @NamedQuery(name = "ServidorSimulacao.findByTipo", query = "SELECT s FROM ServidorSimulacao s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "ServidorSimulacao.buscarUltimoId", query = "SELECT s FROM ServidorSimulacao s ORDER BY s.idProfessor DESC")})
public class ServidorSimulacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProfessor")
    private Integer idProfessor;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "idArea", referencedColumnName = "idArea")
    @ManyToOne(optional = false)
    private Area idArea;
    @JoinColumn(name = "idLotacao", referencedColumnName = "idLotacao")
    @ManyToOne
    private Lotacao idLotacao;

    public ServidorSimulacao() {
    }

    public ServidorSimulacao(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public ServidorSimulacao(Integer idProfessor, String nome, boolean status, String tipo) {
        this.idProfessor = idProfessor;
        this.nome = nome;
        this.status = status;
        this.tipo = tipo;
    }
    
    public String getLog(ServidorSimulacao s) {
        String log; 
        
        if(tipo.equals("PROFESSOR")) {
            log = "[SERVIDOR - "+ idArea.getDescricao() + "] " + nome + " (" + idProfessor + "): <br>";
        } else {
            log = "[SERVIDOR] " + nome + " (" + idProfessor + "): <br>";
        }
        
        int flag = 0;
        
        if(!s.tipo.equals(tipo)) {
            log = log + "Tipo alterado de " + s.tipo + " para " + tipo + ".<br>";
            flag = 1;
        }
        
        if(s.status != status) {
            log = log + "Status alterado de " + s.status + " para " + status + ".<br>";
            flag = 1;
        }
        
        if(!s.idLotacao.getDescricao().equals(idLotacao.getDescricao())) {
            log = log + "Área de Lotação alterada de " + s.getIdLotacao().getDescricao() + " para " + idLotacao.getDescricao() + ".<br>";
            flag = 1;
        }
        
        if(tipo.equals("TAE")) {
            return log;
        }
        
        if((s.getIdArea() == null && idArea != null) || (!s.idArea.getDescricao().equals(idArea.getDescricao()))) {
            if(s.getIdArea() == null) {
                log = log + "Área de concurso alterada para " + idArea.getDescricao() + ".<br>";
            } else {
                log = log + "Área de concurso alterada de " + s.getIdArea().getDescricao() + " para " + idArea.getDescricao() + ".<br>";

            }
            
            flag = 1;
        }
        
        if(flag==1) {
            return log;
        }
        
        return "";
    }
    
    public String getLog() {
        String log;
        
        if(tipo.equals("PROFESSOR")) {
            log = "[SERVIDOR - "+ idArea.getDescricao() + "] " + nome + " (" + idProfessor + ") criado: <br>";
        } else {
            log = "[SERVIDOR] " + nome + " (" + idProfessor + "): <br>";
        }
        
        log = log + "Status: " + status + ".<br>";
        log = log + "Área de Lotação: " + idLotacao.getDescricao() + ".<br>";
        
        if(tipo.equals("PROFESSOR")) {
            log = log + "Área de concurso: " + idArea.getDescricao() + ".<br>";
        }
        
        return log;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public Lotacao getIdLotacao() {
        return idLotacao;
    }

    public void setIdLotacao(Lotacao idLotacao) {
        this.idLotacao = idLotacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfessor != null ? idProfessor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServidorSimulacao)) {
            return false;
        }
        ServidorSimulacao other = (ServidorSimulacao) object;
        if ((this.idProfessor == null && other.idProfessor != null) || (this.idProfessor != null && !this.idProfessor.equals(other.idProfessor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ServidorSimulacao[ idProfessor=" + idProfessor + " ]";
    }
    
}
