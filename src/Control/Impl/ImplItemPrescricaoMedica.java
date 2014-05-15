/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IDAO;
import Model.ItemPrescricaoMedica;
import Model.PrescricaoMedica;
import Model.Remedio;
import Util.ConectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class ImplItemPrescricaoMedica implements IDAO<ItemPrescricaoMedica> {

    private static ImplItemPrescricaoMedica instance;
    
    private ImplItemPrescricaoMedica(){
        
    }
    
    public static ImplItemPrescricaoMedica getInstance(){
        if(instance == null){
            instance = new ImplItemPrescricaoMedica();
        }
        return instance;
    }
    
    @Override
    public void inserir(ItemPrescricaoMedica item) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        try {
            //TODO Fazer o insert do idoso aqui
            prepared = con.prepareStatement("insert into item_prescricao_medica ("
                    + "COD_PRESCRICAO,"
                    + "NUM_REMEDIO,"
                    + "COD_REMEDIO,"
                    + "OBS_REMEDIO,"
                    + "QTD_REMEDIO) "
                    + "values (?,?,?,?,?)");
            
            prepared.setInt(1, item.getPrescricao().getCodigoPrescricao());
            prepared.setInt(2, item.getNumeroRemedio());
            prepared.setInt(3, item.getRemedio().getCodigo());
            prepared.setString(4, item.getObservacaoRemedio());
            prepared.setInt(5, item.getQtdRemedio());

            prepared.execute();
            
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

    @Override
    public void atualizar(ItemPrescricaoMedica item) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from item_prescricao_medica"
                    + " where COD_PRESCRICAO = ? "
                    + "   and NUM_PRESCRICAO = ? ";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, item.getPrescricao().getCodigoPrescricao());
            prepared.setInt(2, item.getNumeroRemedio());
            
            result = prepared.executeQuery();
            
            if(!result.next()){
                inserir(item);
            }else{
                sql =  "update item_prescricao_medica "
                        + "set COD_PRESCRICAO = ?,"
                            + "NUM_PRESCRICAO = ?,"
                            + "COD_REMEDIO = ?,"
                            + "OBS_REMEDIO = ?,"
                            + "QTD_REMEDIO = ? "
                      + "where COD_PRESCRICAO = ? "
                        + "and NUM_PRESCRICAO = ? ";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, item.getPrescricao().getCodigoPrescricao());
                prepared.setInt(2, item.getNumeroRemedio());
                prepared.setInt(3, item.getRemedio().getCodigo());
                prepared.setString(4, item.getObservacaoRemedio());
                prepared.setInt(5, item.getQtdRemedio());
                prepared.setInt(6, item.getPrescricao().getCodigoPrescricao());
                prepared.setInt(7, item.getNumeroRemedio());
                
                prepared.execute();
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

    @Override
    public void remover(ItemPrescricaoMedica item) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from item_prescricao_medica"
                    + " where COD_PRESCRICAO = ? "
                    + "   and NUM_PRESCRICAO = ? ";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, item.getPrescricao().getCodigoPrescricao());
            prepared.setInt(2, item.getNumeroRemedio());
            
            result = prepared.executeQuery();
            
            if(result.next()){
                sql = "delete item_prescricao_medica "
                    + " where COD_PRESCRICAO = ? "
                    + "   and NUM_PRESCRICAO = ? ";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, item.getPrescricao().getCodigoPrescricao());
                prepared.setInt(2, item.getNumeroRemedio());
                
                prepared.execute();
            }else{
                throw new DAOException("Não foi possível encontrar o cardapio informado! Cod: " 
                        + item.getPrescricao().getCodigoPrescricao() + " - " + item.getNumeroRemedio());
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

    public List<ItemPrescricaoMedica> encontrarTodos(int codPrescricao) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        List<ItemPrescricaoMedica> lista = new ArrayList<>();
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from item_prescricao_medica "
                       + " where COD_PRESCRICAO = ? ";
            prepared = con.prepareStatement(sql);
            prepared.setInt(1,codPrescricao);
            result = prepared.executeQuery();
            
            ItemPrescricaoMedica a = null;
            PrescricaoMedica p = null;
            while(result.next()){
                int codPresc = result.getInt("COD_PRESCRICAO");
                if(p == null){
                    p = ImplPrescricaoMedica.getInstance().encontrarPorCodigo(codPresc);
                }
                int numPrescricao = result.getInt("NUM_PRESCRICAO");
                int codRemed = result.getInt("COD_REMEDIO");
                Remedio rem = ImplRemedioDAO.getInstance().encontrarPorCodigo(codRemed);
                
                String obsRemedio = result.getString("OBS_REMEDIO");
                int qtdRemedio = result.getInt("QTD_REMEDIO");
                a = new ItemPrescricaoMedica(p, rem.getCodigo(), rem, obsRemedio, qtdRemedio);
                lista.add(a);
            }
            if(lista.isEmpty()){
                throw new DAOException("Não foi possível encontrar cardapio");
            }
            return lista;
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

    public ItemPrescricaoMedica encontrarPorCodigo(int codPrescricao, int numRemed) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from item_prescricao_medica "
                        + "where COD_PRESCRICAO = ? "
                        + "  and NUM_REMEDIO = ? ";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, codPrescricao);
            prepared.setInt(2, numRemed);
            
            result = prepared.executeQuery();
            
            ItemPrescricaoMedica a = null;
            while(result.next()){
                int codPresc = result.getInt("COD_PRESCRICAO");
                PrescricaoMedica p = ImplPrescricaoMedica.getInstance().encontrarPorCodigo(codPresc);
                int numPrescricao = result.getInt("NUM_PRESCRICAO");
                int codRemed = result.getInt("COD_REMEDIO");
                Remedio rem = ImplRemedioDAO.getInstance().encontrarPorCodigo(codRemed);
                
                String obsRemedio = result.getString("OBS_REMEDIO");
                int qtdRemedio = result.getInt("QTD_REMEDIO");
                a = new ItemPrescricaoMedica(p, rem.getCodigo(), rem, obsRemedio, qtdRemedio);
            }
            
            if(a == null){
                throw new DAOException("Não foi possível o encontrar Cardapio! Cod = " + codPrescricao + " - " + numRemed);
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
}
