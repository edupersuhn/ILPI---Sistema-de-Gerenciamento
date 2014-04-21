/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.Evento;
import Model.Idoso;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IIdosoDAO {
    

    public void inserir(Idoso idoso) throws DAOException;
    public void atualizar(Idoso idoso) throws DAOException;
    public void remover(Idoso idoso) throws DAOException;
    public List<Idoso> encontrarTodosIdosos() throws DAOException;
    public List<Idoso> encontrarTodosEvento(int evento) throws DAOException;
    public Idoso encontrarPorCodigo(int codigo) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
    
}
