/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ReuniaoDAO;
import dao.ServidorDAO;
import dao.ArquivoDAO;
import dao.ComentarioDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.Reuniao;
import model.Comentario;
import model.Servidor;
import model.Arquivo;
import model.Frequencia;
import model.FrequenciaPK;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import util.Util;

@Named
@SessionScoped
public class ReuniaoController implements Serializable {

    @Inject
    private ReuniaoDAO reuniaoDAO;

    @Inject
    private ServidorDAO servidorDAO;

    @Inject
    private ComentarioDAO comentarioDAO;

    @Inject
    private ArquivoDAO arquivoDAO;

    private Servidor servidor = null;
    private Frequencia frequenciaAtual;

    private List<Frequencia> frequencias = new ArrayList<>();
    private List<Frequencia> frequenciasT = new ArrayList<>();
    private List<Frequencia> frequenciasEdit = new ArrayList<>();

    private List<Comentario> comentarios;
    private List<Comentario> comentariosEdit;
    private List<Comentario> comentariosRemoveEdit;

    private Comentario comentario;
    private Comentario comentarioEdit;

    private Date data_inicio = new Date();
    private Date data_termino = new Date();
    private Date minDate;

    private Reuniao reuniao;
    private Reuniao reuniaoEdit;
    private Reuniao reuniaoATA;
    private List<Reuniao> reunioes;

    private Arquivo arquivo;
    private Arquivo arquivoEdit;
    private UploadedFile fileUp;
    private UploadedFile fileUpEdit;

    private StreamedContent fileDown;

    private List<Arquivo> arquivos;
    private List<Arquivo> arquivosEdit;
    private List<Arquivo> arquivosRemoveEdit;
    private List<UploadedFile> arquivosUpload;

    private String tipo;

    public ReuniaoController() {
        openNew();
    }

    @PostConstruct
    public void fillReuniaoList() {
        reunioes = reuniaoDAO.buscarTodas();
        List<Servidor> servidores = servidorDAO.buscarTodos();
        //System.out.println("Tamanho = " + servidores.size());
        this.frequencias.clear();
        if (!servidores.isEmpty()) {

            for (Servidor f : servidores) {
                Frequencia fre = new Frequencia();
                fre.setFrequencia(true);
                fre.setServidor(f);
                fre.setDescricao("");
                this.frequencias.add(fre);
            }
        }
        this.frequenciasT = this.frequencias;

    }

    public void openNew() {
        reuniao = new Reuniao();
        reuniaoEdit = new Reuniao();
        comentario = new Comentario();
        comentarioEdit = new Comentario();
        servidor = new Servidor();
        frequenciaAtual = new Frequencia();
        arquivo = new Arquivo();
        arquivoEdit = new Arquivo();
        tipo = "";

        comentarios = new ArrayList<>();
        comentariosEdit = new ArrayList<>();
        comentariosRemoveEdit = new ArrayList<>();
        arquivosRemoveEdit = new ArrayList<>();
        arquivos = new ArrayList<>();
        arquivosEdit = new ArrayList<>();
        arquivosUpload = new ArrayList<>();

    }

    public void fileDownload(String name) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        String realPath = ec.getRealPath("/").replace("/PDI", "");

        String pathDefinition = realPath + File.separator + "images" + File.separator + name;

