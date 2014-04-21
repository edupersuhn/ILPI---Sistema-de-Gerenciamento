/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.Cardapio;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface ICardapioDAO {
    
    public void inserir(Cardapio card) throws DAOException;
    public void atualizar(Cardapio card) throws DAOException;
    public void remover(Cardapio card) throws DAOException;
    public List<Cardapio> encontrarTodos() throws DAOException;
    public Cardapio encontrarPorCodigo(int codigo) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
    
}
