/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IDAO;
import Model.Funcionario;
import Model.Usuario;
import Util.ConectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bruno
 */
public class ImplUsuarioDAO implements IDAO<Usuario> {

    private static ImplUsuarioDAO instance;
    
    private ImplUsuarioDAO(){
        
    }
    
    public static ImplUsuarioDAO getInstance(){
        if(instance == null){
            instance = new ImplUsuarioDAO();
        }
        return instance;
    }
    
    @Override
    public void inserir(Usuario user) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void atualizar(Usuario user) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remover(Usuario user) throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Usuario encontrarPorCodigo(int codigo) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from usuario "
                        + "where COD_USUARIO = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, codigo);
            
            result = prepared.executeQuery();
            
            Usuario a = null;
            while(result.next()){
                int codUsuario = result.getInt("COD_USUARIO");
                String nomeUsuario = result.getString("NOM_USUARIO");
                String senhaUsuario = result.getString("SENHA_USUARIO");
                boolean flgAtivo = result.getBoolean("FLG_ATIVO");
                int codFunc = result.getInt("COD_FUNCIONARIO");
                Funcionario func = ImplFuncionarioDAO.getInstance().encontrarPorCodigo(codFunc);
                
                a = new Usuario(codUsuario, nomeUsuario, senhaUsuario, flgAtivo);
                if(func != null){
                    a.setFunc(func);
                }
            }
            
            if(a == null){
                throw new DAOException("Não foi possível o encontrar Usuario! Cod = " + codigo);
            }
            return a;
        } finally {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                System.out.println("Erro ao realizar rollback! ");
                System.out.println(ex1.getMessage());
                ex1.printStackTrace();
            }
        }
    }

    public Usuario encontrarPorNome(String nomeUsuario) throws DAOException,  SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from usuario "
                        + "where NOM_USUARIO = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setString(1, nomeUsuario);
            
            result = prepared.executeQuery();
            
            Usuario a = null;
            while(result.next()){
                int codUsuario = result.getInt("COD_USUARIO");
                String nomeUser = result.getString("NOM_USUARIO");
                String senhaUsuario = result.getString("SENHA_USUARIO");
                boolean flgAtivo = result.getBoolean("FLG_ATIVO");
                int codFunc = result.getInt("COD_FUNCIONARIO");
                Funcionario func = ImplFuncionarioDAO.getInstance().encontrarPorCodigo(codFunc);
                
                a = new Usuario(codUsuario, nomeUser, senhaUsuario, flgAtivo);
                if(func != null){
                    a.setFunc(func);
                }
            }
            
            if(a == null){
                throw new DAOException("Não foi possível o encontrar Usuario! Cod = " + nomeUsuario);
            }
            return a;
        } finally {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                System.out.println("Erro ao realizar rollback! ");
                System.out.println(ex1.getMessage());
                ex1.printStackTrace();
            }
        }
    }

    public Usuario permiteAcesso(String nomeUser, String senha) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select COD_USUARIO,"
                              + "NOM_USUARIO,"
                              + "SENHA_USUARIO,"
                              + "FLG_ATIVO,"
                              + "COD_FUNCIONARIO "
                        + " from usuario "
                        + "where NOM_USUARIO = ? "
                        + "  and SENHA_USUARIO = ? ";
            prepared = con.prepareStatement(sql);
            
            prepared.setString(1, nomeUser);
            prepared.setString(2, senha);
            
            result = prepared.executeQuery();
            
            if(result.next()){
                int codUsuario = result.getInt("COD_USUARIO");
                String nomUser = result.getString("NOM_USUARIO");
                String senhaUsuario = result.getString("SENHA_USUARIO");
                boolean flgAtivo = result.getBoolean("FLG_ATIVO");
                int codFunc = result.getInt("COD_FUNCIONARIO");
                Funcionario func = ImplFuncionarioDAO.getInstance().encontrarPorCodigo(codFunc);
                
                Usuario u = new Usuario(codUsuario, nomUser, senhaUsuario, flgAtivo);
                if(func != null){
                    u.setFunc(func);
                }
                return u;
            }else{
                return null;
            }
        } finally {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                System.out.println("Erro ao realizar rollback! ");
                System.out.println(ex1.getMessage());
                ex1.printStackTrace();
            }
        }
    }
}
