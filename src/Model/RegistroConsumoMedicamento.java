/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.text.SimpleDateFormat;



/**
 *
 * @author Bruno
 */
public class RegistroConsumoMedicamento {
 
    private ItemPrescricaoMedica item;
    private Date dataUtilizacao;
    private int horaUtilizacao;
    private Funcionario funcionario;

    public RegistroConsumoMedicamento(ItemPrescricaoMedica item, Date dataUtilizacao, int horaUtilizacao, Funcionario funcionario) {
        this.item = item;
        this.dataUtilizacao = dataUtilizacao;
        this.horaUtilizacao = horaUtilizacao;
        this.funcionario = funcionario;
    }

    public Date getDataUtilizacao() {
        return dataUtilizacao;
    }

    public void setDataUtilizacao(Date dataUtilizacao) {
        this.dataUtilizacao = dataUtilizacao;
    }

    public int getHoraUtilizacao() {
        return horaUtilizacao;
    }

    public void setHoraUtilizacao(int horaUtilizacao) {
        this.horaUtilizacao = horaUtilizacao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public ItemPrescricaoMedica getItem() {
        return item;
    }

    public void setItem(ItemPrescricaoMedica item) {
        this.item = item;
    }
    
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(item.getRemedio().getNomeRemedio() + " utilizado em ");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        s.append(format.format(dataUtilizacao));
        s.append(" as " + horaUtilizacao);
        return s.toString(); 
    }
    
}
