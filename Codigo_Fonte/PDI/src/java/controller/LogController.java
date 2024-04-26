/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import converter.SimulacaoConverter;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import util.DateUtils;

@Named
@SessionScoped
public class LogController implements Serializable {

    private String logs;

    public LogController() {
        logs = "";
    }

    public void addLog(String log) {
        System.out.println("log - " + log);
        logs = logs + "<br>" + DateUtils.convertToDateTime(new Date()) + "<br>" + log;
        SimulacaoConverter.simulacaoAtual.setLogs(logs);
    }
    
    public void resetLogs() {
        logs = "";
    }
    
    public String getLogs() {
        return logs;
    }
}
