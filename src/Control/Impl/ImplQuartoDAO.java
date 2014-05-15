/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IDAO;
import Model.Quarto;
import Util.ConectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class ImplQuartoDAO implements IDAO<Quarto> {

    private static ImplQuartoDAO instance;
    
    private ImplQuartoDAO(){
        
    }
    
    public static ImplQuartoDAO getInstance(){
        if(instance == null){
            instance = new ImplQuartoDAO();
        }
        return instance;
    }
    
    @Override
    public void inserir(Quarto quarto) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        try {
            //TODO Fazer o insert do idoso aqui
            prepared = con.prepareStatement("insert into quarto ("
                    + "NUM_QUARTO,"
                    + "NUM_ANDAR,"
                    + "NUM_CAPACIDADE,"
                    + "ESTADO) "
                    + "values (?,?,?,?)");
            
            prepared.setInt(1, quarto.getNumQuarto());
            prepared.setInt(2, quarto.getNumAndar());
            prepared.setInt(3, quarto.getCapacidade());
            prepared.setString(4, quarto.getEstado());

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
    public void atualizar(Quarto quarto) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from quarto"
                    + " where NUM_QUARTO = ?"
                    + "   and NUM_ANDAR  = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, quarto.getNumQuarto());
            prepared.setInt(2, quarto.getNumAndar());
            
            result = prepared.executeQuery();
            
            if(!result.next()){
                inserir(quarto);
            }else{
                sql =  "update quarto "
                        + "set NUM_QUARTO = ?,"
                            + "NUM_ANDAR = ?,"
                            + "NUM_CAPACIDADE = ?,"
                            + "ESTADO = ? "
                      + "where NUM_QUARTO = ?"
                       + " and NUM_ANDAR = ?";
                prepared = con.prepareStatement(sql);
                
                prepared.setInt(1, quarto.getNumQuarto());
                prepared.setInt(2, quarto.getNumAndar());
                prepared.setInt(3, quarto.getCapacidade());
                prepared.setString(4, quarto.getEstado());
                prepared.setInt(5, quarto.getNumQuarto());
                prepared.setInt(6, quarto.getNumAndar());
                
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
    public void remover(Quarto quarto) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from quarto"
                    + " where NUM_QUARTO = ?"
                    + "   and NUM_ANDAR = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, quarto.getNumQuarto());
            prepared.setInt(2, quarto.getNumAndar());
            
            result = prepared.executeQuery();
            
            if(result.next()){
                sql = "delete quarto "
                     + "where NUM_QUARTO = ?"
                      + " and NUM_ANDAR = ? ";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, quarto.getNumQuarto());
                prepared.setInt(2, quarto.getNumAndar());
                prepared.execute();
            }else{
                throw new DAOException("Não foi possível encontrar o quarto informado! Cod: " + quarto.getNumQuarto() + " " + quarto.getNumAndar());
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

    public List<Quarto> encontrarTodos() throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        List<Quarto> lista = new ArrayList<>();
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from quarto";
            prepared = con.prepareStatement(sql);
            
            result = prepared.executeQuery();
            
            Quarto q = null;
            while(result.next()){
                int num = result.getInt("NUM_QUARTO");
                int andar = result.getInt("NUM_ANDAR");
                int capacidade = result.getInt("NUM_CAPACIDADE");
                String estado = result.getString("ESTADO");
                q = new Quarto(num, andar, capacidade, estado);
                lista.add(q);
            }
            if(lista.isEmpty()){
                throw new DAOException("Não foi possível encontrar alimentos");
            }
            Collections.sort(lista);
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

    public Quarto encontrarPorCodigo(int numero, int andar) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from quarto "
                        + "where NUM_QUARTO = ? "
                         + " and NUM_ANDAR = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, numero);
            prepared.setInt(2, andar);
            
            result = prepared.executeQuery();
            
            Quarto a = null;
            while(result.next()){
                
                int numQuarto = result.getInt("NUM_QUARTO");
                int numAndar = result.getInt("NUM_ANDAR");
                int capacidade = result.getInt("NUM_CAPACIDADE");
                String estado = result.getString("ESTADO");
                
                a = new Quarto(numQuarto, numAndar, capacidade, estado);
            }
            
            if(a == null){
                throw new DAOException("Não foi possível o encontrar encontrarPorCodigo! Cod = " + numero + " - " + andar);
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

    public List<Quarto> encontrarQuartosAndar(int andar) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        List<Quarto> lista = new ArrayList<>();
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from quarto "
                        + "where NUM_ANDAR = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, andar);
            
            result = prepared.executeQuery();
            
            Quarto a = null;
            while(result.next()){
                
                int numQuarto = result.getInt("NUM_QUARTO");
                int numAndar = result.getInt("NUM_ANDAR");
                int capacidade = result.getInt("NUM_CAPACIDADE");
                String estado = result.getString("ESTADO");
                
                a = new Quarto(numQuarto, numAndar, capacidade, estado);
                lista.add(a);
            }
            
            if(lista.size() > 0){
                throw new DAOException("Não foi possível o encontrar Quarto! Cod = " + andar);
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
}