        System.out.println("PATH: " + pathDefinition);
        InputStream in;
        try {
            in = new FileInputStream(pathDefinition);

            fileDown = DefaultStreamedContent.builder()
                    .name(name)
                    .contentType("application/octet-stream")
                    .stream(() -> in)
                    .build();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReuniaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fileUpload(UploadedFile file) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        String realPath = ec.getRealPath("/").replace("/PDI", "");
        String pathDefinition = realPath + File.separator + "images" + File.separator + file.getFileName();

        try ( InputStream in = file.getInputStream();  OutputStream out = new FileOutputStream(pathDefinition)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void adicionarArquivo() {

        Arquivo tmp = arquivoDAO.buscarPorNomeUpload(fileUp.getFileName());

        if (tmp != null) {
            Util.addMessageError("Erro ao cadastrar arquivo. Nome de arquivo em uso.");
            return;
        }

        for (UploadedFile f : arquivosUpload) {
            if (f.getFileName().equals(fileUp.getFileName())) {
                Util.addMessageError("Arquivo já adicionado");
                return;
            }
        }
        if (arquivo.getDescricao().equals("")) {
            arquivo.setDescricao("Não há descrição");
        }

        arquivo.setIdArquivo(Integer.parseInt(String.valueOf(new Date().getTime()).substring(8)));
        arquivo.setNomeUpload(fileUp.getFileName());

        arquivosUpload.add(fileUp);
        arquivos.add(arquivo);

        fileUp = null;
        arquivo = new Arquivo();

        Util.addMessageInformation("Arquivo Adicionado");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-arquivos");
    }

    public void adicionarArquivoEdit() {

        Arquivo tmp = arquivoDAO.buscarPorNomeUpload(fileUpEdit.getFileName());

        if (tmp != null) {
            Util.addMessageError("Erro ao cadastrar arquivo. Nome de arquivo em uso.");
            return;
        }

        for (UploadedFile f : arquivosUpload) {
            if (f.getFileName().equals(fileUpEdit.getFileName())) {
                Util.addMessageError("Arquivo já adicionado");
                return;
            }
        }

        if (arquivoEdit.getDescricao().equals("")) {
            arquivoEdit.setDescricao("Não há descrição");
        }

        arquivoEdit.setIdArquivo(Integer.parseInt(String.valueOf(new Date().getTime()).substring(8)));
        arquivoEdit.setNomeUpload(fileUpEdit.getFileName());

        arquivosUpload.add(fileUpEdit);
        arquivosEdit.add(arquivoEdit);

        fileUpEdit = null;
        arquivoEdit = new Arquivo();

        Util.addMessageInformation("Arquivo Adicionado");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-arquivos-edit");

    }

    public void removerArquivo() {
        int index = -1;
        for (int i = 0; i < arquivos.size(); i++) {

            if (arquivos.get(i).equals(arquivo)) {
                index = i;
            }
        }

        arquivos.remove(arquivo);
        if (!arquivosUpload.isEmpty()) {
            arquivosUpload.remove(index);
        }

        fileUp = null;
        arquivo = new Arquivo();

        Util.addMessageInformation("Arquivo Removido");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-arquivos");
    }

    public void removerArquivoEdit() {

        int index = -1;

        for (int i = 0; i < arquivosEdit.size(); i++) {

            if (arquivosEdit.get(i).equals(arquivoEdit)) {
                index = i;
            }
        }
        System.out.println(index);
        if (!arquivosUpload.isEmpty()) {
            int newIndex = Math.abs((arquivosEdit.size() - index) - arquivosUpload.size());
            System.out.println("new index = " + newIndex);
            arquivosUpload.remove(newIndex);
        }

        arquivosEdit.remove(arquivoEdit);

        arquivosRemoveEdit.add(arquivoEdit);

        fileUp = null;
        arquivoEdit = new Arquivo();

        Util.addMessageInformation("Arquivo Removido");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-arquivos-edit");
    }

    public String cadastrarReuniao() {
        Date Date = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(Date);
        try {
            Reuniao tmp = reuniaoDAO.buscarPorTitulo(reuniao.getTitulo().trim());

            if (tmp != null) {
                Util.addMessageError("Erro ao cadastrar reunião. Titulo em uso.");
                return "";
            }

            if (reuniao.getTitulo().isEmpty()) {
                Util.addMessageWarning("Insira um título para a reunião.");
                return "";
            }

            if (reuniao.getDescricao().isEmpty()) {
                Util.addMessageWarning("Insira uma descrição para a reunião.");
                return "";
            }

            if (this.data_inicio.after(this.data_termino) || this.data_inicio.equals(this.data_termino)) {
                Util.addMessageWarning("A data de início não pode ser depois ou igual à data de término.");
                return "";
            }

            reuniao.setDataInicio(data_inicio);
            reuniao.setDataTermino(data_termino);
            reuniao.setUltimaAlteracao(Date);
            reuniao.setIdReuniao(Integer.parseInt(String.valueOf(new Date().getTime()).substring(8)));

            for (Comentario c : comentarios) {
                c.setIdReuniao(reuniao);
            }
            reuniao.setComentarioList(comentarios);

            for (int i = 0; i < arquivosUpload.size(); i++) {
                Arquivo arquivo = arquivos.get(i);
                UploadedFile upFile = arquivosUpload.get(i);

                arquivo.setIdReuniao(reuniao);
                arquivo.setNomePasta(formattedDate + upFile.getFileName());

                this.fileUpload(upFile);
            }
            reuniao.setArquivoList(arquivos);

            reuniaoDAO.create(reuniao);

            Reuniao aux = reuniaoDAO.buscarPorTitulo(reuniao.getTitulo());

            for (Frequencia f : this.frequencias) {
                FrequenciaPK fpk = new FrequenciaPK();
                fpk.setReuniaoId(aux.getIdReuniao());
                fpk.setServidorProntuario(f.getServidor().getProntuario());
                f.setFrequenciaPK(fpk);
            }

            aux.setFrequenciaCollection(this.frequencias);
            reuniaoDAO.update(aux);
            this.tipo = "";
            fillReuniaoList();
            openNew();

            Util.addMessageInformation("Reunião Cadastrada");

            return "/pages/reuniao/consultar.xhtml?faces-redirect=true";
        } catch (EJBException e) {
            Util.addMessageError("Erro ao cadastrar reunião.");
            return "";
        }
    }

    public void adicionarComentarioEdit() {
        Date Date = new Date();

        if (comentarioEdit.getProntuarioServidor() == null || comentarioEdit.getDescricao().isEmpty()) {
            Util.addMessageWarning("Preencha o comentário por completo");
            return;
        }

        comentarioEdit.setIdComentario(Integer.parseInt(String.valueOf(new Date().getTime()).substring(8)));
        comentarioEdit.setData(Date);

        comentariosEdit.add(comentarioEdit);

        comentarioEdit = new Comentario();

        Util.addMessageInformation("Comentário Adicionado");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-comentarios-edit");
    }

    public void removerComentarioEdit() {
        comentariosEdit.remove(comentarioEdit);
        this.comentariosRemoveEdit.add(comentarioEdit);

        comentarioEdit = new Comentario();

        Util.addMessageInformation("Comentário Removido");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-comentarios-edit");

    }

    public void adicionarComentario() {
        Date Date = new Date();
        if (comentario.getProntuarioServidor() == null || comentario.getDescricao().isEmpty()) {
            Util.addMessageWarning("Preencha o comentário por completo");
            return;
        }

        comentario.setIdComentario(Integer.parseInt(String.valueOf(new Date().getTime()).substring(8)));
        comentario.setData(Date);

        comentarios.add(comentario);

        comentario = new Comentario();

        Util.addMessageInformation("Comentário Adicionado");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-comentarios");
    }

    public void removerComentario() {
        comentarios.remove(comentario);
        comentario = new Comentario();

        Util.addMessageInformation("Comentário Removido");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-comentarios");

    }

    public String editarReuniao() {
        Date Date = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(Date);

        Reuniao tmp = reuniaoDAO.buscarPorTitulo(reuniaoEdit.getTitulo().trim());

        if (tmp != null && reuniaoEdit.getIdReuniao() != tmp.getIdReuniao()) {
            Util.addMessageError("Erro ao cadastrar reunião. Titulo em uso.");
            return "";
        }

        reuniaoEdit.setUltimaAlteracao(Date);
        reuniaoEdit.setComentarioList(comentariosEdit);
        for (Comentario c : comentariosEdit) {
            c.setIdReuniao(reuniaoEdit);
        }

        for (Comentario c : comentariosRemoveEdit) {
            comentarioDAO.remove(c);
        }

        reuniaoEdit.setArquivoList(arquivosEdit);
        for (Arquivo ar : arquivosEdit) {
            ar.setIdReuniao(reuniaoEdit);
            ar.setNomePasta(formattedDate + ar.getNomeUpload());
        }

//        for (int i = 0; i <= arquivosUpload.size(); i++) {
//
//            for (Arquivo ar : arquivosRemoveEdit) {
//                if (ar.getNomeUpload().equals(arquivosUpload.get(i).getFileName())) {
//                    arquivosUpload.remove(i);
//                }
//            }
//        }
        for (Arquivo ar : arquivosRemoveEdit) {
            arquivoDAO.remove(ar);
        }

        for (UploadedFile ar : arquivosUpload) {
            this.fileUpload(ar);
        }

        reuniaoDAO.update(reuniaoEdit);

        fillReuniaoList();
        openNew();

        Util.addMessageInformation("Reunião Editada");

        return "/pages/reuniao/consultar.xhtml?faces-redirect=true";
    }

    public void removerReuniao() {
        try {
            reuniaoDAO.remove(reuniao);
            fillReuniaoList();
        } catch (EJBException e) {
            Util.addMessageError("Não é possível remover esta reunião");
            return;
        }
        Util.addMessageInformation("Reunião Removida");

        PrimeFaces.current().executeScript("PF('editReuniaoDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-reunioes");
    }

    public void adicionarFalta() {

        this.frequenciaAtual.setFrequencia(false);
        Util.addMessageInformation("Falta adicionada ao servidor " + this.frequenciaAtual.getServidor().getNome());
        PrimeFaces.current().executeScript("PF('createFaltaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-servidores-reuniao");
        this.frequenciaAtual = new Frequencia();
    }

    public void filtrarTipo() {
        this.frequencias = new ArrayList<>();
        String selectedTipo = this.tipo;
        for (Frequencia frequencia : frequenciasT) {
            if (selectedTipo.equals("AMBOS") || frequencia.getServidor().getTipo().equals(selectedTipo)) {
                this.frequencias.add(frequencia);
            }
        }
    }
    public void removerFalta() {

        this.frequenciaAtual.setFrequencia(true);
        this.frequenciaAtual.setDescricao("");

        Util.addMessageInformation("Falta removida do servidor " + this.frequenciaAtual.getServidor().getNome());
        PrimeFaces.current().executeScript("PF('removeFaltaDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-servidores-reuniao");
    }

    public String gerarTextoData() {
        return util.DateUtils.converterDataParaTexto(reuniaoATA.getDataInicio());
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Reuniao> getReunioes() {
        return reunioes;
    }

    public void setReunioes(List<Reuniao> reunioes) {
        this.reunioes = reunioes;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Reuniao getReuniao() {
        return reuniao;
    }

    public void setReuniao(Reuniao reuniao) {
        this.reuniao = reuniao;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_termino() {
        return data_termino;
    }

    public void setData_termino(Date data_termino) {
        this.data_termino = data_termino;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Reuniao getReuniaoEdit() {
        return reuniaoEdit;
    }

    public void setReuniaoEdit(Reuniao reuniaoEdit) {
        comentariosEdit = reuniaoEdit.getComentarioList();
        arquivosEdit = reuniaoEdit.getArquivoList();

        this.reuniaoEdit = reuniaoEdit;
    }

    public List<Comentario> getComentariosEdit() {
        return comentariosEdit;
    }

    public void setComentariosEdit(List<Comentario> comentariosEdit) {
        this.comentariosEdit = comentariosEdit;
    }

    public Comentario getComentarioEdit() {
        return comentarioEdit;
    }

    public void setComentarioEdit(Comentario comentarioEdit) {
        this.comentarioEdit = comentarioEdit;
    }

    public List<Comentario> getComentariosRemoveEdit() {
        return comentariosRemoveEdit;
    }

    public void setComentariosRemoveEdit(List<Comentario> comentariosRemoveEdit) {
        this.comentariosRemoveEdit = comentariosRemoveEdit;
    }

    public Frequencia getFrequenciaAtual() {
        return frequenciaAtual;
    }

    public void setFrequenciaAtual(Frequencia frequenciaAtual) {
        this.frequenciaAtual = frequenciaAtual;
    }

    public List<Frequencia> getFrequencias() {
        return frequencias;
    }

    public void setFrequencias(List<Frequencia> frequencias) {
        this.frequencias = frequencias;
    }

    public Reuniao getReuniaoATA() {
        return reuniaoATA;
    }

    public void setReuniaoATA(Reuniao reuniaoATA) {
        this.reuniaoATA = reuniaoATA;
    }

    public UploadedFile getFileUp() {
        return fileUp;
    }

    public void setFileUp(UploadedFile fileUp) {
        this.fileUp = fileUp;
    }

    public StreamedContent getFileDown() {
        return fileDown;
    }

    public void setFileDown(StreamedContent fileDown) {
        this.fileDown = fileDown;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public List<UploadedFile> getArquivosUpload() {
        return arquivosUpload;
    }

    public void setArquivosUpload(List<UploadedFile> arquivosUpload) {
        this.arquivosUpload = arquivosUpload;
    }

    public List<Arquivo> getArquivosRemoveEdit() {
        return arquivosRemoveEdit;
    }

    public void setArquivosRemoveEdit(List<Arquivo> arquivosRemoveEdit) {
        this.arquivosRemoveEdit = arquivosRemoveEdit;
    }

    public Arquivo getArquivoEdit() {
        return arquivoEdit;
    }

    public void setArquivoEdit(Arquivo arquivoEdit) {
        this.arquivoEdit = arquivoEdit;
    }

    public List<Arquivo> getArquivosEdit() {
        return arquivosEdit;
    }

    public void setArquivosEdit(List<Arquivo> arquivosEdit) {
        this.arquivosEdit = arquivosEdit;
    }

    public void setFileUpEdit(UploadedFile fileUpEdit) {
        this.fileUpEdit = fileUpEdit;
    }

    public UploadedFile getFileUpEdit() {
        return fileUpEdit;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Frequencia> getFrequenciasT() {
        return frequenciasT;
    }

    public void setFrequenciasT(List<Frequencia> frequenciasT) {
        this.frequenciasT = frequenciasT;
    }

    public List<Frequencia> getFrequenciasEdit() {
        return frequenciasEdit;
    }

    public void setFrequenciasEdit(List<Frequencia> frequenciasEdit) {
        this.frequenciasEdit = frequenciasEdit;
    }

}
