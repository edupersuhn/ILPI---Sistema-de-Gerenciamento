/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IDAO;
import Model.Cardapio;
import Util.ConectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Bruno
 */
public class ImplCardapioDAO implements IDAO<Cardapio> {

    private static ImplCardapioDAO instance;
    
    private ImplCardapioDAO(){
        
    }
    
    public static ImplCardapioDAO getInstance(){
        if(instance == null){
            instance = new ImplCardapioDAO();
        }
        return instance;
    }
    
    @Override
    public void inserir(Cardapio card) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        //TODO Fazer o insert do idoso aqui
        prepared = con.prepareStatement("insert into cardapio ("
                + "COD_CARDAPIO,"
                + "NUM_INDICE,"
                + "DAT_CRIACAO,"
                + "DAT_FIM) "
                + "values (?,?,?,?)");

        prepared.setInt(1, card.getCodigo());
        prepared.setInt(2, card.getIndice());
        prepared.setDate(3, card.getDataCriacao());
        prepared.setDate(4, card.getDataFim());

        prepared.execute();
    }

    @Override
    public void atualizar(Cardapio card) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from cardapio"
                + " where COD_CARDAPIO = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, card.getCodigo());

        result = prepared.executeQuery();

        if(!result.next()){
            inserir(card);
        }else{
            sql =  "update cardapio "
                    + "set COD_CARDAPIO = ?,"
                        + "NUM_INDICE = ?,"
                        + "DAT_CRIACAO = ?,"
                        + "DAT_FIM = ? "
                  + "where COD_CARDAPIO = ?";
            prepared = con.prepareStatement(sql);
            prepared.setInt(1, card.getCodigo());
            prepared.setInt(2, card.getIndice());
            prepared.setDate(3, card.getDataCriacao());
            prepared.setDate(4, card.getDataFim());
            prepared.setInt(5, card.getCodigo());
            prepared.execute();
        }
    }

    @Override
    public void remover(Cardapio card) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from cardapio"
                + " where COD_CARDAPIO = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, card.getCodigo());

        result = prepared.executeQuery();

        if(result.next()){
            sql = "delete cardapio "
                 + "where COD_CARDAPIO = ?";
            prepared = con.prepareStatement(sql);
            prepared.setInt(1, card.getCodigo());

            prepared.execute();
        }else{
            throw new DAOException("Não foi possível encontrar o cardapio informado! Cod: " + card.getCodigo());
        }
    }

    public List<Cardapio> encontrarTodos() throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        List<Cardapio> lista = new ArrayList<>();
        PreparedStatement prepared;
        ResultSet result;
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from alimento";
            prepared = con.prepareStatement(sql);
            
            result = prepared.executeQuery();
            
            Cardapio a = null;
//            while(result.next()){
//                int codigo = result.getInt("COD_CARDAPIO");
//                int codIdoso = result.getString("INF_NUTRICIONAL");
//                String nomAlimento = result.getString("NOM_ALIMENTO");
//                int qtdEstoq = result.getInt("QTD_ESTOQUE");
//                a = new Cardapio(codigo, infNutricional, nomAlimento,qtdEstoq);
//                lista.add(a);
//            }
            if(lista.isEmpty()){
                throw new DAOException("Não foi possível encontrar cardapio");
            }
            return lista;
    }

    public Cardapio encontrarPorCodigo(int codigo) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from cardapio "
                    + "where COD_CARDAPIO = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, codigo);

        result = prepared.executeQuery();

        Cardapio a = null;
        while(result.next()){
            int codigoCardapio = result.getInt("COD_CARDAPIO");
            int numIndice = result.getInt("NUM_INDICE");
            Date datCriacao = result.getDate("DAT_CRIACAO");
            Date datFim = result.getDate("DAT_FIM");

            a = new Cardapio(codigoCardapio,numIndice, datCriacao,datFim);
        }

        if(a == null){
            throw new DAOException("Não foi possível o encontrar Cardapio! Cod = " + codigo);
        }
        return a;
    }
    
    public Cardapio encontraPorIndice(int indice) throws SQLException, DAOException{
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from cardapio "
                    + "where NUM_INDICE = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, indice);

        result = prepared.executeQuery();

        Cardapio a = null;
        while(result.next()){
            int codigoCardapio = result.getInt("COD_CARDAPIO");
            int numIndice = result.getInt("NUM_INDICE");
            Date datCriacao = result.getDate("DAT_CRIACAO");
            Date datFim = result.getDate("DAT_FIM");

            a = new Cardapio(codigoCardapio,numIndice, datCriacao,datFim);
        }

        if(a == null){
            throw new DAOException("Não foi possível o encontrar Cardapio! Cod = " + indice);
        }
        return a;
    }
    
    public Map<Integer, String> retornaPeriodos() throws DAOException, SQLException{
        Map<Integer, String> hash = new HashMap<>();
        
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select num_indice,nom_cardapio"
                   + "  from cardapio "
                   + " order by num_indice";

        prepared = con.prepareStatement(sql);

        result = prepared.executeQuery();

        while(result.next()){
            Integer i = result.getInt("NUM_INDICE");
            String s = result.getString("NOM_CARDAPIO");
            hash.put(i, s);
        }

        if(hash.isEmpty()){
            throw new DAOException("Não foi possível encontrar Indices de Cardapio");
        }
        return hash;
    }
    
    public int encontraMaiorNumero() throws DAOException, SQLException{
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select max(cod_cardapio) + 1 as VAL"
                   + "  from cardapio ";

        prepared = con.prepareStatement(sql);

        result = prepared.executeQuery();

        if(result.next()){
            return result.getInt("VAL");
        }
        return 1;
    }
}
