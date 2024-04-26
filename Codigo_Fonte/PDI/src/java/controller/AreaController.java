/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AreaDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Area;
import org.primefaces.PrimeFaces;
import util.Util;


@Named
@SessionScoped
public class AreaController implements Serializable {

    @Inject
    private AreaDAO areaDAO;

    private Area area;
    private List<Area> areas;

    @PostConstruct
    public void fillAreaList() {
        areas = areaDAO.buscarTodas();
    }

    public AreaController() {
        openNew();
    }

    public void openNew() {
        area = new Area();
    }

    public void cadastrarArea() {
        try {
            Area tmp = areaDAO.buscarPorDescricao(area.getDescricao().trim());
            
            if(tmp != null) {
                Util.addMessageError("Erro ao cadastrar área de concurso. Descrição em uso.");
                return;
            }
            
            areaDAO.create(area);
            fillAreaList();
            Util.addMessageInformation("Área Cadastrado");
        } catch (EJBException e) {
            Util.addMessageError("Erro ao cadastrar área. Verifique se a área já existe.");
        }

        PrimeFaces.current().executeScript("PF('createAreaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-areas");
    }
    

    public void editarArea() {
        try {     
            Area tmp = areaDAO.buscarPorDescricao(area.getDescricao().trim());
            
            if(tmp != null && tmp.getIdArea() !=  area.getIdArea()) {
                Util.addMessageError("Erro ao cadastrar área de concurso. Descrição em uso.");
                return;
            }
            
            areaDAO.update(area);
            Util.addMessageInformation("Área Editada");
        } catch (EJBException e) {
            Util.addMessageInformation("Erro ao editar área");
        }

        PrimeFaces.current().executeScript("PF('editAreaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-areas");
    }

    public void removerArea() {
        try {
            areaDAO.remove(area);
            fillAreaList();
        } catch (EJBException e) {
            
            if(!area.getServidorSimulacaoList().isEmpty()){
                Util.addMessageError("Não é possível remover esta área. Há algum servidor de simulação que utiliza esta área.");
            }
            
            if(!area.getDisciplinaList().isEmpty() || !area.getDisciplina2List().isEmpty()){
                Util.addMessageError("Não é possível remover esta área. Há alguma disciplina que utiliza esta área.");
            }
            
            return;
        }
        Util.addMessageInformation("Área Removida");

        PrimeFaces.current().executeScript("PF('editAreaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-areas");
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

}
