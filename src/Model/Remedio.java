/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bruno
 */
public class Remedio implements Comparable<Remedio> {
    
    private int codigo;
    private String nomeRemedio;
    private int qtdEstoque;
    private String unidadeMedida;

    public Remedio() {
        this(0, "", 0, "");
    }
    
    public Remedio(int codigo, String nomeRemedio, int qtdEstoque, String unidadeMedida) {
        this.codigo = codigo;
        this.nomeRemedio = nomeRemedio;
        this.qtdEstoque = qtdEstoque;
        this.unidadeMedida = unidadeMedida;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeRemedio() {
        return nomeRemedio;
    }

    public void setNomeRemedio(String nomeRemedio) {
        this.nomeRemedio = nomeRemedio;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
    
    
    @Override
    public int compareTo(Remedio o) {
        return nomeRemedio.compareTo(o.getNomeRemedio());
    }
    
    @Override
    public String toString() {
        return nomeRemedio;
    }
    
}
