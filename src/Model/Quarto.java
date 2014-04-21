/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class Quarto implements Comparable<Quarto> {
    
    public static final String ESTADO_DISPONIVEL = "disponível";
    public static final String ESTADO_INDISPONIVEL = "indisponível";
    
    private int numQuarto;
    private int numAndar;
    private int capacidade;
    private String estado;
    private List<Idoso> listaIdoso;

    public Quarto(){
        this(0, 0, 0, ESTADO_DISPONIVEL);
    }
    
    public Quarto(int numQuarto, int numAndar, int capacidade,String estado) {
        this.numQuarto = numQuarto;
        this.numAndar = numAndar;
        this.capacidade = capacidade;
        this.estado = estado;
        this.listaIdoso = new ArrayList<Idoso>();
    }

    public int getNumQuarto() {
        return numQuarto;
    }

    public void setNumQuarto(int numQuarto) {
        this.numQuarto = numQuarto;
    }

    public int getNumAndar() {
        return numAndar;
    }

    public void setNumAndar(int numAndar) {
        this.numAndar = numAndar;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Idoso> getListaIdoso() {
        return listaIdoso;
    }

    public void setListaIdoso(List<Idoso> listaIdoso) {
        this.listaIdoso = listaIdoso;
    }

    @Override
    public int compareTo(Quarto o) {
        return Integer.compare(numQuarto, o.getNumQuarto());
    }
    
    @Override
    public String toString() {
        return "Quarto n° " + numQuarto;
    }
    
}
