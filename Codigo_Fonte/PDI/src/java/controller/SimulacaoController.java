/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Map;
import converter.SimulacaoConverter;
import dao.AreaDAO;
import dao.SimulacaoDAO;
import dao.SinalizadorDAO;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.Area;
import model.DadosSimulacao;
import model.Disciplina;
import model.ServidorSimulacao;
import model.Simulacao;
import model.Sinalizador;
import org.apache.poi.ss.formula.functions.Areas;
import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import util.ColorsUtils;
import util.DateUtils;
import util.Util;

@Named
@SessionScoped
public class SimulacaoController implements Serializable {

    private Simulacao simulacaoAtual;

    private Simulacao simulacao1;
    private Simulacao simulacao2;

    private Simulacao simulacaoComparacao = new Simulacao();

    private List<Simulacao> simulacoes;

    @Inject
    private SimulacaoDAO simulacaoDAO;

    @Inject
    private SinalizadorDAO sinalizadorDAO;

    @Inject
    private LogController logController;

    @Inject
    private AreaDAO areaDAO;

    private BarChartModel professoresModel1;

    private int sinalizadorSobreCargaAlta;
    private int sinalizadorSobreCargaMedia;

    public SimulacaoController() {
        simulacaoAtual = new Simulacao();
        simulacao1 = null;
        simulacao2 = null;
    }

    public void initSinalizadores() {
        if (sinalizadorSobreCargaAlta == 0 || sinalizadorSobreCargaMedia == 0) {
            Sinalizador temp = sinalizadorDAO.findBySinalizador("sobreCargaAlta");

            if (temp == null) {
                sinalizadorDAO.create(new Sinalizador(1, 0, "sobreCargaAlta"));
            }

            temp = sinalizadorDAO.findBySinalizador("sobreCargaMedia");

            if (temp == null) {
                sinalizadorDAO.create(new Sinalizador(2, 0, "sobreCargaMedia"));
            }

            this.sinalizadorSobreCargaAlta = sinalizadorDAO.findBySinalizador("sobreCargaAlta").getValor();
            this.sinalizadorSobreCargaMedia = sinalizadorDAO.findBySinalizador("sobreCargaMedia").getValor();
        }

    }

