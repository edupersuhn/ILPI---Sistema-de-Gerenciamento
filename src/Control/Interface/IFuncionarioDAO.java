/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.Funcionario;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IFuncionarioDAO {
    
    public void inserir(Funcionario func) throws DAOException;
    public void atualizar(Funcionario func) throws DAOException;
    public void remover(Funcionario func) throws DAOException;
    public List<Funcionario> encontrarTodos() throws DAOException;
    public Funcionario encontrarPorCodigo(int codigo) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
    
}
