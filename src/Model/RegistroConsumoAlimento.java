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
public class RegistroConsumoAlimento {
 
    private Funcionario funcionario;
    private ItemCardapio item;
    private Date dataConsumo;
    private int horaConsumo;

    public RegistroConsumoAlimento(Funcionario funcionario, ItemCardapio item, Date dataConsumo, int horaConsumo) {
        this.funcionario = funcionario;
        this.item = item;
        this.dataConsumo = dataConsumo;
        this.horaConsumo = horaConsumo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Date getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(Date dataConsumo) {
        this.dataConsumo = dataConsumo;
    }

    public int getHoraConsumo() {
        return horaConsumo;
    }

    public void setHoraConsumo(int horaConsumo) {
        this.horaConsumo = horaConsumo;
    }

    public ItemCardapio getItem() {
        return item;
    }

    public void setItem(ItemCardapio item) {
        this.item = item;
    }
    
}
