/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IDAO;
import Model.Idoso;
import Util.ConectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author Bruno
 */
public class ImplIdosoDAO implements IDAO<Idoso> {

    private static ImplIdosoDAO instance;
    
    private ImplIdosoDAO(){
        
    }
    
    public static ImplIdosoDAO getInstance(){
        if(instance == null){
            instance = new ImplIdosoDAO();
        }
        return instance;
    }
    
    @Override
    public void inserir(Idoso idoso) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        try {
            //TODO Fazer o insert do idoso aqui
            prepared = con.prepareStatement("insert into idoso ("
                    + "COD_IDOSO,"
                    + "NOM_IDOSO,"
                    + "DAT_NASCIMENTO,"
                    + "ACAMADO,"
                    + "NUM_RG,"
                    + "NOM_PARENT_RESP,"
                    + "DSC_END_PARENT,"
                    + "NUM_TEL_PARENT,"
                    + "LOCAL_ORIGEM,"
                    + "DSC_ENDERECO,"
                    + "DSC_CUIDADOS_ESP) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?)");
            
            prepared.setInt(1, idoso.getCodIdoso());
            prepared.setString(2, idoso.getNomeIdoso());
            prepared.setDate(3, idoso.getDataNascimento());
            prepared.setBoolean(4, idoso.getAcamado());
            prepared.setInt(5, idoso.getRg());
            prepared.setString(6, idoso.getNomeParenteResponsavel());
            prepared.setString(7, idoso.getEndParente());
            prepared.setString(8, idoso.getNumTelefoneParente());
            prepared.setString(9, idoso.getLocalOrigem());
            prepared.setString(10,"");
            prepared.setString(11, idoso.getCuidadosEspeciais());

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
    public void atualizar(Idoso idoso) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from idoso"
                    + " where COD_IDOSO = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, idoso.getCodIdoso());
            
            result = prepared.executeQuery();
            
            if(!result.next()){
                inserir(idoso);
            }else{
                sql =  "update idoso "
                        + "set COD_IDOSO = ?,"
                            + "NOM_IDOSO = ?,"
                            + "DAT_NASCIMENTO = ?,"
                            + "LOCAL_ORIGEM = ? "
                            + "NUM_TELEFONE = ?,"
                            + "DSC_ENDERECO = ?,"
                            + "DSC_CUIDADO_ESP = ? "
                      + "where COD_IDOSO = ?";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, idoso.getCodIdoso());
                prepared.setString(2, idoso.getNomeIdoso());
                prepared.setDate(3, idoso.getDataNascimento());
                prepared.setString(4, idoso.getLocalOrigem());
                prepared.setString(5, idoso.getNumTelefoneParente());
                prepared.setString(6, idoso.getEndParente());
                prepared.setString(7, idoso.getCuidadosEspeciais());
                prepared.setInt(8, idoso.getCodIdoso());
                
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
    public void remover(Idoso idoso) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from idoso"
                    + " where COD_IDOSO = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, idoso.getCodIdoso());
            
            result = prepared.executeQuery();
            
            if(result.next()){
                sql = "delete IDOSO "
                     + "where COD_IDOSO = ?";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, idoso.getCodIdoso());
                prepared.execute();
            }else{
                throw new DAOException("Não foi possível encontrar o alimento informado! Cod: " + idoso.getCodIdoso());
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

    public List<Idoso> encontrarTodosIdosos() throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        TreeSet<Idoso> set = new TreeSet<>();
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from idoso";
            prepared = con.prepareStatement(sql);
            
            result = prepared.executeQuery();
            
            Idoso a = null;
            while(result.next()){
                int codIdoso = result.getInt("COD_IDOSO");
                String nome = result.getString("NOM_IDOSO");
                Date data = result.getDate("DAT_NASCIMENTO");
                String local = result.getString("LOCAL_ORIGEM");
                String fone = result.getString("NUM_TEL_PARENT");
                String end = result.getString("DSC_END_PARENT");
                String cuidados = result.getString("DSC_CUIDADOS_ESP");
                boolean acamado = result.getBoolean("ACAMADO");
                String cpf = result.getString("NUM_CPF");
                int rg = result.getInt("NUM_RG");
                a = new Idoso(codIdoso, nome, data, local, fone, end, cpf, rg, cuidados, acamado);
                
                set.add(a);
            }
            if(set.isEmpty()){
                throw new DAOException("Não foi possível encontrar alimentos");
            }
            ArrayList<Idoso> list = new ArrayList<>();
            for (Iterator<Idoso> it = set.iterator(); it.hasNext();) {
                Idoso idoso = it.next();
                list.add(idoso);
            }
            return list;
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
    
    public List<Idoso> encontrarTodosEvento(int evento) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        List<Idoso> lista = new ArrayList<>();
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from idoso_evento "
                        + "where COD_EVENTO = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, evento);
            
            result = prepared.executeQuery();
            
            Idoso a = null;
            while(result.next()){
                int codIdoso = result.getInt("COD_IDOSO");
                
                a = encontrarPorCodigo(codIdoso);
                lista.add(a);
            }
            if(lista.isEmpty()){
                throw new DAOException("Não foi possível encontrar alimentos");
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

    public Idoso encontrarPorCodigo(int codigo) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from idoso "
                        + "where COD_IDOSO = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, codigo);
            
            result = prepared.executeQuery();
            
            Idoso a = null;
            while(result.next()){
                int codIdoso = result.getInt("COD_IDOSO");
                String nome = result.getString("NOM_IDOSO");
                Date data = result.getDate("DAT_NASCIMENTO");
                String local = result.getString("LOCAL_ORIGEM");
                String fone = result.getString("NUM_TEL_PARENT");
                String end = result.getString("DSC_END_PARENT");
                String cuidados = result.getString("DSC_CUIDADOS_ESP");
                boolean acamado = result.getBoolean("ACAMADO");
                String cpf = result.getString("NUM_CPF");
                int rg = result.getInt("NUM_RG");
                a = new Idoso(codIdoso, nome, data, local, fone, end, cpf, rg, cuidados, acamado);
            }
            
            if(a == null){
                throw new DAOException("Não foi possível o encontrar alimento! Cod = " + codigo);
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
