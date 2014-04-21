/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bruno
 */
public class Funcionario {
    
    private int codFuncionario;
    private String nomeFuncionario;
    private String nomeFuncao;
    private String endereco;
    private long telefone;

    public Funcionario(){
        
    }
    
    public Funcionario(int codFuncionario, String nomeFuncionario, String nomeFuncao, String endereco, long telefone) {
        this.codFuncionario = codFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.nomeFuncao = nomeFuncao;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNomeFuncao() {
        return nomeFuncao;
    }

    public void setNomeFuncao(String nomeFuncao) {
        this.nomeFuncao = nomeFuncao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
    
}
