/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Impl;

import Control.Impl.Exception.DAOException;
import Control.Interface.IEventoDAO;
import Model.Alimento;
import Model.Cardapio;
import Model.Evento;
import Model.Funcionario;
import Model.Idoso;
import Util.ConectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class ImplEventoDAO implements IEventoDAO{

    private static ImplEventoDAO instance;
    
    private ImplEventoDAO(){
        
    }
    
    public static ImplEventoDAO getInstance(){
        if(instance == null){
            instance = new ImplEventoDAO();
        }
        return instance;
    }

    @Override
    public void inserir(Evento evento, Idoso idoso) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        PreparedStatement prepared2;
        ResultSet result2;
        try {
            //TODO Fazer o insert do idoso aqui
            prepared = con.prepareStatement("insert into evento ("
                    + "COD_EVENTO,"
                    + "COD_FUNCIONARIO,"
                    + "DAT_EVENTO,"
                    + "NOM_EVENTO,"
                    + "DSC_DESCRICAO_EVENTO) "
                    + "values (?,?,?,?,?)");
            
            prepared.setInt(1, evento.getCodigo());
            prepared.setInt(2, evento.getFunc().getCodFuncionario());
            prepared.setDate(3, evento.getDataEvento());
            prepared.setString(4, evento.getNomeEvento());
            prepared.setString(5, evento.getDescricaoEvento());

            result = prepared.executeQuery();
            
            prepared2 = con.prepareStatement("insert into idoso_evento ("
                    + "COD_EVENTO,"
                    + "COD_IDOSO) "
                    + "values (?,?)");
            
            prepared2.setInt(1, evento.getCodigo());
            prepared2.setInt(2, idoso.getCodIdoso());

            result2 = prepared.executeQuery();
            
            
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao inserir Evento.");
            System.out.println(ex.getMessage());
        }finally{
            try {
                con.rollback();
            } catch (SQLException ex1) {
                System.out.println("Erro ao tentar excluir Evento! ");
                System.out.println(ex1.getMessage());
            }
        }
    }

    @Override
    public void atualizar(Evento evento, Idoso idoso) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remover(Evento evento) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        PreparedStatement prepared2;
        ResultSet result2;
        
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from evento"
                       + " where COD_EVENTO = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, evento.getCodigo());
            
            result = prepared.executeQuery();
            
            if(result.next()){
                sql = "delete evento "
                     + "where COD_EVENTO = ?";
                prepared = con.prepareStatement(sql);
                prepared.setInt(1, evento.getCodigo());
                result = prepared.executeQuery();
                
                sql = "delete idoso_evento "
                     + "where COD_EVENTO = ?";
                prepared2 = con.prepareStatement(sql);
                prepared2.setInt(1, evento.getCodigo());
                result2 = prepared.executeQuery();
                
            }else{
                throw new DAOException("Não foi possível encontrar o alimento informado! Cod: " + evento.getCodigo());
            }
                
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao excluir o Evento.");
            System.out.println(ex.getMessage());
        } catch(DAOException dao){
            System.out.println("Erro ao tentar excluir Evento! ");
            System.out.println(dao.getMessage());
        }finally{
            try {
                con.rollback();
            } catch (SQLException ex1) {
                System.out.println("Erro ao tentar excluir Evento! ");
                System.out.println(ex1.getMessage());
            }
        }
    }

    @Override
    public List<Evento> encontrarTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Evento encontrarPorCodigo(int codigo) throws DAOException {
        Connection con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        ResultSet result;
        try {
            //TODO Fazer o insert do idoso aqui
            String sql = "select * from evento "
                        + "where COD_EVENTO = ?";
            prepared = con.prepareStatement(sql);
            
            prepared.setInt(1, codigo);
            
            result = prepared.executeQuery();
            
            Evento a = null;
            while(result.next()){
                int codEvento = result.getInt("COD_EVENTO");
                
                int codFunc = result.getInt("COD_FUNCIONARIO");
                Funcionario func = ImplFuncionarioDAO.getInstance().encontrarPorCodigo(codFunc);
                
                List<Idoso> listaIdoso = ImplIdosoDAO.getInstance().encontrarTodosEvento(codEvento);
                
                Date datEvento = result.getDate("DAT_EVENTO");
                String nomEvento = result.getString("NOM_EVENTO");
                String dscEvento = result.getString("DSC_DESCRICAO_EVENTO");
                
                a = new Evento(codEvento, func, listaIdoso, datEvento,nomEvento, dscEvento);
            }
            
            if(a == null){
                throw new DAOException("Não foi possível o encontrar alimento! Cod = " + codigo);
            }
            return a;
        } catch (SQLException ex) {
            //Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao excluir o alimento.");
            System.out.println(ex.getMessage());
            return null;
        } catch(DAOException dao){
            System.out.println("Erro ao tentar encontrar o alimento! ");
            System.out.println(dao.getMessage());
            return null;
        }finally{
            try {
                con.rollback();
            } catch (SQLException ex1) {
                System.out.println("Erro ao tentar excluir Evento! ");
                System.out.println(ex1.getMessage());
            }
        }
    }
    
    
}
    