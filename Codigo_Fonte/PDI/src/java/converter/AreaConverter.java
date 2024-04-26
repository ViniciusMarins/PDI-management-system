package converter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import dao.AreaDAO;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Area;

/**
 *
 * @author linkf
 */
@FacesConverter(forClass = Area.class)
public class AreaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        AreaDAO dao = CDI.current().select(AreaDAO.class).get();
        System.out.println(string);
        Area area = dao.buscarPorId(Integer.parseInt(string));
        return area;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Area l = (Area) t;
        return String.valueOf(l.getIdArea());
    }
}
