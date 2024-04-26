package dao;

import java.util.List;
import javax.ejb.Stateless;
import model.Arquivo;


@Stateless
public class ArquivoDAO extends AbstractDAO<Arquivo> {

    public List<Arquivo> buscarTodas() {
        return getEntityManager()
                .createNamedQuery("Arquivo.findAll", Arquivo.class)
                .getResultList();
    }

    public Arquivo buscarPorId(int id) {
        return getEntityManager()
                .createNamedQuery("Arquivo.findByIdArquivo", Arquivo.class)
                .setParameter("idArea", id)
                .getSingleResult();
    }
    
    public Arquivo buscarPorDescricao(String descricao) {
        try{
            return getEntityManager()
                .createNamedQuery("Arquivo.findByDescricao", Arquivo.class)
                .setParameter("descricao", descricao)
                .getSingleResult();
        }catch(Exception e){
            return null;
        }
        
    }
    
    public Arquivo buscarPorNomeUpload(String nomeUpload) {
        try{
            System.out.println("entrei");
            return getEntityManager()
                .createNamedQuery("Arquivo.findByNomeUpload", Arquivo.class)
                .setParameter("nome_upload", nomeUpload)
                .getSingleResult();
        }catch(Exception e){
            return null;
        }
        
    }  
}
