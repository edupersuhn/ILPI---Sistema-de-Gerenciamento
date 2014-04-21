/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IIncidenteDAO;
import Model.Cardapio;
import Model.Funcionario;
import Model.Idoso;
import Model.Incidente;
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
public class ImplIncidenteDAO implements IIncidenteDAO{

    private static ImplIncidenteDAO instance;
    
    private ImplIncidenteDAO(){
        
    }
    
    public static ImplIncidenteDAO getInstance(){
        if(instance == null){
            instance = new ImplIncidenteDAO();
        }
        return instance;
    }
    
    @Override
    public void inserir(Incidente inc) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            prepared = con.prepareStatement("insert into incidente ("
                    + "COD_INCIDENTE,"
                    + "COD_IDOSO,"
                    + "COD_FUNCIONARIO,"
                    + "DAT_OCORRENCIA,"
                    + "DSC_INCIDENTE) "
                    + "values (?,?,?,?,?)");
            
            prepared.setInt(1, inc.getCodigoIncidente());
            prepared.setInt(2, inc.getIdoso().getCodIdoso());
            prepared.setInt(3, inc.getFunc().getCodFuncionario());
            prepared.setDate(4, inc.getDataIncidente());
            prepared.setString(5, inc.getDescricaoIncidente());

            result = prepared.executeQuery();
            
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao inserir alimento.");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void atualizar(Incidente inc) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from incidente"
                    + " where COD_INCIDENTE = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, inc.getCodigoIncidente());
            
            result = prepared.executeQuery();
            
            if(!result.next()){
                inserir(inc);
            }else{
                sql =  "update incidente "
                        + "set COD_INCIDENTE = ?,"
                            + "COD_IDOSO = ?,"
                            + "COD_FUNCIONARIO = ?,"
                            + "DAT_OCORRENCIA = ?,"
                            + "DSC_INCIDENTE = ? "
                      + "where COD_INCIDENTE = ?";
                
                prepared = con.prepareStatement(sql);
                
                prepared.setInt(1, inc.getCodigoIncidente());
                prepared.setInt(2, inc.getIdoso().getCodIdoso());
                prepared.setInt(3, inc.getFunc().getCodFuncionario());
                prepared.setDate(4, inc.getDataIncidente());
                prepared.setString(5, inc.getDescricaoIncidente());
                prepared.setInt(6, inc.getCodigoIncidente());
                
                result = prepared.executeQuery();
            }
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao atualizar alimento.");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void remover(Incidente inc) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from incidente"
                    + " where COD_INCIDENTE = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, inc.getCodigoIncidente());
            
            result = prepared.executeQuery();
            
            if(result.next()){
                sql = "delete incidente "
                     + "where COD_INCIDENTE = ?";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, inc.getCodigoIncidente());
                result = prepared.executeQuery();
            }else{
                throw new DAOException("Não foi possível encontrar o alimento informado! Cod: " + inc.getCodigoIncidente());
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
    public List<Incidente> encontrarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Incidente encontrarPorCodigo(int codigo) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from incidente "
                        + "where COD_INCIDENTE = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, codigo);
            
            result = prepared.executeQuery();
            
            Incidente a = null;
            while(result.next()){
                int codIncidente = result.getInt("COD_INCIDENTE");
                int codIdoso = result.getInt("COD_IDOSO");
                Idoso i = ImplIdosoDAO.getInstance().encontrarPorCodigo(codIdoso);
                
                int codFunc = result.getInt("COD_FUNCIONARIO");
                Funcionario f = ImplFuncionarioDAO.getInstance().encontrarPorCodigo(codFunc);
                
                Date datOcorrencia = result.getDate("DAT_OCORRENCIA");
                String dscIncidente = result.getString("DSC_INCIDENTE");
                
                a = new Incidente(codIncidente, f, i,datOcorrencia,dscIncidente);
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
