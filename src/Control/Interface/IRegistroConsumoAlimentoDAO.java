/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.RegistroConsumoAlimento;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IRegistroConsumoAlimentoDAO {
    
    public void inserir(RegistroConsumoAlimento reg) throws DAOException;
    public void atualizar(RegistroConsumoAlimento reg) throws DAOException;
    public void remover(RegistroConsumoAlimento reg) throws DAOException;
    public List<RegistroConsumoAlimento> encontrarTodos() throws DAOException;
    public RegistroConsumoAlimento encontrarPorCodigo(int codigo, int numAlimento) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
    
}
