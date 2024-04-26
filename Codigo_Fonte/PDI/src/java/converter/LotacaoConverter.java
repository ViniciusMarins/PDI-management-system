package converter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import dao.LotacaoDAO;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Lotacao;

/**
 *
 * @author linkf
 */
@FacesConverter(forClass = Lotacao.class)
public class LotacaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        LotacaoDAO dao = CDI.current().select(LotacaoDAO.class).get();
        Lotacao lotacao = dao.buscarPorId(Integer.parseInt(string));
        return lotacao;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Lotacao l = (Lotacao) t;
        return String.valueOf(l.getIdLotacao());
    }
}
