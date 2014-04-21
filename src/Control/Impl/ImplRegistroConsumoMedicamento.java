/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IRegistroConsumoMedicamentoDAO;
import Model.RegistroConsumoMedicamento;
import Util.ConectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class ImplRegistroConsumoMedicamento implements IRegistroConsumoMedicamentoDAO{

    private static ImplRegistroConsumoMedicamento instance;
    
    private ImplRegistroConsumoMedicamento(){
        
    }
    
    public static ImplRegistroConsumoMedicamento getInstance(){
        if(instance == null){
            instance = new ImplRegistroConsumoMedicamento();
        }
        return instance;
    }
    
    @Override
    public void inserir(RegistroConsumoMedicamento reg) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            prepared = con.prepareStatement("insert into registro_consumo_medicamento ("
                    + "COD_PRESCRICAO,"
                    + "NUM_REMEDIO,"
                    + "DAT_UTILIZACAO,"
                    + "HOR_UTILIZACAO,"
                    + "COD_FUNCIONARIO) "
                    + "values (?,?,?,?,?)");
            
            prepared.setInt(1, reg.getItem().getPrescricao().getCodigoPrescricao());
            prepared.setInt(2, reg.getItem().getNumeroRemedio());
            prepared.setDate(3, reg.getDataUtilizacao());
            prepared.setInt(4, reg.getHoraUtilizacao());
            prepared.setInt(5, reg.getFuncionario().getCodFuncionario());

            result = prepared.executeQuery();
            
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao inserir registro de consumo de medicamento.");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void atualizar(RegistroConsumoMedicamento reg) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from registro_consumo_medicamento"
                    + " where COD_PRESCRICAO = ? "
                    + "   and NUM_REMEDIO = ? ";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, reg.getItem().getPrescricao().getCodigoPrescricao());
            prepared.setInt(2, reg.getItem().getNumeroRemedio());
            
            result = prepared.executeQuery();
            
            if(!result.next()){
                inserir(reg);
            }else{
                sql =  "update registro_consumo_medicamento "
                        + "set COD_PRESCRICAO = ?,"
                            + "NUM_PRESCRICAO = ?,"
                            + "DAT_UTILIZACAO = ?,"
                            + "HOR_UTILIZACAO = ?,"
                            + "COD_FUNCIONARIO = ? "
                      + "where COD_PRESCRICAO = ? "
                        + "and NUM_PRESCRICAO = ? ";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, reg.getItem().getPrescricao().getCodigoPrescricao());
                prepared.setInt(2, reg.getItem().getNumeroRemedio());
                prepared.setDate(3, reg.getDataUtilizacao());
                prepared.setInt(4, reg.getHoraUtilizacao());
                prepared.setInt(5, reg.getFuncionario().getCodFuncionario());
                prepared.setInt(6, reg.getItem().getPrescricao().getCodigoPrescricao());
                prepared.setInt(7, reg.getItem().getNumeroRemedio());
                result = prepared.executeQuery();
            }
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao atualizar cardapio.");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void remover(RegistroConsumoMedicamento reg) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from registro_consumo_medicamento"
                    + " where COD_PRESCRICAO = ? "
                    + "   and NUM_REMEDIO = ? ";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, reg.getItem().getPrescricao().getCodigoPrescricao());
            prepared.setInt(2, reg.getItem().getNumeroRemedio());
            
            result = prepared.executeQuery();
            
            if(result.next()){
                sql = "delete registro_consumo_medicamento "
                     + "where COD_PRESCRICAO = ? "
                     + "  and NUM_PRESCRICAO = ? ";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, reg.getItem().getPrescricao().getCodigoPrescricao());
                prepared.setInt(2, reg.getItem().getNumeroRemedio());
                result = prepared.executeQuery();
            }else{
                throw new DAOException("Não foi possível encontrar o cardapio informado! Cod: " 
                        + reg.getItem().getPrescricao().getCodigoPrescricao() + " - " 
                        + reg.getItem().getNumeroRemedio());
            }
                
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao excluir o cardapio.");
            System.out.println(ex.getMessage());
        } catch(DAOException dao){
            System.out.println("Erro ao tentar excluir cardapio! ");
            System.out.println(dao.getMessage());
        }
    }

    @Override
    public List<RegistroConsumoMedicamento> encontrarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RegistroConsumoMedicamento encontrarPorCodigo(int codigo) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