    @PostConstruct
    public void gerarSimulacaoAtual() {
        Simulacao s = new Simulacao();

        List<Area> areas = areaDAO.buscarTodas();
        List<DadosSimulacao> dados = new ArrayList<DadosSimulacao>();
        List<Disciplina> disciplinas;

        int parParcial = 0;
        int parIntegral = 0;
        int parPrincipal = 0;
        int imparParcial = 0;
        int imparIntegral = 0;
        int imparPrincipal = 0;
        int qtdProfessores = 0;

        // para cada area no banco
        for (Area a : areas) {

            List<ServidorSimulacao> servidores = a.getServidorSimulacaoList();
            servidores.removeIf((t) -> !t.getStatus());
            qtdProfessores = servidores.size();

            disciplinas = a.getDisciplinaList();
            disciplinas.removeIf((Disciplina t) -> !t.isStatus() || !t.getIdCurso().getStatus());

            // para cada disciplina ativa dessa area
            for (Disciplina dis : disciplinas) {
                // para disciplinas pares
                if (dis.getSemestre().equals("PAR") || dis.getSemestre().equals("AMBOS")) {
                    parPrincipal += dis.getCargaHoraria();

                    if (dis.getRegencia().equals("PARCIAL")) {
                        parParcial += dis.getCargaHorariaParcial();
                    }
                }

                if (dis.getSemestre().equals("IMPAR") || dis.getSemestre().equals("AMBOS")) {

                    imparPrincipal += dis.getCargaHoraria();

                    if (dis.getRegencia().equals("PARCIAL")) {
                        imparParcial += dis.getCargaHorariaParcial();
                    }
                }
            }

            disciplinas = a.getDisciplina2List();
            disciplinas.removeIf((Disciplina t) -> !t.isStatus() || !t.getIdCurso().getStatus());

            // para cada disciplina ativa dessa area
            for (Disciplina dis : disciplinas) {
                // para disciplinas pares
                if (dis.getSemestre().equals("PAR") || dis.getSemestre().equals("AMBOS")) {
                    if (dis.getRegencia().equals("INTEGRAL")) {
                        parIntegral += dis.getCargaHoraria();
                    }
                }

                if (dis.getSemestre().equals("IMPAR") || dis.getSemestre().equals("AMBOS")) {
                    if (dis.getRegencia().equals("INTEGRAL")) {
                        imparIntegral += dis.getCargaHoraria();
                    }
                }

            }

            dados.add(new DadosSimulacao(a.getDescricao(), qtdProfessores, parParcial, parIntegral, parPrincipal, imparParcial, imparIntegral, imparPrincipal));
            parParcial = 0;
            parIntegral = 0;
            parPrincipal = 0;
            imparParcial = 0;
            imparIntegral = 0;
            imparPrincipal = 0;
            qtdProfessores = 0;
        }

        s.setDadosSimulacaoList(dados);

        s.setTitulo("Simulação " + DateUtils.convertToDateTime(new Date()));
        s.setDescricao("Essa simulação contém os dados atuais do sistema");
        s.setIdSimulacao(0);

        simulacoes = new ArrayList<>();
        simulacoes.add(s);
        simulacoes.addAll(simulacaoDAO.buscarTodas());

        simulacaoAtual = s;

        if (simulacao1 != null && simulacao1.getIdSimulacao() == 0) {
            this.setSimulacao1(simulacaoAtual);
        }

        if (simulacao2 != null && simulacao2.getIdSimulacao() == 0) {
            this.setSimulacao2(simulacaoAtual);
        }

        SimulacaoConverter.setSimulacaoAtual(simulacaoAtual);
        SimulacaoConverter.simulacaoAtual.setLogs(logController.getLogs());
        createProfessoresModel1();
        
        
    }

    public Simulacao getSimulacaoAtual() {
        return simulacaoAtual;
    }

    public void setSimulacaoAtual(Simulacao simulacaoAtual) {
        this.simulacaoAtual = simulacaoAtual;
    }

    public void atualizaSimulacao2() {
        if (simulacao2 == null) {
            simulacaoComparacao = new Simulacao();
            return;
        }

        if (simulacao1 == null) {
            simulacaoComparacao = simulacao2;
            return;
        }

        List<DadosSimulacao> dados1 = simulacao1.getDadosSimulacaoList();
        List<DadosSimulacao> dados2 = simulacao2.getDadosSimulacaoList();

        int flag = 0;
        for (DadosSimulacao d1 : dados1) {
            flag = 0;
            // para cada dado da simulacao 1, procura equivalencia de aream em d2 
            for (DadosSimulacao d2 : dados2) {
                if (d1.getArea().equals(d2.getArea())) {
                    flag = 1;
                }
            }

            if (flag == 0) {
                dados2.add(new DadosSimulacao(d1.getArea(), 0, 0, 0, 0, 0, 0, 0));
            }
        }
        sort(dados2);

        simulacaoComparacao.setDadosSimulacaoList(dados2);
    }

    public void salvarSimulacaoAtual() {
        try {
            Simulacao tmp = simulacaoDAO.buscarPorTitulo(simulacaoAtual.getTitulo().trim());

            if (tmp != null) {
                Util.addMessageError("Erro ao cadastrar simulação. Título em uso.");
                return;
            }

            for (DadosSimulacao ds : simulacaoAtual.getDadosSimulacaoList()) {
                ds.setIdSimulacao(simulacaoAtual);
            }
            simulacaoAtual.setLogs(logController.getLogs());
            simulacaoDAO.create(simulacaoAtual);
            logController.resetLogs();
            simulacaoAtual.setDescricao("");
            Util.addMessageInformation("Simulação Salva com Sucesso!");
        } catch (EJBException e) {
            Util.addMessageError("Erro ao cadastrar simulação. Verifique se a simulação já existe.");
        }

        PrimeFaces.current().executeScript("PF('createSimulacaoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form_simulacao");

    }

