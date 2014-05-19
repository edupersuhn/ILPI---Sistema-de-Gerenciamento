/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IDAO;
import Model.Funcionario;
import Model.Idoso;
import Model.Incidente;
import Util.ConectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class ImplIncidenteDAO implements IDAO<Incidente> {

    private static ImplIncidenteDAO instance;
    
    private ImplIncidenteDAO(){
        
    }
    
    public static ImplIncidenteDAO getInstance(){
        if(instance == null){
            instance = new ImplIncidenteDAO();
        }
        return instance;
    }
    
    @Override
    public void inserir(Incidente inc) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        //TODO Fazer o insert do idoso aqui
        prepared = con.prepareStatement("insert into incidente ("
                + "COD_INCIDENTE,"
                + "COD_IDOSO,"
                + "COD_FUNCIONARIO,"
                + "DAT_OCORRENCIA,"
                + "DSC_INCIDENTE) "
                + "values (?,?,?,?,?)");

        prepared.setInt(1, inc.getCodigoIncidente());
        prepared.setInt(2, inc.getIdoso().getCodIdoso());
        prepared.setInt(3, inc.getFunc().getCodFuncionario());
        prepared.setDate(4, inc.getDataIncidente());
        prepared.setString(5, inc.getDescricaoIncidente());

        prepared.execute();
    }

    @Override
    public void atualizar(Incidente inc) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from incidente"
                + " where COD_INCIDENTE = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, inc.getCodigoIncidente());

        result = prepared.executeQuery();

        if(!result.next()){
            inserir(inc);
        }else{
            sql =  "update incidente "
                    + "set COD_INCIDENTE = ?,"
                        + "COD_IDOSO = ?,"
                        + "COD_FUNCIONARIO = ?,"
                        + "DAT_OCORRENCIA = ?,"
                        + "DSC_INCIDENTE = ? "
                  + "where COD_INCIDENTE = ?";

            prepared = con.prepareStatement(sql);

            prepared.setInt(1, inc.getCodigoIncidente());
            prepared.setInt(2, inc.getIdoso().getCodIdoso());
            prepared.setInt(3, inc.getFunc().getCodFuncionario());
            prepared.setDate(4, inc.getDataIncidente());
            prepared.setString(5, inc.getDescricaoIncidente());
            prepared.setInt(6, inc.getCodigoIncidente());

            prepared.execute();
        }
    }

    @Override
    public void remover(Incidente inc) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from incidente"
                + " where COD_INCIDENTE = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, inc.getCodigoIncidente());

        result = prepared.executeQuery();

        if(result.next()){
            sql = "delete incidente "
                 + "where COD_INCIDENTE = ?";
            prepared = con.prepareStatement(sql);
            prepared.setInt(1, inc.getCodigoIncidente());
            prepared.execute();
        }else{
            throw new DAOException("Não foi possível encontrar o alimento informado! Cod: " + inc.getCodigoIncidente());
        }
    }

    public List<Incidente> encontrarTodos() throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Incidente encontrarPorCodigo(int codigo) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from incidente "
                    + "where COD_INCIDENTE = ?";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, codigo);

        result = prepared.executeQuery();

        Incidente a = null;
        while(result.next()){
            int codIncidente = result.getInt("COD_INCIDENTE");
            int codIdoso = result.getInt("COD_IDOSO");
            Idoso i = ImplIdosoDAO.getInstance().encontrarPorCodigo(codIdoso);

            int codFunc = result.getInt("COD_FUNCIONARIO");
            Funcionario f = ImplFuncionarioDAO.getInstance().encontrarPorCodigo(codFunc);

            Date datOcorrencia = result.getDate("DAT_OCORRENCIA");
            String dscIncidente = result.getString("DSC_INCIDENTE");

            a = new Incidente(codIncidente, f, i,datOcorrencia,dscIncidente);
        }

        if(a == null){
            throw new DAOException("Não foi possível o encontrar alimento! Cod = " + codigo);
        }
        return a;
    }
}
