/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.RegistroConsumoMedicamento;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IRegistroConsumoMedicamentoDAO {
    
    public void inserir(RegistroConsumoMedicamento reg) throws DAOException;
    public void atualizar(RegistroConsumoMedicamento reg) throws DAOException;
    public void remover(RegistroConsumoMedicamento reg) throws DAOException;
    public List<RegistroConsumoMedicamento> encontrarTodos() throws DAOException;
    public RegistroConsumoMedicamento encontrarPorCodigo(int codigo) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
    
}
