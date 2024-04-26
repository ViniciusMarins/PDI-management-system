package dao;

import java.util.List;
import javax.ejb.Stateless;
import model.Simulacao;


@Stateless
public class SimulacaoDAO extends AbstractDAO<Simulacao> {
    
    public List<Simulacao> buscarTodas() {
        return getEntityManager()
                .createNamedQuery("Simulacao.findAll", Simulacao.class)
                .getResultList();
    }
    
    public Simulacao buscarPorId(int id) {
        return getEntityManager()
                .createNamedQuery("Simulacao.findByIdSimulacao", Simulacao.class)
                .setParameter("idSimulacao", id)
                .getSingleResult();
    }
    
    public Simulacao buscarPorTitulo(String titulo) {
       try{
            return getEntityManager()
                .createNamedQuery("Simulacao.findByTitulo", Simulacao.class)
                .setParameter("titulo", titulo)
                .getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
}
