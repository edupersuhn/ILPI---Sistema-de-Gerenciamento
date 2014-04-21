/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IQuartoDAO;
import Model.Alimento;
import Model.Cardapio;
import Model.Idoso;
import Model.Quarto;
import Util.ConectionManager;
import java.sql.Connection;
import java.sql.Date;
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
public class ImplQuartoDAO implements IQuartoDAO{

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
    public void inserir(Quarto quarto) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
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

            result = prepared.executeQuery();
            
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao inserir quarto.");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void atualizar(Quarto quarto) throws DAOException {
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
                
                result = prepared.executeQuery();
            }
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao atualizar quarto.");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void remover(Quarto quarto) throws DAOException {
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
                result = prepared.executeQuery();
            }else{
                throw new DAOException("Não foi possível encontrar o quarto informado! Cod: " + quarto.getNumQuarto() + " " + quarto.getNumAndar());
            }
                
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao remover o quarto.");
            System.out.println(ex.getMessage());
        } catch(DAOException dao){
            System.out.println("Erro ao tentar remover quarto! ");
            System.out.println(dao.getMessage());
        }
    }

    @Override
    public List<Quarto> encontrarTodos() throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        List<Quarto> lista = new ArrayList<Quarto>();
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
            if(lista.size() == 0){
                throw new DAOException("Não foi possível encontrar alimentos");
            }
            Collections.sort(lista);
            return lista;
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao excluir o alimento.");
            System.out.println(ex.getMessage());
            return null;
        } catch(DAOException dao){
            System.out.println("Erro ao tentar excluir alimento! ");
            System.out.println(dao.getMessage());
            return null;
        }
    }

    @Override
    public Quarto encontrarPorCodigo(int numero, int andar) throws DAOException {
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
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao encontrarPorCodigo o quarto.");
            System.out.println(ex.getMessage());
            return null;
        } catch(DAOException dao){
            System.out.println("Erro ao tentar encontrarPorCodigo o quarto! ");
            System.out.println(dao.getMessage());
            return null;
        }
    }

    @Override
    public List<Quarto> encontrarQuartosAndar(int andar) throws DAOException {
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
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao encontrarQuartosAndar o quarto.");
            System.out.println(ex.getMessage());
            return null;
        } catch(DAOException dao){
            System.out.println("Erro ao tentar encontrarQuartosAndar o quarto! ");
            System.out.println(dao.getMessage());
            return null;
        }
    }
    
}
