/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bruno
 */
public class Pertence {
    
    private Idoso idoso;
    private int numeroPertence;
    private String nomePertence;
    private String descricao;

    public Pertence(Idoso idoso, String nomePertence, String descricao) {
        this.idoso = idoso;
        this.numeroPertence = 0;
        this.nomePertence = nomePertence;
        this.descricao = descricao;
    }
    
    public Pertence(Idoso idoso, int numeroPertence, String nomePertence, String descricao) {
        this.idoso = idoso;
        this.numeroPertence = numeroPertence;
        this.nomePertence = nomePertence;
        this.descricao = descricao;
    }

    public Idoso getIdoso() {
        return idoso;
    }

    public void setIdoso(Idoso idoso) {
        this.idoso = idoso;
    }

    public int getNumeroPertence() {
        return numeroPertence;
    }

    public void setNumeroPertence(int numeroPertence) {
        this.numeroPertence = numeroPertence;
    }

    public String getNomePertence() {
        return nomePertence;
    }

    public void setNomePertence(String nomePertence) {
        this.nomePertence = nomePertence;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
