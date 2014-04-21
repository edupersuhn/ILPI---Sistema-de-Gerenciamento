/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.Idoso;
import Model.Incidente;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IIncidenteDAO {
    
    public void inserir(Incidente inc) throws DAOException;
    public void atualizar(Incidente inc) throws DAOException;
    public void remover(Incidente inc) throws DAOException;
    public List<Incidente> encontrarTodos() throws DAOException;
    public Incidente encontrarPorCodigo(int codigo) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
    
}
