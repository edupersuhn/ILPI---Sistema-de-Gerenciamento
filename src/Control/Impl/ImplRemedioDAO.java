/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IDAO;
import Model.Remedio;
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
public class ImplRemedioDAO implements IDAO<Remedio> {

    private static ImplRemedioDAO instance;
    
    private ImplRemedioDAO(){
        
    }
    
    public static ImplRemedioDAO getInstance(){
        if(instance == null){
            instance = new ImplRemedioDAO();
        }
        return instance;
    }
    
    @Override
    public void inserir(Remedio rem) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        //TODO Fazer o insert do idoso aqui
        prepared = con.prepareStatement("insert into remedio ("
                + "COD_REMEDIO,"
                + "NOM_REMEDIO,"
                + "QTD_ESTOQ,"
                + "DSC_UNIDADE_MEDIDA) "
                + "values (?,?,?,?)");

        prepared.setInt(1, rem.getCodigo());
        prepared.setString(2, rem.getNomeRemedio());
        prepared.setInt(3, rem.getQtdEstoque());
        prepared.setString(4, rem.getUnidadeMedida());

        prepared.execute();
    }

    @Override
    public void atualizar(Remedio rem) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from remedio"
                + " where COD_REMEDIO = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, rem.getCodigo());

        result = prepared.executeQuery();
        if(!result.next()){
            inserir(rem);
        }else{
            sql =  "update remedio "
                    + "set COD_REMEDIO = ?,"
                        + "NOM_REMEDIO = ?,"
                        + "QTD_ESTOQ = ? ,"
                        + "DSC_UNIDADE_MEDIDA = ?"
                  + "where COD_REMEDIO = ?";
            prepared = con.prepareStatement(sql);

            prepared.setInt(1, rem.getCodigo());
            prepared.setString(2, rem.getNomeRemedio());
            prepared.setInt(3, rem.getQtdEstoque());
            prepared.setString(4, rem.getUnidadeMedida());
            prepared.setInt(5, rem.getCodigo());

            prepared.execute();
        }
    }

    @Override
    public void remover(Remedio rem) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from remedio"
                + " where COD_REMEDIO = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, rem.getCodigo());

        result = prepared.executeQuery();

        if(result.next()){
            sql = "delete remedio "
                 + "where COD_REMEDIO = ?";
            prepared = con.prepareStatement(sql);
            prepared.setInt(1, rem.getCodigo());

            prepared.execute();
        }else{
            throw new DAOException("Não foi possível encontrar o cardapio informado! Cod: " + rem.getCodigo());
        }
    }

    public List<Remedio> encontrarTodos() throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        List<Remedio> lista = new ArrayList<>();
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from remedio";
        prepared = con.prepareStatement(sql);

        result = prepared.executeQuery();

        Remedio r = null;
        while(result.next()){
            int codigo = result.getInt("COD_REMEDIO");
            String nomRemedio = result.getString("NOM_REMEDIO");
            int qtdEstoq = result.getInt("QTD_ESTOQ");
            String uniMedida = result.getString("DSC_UNIDADE_MEDIDA");
            r = new Remedio(codigo, nomRemedio, qtdEstoq, uniMedida);
            lista.add(r);
        }
        if(lista.isEmpty()){
            throw new DAOException("Não foi possível encontrar alimentos");
        }
        Collections.sort(lista);
        return lista;
    }

    public Remedio encontrarPorCodigo(int codigo) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from remedio "
                    + "where COD_REMEDIO = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, codigo);

        result = prepared.executeQuery();

        Remedio a = null;
        while(result.next()){
            int codRemedio = result.getInt("COD_REMEDIO");
            String nomRemedio = result.getString("NOM_REMEDIO");
            int qtdEstoq = result.getInt("QTD_ESTOQ");
            String uniMedida = result.getString("UNIDADE_MEDIDA");
            a = new Remedio(codRemedio, nomRemedio, qtdEstoq, uniMedida);
        }

        if(a == null){
            throw new DAOException("Não foi possível o encontrar Cardapio! Cod = " + codigo);
        }
        return a;
    }

    public Remedio encontrarPorNome(String nome) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        System.out.println("Nome: " + nome);
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from remedio "
                    + "where NOM_REMEDIO = ?";
        prepared = con.prepareStatement(sql);

        prepared.setString(1, nome);

        result = prepared.executeQuery();

        Remedio a = null;
        while(result.next()){
            int codRemedio = result.getInt("COD_REMEDIO");
            String nomRemedio = result.getString("NOM_REMEDIO");
            int qtdEstoq = result.getInt("QTD_ESTOQ");
            String uniMedida = result.getString("DSC_UNIDADE_MEDIDA");
            a = new Remedio(codRemedio, nomRemedio, qtdEstoq, uniMedida);
        }
        return a;
    }
}
