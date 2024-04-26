/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vinicius
 */
@Entity
@Table(name = "comentario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentario.findAll", query = "SELECT c FROM Comentario c"),
    @NamedQuery(name = "Comentario.findByIdComentario", query = "SELECT c FROM Comentario c WHERE c.idComentario = :idComentario"),
    @NamedQuery(name = "Comentario.findByData", query = "SELECT c FROM Comentario c WHERE c.data = :data")})
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idComentario")
    private Integer idComentario;
    @Basic(optional = false)
    @Lob
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @JoinColumn(name = "idReuniao", referencedColumnName = "idReuniao")
    @ManyToOne(optional = false)
    private Reuniao idReuniao;
    @JoinColumn(name = "prontuarioServidor", referencedColumnName = "prontuario")
    @ManyToOne(optional = false)
    private Servidor prontuarioServidor;

    public Comentario() {
    }

    public Comentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public Comentario(Integer idComentario, String descricao, Date data) {
        this.idComentario = idComentario;
        this.descricao = descricao;
        this.data = data;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Reuniao getIdReuniao() {
        return idReuniao;
    }

    public void setIdReuniao(Reuniao idReuniao) {
        this.idReuniao = idReuniao;
    }

    public Servidor getProntuarioServidor() {
        return prontuarioServidor;
    }

    public void setProntuarioServidor(Servidor prontuarioServidor) {
        this.prontuarioServidor = prontuarioServidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComentario != null ? idComentario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.idComentario == null && other.idComentario != null) || (this.idComentario != null && !this.idComentario.equals(other.idComentario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Comentario[ idComentario=" + idComentario + " ]";
    }
    
}
