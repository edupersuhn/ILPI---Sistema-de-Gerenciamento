/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bruno
 */
public class ItemCardapio {
    
    private int cod = 0;
    
    private Cardapio cardapio;
    private Alimento alimento;
    private int qtdProduzida;
    private int numeroAlimento;

    public ItemCardapio() {
        this(null, null, 0, 0);
    }
    
    public ItemCardapio(Cardapio cardapio, Alimento alimento, int qtdProduzida, int numeroAlimento) {
        this.cardapio = cardapio;
        this.alimento = alimento;
        this.qtdProduzida = qtdProduzida;
        this.numeroAlimento = numeroAlimento;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public int getQtdProduzida() {
        return qtdProduzida;
    }

    public void setQtdProduzida(int qtdProduzida) {
        this.qtdProduzida = qtdProduzida;
    }

    public int getNumeroAlimento() {
        return numeroAlimento;
    }

    public void setNumeroAlimento(int numeroAlimento) {
        this.numeroAlimento = numeroAlimento;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof ItemCardapio) {
            ItemCardapio iC = (ItemCardapio) o;
            return this.numeroAlimento == iC.getNumeroAlimento() &&
                   this.cod == iC.getCod();
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "Alimento: " + alimento.getNomeAlimento() + " Quantidade: " + qtdProduzida;
    }
    
}
