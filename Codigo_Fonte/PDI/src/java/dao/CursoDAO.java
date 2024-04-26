package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import javax.ejb.Stateless;
import model.Curso;
import model.Curso;

/**
 *
 * @author linkf
 */
@Stateless
public class CursoDAO extends AbstractDAO<Curso> {

    public List<Curso> buscarTodos() {
        return getEntityManager()
                .createNamedQuery("Curso.findAll", Curso.class)
                .getResultList();
    }

    public Curso buscarPorIdCurso(int idCurso) {
        return getEntityManager()
                .createNamedQuery("Curso.findByIdCurso", Curso.class)
                .setParameter("idCurso", idCurso)
                .getSingleResult();
    }

    public Curso buscarPorSigla(String sigla) {
        try {
            return getEntityManager()
                .createNamedQuery("Curso.findBySigla", Curso.class)
                .setParameter("sigla", sigla)
                .getSingleResult();    
        } catch (Exception e) {
            return null;
        }
        
    }
}
