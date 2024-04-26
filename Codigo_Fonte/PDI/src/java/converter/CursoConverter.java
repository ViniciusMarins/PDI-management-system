/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converter;

import dao.CursoDAO;
import dao.ServidorDAO;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Curso;

/**
 *
 * @author vitor
 */
@FacesConverter(forClass = Curso.class)
public class CursoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        CursoDAO dao = CDI.current().select(CursoDAO.class).get();
        Curso curso = dao.buscarPorSigla(string);

        return curso;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Curso s = (Curso) o;
        return String.valueOf(s.getSigla());
    }

}
