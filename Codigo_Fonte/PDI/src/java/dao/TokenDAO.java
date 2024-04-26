/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Stateless;
import model.Token;

/**
 *
 * @author linkf
 */
@Stateless
public class TokenDAO extends AbstractDAO<Token> {
    public Token buscarToken(String idToken) {
        try {
        return getEntityManager()
                .createNamedQuery("Token.findToken", Token.class)
                .setParameter("codigo", idToken)
                .getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }
}
