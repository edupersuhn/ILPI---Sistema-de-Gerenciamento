/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.Alimento;
import Model.Evento;
import Model.Idoso;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IEventoDAO {
    
    public void inserir(Evento evento, Idoso idoso) throws DAOException;
    public void atualizar(Evento evento, Idoso idoso) throws DAOException;
    public void remover(Evento evento) throws DAOException;
    public List<Evento> encontrarTodos() throws DAOException;
    public Evento encontrarPorCodigo(int codigo) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
    
}
