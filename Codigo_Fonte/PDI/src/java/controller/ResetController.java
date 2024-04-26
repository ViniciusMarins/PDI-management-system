/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ServidorDAO;
import dao.TokenDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import model.Servidor;
import model.Token;
import org.primefaces.PrimeFaces;
import util.HashUtils;
import util.Util;

/**
 *
 * @author linkf
 */
@Named
@RequestScoped
public class ResetController implements Serializable {

    private String senha = "";
    private String confirmarSenha = "";
    private String token = "";

    @Inject
    private ServidorDAO servidorDAO;

    @Inject
    private TokenDAO tokenDAO;

    public ResetController() {
        senha = "";
        token = "";
        confirmarSenha = "";
    }

    public String redefinir() {
        if (senha.length() < 5) {
            Util.addMessageError("A nova senha deve ter pelo menos 5 digitos.");
            return null;
        }

        if (!senha.equals(confirmarSenha)) {
            Util.addMessageError("As senhas nao se coincidem.");
            return null;
        }

        Token t = tokenDAO.buscarToken(token);

        if (tokenDAO.buscarToken(token) == null) {
            Util.addMessageError("O Token informado não é válido.");
            return null;
        }

        if (t.getDataExpiracao().before(new Date())) {
            Util.addMessageError("O Token informado está expirado.");
            return null;
        }

        Servidor s = servidorValidador(t.getUsuario());

        s.setSenha(HashUtils.gerarHash(senha));
        servidorDAO.update(s);

        t.setUsado(true);
        tokenDAO.update(t);

        Util.addMessageInformation("Senha alterada com sucesso.");
        return "login";
    }

    public Servidor servidorValidador(String prontuario) {
        try {
            return servidorDAO.buscarPorProntuario(prontuario);
        } catch (Exception e) {
            return null;
        }
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
