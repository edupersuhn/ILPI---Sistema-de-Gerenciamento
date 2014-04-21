/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IFuncionarioDAO;
import Model.Cardapio;
import Model.Funcionario;
import Model.Idoso;
import Util.ConectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class ImplFuncionarioDAO implements IFuncionarioDAO{

    private static ImplFuncionarioDAO instance;
    
    private ImplFuncionarioDAO(){
        
    }
    
    public static ImplFuncionarioDAO getInstance(){
        if(instance == null){
            instance = new ImplFuncionarioDAO();
        }
        return instance;
    }
    
    @Override
    public void inserir(Funcionario func) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            prepared = con.prepareStatement("insert into Funcionario ("
                    + "COD_FUNCIONARIO,"
                    + "NOM_FUNCIONARIO,"
                    + "NOM_FUNCAO,"
                    + "ENDERECO,"
                    + "NUM_TELEFONE) "
                    + "values (?,?,?,?,?)");
            
            prepared.setInt(1, func.getCodFuncionario());
            prepared.setString(2, func.getNomeFuncionario());
            prepared.setString(3, func.getNomeFuncao());
            prepared.setString(4, func.getEndereco());
            prepared.setLong(5, func.getTelefone());

            result = prepared.executeQuery();
            
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao inserir funcionario.");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void atualizar(Funcionario func) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from funcionario"
                    + " where COD_FUNCIONARIO = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, func.getCodFuncionario());
            
            result = prepared.executeQuery();
            
            if(!result.next()){
                inserir(func);
            }else{
                sql =  "update funcionario "
                        + "set COD_FUNCIONARIO = ?,"
                            + "NOM_FUNCIONARIO = ?,"
                            + "NOM_FUNCAO = ?,"
                            + "ENDERECO = ?,"
                            + "NUM_TELEFONE = ? "
                      + "where COD_FUNCIONARIO = ?";
                prepared = con.prepareStatement(sql);
                
                prepared.setInt(1, func.getCodFuncionario());
                prepared.setString(2, func.getNomeFuncionario());
                prepared.setString(3, func.getNomeFuncao());
                prepared.setString(4, func.getEndereco());
                prepared.setLong(5, func.getTelefone());
                prepared.setInt(6, func.getCodFuncionario());
                
                result = prepared.executeQuery();
            }
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao atualizar alimento.");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void remover(Funcionario func) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from funcionario"
                    + " where COD_FUNCIONARIO = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, func.getCodFuncionario());
            
            result = prepared.executeQuery();
            
            if(result.next()){
                sql = "delete funcionario "
                     + "where COD_FUNCIONARIO = ?";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, func.getCodFuncionario());
                result = prepared.executeQuery();
            }else{
                throw new DAOException("Não foi possível encontrar o alimento informado! Cod: " + func.getCodFuncionario());
            }
                
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao excluir o alimento.");
            System.out.println(ex.getMessage());
        } catch(DAOException dao){
            System.out.println("Erro ao tentar excluir alimento! ");
            System.out.println(dao.getMessage());
        }
    }

    @Override
    public List<Funcionario> encontrarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Funcionario encontrarPorCodigo(int codigo) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from funcionario "
                        + "where COD_FUNCIONARIO = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, codigo);
            
            result = prepared.executeQuery();
            
            Funcionario a = null;
            while(result.next()){
                int codFuncionario = result.getInt("COD_FUNCIONARIO");
                String nomeFuncionario = result.getString("NOM_FUNCIONARIO").trim();
                String nomFuncao = result.getString("NOM_FUNCAO").trim();
                String endereco = result.getString("ENDERECO").trim();
                long telefone = result.getLong("NUM_TELEFONE");
                
                a = new Funcionario(codFuncionario, nomeFuncionario, nomFuncao, endereco, telefone);
            }
            
            if(a == null){
                throw new DAOException("Não foi possível o encontrar alimento! Cod = " + codigo);
            }
            return a;
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao excluir o alimento.");
            System.out.println(ex.getMessage());
            return null;
        } catch(DAOException dao){
            System.out.println("Erro ao tentar encontrar o alimento! ");
            System.out.println(dao.getMessage());
            return null;
        }
    }
    
}
