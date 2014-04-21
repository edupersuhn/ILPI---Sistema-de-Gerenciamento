/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Bruno
 */
public class ConectionManager {

    private static ConectionManager conexcaoSingleton;
    private String url;
    private String user;
    private String pass;
    private Connection con;
    
    public ConectionManager(){
        
        try{
            Class.forName("org.postgresql.Driver");
        }catch(Exception e){
            System.out.println("Classe nao encontrada!");
        }
        
        Properties props = new Properties();
        FileInputStream in = null;
        
        try {
            in = new FileInputStream("src\\propriedade\\config.properties");
            props.load(in);
        } catch (IOException ex) {
            System.out.println("Deu erro na recuperação das propriedades!");
        }
        try{
            
            this.url = props.getProperty("database");
            this.user = props.getProperty("db.user");
            this.pass = props.getProperty("db.password");
            
            con = DriverManager.getConnection(this.url, this.user, this.pass); 
            
            System.out.println("Conexao realizada com Sucesso!");
        }catch ( SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static ConectionManager getInstance(){
        if(conexcaoSingleton == null){
            conexcaoSingleton = new ConectionManager();
        }
        return conexcaoSingleton;
    }
    
    public Connection getConexao(){
        return this.con;
    }
    
    
    public static void main(String[] args) {
        
        ConectionManager con = ConectionManager.getInstance();
        try{
            PreparedStatement pre = con.getConexao().prepareStatement("Select * from alimento");
            
            ResultSet result = pre.executeQuery();
            System.out.println("Testando");
            while(result.next()){
                System.out.print(result.getString(1));
                System.out.print(" : ");
                System.out.println(result.getString(2));
            }
            
        }catch(Exception e){
            System.out.println(" Erro " + e.getMessage());
        }
        
    }
}
