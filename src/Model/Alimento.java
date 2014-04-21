/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bruno
 */
public class Alimento implements Comparable<Alimento> {
    
    private int codigo;
    private String infoNutricional;
    private String nomeAlimento;
    private int qtdEstoque;
    private String refeicao;

    public Alimento() {
        this(0, "", "", 0, "");
    }
    
    public Alimento(int codigo, String infoNutricional, String nomeAlimento, int qtdEstoque, String refeicao) {
        this.codigo = codigo;
        this.infoNutricional = infoNutricional;
        this.nomeAlimento = nomeAlimento;
        this.qtdEstoque = qtdEstoque;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getInfoNutricional() {
        return infoNutricional;
    }

    public void setInfoNutricional(String infoNutricional) {
        this.infoNutricional = infoNutricional;
    }

    public String getNomeAlimento() {
        return nomeAlimento;
    }

    public void setNomeAlimento(String nomeAlimento) {
        this.nomeAlimento = nomeAlimento;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(String refeicao) {
        this.refeicao = refeicao;
    }
    
    @Override
    public int compareTo(Alimento a) {
        return nomeAlimento.compareTo(a.getNomeAlimento());
    }
    
    @Override
    public String toString() {
        return nomeAlimento;
    }
    
}