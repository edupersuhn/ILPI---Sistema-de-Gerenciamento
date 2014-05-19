/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IDAO;
import Model.Idoso;
import Model.Pertence;
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
public class ImplPertenceDAO implements IDAO<Pertence> {
    
    private static ImplPertenceDAO instance;
    
    private ImplPertenceDAO(){
        
    }
    
    public static ImplPertenceDAO getInstance(){
        if(instance == null){
            instance = new ImplPertenceDAO();
        }
        return instance;
    }

    @Override
    public void inserir(Pertence pert) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        //TODO Fazer o insert do idoso aqui
        prepared = con.prepareStatement("insert into pertence ("
                + "COD_IDOSO,"
                + "NUM_PERTENCE,"
                + "NOM_PERTENCE,"
                + "DSC_PERTENCE) "
                + "values (?,?,?,?)");

        prepared.setInt(1, pert.getIdoso().getCodIdoso());
        prepared.setInt(2, pert.getNumeroPertence());
        prepared.setString(3, pert.getNomePertence());
        prepared.setString(4, pert.getDescricao());

        prepared.execute();
    }

    @Override
    public void atualizar(Pertence pert) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from pertence"
                   + " where COD_IDOSO = ?"
                   + "   and NUM_PERTENCE = ? ";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, pert.getIdoso().getCodIdoso());
        prepared.setInt(2, pert.getNumeroPertence());

        result = prepared.executeQuery();

        if(!result.next()){
            inserir(pert);
        }else{
            sql =  "update pertence "
                    + "set COD_IDOSO = ?,"
                        + "NUM_PERTENCE = ?,"
                        + "NOM_PERTENCE = ?,"
                        + "DSC_PERTENCE = ? "
                  + "where COD_IDOSO = ? "
                  + "  and NUM_PERTENCE = ?";
            prepared = con.prepareStatement(sql);
            prepared.setInt(1, pert.getIdoso().getCodIdoso());
            prepared.setInt(2, pert.getNumeroPertence());
            prepared.setString(3, pert.getNomePertence());
            prepared.setString(4, pert.getDescricao());
            prepared.setInt(5, pert.getIdoso().getCodIdoso());
            prepared.setInt(6, pert.getNumeroPertence());

            prepared.execute();
        }
    }

    @Override
    public void remover(Pertence pert) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from pertence"
                + " where COD_IDOSO = ? "
                + "   and NUM_PERTENCE = ? ";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, pert.getIdoso().getCodIdoso());
        prepared.setInt(2, pert.getNumeroPertence());

        result = prepared.executeQuery();

        if(result.next()){
            sql = "delete pertence "
                 + "where COD_IDOSO = ? "
                + "   and NUM_PERTENCE = ? ";
            prepared = con.prepareStatement(sql);
            prepared.setInt(1, pert.getIdoso().getCodIdoso());
            prepared.setInt(2, pert.getNumeroPertence());
            prepared.execute();
        }else{
            throw new DAOException("Não foi possível encontrar o alimento informado! Cod: " + pert.getIdoso().getCodIdoso() + " " + pert.getNomePertence());
        }
    }

    public List<Pertence> encontrarTodos(int codigoIdoso) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        List<Pertence> lista = new ArrayList<>();
        PreparedStatement prepared;
        ResultSet result;
        PreparedStatement prepared2;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from pertence"
                   + " where COD_IDOSO = ? ";
        prepared = con.prepareStatement(sql);
        prepared.setInt(1, codigoIdoso);
        result = prepared.executeQuery();

        Pertence a = null;
        while(result.next()){
            int codIdoso = result.getInt("COD_IDOSO");
            Idoso i = ImplIdosoDAO.getInstance().encontrarPorCodigo(codIdoso);

            int numPertence = result.getInt("NUM_PERTENCE");
            String nomPertence = result.getString("NOM_PERTENCE");
            String dscPertence = result.getString("DSC_PERTENCE");
            a = new Pertence(i, numPertence,nomPertence,dscPertence);
            lista.add(a);
        }
        if(lista.size() == 0){
            throw new DAOException("Não foi possível encontrar alimentos");
        }
        return lista;
    }

    public Pertence encontrarPorCodigo(int codigo, int numPertence) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from pertence "
                    + "where COD_PERTENCE = ?"
                    + "  and NUM_PERTENCE = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, codigo);
        prepared.setInt(2, numPertence);

        result = prepared.executeQuery();

        Pertence a = null;
        while(result.next()){
            int codIdoso = result.getInt("COD_IDOSO");
            int numPert = result.getInt("NUM_PERTENCE");
            String nomePertence = result.getString("NOM_PERTENCE");
            String dscPertence = result.getString("DSC_PERTENCE");

            Idoso idoso = ImplIdosoDAO.getInstance().encontrarPorCodigo(codIdoso);

            a = new Pertence(idoso, numPert, nomePertence,dscPertence);
        }

        if(a == null){
            throw new DAOException("Não foi possível o encontrar alimento! Cod = " + codigo);
        }
        return a;
    }
}
