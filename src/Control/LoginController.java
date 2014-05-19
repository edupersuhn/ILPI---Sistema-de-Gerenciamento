package Control;

import Control.Impl.Exception.DAOException;
import Control.Impl.ImplFuncionarioDAO;
import Control.Impl.ImplUsuarioDAO;
import Model.Usuario;
import View.FrameCuidador;
import View.FrameLogin;
import View.FrameRecepcionista;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Model.Usuario;
import Model.Funcionario;

public class LoginController {
    
    private FrameLogin frameLogin;
    
    private Usuario user;
    
    public LoginController() {
        frameLogin = new FrameLogin(this);
        frameLogin.setVisible(true);
        user = null;
    }

    public Usuario getUser() {
        return user;
    }
    
    
    public boolean entrar(String nomeUser, String senha) {
        try {
            user = null;
            if((user = ImplUsuarioDAO.getInstance().permiteAcesso(nomeUser,senha)) != null){ // usuario existe
                if(user.getFunc() != null){ // usuario já cadastrado como funcionario
                    alteraExibicao(user.getFunc().getNomeFuncao());
                    frameLogin.setVisible(false);
                    frameLogin.limpar();
                    return true;
                }
            }
            return false;
<<<<<<< HEAD
        } catch (Exception ex) {
=======
        } catch (DAOException | SQLException ex) {
>>>>>>> 12f403a694b2c33e40a8ed6f73884abe01637a25
            //Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao realizar login");
            return false;
        }
    } 
    
    public void alteraExibicao(String nomeFunc) {
        switch(nomeFunc.toLowerCase()) {
            case "login": exibeLogin();
                break;
            case "recepcionista": exibeRecepcionista();
                break;
            case "cuidador": exibeCuidador();
                break;
        }
    }
    
    private void exibeLogin() {
        frameLogin.setVisible(true);
    }
    
    private void exibeRecepcionista() {
        FrameRecepcionista frame = new FrameRecepcionista(this);
        frame.setVisible(true);
    }
    
    private void exibeCuidador() {
        FrameCuidador frame = new FrameCuidador(this);
        frame.setVisible(true);
    }
    
}
