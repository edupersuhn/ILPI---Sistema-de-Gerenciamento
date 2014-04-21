/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.Remedio;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IRemedioDAO {
    
    public void inserir(Remedio rem) throws DAOException;
    public void atualizar(Remedio rem) throws DAOException;
    public void remover(Remedio rem) throws DAOException;
    public List<Remedio> encontrarTodos() throws DAOException;
    public Remedio encontrarPorCodigo(int codigo) throws DAOException;
    public Remedio encontrarPorNome(String nome) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
    
}
