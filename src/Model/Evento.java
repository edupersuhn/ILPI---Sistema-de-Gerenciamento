/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.sql.Date;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class Evento {
    
    private int codigo;
    private Funcionario func;
    private List<Idoso> listaIdosos;
    private Date dataEvento;
    private String nomeEvento;
    private String descricaoEvento;

    public Evento(int codigo, Funcionario func, List<Idoso> listaIdosos, Date dataEvento, String nomeEvento, String descricaoEvento) {
        this.codigo = codigo;
        this.func = func;
        this.listaIdosos = listaIdosos;
        this.dataEvento = dataEvento;
        this.nomeEvento = nomeEvento;
        this.descricaoEvento = descricaoEvento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Funcionario getFunc() {
        return func;
    }

    public void setFunc(Funcionario func) {
        this.func = func;
    }

    public List<Idoso> getListaIdosos() {
        return listaIdosos;
    }

    public void setListaIdosos(List<Idoso> listaIdosos) {
        this.listaIdosos = listaIdosos;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }
    
    
}
