/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CursoDAO;
import dao.DisciplinaDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Curso;
import model.Disciplina;
import org.primefaces.PrimeFaces;
import util.Util;

/**
 *
 * @author vitor
 */
@Named
@SessionScoped
public class DisciplinaController implements Serializable {

    @Inject
    private DisciplinaDAO disciplinaDAO;
    @Inject
    private CursoDAO cursoDAO;
    
    @Inject
    private LogController logController;

    private Disciplina disciplina;
    private List<Disciplina> disciplinas;

    private int cargaAnterior;

    @PostConstruct
    public void fillDisciplinaList() {
        this.disciplinas = disciplinaDAO.buscarTodos();
    }

    public DisciplinaController() {
        openNew();
    }

    public void openNew() {
        this.disciplina = new Disciplina();
        this.disciplina.setIdCurso(new Curso());
    }

    public void cadastrarDisciplina() {
        try {
            Disciplina tmp = disciplinaDAO.buscarPorSigla(this.disciplina.getSigla().trim());

            if (tmp != null) {
                Curso curso = cursoDAO.buscarPorIdCurso(tmp.getIdCurso().getIdCurso());

                if (curso.equals(this.disciplina.getIdCurso())) {
                    Util.addMessageError("Erro ao cadastrar disciplina. Sigla em uso.");
                    return;
                }
            }

            Curso curso = cursoDAO.buscarPorIdCurso(disciplina.getIdCurso().getIdCurso());

            this.disciplina.setIdCurso(curso);
            if(this.disciplina.getRegencia().equals("PARCIAL") && this.disciplina.getCargaHorariaParcial() < 1){
                Util.addMessageWarning("Erro ao cadastrar disciplina. A Carga Horária Parcial deve ser maior que 0");
                return;
            }
            disciplinaDAO.create(this.disciplina);
            
            logController.addLog(disciplina.getLog());
            
            this.fillDisciplinaList();
            Util.addMessageInformation("Disciplina Cadastrada");
        } catch (EJBException e) {
            Util.addMessageError("Erro ao cadastrar disciplina. Verifique se a disciplina já existe.");
        }

        PrimeFaces.current().executeScript("PF('createDisciplinaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-disciplinas");
        this.fillDisciplinaList();
    }

    public void editarDisciplina() {
        try {
            Disciplina tmp = disciplinaDAO.buscarPorSigla(this.disciplina.getSigla().trim());

            if (tmp != null && tmp.getIdDisciplina() != this.disciplina.getIdDisciplina()) {
                Util.addMessageError("Erro ao editar disciplina. Sigla em uso.");
                return;
            }

            if (!this.disciplina.getRegencia().equals("PARCIAL")) {
                this.disciplina.setCargaHorariaParcial(0);
            }
            
            if(this.disciplina.getRegencia().equals("PARCIAL") && this.disciplina.getCargaHorariaParcial() < 1){
                Util.addMessageWarning("Erro ao editar disciplina. A Carga Horária Parcial deve ser maior que 0");
                return;
            }

            disciplinaDAO.update(this.disciplina);
            
            logController.addLog(disciplina.getLog(tmp));
            
            Util.addMessageInformation("Disciplina Editada");
        } catch (EJBException e) {
            Util.addMessageInformation("Erro ao editar disciplina");
        }

        PrimeFaces.current().executeScript("PF('editDisciplinaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-disciplinas");
        this.fillDisciplinaList();
    }

    public void excluirDisciplina() {
        try {
            disciplinaDAO.remove(this.disciplina);
            this.fillDisciplinaList();
            
            String log;
            
            log = "[DISCIPLINA] " + disciplina.getNome() + " (" + disciplina.getIdDisciplina() + ") excluída.<br>";
            
            logController.addLog(log);

        } catch (EJBException e) {
            Util.addMessageError("Não é possível remover esta disciplina");
            return;
        }
        Util.addMessageInformation("Disciplina Removida");

        PrimeFaces.current().executeScript("PF('editDisciplinaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-disciplinas");
    }

    public void visualizarRegencia(String op) {
        System.out.println("regencia = " + this.disciplina.getRegencia());

        if (op.equals("EDIT")) {
            if (this.disciplina.getCargaHorariaParcial() > 0) {
                this.cargaAnterior = this.disciplina.getCargaHorariaParcial();
            }

            if (!this.disciplina.getRegencia().equals("PARCIAL")) {
                this.disciplina.setCargaHorariaParcial(0);
            } else {
                this.disciplina.setCargaHorariaParcial(this.cargaAnterior);
            }
        }

    }

    public void alterarDisciplina(Disciplina d) {
        try {
            Disciplina tmp = disciplinaDAO.buscarPorSigla(d.getSigla().trim());
            
            disciplinaDAO.update(d);
            
            logController.addLog(d.getLog(tmp));
            Util.addMessageInformation("Disciplina Editada");
        } catch (Exception e) {
            Util.addMessageError("Erro ao editar disciplina");
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-disciplinas");
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

}
