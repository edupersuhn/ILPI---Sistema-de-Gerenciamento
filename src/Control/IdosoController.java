/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Idoso;
import Util.ConectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Bruno
 */
public class IdosoController {
    
    private Connection con;

    public IdosoController() {
        
    }

    public void insere(Idoso idoso) {
        this.con = ConectionManager.getInstance().getConexao();
        
        PreparedStatement prepared;
        try {
            //TODO Fazer o insert do idoso aqui
            prepared = con.prepareStatement("insert into idoso ("
                    + "COD_IDOSO,"
                    + "NOME,"
                    + "DAT_NASCIMENTO,"
                    + "LOCAL_ORIGEM,"
                    + "NUM_TELEFONE,"
                    + "DSC_ENDERECO,"
                    + "DSC_CUIDADOS_ESP) "
                    + "values (?,?,?,?,?,?,?)");
            
            prepared.setInt(1, idoso.getCodIdoso());
            prepared.setString(2,idoso.getNomeIdoso());
            prepared.setDate(3, idoso.getDataNascimento());
            prepared.setString(4, idoso.getLocalOrigem());
            prepared.setLong(5, idoso.getNumTelefone());
            ResultSet resultSet = prepared.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(IdosoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void atualiza() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
