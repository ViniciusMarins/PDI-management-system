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
@Table(name = "sinalizador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sinalizador.findAll", query = "SELECT s FROM Sinalizador s order by s.sinalizador"),
    @NamedQuery(name = "Sinalizador.findBySinalizador", query = "SELECT s FROM Sinalizador s WHERE s.sinalizador = :sinalizador")})
public class Sinalizador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSinalizador")
    private Integer idSinalizador;
    
    @Basic(optional = false)
    @Column(name = "valor")
    private Integer valor;
    
    @Basic(optional = false)
    @Column(name = "sinalizador")
    private String sinalizador;

 
    public Sinalizador() {
    }

    public Sinalizador(Integer idSinalizador, Integer valor, String sinalizador) {
        this.idSinalizador = idSinalizador;
        this.valor = valor;
        this.sinalizador = sinalizador;
    }
    
    

    public Integer getIdSinalizador() {
        return idSinalizador;
    }

    public void setIdSinalizador(Integer idSinalizador) {
        this.idSinalizador = idSinalizador;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getSinalizador() {
        return sinalizador;
    }

    public void setSinalizador(String sinalizador) {
        this.sinalizador = sinalizador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.idSinalizador);
        return hash;
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
        final Sinalizador other = (Sinalizador) obj;
        return Objects.equals(this.idSinalizador, other.idSinalizador);
    }    
    
}
