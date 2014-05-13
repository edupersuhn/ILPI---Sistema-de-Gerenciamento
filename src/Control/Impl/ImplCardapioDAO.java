/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IDAO;
import Model.Cardapio;
import Model.Idoso;
import Util.ConectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        try {
            //TODO Fazer o insert do idoso aqui
            prepared = con.prepareStatement("insert into cardapio ("
                    + "COD_CARDAPIO,"
                    + "COD_IDOSO,"
                    + "DAT_CRIACAO,"
                    + "DAT_FIM) "
                    + "values (?,?,?,?)");
            
            prepared.setInt(1, card.getCodigo());
            prepared.setInt(2,card.getIdoso().getCodIdoso());
            prepared.setDate(3, card.getDataCriacao());
            prepared.setDate(4, card.getDataFim());

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
    public void atualizar(Cardapio card) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
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
                            + "COD_IDOSO = ?,"
                            + "DAT_CRIACAO = ?,"
                            + "DAT_FIM = ? "
                      + "where COD_CARDAPIO = ?";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, card.getCodigo());
                prepared.setInt(2, card.getIdoso().getCodIdoso());
                prepared.setDate(3, card.getDataCriacao());
                prepared.setDate(4, card.getDataFim());
                prepared.setInt(5, card.getCodigo());
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
    public void remover(Cardapio card) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
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

    public List<Cardapio> encontrarTodos() throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        List<Cardapio> lista = new ArrayList<>();
        PreparedStatement prepared;
        ResultSet result;
        try {
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

    public Cardapio encontrarPorCodigo(int codigo) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from cardapio "
                        + "where COD_CARDAPIO = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, codigo);
            
            result = prepared.executeQuery();
            
            Cardapio a = null;
            while(result.next()){
                int codigoCardapio = result.getInt("COD_CARDAPIO");
                int codIdoso = result.getInt("COD_IDOSO");
                Date datCriacao = result.getDate("DAT_CRIACAO");
                Date datFim = result.getDate("DAT_FIM");
                
                Idoso idoso = ImplIdosoDAO.getInstance().encontrarPorCodigo(codIdoso);
                
                a = new Cardapio(idoso, codigoCardapio, datCriacao,datFim);
            }
            
            if(a == null){
                throw new DAOException("Não foi possível o encontrar Cardapio! Cod = " + codigo);
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
