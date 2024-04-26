/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import javax.ejb.Stateless;
import model.Disciplina;

/**
 *
 * @author vitor
 */
@Stateless
public class DisciplinaDAO extends AbstractDAO<Disciplina> {
    
    public List<Disciplina> buscarTodos() {
        return getEntityManager()
                .createNamedQuery("Disciplina.findAll", Disciplina.class)
                .getResultList();
    }

    public Disciplina buscarPorIdDisciplina(int idDiscplina) {
        try{
        return getEntityManager()
                .createNamedQuery("Disciplina.findByIdDisciplina", Disciplina.class)
                .setParameter("idDisciplina", idDiscplina)
                .getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

    public Disciplina buscarPorSigla(String sigla) {
        try {
            return getEntityManager()
                .createNamedQuery("Disciplina.findBySigla", Disciplina.class)
                .setParameter("sigla", sigla)
                .getSingleResult();    
        } catch (Exception e) {
            return null;
        }
        
    }
}
