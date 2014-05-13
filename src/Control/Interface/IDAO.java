/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import java.sql.SQLException;

/**
 *
 * @author Bruno
 */
public interface IDAO <T>{
    
    public abstract void inserir(T t) throws DAOException,SQLException;
    public abstract void atualizar(T t) throws DAOException,SQLException;
    public abstract void remover(T t) throws DAOException,SQLException;
    
}
