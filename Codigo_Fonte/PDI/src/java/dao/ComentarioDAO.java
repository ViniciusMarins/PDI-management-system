package dao;

import java.util.List;
import javax.ejb.Stateless;
import model.Comentario;


@Stateless
public class ComentarioDAO extends AbstractDAO<Comentario> {

    public List<Comentario> buscarTodas() {
        return getEntityManager()
                .createNamedQuery("Comentario.findAll", Comentario.class)
                .getResultList();
    }

}
