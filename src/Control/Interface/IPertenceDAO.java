/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.Pertence;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IPertenceDAO {
    
    public void inserir(Pertence pert) throws DAOException;
    public void atualizar(Pertence pert) throws DAOException;
    public void remover(Pertence pert) throws DAOException;
    public List<Pertence> encontrarTodos(int codigoIdoso) throws DAOException;
    public Pertence encontrarPorCodigo(int codigo, int numPertence) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
    
}
