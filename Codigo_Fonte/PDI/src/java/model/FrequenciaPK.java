/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vitor
 */
@Embeddable
public class FrequenciaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "reuniao_idReuniao", nullable = false)
    private int reuniaoId;
    @Basic(optional = false)
    @Column(name = "servidor_prontuario", nullable = false, length = 9)
    private String servidorProntuario;

    public FrequenciaPK() {
    }

    public FrequenciaPK(int reuniaoId, String servidorProntuario) {
        this.reuniaoId = reuniaoId;
        this.servidorProntuario = servidorProntuario;
    }

    public int getReuniaoId() {
        return reuniaoId;
    }

    public void setReuniaoId(int reuniaoId) {
        this.reuniaoId = reuniaoId;
    }

    public String getServidorProntuario() {
        return servidorProntuario;
    }

    public void setServidorProntuario(String servidorProntuario) {
        this.servidorProntuario = servidorProntuario;
    }

   @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) reuniaoId;
        hash += (servidorProntuario != null ? servidorProntuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrequenciaPK)) {
            return false;
        }
        FrequenciaPK other = (FrequenciaPK) object;
        if (this.reuniaoId != other.reuniaoId) {
            return false;
        }
        if ((this.servidorProntuario == null && other.servidorProntuario != null) || (this.servidorProntuario != null && !this.servidorProntuario.equals(other.servidorProntuario))) {
            return false;
        }
        return true;
    }

}
