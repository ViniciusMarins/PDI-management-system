/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Vinicius
 */
@Entity
@Table(name = "reuniao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reuniao.findAll", query = "SELECT r FROM Reuniao r"),
    @NamedQuery(name = "Reuniao.findByIdReuniao", query = "SELECT r FROM Reuniao r WHERE r.idReuniao = :idReuniao"),
    @NamedQuery(name = "Reuniao.findByTitulo", query = "SELECT r FROM Reuniao r WHERE r.titulo = :titulo"),
    @NamedQuery(name = "Reuniao.findByDataInicio", query = "SELECT r FROM Reuniao r WHERE r.dataInicio = :dataInicio"),
    @NamedQuery(name = "Reuniao.findByUltimaAlteracao", query = "SELECT r FROM Reuniao r WHERE r.ultimaAlteracao = :ultimaAlteracao")})
public class Reuniao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReuniao")
    private Integer idReuniao;

    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;

    @Basic(optional = false)
    @Lob
    @Column(name = "descricao")
    private String descricao;

    @Basic(optional = false)
    @Column(name = "dataInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;

    @Basic(optional = false)
    @Column(name = "dataTermino")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataTermino;

    @Basic(optional = false)
    @Column(name = "ultimaAlteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaAlteracao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReuniao")
    private List<Arquivo> arquivoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReuniao")
    private List<Comentario> comentarioList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reuniao", fetch = FetchType.LAZY)
    private Collection<Frequencia> frequenciaCollection;

    public Reuniao() {
    }

    public Reuniao(Integer idReuniao) {
        this.idReuniao = idReuniao;
    }

    public Reuniao(Integer idReuniao, String titulo, String descricao, Date dataInicio, Date dataTermino, Date ultimaAlteracao) {
        this.idReuniao = idReuniao;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getIdReuniao() {
        return idReuniao;
    }

    public void setIdReuniao(Integer idReuniao) {
        this.idReuniao = idReuniao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    @XmlTransient
    public List<Arquivo> getArquivoList() {
        return arquivoList;
    }

    public void setArquivoList(List<Arquivo> arquivoList) {
        this.arquivoList = arquivoList;
    }

    @XmlTransient
    public List<Comentario> getComentarioList() {
        return comentarioList;
    }

    public void setComentarioList(List<Comentario> comentarioList) {
        this.comentarioList = comentarioList;
    }

    @XmlTransient
    public Collection<Frequencia> getFrequenciaCollection() {
        return frequenciaCollection;
    }

    public void setFrequenciaCollection(Collection<Frequencia> frequenciaCollection) {
        this.frequenciaCollection = frequenciaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReuniao != null ? idReuniao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reuniao)) {
            return false;
        }
        Reuniao other = (Reuniao) object;
        if ((this.idReuniao == null && other.idReuniao != null) || (this.idReuniao != null && !this.idReuniao.equals(other.idReuniao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Reuniao[ idReuniao=" + idReuniao + " ]";
    }

}
