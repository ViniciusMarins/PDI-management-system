/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vitor
 */
@Entity
@Table(name = "frequencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Frequencia.findAll", query = "SELECT f FROM Frequencia f"),
    @NamedQuery(name = "Frequencia.findByReuniaoId", query = "SELECT f FROM Frequencia f WHERE f.frequenciaPK.reuniaoId = :reuniaoId"),
    @NamedQuery(name = "Frequencia.findByServidorProntuario", query = "SELECT f FROM Frequencia f WHERE f.frequenciaPK.servidorProntuario = :servidorProntuario"),
    @NamedQuery(name = "Frequencia.findByFrequencia", query = "SELECT f FROM Frequencia f WHERE f.frequencia = :frequencia")})
public class Frequencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FrequenciaPK frequenciaPK;

    @Basic(optional = false)
    @NotNull
    @Column(name = "frequencia", nullable = false)
    private boolean frequencia;

    @JoinColumn(name = "reuniao_idReuniao", referencedColumnName = "idReuniao", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reuniao reuniao;

    @JoinColumn(name = "servidor_prontuario", referencedColumnName = "prontuario", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Servidor servidor;
    
    @Column(name = "descricao")
    private String descricao;

    public Frequencia() {
    }

    public Frequencia(FrequenciaPK frequenciaPK) {
        this.frequenciaPK = frequenciaPK;
    }

    public Frequencia(FrequenciaPK frequenciaPK, boolean frequencia) {
        this.frequenciaPK = frequenciaPK;
        this.frequencia = frequencia;
    }

    public Frequencia(Reuniao reuniao, Servidor servidor) {
        this.reuniao = reuniao;
        this.servidor = servidor;
    }

    public FrequenciaPK getFrequenciaPK() {
        return frequenciaPK;
    }

    public void setFrequenciaPK(FrequenciaPK frequenciaPK) {
        this.frequenciaPK = frequenciaPK;
    }

    public boolean isFrequencia() {
        return frequencia;
    }

    public void setFrequencia(boolean frequencia) {
        this.frequencia = frequencia;
    }

    public Reuniao getReuniao() {
        return reuniao;
    }

    public void setReuniao(Reuniao reuniao) {
        this.reuniao = reuniao;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (frequenciaPK != null ? frequenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frequencia)) {
            return false;
        }
        Frequencia other = (Frequencia) object;
        if ((this.frequenciaPK == null && other.frequenciaPK != null) || (this.frequenciaPK != null && !this.frequenciaPK.equals(other.frequenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Frequencia[ frequenciaPK=" + frequenciaPK + " ]";
    }

}
