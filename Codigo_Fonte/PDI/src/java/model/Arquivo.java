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
import javax.persistence.Lob;
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
@Table(name = "arquivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arquivo.findAll", query = "SELECT a FROM Arquivo a"),
    @NamedQuery(name = "Arquivo.findByIdArquivo", query = "SELECT a FROM Arquivo a WHERE a.idArquivo = :idArquivo"),
    @NamedQuery(name = "Arquivo.findByNomeUpload", query = "SELECT a FROM Arquivo a WHERE a.nomeUpload = :nome_upload"),
    @NamedQuery(name = "Arquivo.findByDescricao", query = "SELECT a FROM Arquivo a WHERE a.descricao = :descricao")})
public class Arquivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArquivo")
    private Integer idArquivo;
    
    @Basic(optional = false)
    @Column(name = "nome_pasta")
    private String nomePasta;
    
    @Basic(optional = false)
    @Column(name = "nome_upload")
    private String nomeUpload;
    
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    
    @JoinColumn(name = "idReuniao", referencedColumnName = "idReuniao")
    @ManyToOne(optional = false)
    private Reuniao idReuniao;

    public Arquivo() {
    }

    public Arquivo(Integer idArquivo) {
        this.idArquivo = idArquivo;
    }

    public Arquivo(Integer idArquivo, String nomeUpload,String nomePasta, String descricao) {
        this.idArquivo = idArquivo;
        this.nomeUpload = nomeUpload;
        this.nomePasta = nomePasta;
        this.descricao = descricao;
    }

    public Integer getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(Integer idArquivo) {
        this.idArquivo = idArquivo;
    }

    public String getNomePasta() {
        return nomePasta;
    }
    
    public void setNomePasta(String nomePasta) {
        this.nomePasta = nomePasta;
    }

    public String getNomeUpload() {
        return nomeUpload;
    }

    public void setNomeUpload(String nomeUpload) {
        this.nomeUpload = nomeUpload;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Reuniao getIdReuniao() {
        return idReuniao;
    }

    public void setIdReuniao(Reuniao idReuniao) {
        this.idReuniao = idReuniao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArquivo != null ? idArquivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arquivo)) {
            return false;
        }
        Arquivo other = (Arquivo) object;
        if ((this.idArquivo == null && other.idArquivo != null) || (this.idArquivo != null && !this.idArquivo.equals(other.idArquivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Arquivo[ idArquivo=" + idArquivo + " ]";
    }

}
