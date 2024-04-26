/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LotacaoDAO;
import dao.ServidorDAO;
import dao.ServidorSimulacaoDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Area;
import model.Lotacao;
import model.ServidorSimulacao;
import org.primefaces.PrimeFaces;
import util.Util;

@Named
@SessionScoped
public class ServidorSimulacaoController implements Serializable {

    @Inject
    private ServidorSimulacaoDAO servidorSimulacaoDAO;
    
    @Inject
    private LogController logController;

    private ServidorSimulacao servidor;
    private List<ServidorSimulacao> servidoresSimulacao;

    private Area area;
    private Lotacao lotacao;

    private Area areaAnterior;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Lotacao getLotacao() {
        return lotacao;
    }

    public void setLotacao(Lotacao lotacao) {
        this.lotacao = lotacao;
    }

    @PostConstruct
    public void fillServidoresSimulacaoList() {
        servidoresSimulacao = servidorSimulacaoDAO.buscarTodas();
        openNew();
        servidor.setNome("Servidor " + (servidorSimulacaoDAO.buscarUltimoId() + 1));

    }

    public ServidorSimulacaoController() {
        openNew();
    }

    public void openNew() {
        servidor = new ServidorSimulacao();
    }

    public void cadastrarServidor() {
        try {
            servidorSimulacaoDAO.create(servidor);
            
            logController.addLog(servidor.getLog());
            
            fillServidoresSimulacaoList();
            Util.addMessageInformation("Servidor de Simulação Cadastrada");
        } catch (EJBException e) {
            Util.addMessageError("Erro ao cadastrar área de lotação. Verifique se o servidor já existe.");
        }

        PrimeFaces.current().executeScript("PF('createServidorDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-servidores");
    }

    public void editarServidor() {
        try {
            ServidorSimulacao temp = servidorSimulacaoDAO.buscarPorId(servidor.getIdProfessor());
            
            servidorSimulacaoDAO.update(servidor);
            
            logController.addLog(servidor.getLog(temp));
            
            fillServidoresSimulacaoList();
            Util.addMessageInformation("Servidor de Simulação Editado");
        } catch (EJBException e) {
            Util.addMessageInformation("Erro ao editar servidor");
        }

        PrimeFaces.current().executeScript("PF('editServidorDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-servidores");
    }

    public void removerServidor() {
        try {
            servidorSimulacaoDAO.remove(servidor);
            
            String log;
        
            if (servidor.getTipo().equals("PROFESSOR")) {
                log = "[SERVIDOR - " + servidor.getIdArea().getDescricao() + "] " + servidor.getNome() + " (" + servidor.getIdProfessor() + ") excluído.<br>";
            } else {
                log = "[SERVIDOR] " + servidor.getNome() + " (" + servidor.getIdProfessor() + ") excluído.<br>";
            }
            logController.addLog(log);
            
            fillServidoresSimulacaoList();
        } catch (EJBException e) {
            Util.addMessageError("Não é possível remover este Servidor de Simulação");
            return;
        }
        Util.addMessageInformation("Servidor removido");

        PrimeFaces.current().executeScript("PF('editServidorDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-servidores");
    }

    
    // feito pelo vitor augusto
    public void alterarServidor(ServidorSimulacao s) {
        try {
            ServidorSimulacao temp = servidorSimulacaoDAO.buscarPorId(s.getIdProfessor());
            
            servidorSimulacaoDAO.update(s);
            
            logController.addLog(s.getLog(temp));
            Util.addMessageInformation("Servidor de simulação Editado");
        } catch (Exception e) {
            Util.addMessageError("Erro ao editar servidor de simulação");
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-servidores");
    }

    public void voltarArea() {

        //System.out.println("tipo = " + this.servidor.getTipo());
        if (this.servidor.getIdArea() != null) {
            this.areaAnterior = this.servidor.getIdArea();
        }
        if (!this.servidor.getTipo().equals("PROFESSOR")) {
            this.servidor.setIdArea(null);
        } else if (this.servidor.getTipo().equals("PROFESSOR")) {
            this.servidor.setIdArea(this.areaAnterior);
        }
    }

    public ServidorSimulacao getServidor() {
        return servidor;
    }

    public void setServidor(ServidorSimulacao servidor) {
        this.servidor = servidor;
    }

    public List<ServidorSimulacao> getServidoresSimulacao() {
        return servidoresSimulacao;
    }

    public void setServidoresSimulacao(List<ServidorSimulacao> servidoresSimulacao) {
        this.servidoresSimulacao = servidoresSimulacao;
    }
}
