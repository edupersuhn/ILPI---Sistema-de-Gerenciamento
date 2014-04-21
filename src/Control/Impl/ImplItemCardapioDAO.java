/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IItemCardapioDAO;
import Model.Alimento;
import Model.Cardapio;
import Model.ItemCardapio;
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
public class ImplItemCardapioDAO implements IItemCardapioDAO{

    private static ImplItemCardapioDAO instance;
    
    private ImplItemCardapioDAO(){
        
    }
    
    public static ImplItemCardapioDAO getInstance(){
        if(instance == null){
            instance = new ImplItemCardapioDAO();
        }
        return instance;
    }
    
    @Override
    public void inserir(ItemCardapio item) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            prepared = con.prepareStatement("insert into item_cardapio_alimento ("
                    + "COD_CARDAPIO,"
                    + "COD_ALIMENTO,"
                    + "QTD_PRODUZIDA,"
                    + "NUM_ALIMENTO) "
                    + "values (?,?,?,?)");
            
            prepared.setInt(1, item.getCardapio().getCodigo());
            prepared.setInt(2, item.getAlimento().getCodigo());
            prepared.setInt(3, item.getQtdProduzida());
            prepared.setInt(4, item.getNumeroAlimento());

            result = prepared.executeQuery();
            
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao inserir alimento.");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void atualizar(ItemCardapio item) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from item_cardapio_alimento"
                    + " where COD_CARDAPIO = ? and "
                           + "NUM_ALIMENTO = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, item.getCardapio().getCodigo());
            prepared.setInt(1, item.getNumeroAlimento());
            
            result = prepared.executeQuery();
            
            if(!result.next()){
                inserir(item);
            }else{
                sql =  "update item_cardapio_alimento "
                        + "set COD_CARDAPIO = ?,"
                            + "COD_ALIMENTO = ?,"
                            + "QTD_PROD = ?,"
                            + "NUM_ALIMENTO = ?"
                      + "where COD_CARDAPIO = ?";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, item.getCardapio().getCodigo());
                prepared.setInt(2, item.getAlimento().getCodigo());
                prepared.setInt(3, item.getQtdProduzida());
                prepared.setInt(4, item.getNumeroAlimento());
                prepared.setInt(5, item.getCardapio().getCodigo());
            }
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao atualizar alimento.");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void remover(ItemCardapio item) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from item_cardapio_alimento"
                    + " where COD_CARDAPIO = ? and "
                           + "NUM_ALIMENTO = ? ";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, item.getCardapio().getCodigo());
            prepared.setInt(1, item.getNumeroAlimento());
            
            result = prepared.executeQuery();
            
            if(result.next()){
                sql = "delete item_cardapio_alimento "
                     + "where COD_CARDAPIO = ? "
                       + "and NUM_ALIMENTO = ? ";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, item.getCardapio().getCodigo());
                prepared.setInt(1, item.getNumeroAlimento());
                result = prepared.executeQuery();
            }else{
                throw new DAOException("Não foi possível encontrar o alimento informado! Cod: " + item.getCardapio().getCodigo() + " " + item.getNumeroAlimento());
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
    public List<ItemCardapio> encontrarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ItemCardapio encontrarPorCodigo(int codCardapio,int numAlimento) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from item_cardapio_alimento "
                        + "where COD_CARDAPIO = ? "
                          + "and NUM_ALIMENTO = ? ";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, codCardapio);
            prepared.setInt(1, numAlimento);
            
            result = prepared.executeQuery();
            
            ItemCardapio a = null;
            while(result.next()){
                int codCard = result.getInt("COD_CARDAPIO");
                int codAlimento = result.getInt("COD_ALIMENTO");
                int qtdProduzida = result.getInt("QTD_PRODUZIDA");
                int numAli = result.getInt("NUM_ALIMENTO");
                
                Cardapio cardapio = ImplCardapioDAO.getInstance().encontrarPorCodigo(codCard);
                Alimento alimento = ImplAlimentoDAO.getInstance().encontrarPorCodigo(codAlimento);
                
                a = new ItemCardapio(cardapio, alimento, qtdProduzida,numAli);
                //lista.add(a);
            }
            
            if(a == null){
                throw new DAOException("Não foi possível o encontrar alimento! Cod = " + codCardapio + " " + numAlimento);
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
