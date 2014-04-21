/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Interface;

import Control.Impl.Exception.DAOException;
import Model.Usuario;

/**
 *
 * @author Bruno
 */
public interface IUsuarioDAO {
    
    public void inserir(Usuario user) throws DAOException;
    public void atualizar(Usuario user) throws DAOException;
    public void remover(Usuario user) throws DAOException;
    public Usuario encontrarPorCodigo(int codigo) throws DAOException;
    public Usuario encontrarPorNome(String nomeUsuario) throws DAOException;
    public Usuario permiteAcesso(String nomeUser, String senha) throws DAOException;
    
    //Talvez vai ser necessário criar metodos que pesquisem outras informações
    //dos idosos por exemplo quarto do idoso e assim por diante
    
}
