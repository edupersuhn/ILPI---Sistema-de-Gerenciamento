/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.ItemPrescricaoMedica;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IItemPrescricaoMedicaDAO {
    
    public void inserir(ItemPrescricaoMedica item) throws DAOException;
    public void atualizar(ItemPrescricaoMedica item) throws DAOException;
    public void remover(ItemPrescricaoMedica item) throws DAOException;
    public List<ItemPrescricaoMedica> encontrarTodos(int codPrescricao) throws DAOException;
    public ItemPrescricaoMedica encontrarPorCodigo(int codPrescricao, int numRemed) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
    
}
