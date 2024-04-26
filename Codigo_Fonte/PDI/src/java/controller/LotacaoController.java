/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LotacaoDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Lotacao;
import org.primefaces.PrimeFaces;
import util.Util;


@Named
@SessionScoped
public class LotacaoController implements Serializable {

    @Inject
    private LotacaoDAO lotacaoDAO;

    private Lotacao lotacao;
    private List<Lotacao> lotacoes;

    @PostConstruct
    public void fillLotacaoList() {
        lotacoes = lotacaoDAO.buscarTodas();
    }

    public LotacaoController() {
        openNew();
    }

    public void openNew() {
        lotacao = new Lotacao();
    }

    public void cadastrarLotacao() {
        try {     
            Lotacao tmp = lotacaoDAO.buscarPorDescricao(lotacao.getDescricao().trim());
            
            if(tmp != null) {
                Util.addMessageError("Erro ao cadastrar área de lotação. Descrição em uso.");
                return;
            }
            lotacaoDAO.create(lotacao);
            fillLotacaoList();
            Util.addMessageInformation("Área de Lotação Cadastrada");
        } catch (EJBException e) {
            Util.addMessageError("Erro ao cadastrar área de lotação. Verifique se a área de lotação já existe.");
        }

        PrimeFaces.current().executeScript("PF('createLotacaoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-lotacoes");
    }
    

    public void editarLotacao() {
        try {
            Lotacao tmp = lotacaoDAO.buscarPorDescricao(lotacao.getDescricao().trim());
            
            if(tmp != null && tmp.getIdLotacao() != lotacao.getIdLotacao()) {
                Util.addMessageError("Erro ao cadastrar área de lotação. Descrição em uso.");
                return;
            }
            
            lotacaoDAO.update(lotacao);
            Util.addMessageInformation("Área de lotação Editada");
        } catch (EJBException e) {
            Util.addMessageInformation("Erro ao editar área de lotação");
        }

        PrimeFaces.current().executeScript("PF('editLotacaoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-lotacoes");
    }

    public void removerLotacao() {
        try {
            lotacaoDAO.remove(lotacao);
            fillLotacaoList();
        } catch (EJBException e) {
            
            if(!lotacao.getServidorSimulacaoList().isEmpty()){
                Util.addMessageError("Não é possível remover esta área. Há algum servidor de simulação que utiliza esta área.");
            }
            
            if(!lotacao.getCursoList().isEmpty()){
                Util.addMessageError("Não é possível remover esta área. Há algum curso que utiliza esta área.");
            }
            
            return;          
        }
        Util.addMessageInformation("Área de lotação removida");

        PrimeFaces.current().executeScript("PF('editLotacaoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-lotacoes");
    }

    public Lotacao getLotacao() {
        return lotacao;
    }

    public void setLotacao(Lotacao lotacao) {
        this.lotacao = lotacao;
    }

    public List<Lotacao> getLotacoes() {
        return lotacoes;
    }

    public void setLotacoes(List<Lotacao> lotacoes) {
        this.lotacoes = lotacoes;
    }



}
