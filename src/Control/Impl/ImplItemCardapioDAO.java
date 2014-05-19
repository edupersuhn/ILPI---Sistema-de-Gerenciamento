/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IDAO;
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
public class ImplItemCardapioDAO implements IDAO<ItemCardapio> {

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
    public void inserir(ItemCardapio item) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
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

        prepared.execute();
    }

    @Override
    public void atualizar(ItemCardapio item) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from item_cardapio_alimento"
                + " where COD_CARDAPIO = ? and "
                       + "NUM_ALIMENTO = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, item.getCardapio().getCodigo());
        prepared.setInt(2, item.getNumeroAlimento());

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

            prepared.execute();
        }
    }

    @Override
    public void remover(ItemCardapio item) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from item_cardapio_alimento"
                + " where COD_CARDAPIO = ? and "
                       + "NUM_ALIMENTO = ? ";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, item.getCardapio().getCodigo());
        prepared.setInt(2, item.getNumeroAlimento());

        result = prepared.executeQuery();

        if(result.next()){
            sql = "delete item_cardapio_alimento "
                 + "where COD_CARDAPIO = ? "
                   + "and NUM_ALIMENTO = ? ";
            prepared = con.prepareStatement(sql);
            prepared.setInt(1, item.getCardapio().getCodigo());
            prepared.setInt(2, item.getNumeroAlimento());
            prepared.execute();
        }else{
            throw new DAOException("Não foi possível encontrar o alimento informado! Cod: " + item.getCardapio().getCodigo() + " " + item.getNumeroAlimento());
        }
    }

    public List<ItemCardapio> encontrarTodos() throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ItemCardapio encontrarPorCodigo(int codCardapio,int numAlimento) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from item_cardapio_alimento "
                    + "where COD_CARDAPIO = ? "
                      + "and NUM_ALIMENTO = ? ";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, codCardapio);
        prepared.setInt(2, numAlimento);

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
    }
    
    public int encontrarMaiorNumero(Cardapio card) throws SQLException, DAOException{
        Connection con = ConectionManager.getInstance().getConexao();
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select max(num_cardapio) + 1 as VAL"
                   + "  from item_cardapio_alimento "
                   + " where COD_CARDAPIO = ? ";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, card.getCodigo());

        result = prepared.executeQuery();

        if(result.next()){
            return result.getInt("VAL");
        }
        return 1;
    }
}
