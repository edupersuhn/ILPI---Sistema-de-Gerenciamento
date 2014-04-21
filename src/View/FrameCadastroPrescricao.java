/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Control.Impl.Exception.DAOException;
import Control.Impl.ImplCardapioDAO;
import Control.Impl.ImplIdosoDAO;
import Control.Impl.ImplItemCardapioDAO;
import Control.Impl.ImplItemPrescricaoMedica;
import Control.Impl.ImplPrescricaoMedica;
import Control.Impl.ImplRemedioDAO;
import Model.Alimento;
import Model.Cardapio;
import Model.Idoso;
import Model.ItemCardapio;
import Model.ItemPrescricaoMedica;
import Model.PrescricaoMedica;
import Model.Quarto;
import Model.Remedio;
import Util.DataValidator;
import View.components_controlers.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class FrameCadastroPrescricao extends javax.swing.JFrame {

    private static final String KEY_IDOSO = "idoso";
    private static final String KEY_DATA = "data";
    private static final String KEY_ID = "identificacao";
    private static final String KEY_REMEDIO = "remedio";
    private static final String KEY_HORARIO = "horario";
    private static final String KEY_QUANTIDADE = "quantidade";
    private static final String KEY_OBS = "observacao";
    private static final String KEY_UNIDADE_MEDIDA = "unidadeMedida";
    
    private ArrayList<ItemPrescricaoMedica> listaItens;
    private PrescricaoMedica prescricao;
    
    // COMPONENTES DA ABA DE CADASTRO
    private HashMap<String, ComponentControler> componentesCadastro;
    private ArrayList<Input> entradasCadastro;
    
    // COMPONENTES DA ABA DE EDIÇÃO
    private ComboBoxControler consulta;
    private HashMap<String, ComponentControler> componentesEdicao;
    private ArrayList<Input> entradasEdicao;
    private Idoso idoso;
    
    
    /**
     * Creates new form CadastroPrescricao
     */
    public FrameCadastroPrescricao() {
        initComponents();
        listaItens = new ArrayList<>();
        componentesCadastro = new HashMap<>();
        componentesEdicao = new HashMap<>();
        entradasCadastro = new ArrayList<>();
        entradasEdicao = new ArrayList<>();
        ComboBoxControler idoso = new ComboBoxControler(comboBoxIdoso);
        componentesCadastro.put(KEY_IDOSO, idoso);
        entradasCadastro.add(idoso);
        DateFieldControler data = new DateFieldControler(campoData);
        componentesCadastro.put(KEY_DATA, data);
        entradasCadastro.add(data);
        TextFieldControler id = new TextFieldControler(campoID);
        componentesCadastro.put(KEY_ID, id);
        entradasCadastro.add(id);
        ComboBoxControler remedio = new ComboBoxControler(comboBoxRemedio);
        componentesCadastro.put(KEY_REMEDIO, remedio);
        entradasCadastro.add(remedio);
        TimeFieldControler hora = new TimeFieldControler(campoHora);
        componentesCadastro.put(KEY_HORARIO, hora);
        entradasCadastro.add(hora);
        IntegerFieldContoler qtd = new IntegerFieldContoler(campoQuantidade);
        componentesCadastro.put(KEY_QUANTIDADE, qtd);
        entradasCadastro.add(qtd);
        TextAreaControler obs = new TextAreaControler(areaObs);
        componentesCadastro.put(KEY_OBS, obs);
        entradasCadastro.add(obs);
        componentesCadastro.put(KEY_UNIDADE_MEDIDA, new LabelControler(labelUnidadeMedida));
        
        try {
            List<Idoso> listaI = ImplIdosoDAO.getInstance().encontrarTodosIdosos();
            if(listaI != null) {
                for (Iterator<Idoso> it = listaI.iterator(); it.hasNext();) {
                    Idoso i = it.next();
                    comboBoxIdoso.addItem(i);
                }
            }
            List<Remedio> listaR = ImplRemedioDAO.getInstance().encontrarTodos();
            if(listaR != null) {
                for (Iterator<Remedio> it = listaR.iterator(); it.hasNext();) {
                    Remedio r = it.next();
                    comboBoxRemedio.addItem(r);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        ComboBoxControler idosoE = new ComboBoxControler(comboBoxIdosoEdicao);
        componentesEdicao.put(KEY_IDOSO, idosoE);
        entradasEdicao.add(idosoE);
        DateFieldControler dataE = new DateFieldControler(campoDataEdicao);
        componentesEdicao.put(KEY_DATA, dataE);
        entradasEdicao.add(dataE);
        TextFieldControler idE = new TextFieldControler(campoIDEdicao);
        componentesEdicao.put(KEY_ID, idE);
        entradasEdicao.add(idE);
        ComboBoxControler remedioE = new ComboBoxControler(comboBoxRemedioEdicao);
        componentesEdicao.put(KEY_REMEDIO, remedioE);
        entradasEdicao.add(remedioE);
        TimeFieldControler horaE = new TimeFieldControler(campoHoraEdicao);
        componentesEdicao.put(KEY_HORARIO, horaE);
        entradasEdicao.add(horaE);
        IntegerFieldContoler qtdE = new IntegerFieldContoler(campoQuantidadeEdicao);
        componentesEdicao.put(KEY_QUANTIDADE, qtdE);
        entradasEdicao.add(qtdE);
        TextAreaControler obsE = new TextAreaControler(areaObsEdicao);
        componentesEdicao.put(KEY_OBS, obsE);
        entradasEdicao.add(obsE);
        componentesEdicao.put(KEY_UNIDADE_MEDIDA, new LabelControler(labelUnidadeMedidaEdicao));
    }
    
    private boolean validaAdicionar() {
        return ((Input) componentesCadastro.get(KEY_REMEDIO)).isValid() ||
                ((Input) componentesCadastro.get(KEY_HORARIO)).isValid() ||
                ((Input) componentesCadastro.get(KEY_QUANTIDADE)).isValid();
    }
    
    private boolean validaRemover() {
        return ((Input) componentesCadastro.get(KEY_REMEDIO)).isValid();
    }
    
    private boolean validaCadastro() {
        return  ((Input) componentesCadastro.get(KEY_IDOSO)).isValid() ||
                ((Input) componentesCadastro.get(KEY_DATA)).isValid() ||
                ((Input) componentesCadastro.get(KEY_ID)).isValid() ||
                !listaItens.isEmpty();
               
    }
    
    private boolean validaEdicao() {
        for (Iterator<Input> it = entradasEdicao.iterator(); it.hasNext();) {
            Input input = it.next();
            if(! input.isValid()) return false;
        }
        return true;
    }
    
    private void limparCadastro() {
        for (Iterator<ComponentControler> it = componentesCadastro.values().iterator(); it.hasNext();) {
            ComponentControler componentControler = it.next();
            componentControler.limpar();
        }
    }
    
    private void limparEdicao() {
        for (Iterator<ComponentControler> it = componentesEdicao.values().iterator(); it.hasNext();) {
            ComponentControler componentControler = it.next();
            componentControler.limpar();
        }
    }
    
    private void habilitado(boolean flag) {
        for (Iterator<ComponentControler> it = componentesEdicao.values().iterator(); it.hasNext();) {
            ComponentControler componentControler = it.next();
            componentControler.habilitado(flag);
        }
    }
    
    private void atualizaPrescricao() {
        areaPrescricao.setText("");
        for (Iterator<ItemPrescricaoMedica> it = listaItens.iterator(); it.hasNext();) {
            ItemPrescricaoMedica itemPrescricaoMedica = it.next();
            areaPrescricao.append(itemPrescricaoMedica.toString() + "\n");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        campoQuantidade = new javax.swing.JFormattedTextField();
        comboBoxRemedio = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaObs = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        campoHora = new javax.swing.JFormattedTextField();
        labelUnidadeMedida = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaPrescricao = new javax.swing.JTextArea();
        botaoCadastrar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();
        botaoAdicionar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        comboBoxIdoso = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        campoID = new javax.swing.JTextField();
        campoData = new javax.swing.JFormattedTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        comboBoxIdosoEdicao = new javax.swing.JComboBox();
        comboBoxPrescricao = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaPrescricaoEdicao = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        campoQuantidadeEdicao = new javax.swing.JFormattedTextField();
        comboBoxRemedioEdicao = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        areaObsEdicao = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        campoHoraEdicao = new javax.swing.JFormattedTextField();
        labelUnidadeMedidaEdicao = new javax.swing.JLabel();
        botaoAdicionarEdicao = new javax.swing.JButton();
        botaoRemoverEdicao = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        campoIDEdicao = new javax.swing.JTextField();
        campoDataEdicao = new javax.swing.JFormattedTextField();
        botaoConsultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastra Prescrição Médica");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Remedio"));

        campoQuantidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        comboBoxRemedio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione remédio" }));

        jLabel1.setText("Quantidade:");

        jLabel2.setText("Observações");

        areaObs.setColumns(20);
        areaObs.setRows(5);
        jScrollPane2.setViewportView(areaObs);

        jLabel3.setText("Horario de aplicação:");

        try {
            campoHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        labelUnidadeMedida.setText("U/M");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelUnidadeMedida))
                            .addComponent(comboBoxRemedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(comboBoxRemedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUnidadeMedida))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Prescrição"));

        areaPrescricao.setColumns(20);
        areaPrescricao.setRows(5);
        areaPrescricao.setEnabled(false);
        jScrollPane3.setViewportView(areaPrescricao);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        botaoRemover.setText("Remover");
        botaoRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverActionPerformed(evt);
            }
        });

        botaoAdicionar.setText("Adicionar");
        botaoAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da prescrição"));

        comboBoxIdoso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione idoso" }));
        comboBoxIdoso.setToolTipText("");

        jLabel4.setText("Data:");

        jLabel5.setText("Identificação:");

        try {
            campoData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("hoje");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoData)
                            .addComponent(campoID, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(comboBoxIdoso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBoxIdoso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(campoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoAdicionar))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(345, 345, 345)
                        .addComponent(botaoCadastrar)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(botaoAdicionar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(botaoRemover)
                            .addGap(140, 140, 140)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(botaoCadastrar)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cadastro", jPanel3);

        comboBoxIdosoEdicao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione idoso" }));

        comboBoxPrescricao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione prescrição" }));
        comboBoxPrescricao.setEnabled(false);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Prescrição"));

        areaPrescricaoEdicao.setColumns(20);
        areaPrescricaoEdicao.setRows(5);
        areaPrescricaoEdicao.setEnabled(false);
        jScrollPane1.setViewportView(areaPrescricaoEdicao);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );

        jLabel6.setText("Data:");

        jLabel7.setText("Identificação:");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Remedio"));

        campoQuantidadeEdicao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        campoQuantidadeEdicao.setEnabled(false);

        comboBoxRemedioEdicao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione remédio" }));
        comboBoxRemedioEdicao.setEnabled(false);

        jLabel8.setText("Quantidade:");

        jLabel9.setText("Observações");

        areaObsEdicao.setColumns(20);
        areaObsEdicao.setRows(5);
        areaObsEdicao.setEnabled(false);
        jScrollPane4.setViewportView(areaObsEdicao);

        jLabel10.setText("Horario de aplicação:");

        try {
            campoHoraEdicao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoHoraEdicao.setEnabled(false);

        labelUnidadeMedidaEdicao.setText("U/M");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoHoraEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 78, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoQuantidadeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelUnidadeMedidaEdicao))
                            .addComponent(comboBoxRemedioEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(119, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(comboBoxRemedioEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(campoHoraEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(campoQuantidadeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUnidadeMedidaEdicao))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoAdicionarEdicao.setText("Adicionar");
        botaoAdicionarEdicao.setEnabled(false);
        botaoAdicionarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarEdicaoActionPerformed(evt);
            }
        });

        botaoRemoverEdicao.setText("Remover");
        botaoRemoverEdicao.setEnabled(false);
        botaoRemoverEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverEdicaoActionPerformed(evt);
            }
        });

        botaoSalvar.setText("Salvar alterações");
        botaoSalvar.setEnabled(false);

        campoIDEdicao.setEnabled(false);

        try {
            campoDataEdicao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoDataEdicao.setEnabled(false);

        botaoConsultar.setText("Consultar");
        botaoConsultar.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoRemoverEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoAdicionarEdicao))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxIdosoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoIDEdicao)
                                    .addComponent(campoDataEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(comboBoxPrescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botaoConsultar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSalvar)
                .addGap(318, 318, 318))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBoxIdosoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxPrescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoConsultar))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(campoDataEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(campoIDEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(botaoAdicionarEdicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoRemoverEdicao)))
                .addGap(18, 18, 18)
                .addComponent(botaoSalvar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarActionPerformed
        if(validaAdicionar()) {
            Remedio r = (Remedio) comboBoxRemedio.getSelectedItem();
            ItemPrescricaoMedica ipm = new ItemPrescricaoMedica();
            ipm.setHora((String)componentesCadastro.get(KEY_HORARIO).getValue());
            ipm.setNumeroRemedio(r.getCodigo());
            ipm.setObservacaoRemedio((String)componentesCadastro.get(KEY_OBS).getValue());
            ipm.setQtdRemedio((int)componentesCadastro.get(KEY_QUANTIDADE).getValue());
            ipm.setRemedio(r);
            listaItens.add(ipm);
            atualizaPrescricao();
        }
    }//GEN-LAST:event_botaoAdicionarActionPerformed

    private void botaoRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverActionPerformed
        if(validaRemover()) {
            Remedio r = (Remedio) comboBoxRemedio.getSelectedItem();
            listaItens.remove(r);
            atualizaPrescricao();
        }
    }//GEN-LAST:event_botaoRemoverActionPerformed

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void botaoAdicionarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarEdicaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoAdicionarEdicaoActionPerformed

    private void botaoRemoverEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverEdicaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoRemoverEdicaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaObs;
    private javax.swing.JTextArea areaObsEdicao;
    private javax.swing.JTextArea areaPrescricao;
    private javax.swing.JTextArea areaPrescricaoEdicao;
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoAdicionarEdicao;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoConsultar;
    private javax.swing.JButton botaoRemover;
    private javax.swing.JButton botaoRemoverEdicao;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JFormattedTextField campoData;
    private javax.swing.JFormattedTextField campoDataEdicao;
    private javax.swing.JFormattedTextField campoHora;
    private javax.swing.JFormattedTextField campoHoraEdicao;
    private javax.swing.JTextField campoID;
    private javax.swing.JTextField campoIDEdicao;
    private javax.swing.JFormattedTextField campoQuantidade;
    private javax.swing.JFormattedTextField campoQuantidadeEdicao;
    private javax.swing.JComboBox comboBoxIdoso;
    private javax.swing.JComboBox comboBoxIdosoEdicao;
    private javax.swing.JComboBox comboBoxPrescricao;
    private javax.swing.JComboBox comboBoxRemedio;
    private javax.swing.JComboBox comboBoxRemedioEdicao;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelUnidadeMedida;
    private javax.swing.JLabel labelUnidadeMedidaEdicao;
    // End of variables declaration//GEN-END:variables
}
