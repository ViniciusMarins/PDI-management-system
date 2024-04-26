/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.SimulacaoDAO;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Simulacao;

/**
 *
 * @author linkf value = "simulacaoConverter"
 */
@FacesConverter(forClass = Simulacao.class)
public class SimulacaoConverter implements Converter {

    public static Simulacao simulacaoAtual;
    

    public static Simulacao getSimulacaoAtual() {
        return simulacaoAtual;
    }

    public static void setSimulacaoAtual(Simulacao simulacaoAtualTemp) {
        SimulacaoConverter.simulacaoAtual = simulacaoAtualTemp;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            SimulacaoDAO dao = CDI.current().select(SimulacaoDAO.class).get();
            Simulacao simulacao = dao.buscarPorId(Integer.parseInt(string));
            return simulacao;

        } catch (Exception ex) {
            return simulacaoAtual;
        }

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Simulacao s = (Simulacao) o;

        return String.valueOf(s.getIdSimulacao());
    }

}
