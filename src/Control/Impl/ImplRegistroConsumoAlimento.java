/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IDAO;
import Model.Funcionario;
import Model.ItemCardapio;
import Model.RegistroConsumoAlimento;
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
public class ImplRegistroConsumoAlimento implements IDAO<RegistroConsumoAlimento> {

    private static ImplRegistroConsumoAlimento instance;
    
    private ImplRegistroConsumoAlimento(){
        
    }
    
    public static ImplRegistroConsumoAlimento getInstance(){
        if(instance == null){
            instance = new ImplRegistroConsumoAlimento();
        }
        return instance;
    }
    
    @Override
    public void inserir(RegistroConsumoAlimento reg) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        //TODO Fazer o insert do idoso aqui
        prepared = con.prepareStatement("insert into registro_consumo_alimento ("
                + "COD_CARDAPIO,"
                + "NUM_ALIMENTO,"
                + "DAT_CONSUMO,"
                + "NUM_HORA_CONSUMO,"
                + "COD_FUNCIONARIO) "
                + "values (?,?,?,?,?)");

        prepared.setInt(1, reg.getItem().getCardapio().getCodigo());
        prepared.setInt(2, reg.getItem().getNumeroAlimento());
        prepared.setDate(3, reg.getDataConsumo());
        prepared.setInt(4, reg.getHoraConsumo());
        prepared.setInt(5, reg.getFuncionario().getCodFuncionario());

        prepared.execute();
    }

    @Override
    public void atualizar(RegistroConsumoAlimento reg) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from registro_consumo_alimento"
                + " where COD_CARDAPIO = ? "
                + "   and NUM_ALIMENTO = ? ";
        prepared = con.prepareStatement(sql);

        prepared.setInt(1, reg.getItem().getCardapio().getCodigo());
        prepared.setInt(2, reg.getItem().getNumeroAlimento());

        result = prepared.executeQuery();

        if(!result.next()){
            inserir(reg);
        }else{
            sql =  "update registro_consumo_alimento "
                    + "set COD_CARDAPIO = ?,"
                        + "NUM_ALIMENTO = ?,"
                        + "DAT_CONSUMO = ?,"
                        + "NUM_HORA_CONSUMO = ? "
                        + "COD_FUNCIONARIO = ? "
                  + "where COD_CARDAPIO = ?"
                   + " and NUM_ALIMENTO = ?";

            prepared = con.prepareStatement(sql);
            prepared.setInt(1, reg.getItem().getCardapio().getCodigo());
            prepared.setInt(2, reg.getItem().getNumeroAlimento());
            prepared.setDate(3, reg.getDataConsumo());
            prepared.setInt(4, reg.getHoraConsumo());
            prepared.setInt(5, reg.getFuncionario().getCodFuncionario());
            prepared.setInt(6, reg.getItem().getCardapio().getCodigo());
            prepared.setInt(7, reg.getItem().getNumeroAlimento());

            prepared.execute();
        }
    }

    @Override
    public void remover(RegistroConsumoAlimento reg) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from registro_consumo_alimento"
                   + " where COD_CARDAPIO = ? "
                   + "   and NUM_ALIMENTO = ? ";

        prepared = con.prepareStatement(sql);

        prepared.setInt(1, reg.getItem().getCardapio().getCodigo());
        prepared.setInt(2, reg.getItem().getNumeroAlimento());

        result = prepared.executeQuery();

        if(result.next()){
            sql = "delete registro_consumo_alimento "
                 + "where COD_CARDAPIO = ? "
                 + "  and NUM_ALIMENTO = ? ";

            prepared = con.prepareStatement(sql);
            prepared.setInt(1, reg.getItem().getCardapio().getCodigo());
            prepared.setInt(2, reg.getItem().getNumeroAlimento());

            prepared.execute();

        }else{
            throw new DAOException("Não foi possível encontrar o registro de consumo de alimento"
                    + " informado! Cod: " + reg.toString());
        }
    }

    public List<RegistroConsumoAlimento> encontrarTodos() throws DAOException, SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RegistroConsumoAlimento encontrarPorCodigo(int codigo, int numAlimento) throws DAOException, SQLException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        //TODO Fazer o insert do idoso aqui
        String sql = "select * from registro_consumo_alimento"
                   + " where COD_CARDAPIO = ? "
                   + "   and NUM_ALIMENTO = ? ";

        prepared = con.prepareStatement(sql);

        prepared.setInt(1, codigo);
        prepared.setInt(2, numAlimento);

        result = prepared.executeQuery();

        RegistroConsumoAlimento a = null;
        while(result.next()){

            int codigoCardapio = result.getInt("COD_CARDAPIO");
            int numAli = result.getInt("NUM_ALIMENTO");
            ItemCardapio item = ImplItemCardapioDAO.getInstance().encontrarPorCodigo(codigoCardapio, numAli);

            Date datConsumo = result.getDate("DAT_CONSUMO");
            int numHoraConsumo = result.getInt("NUM_HORA_CONSUMO");

            int codFunc = result.getInt("COD_FUNCIONARIO");
            Funcionario func = ImplFuncionarioDAO.getInstance().encontrarPorCodigo(codFunc);

            a = new RegistroConsumoAlimento(func, item, datConsumo,numHoraConsumo);
        }

        if(a == null){
            throw new DAOException("Não foi possível o encontrar registro de"
                    + " consumo de alimento! Cod = " + codigo);
        }
        return a;
    }
}
