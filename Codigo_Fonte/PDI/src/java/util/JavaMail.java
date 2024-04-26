/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.security.auth.Subject;

import model.Servidor;

/**
 *
 * @author Marilena Oshima
 */
public class JavaMail {
    
    //Session session, String toEmail, String subject, String body
    public static void sendEmail(List<String> to, String subject, String htmlBody) {
        to = to.stream().distinct().collect(Collectors.toList());
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        //System.out.println("SETANDO PROPERTIES");;
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); //TLS
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        //System.out.println("CRIANDO A SESSÃO");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                //return new PasswordAuthentication("recuperacaoparalela.sgrp@gmail.com", "rutlxbvefdlpresh");
              
               return new PasswordAuthentication("sistemadeapoio.pdi@gmail.com", "fljqhfyiwqfcdjcm");

                //return new PasswordAuthentication("recuperacaoparalela.sgrp@gmail.com", "JoseRamosJunior275032819599");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);
        //System.out.println("ATIVA DEBUG PARA A SESSÃO");
        try {
            //System.out.println("TENTANDO EVNIAR A MENSAEGM");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sistemadeapoio.pdi@gmail.com"));
            //Remetente
            InternetAddress[] address = new InternetAddress[to.size()];
            for(int i=0; i< to.size(); i++)
            {
                address[i] = new InternetAddress(to.get(i));
            }
            message.addRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);//Assunto
            Multipart mp = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlBody, "text/html");
            mp.addBodyPart(htmlPart);
            message.setContent(mp);
            Transport.send(message);
            /**
             * Método para enviar a mensagem criada
             */
            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            e.printStackTrace();
        };
    }

    public static void email(List<String> to, String assunto, String sigla, String nome, String mensagem) {
        String subject = assunto + " - " + sigla;

        String htmlBody
                = "<html>"
                + "<h2>Sistema de Apoio do Plano de Desenvolvimento Institucional</h2>"
                + "<p>" + sigla + " - " + nome + ".</p>"
                + "<p>" + mensagem + "</p>"
                + "<p> Entre no sistema para verificar. "
                + "<a href=\"http://sapdi.pep2.ifsp.edu.br\">Acesse: sapdi.pep2.ifsp.edu.br</a>"
                + "<br>"
                + "<h5>Mensagem automática. Não responda.</h5>"
                + "</html>";

        try {
            util.JavaMail.sendEmail(to, subject, htmlBody);
        } catch (Exception e) {
            System.out.println("Não foi possível enviar o e-mail.");
        }
    }

    
}
