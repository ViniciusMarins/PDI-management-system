package dao;

import java.util.List;
import javax.ejb.Stateless;
import model.Reuniao;

@Stateless
public class ReuniaoDAO extends AbstractDAO<Reuniao> {

    public List<Reuniao> buscarTodas() {
        return getEntityManager()
                .createNamedQuery("Reuniao.findAll", Reuniao.class)
                .getResultList();
    }

    public Reuniao buscarPorId(String id) {
        return getEntityManager()
                .createNamedQuery("Reuniao.findByIdReuniao", Reuniao.class)
                .setParameter("idReuniao", id)
                .getSingleResult();
    }

    public Reuniao buscarPorTitulo(String titulo) {
        try {
            return getEntityManager()
                    .createNamedQuery("Reuniao.findByTitulo", Reuniao.class)
                    .setParameter("titulo", titulo)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
