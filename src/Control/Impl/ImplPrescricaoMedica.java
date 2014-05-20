/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IDAO;
import Model.Idoso;
import Model.ItemPrescricaoMedica;
import Model.PrescricaoMedica;
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
public class ImplPrescricaoMedica implements IDAO<PrescricaoMedica> {

    private static ImplPrescricaoMedica instance;

    private ImplPrescricaoMedica() {
    }

    public static ImplPrescricaoMedica getInstance() {
        if (instance == null) {
            instance = new ImplPrescricaoMedica();
        }
        return instance;
    }

    @Override
    public void inserir(PrescricaoMedica pres) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();

        PreparedStatement prepared;
        try {
            //TODO Fazer o insert do idoso aqui
            prepared = con.prepareStatement("insert into prescricao_medica ("
                    + "COD_PRESCRICAO,"
                    + "COD_IDOSO,"
                    + "DSC_OBSERVACAO,"
                    + "DAT_PRESCRICAO,"
                    + "HORA_PRESCRICAO) "
                    + "values (?,?,?,?,?)");

            prepared.setInt(1, pres.getCodigoPrescricao());
            prepared.setInt(2, pres.getIdoso().getCodIdoso());
            prepared.setString(3, pres.getObservacao());
            prepared.setDate(4, pres.getDataPrescricao());
            prepared.setInt(5, pres.getHoraPrescricao());

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
    public void atualizar(PrescricaoMedica pres) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();

        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from prescricao_medica"
                    + " where cod_prescricao = ?";
            prepared = con.prepareStatement(sql);

            prepared.setInt(1, pres.getCodigoPrescricao());

            result = prepared.executeQuery();

            if (!result.next()) {
                inserir(pres);
            } else {
                sql = "update prescricao_medica "
                        + "set COD_PRESCRICAO = ?,"
                        + "COD_IDOSO = ?,"
                        + "DSC_OBSERVACAO = ?,"
                        + "DAT_PRESCRICAO = ?,"
                        + "HORA_PRESCRICAO = ?"
                        + "where COD_PRESCRICAO = ?";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, pres.getCodigoPrescricao());
                prepared.setInt(2, pres.getIdoso().getCodIdoso());
                prepared.setString(3, pres.getObservacao());
                prepared.setDate(4, pres.getDataPrescricao());
                prepared.setInt(5, pres.getHoraPrescricao());
                prepared.setInt(6, pres.getCodigoPrescricao());

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
    public void remover(PrescricaoMedica pres) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();

        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from prescricao_medica"
                    + " where COD_PRESCRICAO = ?";
            prepared = con.prepareStatement(sql);

            prepared.setInt(1, pres.getCodigoPrescricao());

            result = prepared.executeQuery();

            if (result.next()) {
                sql = "delete prescricao_medica "
                        + "where COD_PRESCRICAO = ?";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, pres.getCodigoPrescricao());
                prepared.execute();
            } else {
                throw new DAOException("Não foi possível encontrar a prescricao informada! Cod: " + pres.getCodigoPrescricao());
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
    
    public List<PrescricaoMedica> encontrarTodos() throws DAOException, SQLException {
        
        
        Connection con = ConectionManager.getInstance().getConexao();

        PreparedStatement prepared;
        ResultSet result;
        List<PrescricaoMedica> lista = new ArrayList<>();
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from prescricao_medica";
            prepared = con.prepareStatement(sql);

            result = prepared.executeQuery();

            PrescricaoMedica a = null;
            while (result.next()) {
                int codPrescricao = result.getInt("COD_PRESCRICAO");
                int codIdoso = result.getInt("COD_IDOSO");
                String dscObservacao = result.getString("DSC_OBSERVACAO");
                Date datPrescricao = result.getDate("DAT_PRESCRICAO");
                int horaPrescricao = result.getInt("HORA_PRESCRICAO");
                Idoso idoso = ImplIdosoDAO.getInstance().encontrarPorCodigo(codIdoso);

                a = new PrescricaoMedica(idoso, codPrescricao, dscObservacao, datPrescricao, horaPrescricao);
                lista.add(a);
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
    
    public List<PrescricaoMedica> encontrarTodos(int cdIdoso) throws DAOException, SQLException {
        
        
        Connection con = ConectionManager.getInstance().getConexao();

        PreparedStatement prepared;
        ResultSet result;
        List<PrescricaoMedica> lista = new ArrayList<>();
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from prescricao_medica "
                       + "where COD_IDOSO = ?";
            
            prepared = con.prepareStatement(sql);
            prepared.setInt(1, cdIdoso);

            result = prepared.executeQuery();

            PrescricaoMedica a = null;
            while (result.next()) {
                int codPrescricao = result.getInt("COD_PRESCRICAO");
                int codIdoso = result.getInt("COD_IDOSO");
                String dscObservacao = result.getString("DSC_OBSERVACAO");
                Date datPrescricao = result.getDate("DAT_PRESCRICAO");
                int horaPrescricao = result.getInt("HORA_PRESCRICAO");
                Idoso idoso = ImplIdosoDAO.getInstance().encontrarPorCodigo(codIdoso);

                a = new PrescricaoMedica(idoso, codPrescricao, dscObservacao, datPrescricao, horaPrescricao);
                lista.add(a);
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
    
    public List<ItemPrescricaoMedica> encontrarTodosItens(int codigo) throws DAOException, SQLException {
        return ImplItemPrescricaoMedica.getInstance().encontrarTodos(codigo);
    }

    public PrescricaoMedica encontrarPorCodigo(int codigo) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();

        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from prescricao_medica "
                    + "where COD_PRESCRICAO = ?";
            prepared = con.prepareStatement(sql);

            prepared.setInt(1, codigo);

            result = prepared.executeQuery();

            PrescricaoMedica a = null;
            while (result.next()) {
                int codPrescricao = result.getInt("COD_PRESCRICAO");
                int codIdoso = result.getInt("COD_IDOSO");
                String dscObservacao = result.getString("DSC_OBSERVACAO");
                Date datPrescricao = result.getDate("DAT_PRESCRICAO");
                int horaPrescricao = result.getInt("HORA_PRESCRICAO");
                Idoso idoso = ImplIdosoDAO.getInstance().encontrarPorCodigo(codIdoso);

                a = new PrescricaoMedica(idoso, codPrescricao, dscObservacao, datPrescricao, horaPrescricao);
            }

            if (a == null) {
                throw new DAOException("Não foi possível o encontrar a prescricao! Cod = " + codigo);
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
