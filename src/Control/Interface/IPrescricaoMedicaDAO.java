/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.PrescricaoMedica;
import java.util.List;

/**
 *
 * @author Bruno
 */
public interface IPrescricaoMedicaDAO {
    
    public void inserir(PrescricaoMedica pres) throws DAOException;
    public void atualizar(PrescricaoMedica pres) throws DAOException;
    public void remover(PrescricaoMedica pres) throws DAOException;
    public List<PrescricaoMedica> encontrarTodos() throws DAOException;
    public PrescricaoMedica encontrarPorCodigo(int codigo) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
    
}
