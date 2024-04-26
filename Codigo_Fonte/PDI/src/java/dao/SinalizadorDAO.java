package dao;

import java.util.List;
import javax.ejb.Stateless;
import model.Sinalizador;


@Stateless
public class SinalizadorDAO extends AbstractDAO<Sinalizador> {

    public List<Sinalizador> buscarTodas() {
        return getEntityManager()
                .createNamedQuery("Sinalizador.findAll", Sinalizador.class)
                .getResultList();
    }
    
    public Sinalizador findBySinalizador(String sinalizador) {
        try{
            return getEntityManager()
                .createNamedQuery("Sinalizador.findBySinalizador", Sinalizador.class)
                .setParameter("sinalizador", sinalizador)
                .getSingleResult();
        }catch(Exception e){
            return null;
        }
        
    }
    
}
