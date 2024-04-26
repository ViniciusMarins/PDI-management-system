/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ServidorDAO;
import dao.TokenDAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.Servidor;
import model.Token;
import org.primefaces.PrimeFaces;
import util.HashUtils;
import util.Util;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private String prontuario;
    private String senha;
    private String novaSenha = "";
    private String loginRedefinicao = "";
    private Servidor servidorAutenticado;

    @Inject
    private ServidorDAO servidorDAO;
    @Inject
    private TokenDAO tokenDAO;

    public LoginController() {
        loginRedefinicao = "";

    }

    private String tema = "arya";

    public void changeTema() {

        if (tema.equals("saga")) {
            tema = "arya";
        } else {
            tema = "saga";
        }

        this.refreshPage();
        // return "/pages/home.xhtml?faces-redirect=true";
    }

    public void refreshPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        try {
            String viewId = context.getViewRoot().getViewId();
            String contextPath = externalContext.getRequestContextPath();
            String newURL = contextPath + viewId;
            System.out.println(newURL);
            externalContext.redirect(newURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String login() {

        if (servidorAutenticador()) {

            if (servidorAutenticado != null) {
                if (servidorAutenticado.getSenha().equals(HashUtils.gerarHash(senha))) {
                    if (servidorAutenticado.getPdi()) {
                        this.senha = "";
                        return "pages/home?faces-redirect=true";
                    } else {
                        this.senha = "";
                        Util.addMessageError("Usuário não é membro da comissão!");
                        return "login";
                    }
                } else {
                    Util.addMessageError("Senha inválida");
                }
            } else {
                Util.addMessageError("Prontuário inválido");
                return "login";
            }
        }
        return "login";
    }

    public String solicitarRedefinicao() {
        String emailRedefinicao = null;
        Servidor s = servidorValidador();

        if (s != null) {
            emailRedefinicao = s.getEmail();
            System.out.println("email " + emailRedefinicao);
        }

        if (emailRedefinicao != null) {
            Token t = new Token(loginRedefinicao);
            Util.addMessageInformation("Caso tenha um usuário com este Prontuário, será enviado um Token de redefinição.");
            tokenDAO.create(t);
            List<String> emails = new ArrayList<String>();
            emails.add(emailRedefinicao);

            util.JavaMail.email(emails, "Redefinição de Senha", "Token", t.getCodigo(), "Foi realizado uma solicitaçao de troca de senha.<br/><a href='http://sapdi.pep2.ifsp.edu.br/reset.xhtml'>Redefina em sapdi.pep2.ifsp.edu.br/reset.xhtml</a><br/>Utilize o link citado acima para redefinir. <br />Caso não tenha solicitado, desconsidere o e-mail.");
            PrimeFaces.current().executeScript("PF('createRedefinirDialog').hide()");
            this.loginRedefinicao = "";
            return "login";
        }

        Util.addMessageInformation("Caso tenha um usuário com este Prontuário, será enviado um Token de redefinição.");
        PrimeFaces.current().executeScript("PF('createRedefinirDialog').hide()");
        this.loginRedefinicao = "";
        return "login";
    }

    public void alterarSenha() {

        if (servidorAutenticado != null) {

            servidorAutenticado.setSenha(HashUtils.gerarHash(novaSenha));
            servidorDAO.update(servidorAutenticado);
            Util.addMessageInformation("Senha Alterada");
            PrimeFaces.current().executeScript("PF('editSenhaServidorDialog').hide()");
        }

        PrimeFaces.current().ajax().update("messages");
        senha = "";
        novaSenha = "";

    }

    public boolean servidorAutenticador() {
        try {
            servidorAutenticado = servidorDAO.buscarPorProntuario(prontuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Servidor servidorValidador() {
        try {
            return servidorDAO.buscarPorProntuario(loginRedefinicao);
        } catch (Exception e) {
            return null;
        }
    }

    public String logout() {
        this.prontuario = "";
        this.senha = "";

        servidorAutenticado = null;

        PrimeFaces.current().ajax().update("messages");
        // FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return "/login?faces-redirect=true";
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Servidor getServidorAutenticado() {
        return servidorAutenticado;
    }

    public void setServidorAutenticado(Servidor servidorAutenticado) {
        this.servidorAutenticado = servidorAutenticado;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getLoginRedefinicao() {
        return loginRedefinicao;
    }

    public void setLoginRedefinicao(String loginRedefinicao) {
        this.loginRedefinicao = loginRedefinicao;
    }

}
