/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;

/**
 *
 * @author Bruno
 */
public interface IDAO <T>{
    
    public void inserir(T t) throws DAOException;
    public void atualizar(T t) throws DAOException;
    public void remover(T t) throws DAOException;
    
}
