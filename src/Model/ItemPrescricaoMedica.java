/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bruno
 */
public class ItemPrescricaoMedica {
    
    private int cod;
    
    private PrescricaoMedica prescricao;
    private int numeroRemedio;
    private Remedio remedio;
    private String observacaoRemedio;
    private int qtdRemedio;

    public ItemPrescricaoMedica() {
        this(null, 0, null, null, 0);
    }
    
    public ItemPrescricaoMedica(PrescricaoMedica prescricao, 
                                int numeroRemedio, 
                                Remedio remedio, 
                                String observacaoRemedio, 
                                int qtdRemedio)
    {
        this.prescricao = prescricao;
        this.numeroRemedio = numeroRemedio;
        this.remedio = remedio;
        this.observacaoRemedio = observacaoRemedio;
        this.qtdRemedio = qtdRemedio;
    }

    public PrescricaoMedica getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(PrescricaoMedica prescricao) {
        this.prescricao = prescricao;
    }

    public int getNumeroRemedio() {
        return numeroRemedio;
    }

    public void setNumeroRemedio(int numeroRemedio) {
        this.numeroRemedio = numeroRemedio;
    }

    public Remedio getRemedio() {
        return remedio;
    }

    public void setRemedio(Remedio remedio) {
        this.remedio = remedio;
    }

    public String getObservacaoRemedio() {
        return observacaoRemedio;
    }

    public void setObservacaoRemedio(String observacaoRemedio) {
        this.observacaoRemedio = observacaoRemedio;
    }

    public int getQtdRemedio() {
        return qtdRemedio;
    }

    public void setQtdRemedio(int qtdRemedio) {
        this.qtdRemedio = qtdRemedio;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if(o instanceof ItemPrescricaoMedica) {
            ItemPrescricaoMedica iC = (ItemPrescricaoMedica) o;
            return this.numeroRemedio == iC.getNumeroRemedio() && 
                   this.cod == iC.getCod();
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public String toString() {
        return "Remedio: " + remedio.getNomeRemedio() + " " + qtdRemedio + " " + remedio.getUnidadeMedida();
    }
    
}
