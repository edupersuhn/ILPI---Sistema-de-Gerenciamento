/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bruno
 */
public class Usuario {
    
    private int codigo;
    private String nomeUsuario;
    private String senha;
    private boolean flgAtivo;
    private Funcionario func;

    public Usuario(int codigo, String nomeUsuario, String senha, boolean flgAtivo) {
        this.codigo = codigo;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.flgAtivo = flgAtivo;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getFlgAtivo() {
        return flgAtivo;
    }

    public void setFlgAtivo(boolean flgAtivo) {
        this.flgAtivo = flgAtivo;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }
    
}
