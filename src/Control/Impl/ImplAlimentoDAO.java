/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IDAO;
import Model.Alimento;
import Util.ConectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Bruno
 */
public class ImplAlimentoDAO implements IDAO<Alimento> {

    private static ImplAlimentoDAO instance;
    
    private ImplAlimentoDAO(){
        
    }
    
    public static ImplAlimentoDAO getInstance(){
        if(instance == null){
            instance = new ImplAlimentoDAO();
        }
        return instance;
    }
    
    @Override
    public void inserir(Alimento ali) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        //TODO Fazer o insert do idoso aqui
        prepared = con.prepareStatement("insert into alimento ("
                + "COD_ALIMENTO,"
                + "INF_NUTRICIONAL,"
                + "NOM_ALIMENTO,"
                + "QTD_ESTOQUE) "
                + "values (?,?,?,?)");

        prepared.setInt(1, ali.getCodigo());
        prepared.setString(2,ali.getInfoNutricional());
        prepared.setString(3, ali.getNomeAlimento());
        prepared.setInt(4, ali.getQtdEstoque());

        prepared.execute();
    }

    @Override
    public void atualizar(Alimento ali) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from alimento"
                + " where COD_ALIMENTO = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, ali.getCodigo());

        result = prepared.executeQuery();

        Alimento a = null;
        while(result.next()){
            int codigo = result.getInt("COD_ALIMENTO");
            String infNutricional = result.getString("INF_NUTRICIONAL");
            String nomAlimento = result.getString("NOM_ALIMENTO");
            int qtdEstoq = result.getInt("QTD_ESTOQUE");
            //String refeicao = result.getString("DSC_REFEICAO");
            a = new Alimento(codigo, infNutricional, nomAlimento,qtdEstoq, "" /*refeicao*/);
        }
        if(a == null){
            inserir(ali);
        }else{
            sql = "update alimento "
                    + "set INF_NUTRICIONAL = ?,"
                        + "NOM_ALIMENTO = ?,"
                        + "QTD_ESTOQ = ?"
                  + "where COD_ALIMENTO = ?";
            prepared = con.prepareStatement(sql);
            prepared.setString(1, ali.getInfoNutricional());
            prepared.setString(2, ali.getNomeAlimento());
            prepared.setInt(3, ali.getQtdEstoque());
            prepared.setInt(4, ali.getCodigo());

            prepared.execute();
        }
    }

    @Override
    public void remover(Alimento ali) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from alimento"
                + " where COD_ALIMENTO = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, ali.getCodigo());

        result = prepared.executeQuery();

        if(result.next()){
            sql = "delete alimento "
                    + "where COD_ALIMENTO = ?";
            prepared = con.prepareStatement(sql);
            prepared.setInt(1, ali.getCodigo());
            prepared.execute();
        }else{
            throw new DAOException("Não foi possível encontrar o alimento informado! Cod: " + ali.getCodigo());
        }
    }

    public List<Alimento> encontrarTodos() throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        List<Alimento> lista = new ArrayList<>();
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from alimento";
        prepared = con.prepareStatement(sql);

        result = prepared.executeQuery();

        Alimento a = null;
        while(result.next()){
            int codigo = result.getInt("COD_ALIMENTO");
            String infNutricional = result.getString("INF_NUTRICIONAL");
            String nomAlimento = result.getString("NOM_ALIMENTO").replaceAll(" ", "");
            int qtdEstoq = result.getInt("QTD_ESTOQUE");
            //String refeicao = result.getString("DSC_REFEICAO");
            a = new Alimento(codigo, infNutricional, nomAlimento,qtdEstoq, "" /*refeicao*/);
            lista.add(a);
        }
        if(lista.isEmpty()){
            throw new DAOException("Não foi possível encontrar alimentos");
        }
        Collections.sort(lista);
        return lista;
    }

    public Alimento encontrarPorCodigo(int codigo) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from alimento "
                + "where COD_ALIMENTO = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, codigo);

        result = prepared.executeQuery();

        Alimento a = null;
        while(result.next()){
            int codigoAlimento = result.getInt("COD_ALIMENTO");
            String infNutricional = result.getString("INF_NUTRICIONAL");
            String nomAlimento = result.getString("NOM_ALIMENTO");
            int qtdEstoq = result.getInt("QTD_ESTOQUE");
            //String refeicao = result.getString("DSC_REFEICAO");
            a = new Alimento(codigoAlimento, infNutricional, nomAlimento,qtdEstoq, "" /*refeicao*/);
        }

        if(a == null){
            throw new DAOException("Não foi possível o encontrar alimento! Cod = " + codigo);
        }
        return a;
    }

    public Alimento encontrarPorNome(String nome) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from alimento "
                + "where NOM_ALIMENTO = ?";
        prepared = con.prepareStatement(sql);

        prepared.setString(1, nome);

        result = prepared.executeQuery();

        Alimento a = null;
        while(result.next()){
            int codigoAlimento = result.getInt("COD_ALIMENTO");
            String infNutricional = result.getString("INF_NUTRICIONAL");
            String nomAlimento = result.getString("NOM_ALIMENTO");
            int qtdEstoq = result.getInt("QTD_ESTOQUE");
            //String refeicao = result.getString("DSC_REFEICAO");
            a = new Alimento(codigoAlimento, infNutricional, nomAlimento,qtdEstoq, "" /*refeicao*/);
        }

        if(a == null){
            throw new DAOException("Não foi possível o encontrar alimento! Nom = " + nome);
        }
        return a;
    }
    
    public int encontrarCodMax() throws SQLException, DAOException{
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select max(cod_alimento) + 1 as VAL "
                   + " from alimento ";
        prepared = con.prepareStatement(sql);

        result = prepared.executeQuery();
        if(result.next()){
            int cod = result.getInt("VAL");
            return cod;
        }else{
            return 1;
        }
    }
}
