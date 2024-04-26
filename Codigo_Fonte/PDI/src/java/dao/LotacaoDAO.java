package dao;

import java.util.List;
import javax.ejb.Stateless;
import model.Lotacao;


@Stateless
public class LotacaoDAO extends AbstractDAO<Lotacao> {

    public List<Lotacao> buscarTodas() {
        return getEntityManager()
                .createNamedQuery("Lotacao.findAll", Lotacao.class)
                .getResultList();
    }

    public Lotacao buscarPorId(int id) {
        return getEntityManager()
                .createNamedQuery("Lotacao.findByIdLotacao", Lotacao.class)
                .setParameter("idLotacao", id)
                .getSingleResult();
    }
    
    public Lotacao buscarPorDescricao(String descricao) {
        try{
            return getEntityManager()
                .createNamedQuery("Lotacao.findByDescricao", Lotacao.class)
                .setParameter("descricao", descricao)
                .getSingleResult();
        }catch(Exception e){
            return null;
        }
}

}