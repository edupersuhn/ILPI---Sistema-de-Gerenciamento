/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.Quarto;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IQuartoDAO {
    
    public void inserir(Quarto quarto) throws DAOException;
    public void atualizar(Quarto quarto) throws DAOException;
    public void remover(Quarto quarto) throws DAOException;
    public List<Quarto> encontrarTodos() throws DAOException;
    public Quarto encontrarPorCodigo(int numero, int andar) throws DAOException;
    public List<Quarto> encontrarQuartosAndar(int andar) throws DAOException;
    
}
