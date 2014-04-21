/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;


/**
 *
 * @author Bruno
 */
public class Cardapio {
    
    private Idoso idoso;
    private int codigo;
    private Date dataCriacao;
    private Date dataFim;

    public Cardapio() {
        this(null, 0, null, null);
    }
    
    public Cardapio(Idoso idoso, int codigo, Date dataCriacao, Date dataFim) {
        this.idoso = idoso;
        this.codigo = codigo;
        this.dataCriacao = dataCriacao;
        this.dataFim = dataFim;
    }

    public Idoso getIdoso() {
        return idoso;
    }

    public void setIdoso(Idoso idoso) {
        this.idoso = idoso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    
}
