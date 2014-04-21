/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Control.Impl.ImplUsuarioDAO;
import Model.Usuario;

/**
 *
 * @author Bruno
 */
public class Sessao {
    
    private String user;
    private String funcao;
    
    private static Sessao instance;
    
    private Sessao(){
        
    }
    
    public static Sessao getInstance(){
        if(instance == null){
            instance = new Sessao();
        }
        return instance;
    }
    
    public void iniciaSessao(Usuario user){
        this.user = user.getNomeUsuario();
        this.funcao = user.getFunc().getNomeFuncao();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    
}