    public void createProfessoresModel1() {
        Map<String, Color> areaColorMap = new HashMap<>();

        professoresModel1 = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Quantidade");

        List<Number> values = new ArrayList<>();
        List<String> bgColor = new ArrayList<>();
        List<String> borderColor = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (DadosSimulacao d : simulacaoAtual.getDadosSimulacaoList()) {
            String area = d.getArea();

            Color color = ColorsUtils.generateColorForArea(d.getArea());
            areaColorMap.put(area, color);

            values.add(d.getNumProfs());

            bgColor.add(ColorsUtils.getRGBAColor(areaColorMap.get(area), 0.4));
            borderColor.add(ColorsUtils.getRGBColor(areaColorMap.get(area)));
            labels.add(area);

            //bgColor.add("rgba(53, 152, 48, 0.2)");
            //borderColor.add("rgb(54, 115, 50)");
            //labels.add(d.getArea());
        }

        barDataSet.setData(values);
        barDataSet.setBackgroundColor(bgColor);
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);
        data.addChartDataSet(barDataSet);
        data.setLabels(labels);
        professoresModel1.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
//        options.setMaintainAspectRatio(false);
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(1000);
        animation.setEasing("easeOutQuad");
        options.setAnimation(animation);

        professoresModel1.setOptions(options);
    }

    public void updateSinalizadores() {

        Sinalizador tempSCAlta = sinalizadorDAO.findBySinalizador("sobreCargaAlta");
        tempSCAlta.setValor(sinalizadorSobreCargaAlta);

        Sinalizador tempSCMedia = sinalizadorDAO.findBySinalizador("sobreCargaMedia");
        tempSCMedia.setValor(sinalizadorSobreCargaMedia);

        this.sinalizadorDAO.update(tempSCAlta);
        this.sinalizadorDAO.update(tempSCMedia);

        Util.addMessageInformation("Sinalizadores Atualizados com Sucesso!");
        PrimeFaces.current().executeScript("PF('editSinalizadoresDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form_simulacao");
    }

    public BarChartModel getProfessoresModel1() {
        return professoresModel1;
    }

    public void setProfessoresModel1(BarChartModel professoresModel1) {
        this.professoresModel1 = professoresModel1;
    }

    public List<Simulacao> getSimulacoes() {
        return simulacoes;
    }

    public void setSimulacoes(List<Simulacao> simulacoes) {
        this.simulacoes = simulacoes;
    }

    public Simulacao getSimulacao1() {
        return simulacao1;
    }

    public void setSimulacao1(Simulacao simulacao1) {
        this.simulacao1 = simulacao1;
        atualizaSimulacao2();
    }

    public Simulacao getSimulacao2() {
        return simulacao2;
    }

    public void setSimulacao2(Simulacao simulacao2) {
        this.simulacao2 = simulacao2;
        atualizaSimulacao2();
    }

    public Simulacao getSimulacaoComparacao() {
        return simulacaoComparacao;
    }

    public void setSimulacaoComparacao(Simulacao simulacaoComparacao) {
        this.simulacaoComparacao = simulacaoComparacao;
    }

    public static void sort(List<DadosSimulacao> list) {
        list.sort((o1, o2)
                -> o1.getArea().compareTo(
                        o2.getArea()));
    }

    public int getSinalizadorSobreCargaAlta() {
        return sinalizadorSobreCargaAlta;
    }

    public void setSinalizadorSobreCargaAlta(int sinalizadorSobreCargaAlta) {
        this.sinalizadorSobreCargaAlta = sinalizadorSobreCargaAlta;
    }

    public int getSinalizadorSobreCargaMedia() {
        return sinalizadorSobreCargaMedia;
    }

    public void setSinalizadorSobreCargaMedia(int sinalizadorSobreCargaMedia) {
        this.sinalizadorSobreCargaMedia = sinalizadorSobreCargaMedia;
    }

}
