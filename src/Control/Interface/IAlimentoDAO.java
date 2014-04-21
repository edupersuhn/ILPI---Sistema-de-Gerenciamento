/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.Alimento;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IAlimentoDAO {
    
    public void inserir(Alimento ali) throws DAOException;
    public void atualizar(Alimento ali) throws DAOException;
    public void remover(Alimento ali) throws DAOException;
    public List<Alimento> encontrarTodos() throws DAOException;
    public Alimento encontrarPorCodigo(int codigo) throws DAOException;
    public Alimento encontrarPorNome(String nome) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
}
