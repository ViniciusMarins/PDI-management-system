package dao;

import java.util.List;
import javax.ejb.Stateless;
import model.Area;


@Stateless
public class AreaDAO extends AbstractDAO<Area> {

    public List<Area> buscarTodas() {
        return getEntityManager()
                .createNamedQuery("Area.findAll", Area.class)
                .getResultList();
    }

    public Area buscarPorId(int id) {
        return getEntityManager()
                .createNamedQuery("Area.findByIdArea", Area.class)
                .setParameter("idArea", id)
                .getSingleResult();
    }
    
    public Area buscarPorDescricao(String descricao) {
        try{
            return getEntityManager()
                .createNamedQuery("Area.findByDescricao", Area.class)
                .setParameter("descricao", descricao)
                .getSingleResult();
        }catch(Exception e){
            return null;
        }
        
    }
}
