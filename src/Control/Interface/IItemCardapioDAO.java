/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.ItemCardapio;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IItemCardapioDAO {
    
    public void inserir(ItemCardapio item) throws DAOException;
    public void atualizar(ItemCardapio item) throws DAOException;
    public void remover(ItemCardapio item) throws DAOException;
    public List<ItemCardapio> encontrarTodos() throws DAOException;
    public ItemCardapio encontrarPorCodigo(int codCardapio, int numAlimento) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
}
