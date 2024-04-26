package listener;

import controller.LoginController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marilena Oshima
 */
public class Autorizacao implements PhaseListener {

    @Inject
    private LoginController loginController;

    public boolean autorizacaoAcesso(String request) {
        if (loginController.getServidorAutenticado().getPdi()) {
            if (request.equals("/pages/home.xhtml")
                    || request.equals("/pages/gerenciamento/areas_concurso.xhtml")
                    || request.equals("/pages/gerenciamento/areas_lotacao.xhtml")
                    || request.equals("/pages/gerenciamento/cursos.xhtml")
                    || request.equals("/pages/gerenciamento/disciplinas.xhtml")
                    || request.equals("/pages/gerenciamento/servidores.xhtml")
                    || request.equals("/pages/gerenciamento/servidoresSimulacao.xhtml")
                    || request.equals("/pages/reuniao/cadastrar.xhtml")
                    || request.equals("/pages/reuniao/consultar.xhtml")
                    || request.equals("/pages/reuniao/editar.xhtml")
                    || request.equals("/pages/reuniao/visualizar.xhtml")
                    || request.equals("/resources/alterar_cadastro/index.xhtml")
                    || request.equals("/pages/gerenciamento/comparacoes.xhtml")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void afterPhase(PhaseEvent pe) {
        FacesContext facesContext = pe.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();

        //System.out.println("current page...." + currentPage);

        HttpServletRequest request = (HttpServletRequest) pe.getFacesContext().getExternalContext().getRequest();
        if (!(request.getServletPath().equals("/login.xhtml") || request.getServletPath().equals("/reset.xhtml")))  {
            if (loginController.getServidorAutenticado() == null ) {
                try {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acesso negado", "Você deve realizar login antes de acessar a página"));
                    FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getFlash().setKeepMessages(true);
                    pe.getFacesContext().getExternalContext().redirect("/login.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(Autorizacao.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                boolean temAcesso = autorizacaoAcesso(request.getServletPath());
                if (!temAcesso) {

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acesso negado", "Você não tem autorização para acessar a página"));
                    FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getFlash().setKeepMessages(true);

                    try {
                        pe.getFacesContext().getExternalContext().redirect("/pages/home.xhtml");
                    } catch (IOException ex) {
                        Logger.getLogger(Autorizacao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe
    ) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
