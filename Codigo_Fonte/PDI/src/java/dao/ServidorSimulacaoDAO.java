package dao;

import java.util.List;
import javax.ejb.Stateless;
import model.ServidorSimulacao;


@Stateless
public class ServidorSimulacaoDAO extends AbstractDAO<ServidorSimulacao> {

    public List<ServidorSimulacao> buscarTodas() {
        return getEntityManager()
                .createNamedQuery("ServidorSimulacao.findAll", ServidorSimulacao.class)
                .getResultList();
    }

    public ServidorSimulacao buscarPorId(int id) {
        return getEntityManager()
                .createNamedQuery("ServidorSimulacao.findById", ServidorSimulacao.class)
                .setParameter("idProfessor", id)
                .getSingleResult();
    }
    
    public int buscarUltimoId() {
        try {
            return getEntityManager()
                .createNamedQuery("ServidorSimulacao.buscarUltimoId", ServidorSimulacao.class)
                .setMaxResults(1).getSingleResult().getIdProfessor();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return 0;
        }
    }
    
    
    
    
}
