package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.CursoDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Curso;
import org.primefaces.PrimeFaces;
import util.Util;

/**
 *
 * @author linkf
 */
@Named
@SessionScoped
public class CursoController implements Serializable {

    @Inject
    private CursoDAO cursoDAO;
    
    @Inject
    private LogController logController;

    private Curso curso;
    private List<Curso> cursos;

    @PostConstruct
    public void fillCursoList() {
        cursos = cursoDAO.buscarTodos();
    }

    public CursoController() {
        openNew();
    }

    public void openNew() {
        curso = new Curso();
    }

    public void cadastrarCurso() {
        try {
            Curso tmp = cursoDAO.buscarPorSigla(curso.getSigla().trim());

            if (tmp != null) {
                Util.addMessageError("Erro ao cadastrar curso. Sigla em uso.");
                return;
            }

            cursoDAO.create(curso);
            
            logController.addLog(curso.getLog());
            
            fillCursoList();
            Util.addMessageInformation("Curso Cadastrado");
        } catch (EJBException e) {
            Util.addMessageError("Erro ao cadastrar curso. Verifique se o curso já existe.");
        }

        PrimeFaces.current().executeScript("PF('createCursoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-cursos");
    }

    public void editarCurso() {
        try {
            Curso tmp = cursoDAO.buscarPorSigla(curso.getSigla().trim());
            
            if (tmp != null && tmp.getIdCurso() != curso.getIdCurso()) {
                Util.addMessageError("Erro ao editar curso. Sigla em uso.");
                return;
            }

            cursoDAO.update(curso);
            
            logController.addLog(curso.getLog(tmp));

            fillCursoList();
            Util.addMessageInformation("Curso Editado");
        } catch (EJBException e) {
            Util.addMessageInformation("Erro ao editar curso");
        }

        PrimeFaces.current().executeScript("PF('editCursoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-cursos");
    }

    public void removerCurso() {
        try {
            cursoDAO.remove(curso);
            fillCursoList();
            
            String log;
            
            log = "[CURSO] " + curso.getDescricao() + " (" + curso.getIdCurso() + ") excluído.<br>";
            
            logController.addLog(log);
        } catch (EJBException e) {
            Util.addMessageError("Não é possível remover este curso");
            return;
        }
        Util.addMessageInformation("Curso Removido");

        PrimeFaces.current().executeScript("PF('editCursoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-cursos");
    }

    public void alterarCurso(Curso c) {
        try {
            Curso tmp = cursoDAO.buscarPorSigla(c.getSigla().trim());
            
            cursoDAO.update(c);
            
            logController.addLog(c.getLog(tmp));
            Util.addMessageInformation("Curso Editado");
        } catch (Exception e) {
            Util.addMessageError("Erro ao editar curso");
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-cursos");
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

}
