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
public class PrescricaoMedica {
    
    private Idoso idoso;
    private int codigoPrescricao;
    private String observacao;
    private Date dataPrescricao;
    private int horaPrescricao;

    public PrescricaoMedica() {
        this(null, 0, null, null, 0);
    }
    
    public PrescricaoMedica(Idoso idoso, int codigoPrescricao, String observacao, Date dataPrescricao, int horaPrescricao) {
        this.idoso = idoso;
        this.codigoPrescricao = codigoPrescricao;
        this.observacao = observacao;
        this.dataPrescricao = dataPrescricao;
        this.horaPrescricao = horaPrescricao;
    }

    public Idoso getIdoso() {
        return idoso;
    }

    public void setIdoso(Idoso idoso) {
        this.idoso = idoso;
    }

    public int getCodigoPrescricao() {
        return codigoPrescricao;
    }

    public void setCodigoPrescricao(int codigoPrescricao) {
        this.codigoPrescricao = codigoPrescricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataPrescricao() {
        return dataPrescricao;
    }

    public void setDataPrescricao(Date dataPrescricao) {
        this.dataPrescricao = dataPrescricao;
    }

    public int getHoraPrescricao() {
        return horaPrescricao;
    }

    public void setHoraPrescricao(int horaPrescricao) {
        this.horaPrescricao = horaPrescricao;
    }
    
}
