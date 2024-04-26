/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
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
@Table(name = "dados_simulacao")
@XmlRootElement
@NamedQueries({})
public class DadosSimulacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDadosSimulacao")
    private Integer idDadosSimulacao;
    
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "area")
    private String area;
    
    @Basic(optional = false)
    @Column(name = "num_profs")
    private int numProfs;

    @Basic(optional = false)
    @Column(name = "par_parcial")
    private int parParcial;
    
    @Basic(optional = false)
    @Column(name = "par_integrall")
    private int parIntegral;
    
    @Basic(optional = false)
    @Column(name = "par_principal")
    private int parPrincipal;
    
    @Basic(optional = false)
    @Column(name = "impar_parcial")
    private int imparParcial;
    
    @Basic(optional = false)
    @Column(name = "impar_integral")
    private int imparIntegral;
    
    @Basic(optional = false)
    @Column(name = "impar_principal")
    private int imparPrincipal;
        
    @JoinColumn(name = "idSimulacao", referencedColumnName = "idSimulacao")
    @ManyToOne(optional = false)
    private Simulacao idSimulacao;

    public DadosSimulacao() {
    }

    public DadosSimulacao(String area, int numProfs, int parParcial, int parIntegral, int parPrincipal, int imparParcial, int imparIntegral, int imparPrincipal) {
        this.area = area;
        this.numProfs = numProfs;
        this.parParcial = parParcial;
        this.parIntegral = parIntegral;
        this.parPrincipal = parPrincipal;
        this.imparParcial = imparParcial;
        this.imparIntegral = imparIntegral;
        this.imparPrincipal = imparPrincipal;
    }
    
    public float getMediaImpar() {
        if(numProfs==0)
            return 0;
        
        return ((float)getTotalImpar()/(float)numProfs);
    }
    
    public float getMediaPar() {
        if(numProfs==0)
            return 0;
        
        return ((float) getTotalPar()/ (float )numProfs);
    }
    
    public float getMediaAnual() {
        if(numProfs==0)
            return 0;
        
        return (getMediaImpar() + getMediaPar())/2;
    }
    
    public int getTotalPar() {
        return parIntegral+parParcial+parPrincipal;
    }
    
    public int getTotalImpar() {
        return imparIntegral+imparParcial+imparPrincipal;
    }
    
    public DadosSimulacao(String area) {
        this.area = area;
    }

    public Integer getIdDadosSimulacao() {
        return idDadosSimulacao;
    }

    public void setIdDadosSimulacao(Integer idDadosSimulacao) {
        this.idDadosSimulacao = idDadosSimulacao;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getNumProfs() {
        return numProfs;
    }

    public void setNumProfs(int numProfs) {
        this.numProfs = numProfs;
    }

    public int getParParcial() {
        return parParcial;
    }

    public void setParParcial(int parParcial) {
        this.parParcial = parParcial;
    }

    public int getParIntegral() {
        return parIntegral;
    }

    public void setParIntegral(int parIntegral) {
        this.parIntegral = parIntegral;
    }

    public int getParPrincipal() {
        return parPrincipal;
    }

    public void setParPrincipal(int parPrincipal) {
        this.parPrincipal = parPrincipal;
    }

    public int getImparParcial() {
        return imparParcial;
    }

    public void setImparParcial(int imparParcial) {
        this.imparParcial = imparParcial;
    }

    public int getImparIntegral() {
        return imparIntegral;
    }

    public void setImparIntegral(int imparIntegral) {
        this.imparIntegral = imparIntegral;
    }

    public int getImparPrincipal() {
        return imparPrincipal;
    }

    public void setImparPrincipal(int imparPrincipal) {
        this.imparPrincipal = imparPrincipal;
    }

    public Simulacao getIdSimulacao() {
        return idSimulacao;
    }

    public void setIdSimulacao(Simulacao idSimulacao) {
        this.idSimulacao = idSimulacao;
    }

    public int getComparar(List<DadosSimulacao> s) {
        for(DadosSimulacao d: s) {
            if(d.getArea().equals(area)) {
                if(
                    imparIntegral != d.getImparIntegral() ||
                    imparPrincipal != d.getImparPrincipal() ||
                    imparParcial != d.getImparParcial() ||
                    parIntegral != d.getParIntegral() || 
                    parPrincipal != d.getParPrincipal() || 
                    parParcial != d.getParParcial() || 
                    numProfs != d.getNumProfs()|| 
                    imparIntegral != d.getImparIntegral()
                ) {
                    return 0;
                } else {
                    return 1;
                }
            }    
        }
        
        return 0;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idDadosSimulacao);
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
        final DadosSimulacao other = (DadosSimulacao) obj;
        return Objects.equals(this.idDadosSimulacao, other.idDadosSimulacao);
    }

    
  
}
