/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Control.Impl.Exception.DAOException;
import Control.Impl.ImplIdosoDAO;
import Control.Impl.ImplQuartoDAO;
import Control.Impl.ImplRemedioDAO;
import Model.Idoso;
import Model.Quarto;
import Model.Remedio;
import Util.DataValidator;
import Util.Mensagens;
import View.components_controlers.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class FrameCadastroIdoso extends javax.swing.JFrame {
    
    private static final String KEY_NOME = "nome";
    private static final String KEY_DATA_NASCIMENTO = "dataNascimento";
    private static final String KEY_LOCAL_ORIGEM = "locaOrigem";
    private static final String KEY_CPF = "cpf";
    private static final String KEY_RG = "rg";
    private static final String KEY_OBSERVACOES = "obs";
    private static final String KEY_NOME_PARENTE = "nomeParente";
    private static final String KEY_END_PARENTE = "endParente";
    private static final String KEY_FONE_PARENTE = "foneParente";
    private static final String KEY_QUARTO = "quarto";
    private static final String KEY_CONSULTA = "consulta";
    //COMPONENTES DO QUARTO
    private static final String KEY_NUM_QUARTO = "numQuarto";
    private static final String KEY_NUM_ANDAR = "numAndar";
    private static final String KEY_CAPACIDADE = "capacidade";
    private static final String KEY_ESTADO = "estado";
    
    // COMPONENTES DA ABA DE CADASTRO
    private HashMap<String, ComponentControler> componentesCadastro;
    private ArrayList<Input> entradasCadastro;
    
    // COMPONENTES DA ABA DE EDIÇÃO
    private ComboBoxControler consulta;
    private HashMap<String, ComponentControler> componentesEdicao;
    private ArrayList<Input> entradasEdicao;
    private Idoso idoso;
    
    /**
     * Creates new form FrameCadastroIdoso
     */
    public FrameCadastroIdoso() {
        initComponents();
        componentesCadastro = new HashMap<>();
        componentesEdicao = new HashMap<>();
        entradasCadastro = new ArrayList<>();
        entradasEdicao = new ArrayList<>();
        TextFieldControler nome = new TextFieldControler(campoNome);
        componentesCadastro.put(KEY_NOME, nome);
        entradasCadastro.add(nome);
        DateFieldControler dataNasc = new DateFieldControler(campoDataNascimento);
        componentesCadastro.put(KEY_DATA_NASCIMENTO, dataNasc);
        entradasCadastro.add(dataNasc);
        TextFieldControler local = new TextFieldControler(campoLocOrigem);
        componentesCadastro.put(KEY_LOCAL_ORIGEM, local);
        entradasCadastro.add(local);
        CPFFieldControler cpf = new CPFFieldControler(campoCPF);
        componentesCadastro.put(KEY_CPF, cpf);
        entradasCadastro.add(cpf);
        IntegerFieldContoler rg = new IntegerFieldContoler(campoRG);
        componentesCadastro.put(KEY_RG, rg);
        entradasCadastro.add(rg);
        TextAreaControler obs = new TextAreaControler(areaObservacoes);
        componentesCadastro.put(KEY_OBSERVACOES, obs);
        entradasCadastro.add(obs);
        TextFieldControler nomeParente = new TextFieldControler(campoNomeParente);
        componentesCadastro.put(KEY_NOME_PARENTE, nomeParente);
        entradasCadastro.add(nomeParente);
        TextFieldControler end = new TextFieldControler(campoEnderecoParente);
        componentesCadastro.put(KEY_END_PARENTE, end);
        entradasCadastro.add(end);
        FoneFieldControler fone = new FoneFieldControler(campoFoneParente);
        componentesCadastro.put(KEY_FONE_PARENTE, fone);
        entradasCadastro.add(fone);
        ComboBoxControler quarto = new ComboBoxControler(comboBoxQuarto);
        componentesCadastro.put(KEY_QUARTO, quarto);
        entradasCadastro.add(quarto);
        try {
            List<Quarto> lista = ImplQuartoDAO.getInstance().encontrarTodos();
            if(lista != null) {
                for (Iterator<Quarto> it = lista.iterator(); it.hasNext();) {
                    Quarto q = it.next();
                    quarto.setValue(q);
                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        
        componentesCadastro.put(KEY_NUM_QUARTO, new LabelControler(labelNumQuarto));
        componentesCadastro.put(KEY_NUM_ANDAR, new LabelControler(labelNumAndar));
        componentesCadastro.put(KEY_CAPACIDADE, new LabelControler(labelEspacoDisponivel));
        componentesCadastro.put(KEY_ESTADO, new LabelControler(labelEstado));
        
        
        consulta = new ComboBoxControler(comboBoxIdoso);
        componentesEdicao.put(KEY_CONSULTA, consulta);
        TextFieldControler nomeE = new TextFieldControler(campoNomeEdicao);
        componentesEdicao.put(KEY_NOME, nomeE);
        entradasEdicao.add(nomeE);
        DateFieldControler dataNascE = new DateFieldControler(campoDataNascimentoEdicao);
        componentesEdicao.put(KEY_DATA_NASCIMENTO, dataNascE);
        entradasEdicao.add(dataNascE);
        TextFieldControler localE = new TextFieldControler(campoLocOrigemEdicao);
        componentesEdicao.put(KEY_LOCAL_ORIGEM, localE);
        entradasEdicao.add(localE);
        CPFFieldControler cpfE = new CPFFieldControler(campoCPFEdicao);
        componentesEdicao.put(KEY_CPF, cpfE);
        entradasEdicao.add(cpfE);
        IntegerFieldContoler rgE = new IntegerFieldContoler(campoRGEdicao);
        componentesEdicao.put(KEY_RG, rgE);
        entradasEdicao.add(rgE);
        TextAreaControler obsE = new TextAreaControler(areaObservacoesEdicao);
        componentesEdicao.put(KEY_OBSERVACOES, obsE);
        entradasEdicao.add(obsE);
        TextFieldControler nomeParenteE = new TextFieldControler(campoNomeParenteEdicao);
        componentesEdicao.put(KEY_NOME_PARENTE, nomeParenteE);
        entradasEdicao.add(nomeParenteE);
        TextFieldControler endE = new TextFieldControler(campoEnderecoParenteEdicao);
        componentesEdicao.put(KEY_END_PARENTE, endE);
        entradasEdicao.add(endE);
        FoneFieldControler foneE = new FoneFieldControler(campoFoneParenteEdicao);
        componentesEdicao.put(KEY_FONE_PARENTE, foneE);
        entradasEdicao.add(foneE);
        ComboBoxControler quartoE = new ComboBoxControler(comboBoxQuartoEdicao);
        componentesEdicao.put(KEY_QUARTO, quartoE);
        entradasEdicao.add(quartoE);    
    }
    
    
    private boolean validaCadastro() {
        for (Iterator<Input> it = entradasCadastro.iterator(); it.hasNext();) {
            Input input = it.next();
            if(! input.isValid()) return false;
        }
        Quarto q = (Quarto)componentesCadastro.get(KEY_QUARTO).getValue();
        if((q.getCapacidade() - 1) < 0) return false; 
        return true;
    }
    
    private boolean validaEdicao() {
        for (Iterator<Input> it = entradasEdicao.iterator(); it.hasNext();) {
            Input input = it.next();
            if(! input.isValid()) return false;
        }
        Quarto q = (Quarto)componentesEdicao.get(KEY_QUARTO).getValue();
        if((q.getCapacidade() - 1) < 0) return false; 
        return true;
    }
    
    private void limparCadastro() {
        for (Iterator<ComponentControler> it = componentesCadastro.values().iterator(); it.hasNext();) {
            ComponentControler componentControler = it.next();
            componentControler.limpar();
        }
        checkBoxAcamado.setSelected(false);
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        campoRG = new javax.swing.JFormattedTextField();
        checkBoxAcamado = new javax.swing.JCheckBox();
        campoDataNascimento = new javax.swing.JFormattedTextField();
        campoNome = new javax.swing.JTextField();
        campoCPF = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        campoLocOrigem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaObservacoes = new javax.swing.JTextArea();
        botaoCadastrar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        comboBoxQuarto = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        labelNumQuarto = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        labelNumAndar = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        labelEspacoDisponivel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        labelEstado = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        campoNomeParente = new javax.swing.JTextField();
        campoEnderecoParente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campoFoneParente = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        campoRGEdicao = new javax.swing.JFormattedTextField();
        checkBoxAcamadoEdicao = new javax.swing.JCheckBox();
        campoDataNascimentoEdicao = new javax.swing.JFormattedTextField();
        campoNomeEdicao = new javax.swing.JTextField();
        campoCPFEdicao = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        campoLocOrigemEdicao = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaObservacoesEdicao = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        campoNomeParenteEdicao = new javax.swing.JTextField();
        campoEnderecoParenteEdicao = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        campoFoneParenteEdicao = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        comboBoxQuartoEdicao = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        labelNumQuartoEdicao = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        labelNumAndarEdicao = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        labelEspacoDisponivelEdicao = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        labelEstadoEdicao = new javax.swing.JLabel();
        comboBoxIdoso = new javax.swing.JComboBox();
        botaoSalvar = new javax.swing.JButton();
        botaoConsultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Idoso");
        setResizable(false);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do idoso"));

        jLabel8.setText("CPF:");

        jLabel9.setText("RG:");

        campoRG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        checkBoxAcamado.setText("Acamado");

        try {
            campoDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            campoCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel1.setText("Nome:");

        jLabel3.setText("Local de origem:");

        jLabel2.setText("Data de nascimento:");

        jLabel7.setText("Observações");

        areaObservacoes.setColumns(20);
        areaObservacoes.setRows(5);
        jScrollPane1.setViewportView(areaObservacoes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(85, 85, 85)
                            .addComponent(campoRG))
                        .addComponent(checkBoxAcamado, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel1)
                                .addComponent(jLabel8))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoLocOrigem)
                                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoLocOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(campoRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(checkBoxAcamado)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Alocação de quarto"));

        comboBoxQuarto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione quarto" }));
        comboBoxQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxQuartoActionPerformed(evt);
            }
        });

        jLabel11.setText("N° do Quarto:");

        jLabel12.setText("N° do Andar:");

        jLabel14.setText("Espaço disponível (quantidade de pessoas):");

        jLabel16.setText("Estado:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(148, 148, 148)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNumAndar)
                            .addComponent(labelNumQuarto)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelEstado)
                            .addComponent(labelEspacoDisponivel))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBoxQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(labelNumQuarto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(labelNumAndar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(labelEspacoDisponivel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(labelEstado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do parente responsável"));

        jLabel10.setText("Nome:");

        jLabel5.setText("Endereço:");

        jLabel4.setText("Telefone:");

        try {
            campoFoneParente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoNomeParente)
                    .addComponent(campoEnderecoParente)
                    .addComponent(campoFoneParente, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(campoNomeParente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(campoEnderecoParente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(campoFoneParente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(botaoCadastrar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(botaoCadastrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastrar", jPanel4);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do idoso"));

        jLabel13.setText("CPF:");

        jLabel15.setText("RG:");

        campoRGEdicao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        campoRGEdicao.setEnabled(false);

        checkBoxAcamadoEdicao.setText("Acamado");
        checkBoxAcamadoEdicao.setEnabled(false);

        try {
            campoDataNascimentoEdicao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoDataNascimentoEdicao.setEnabled(false);

        campoNomeEdicao.setEnabled(false);

        try {
            campoCPFEdicao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCPFEdicao.setEnabled(false);

        jLabel17.setText("Nome:");

        campoLocOrigemEdicao.setEnabled(false);

        jLabel18.setText("Local de origem:");

        jLabel19.setText("Data de nascimento:");

        jLabel20.setText("Observações");

        areaObservacoesEdicao.setColumns(20);
        areaObservacoesEdicao.setRows(5);
        areaObservacoesEdicao.setEnabled(false);
        jScrollPane2.setViewportView(areaObservacoesEdicao);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addGap(85, 85, 85)
                            .addComponent(campoRGEdicao))
                        .addComponent(checkBoxAcamadoEdicao, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel19)
                                .addComponent(jLabel18)
                                .addComponent(jLabel17)
                                .addComponent(jLabel13))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoCPFEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoLocOrigemEdicao)
                                    .addComponent(campoNomeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoDataNascimentoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(campoNomeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(campoDataNascimentoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(campoLocOrigemEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(campoCPFEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(campoRGEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(checkBoxAcamadoEdicao)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do parente responsável"));

        jLabel21.setText("Nome:");

        campoNomeParenteEdicao.setEnabled(false);

        campoEnderecoParenteEdicao.setEnabled(false);

        jLabel22.setText("Endereço:");

        jLabel23.setText("Telefone:");

        try {
            campoFoneParenteEdicao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoFoneParenteEdicao.setEnabled(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoNomeParenteEdicao)
                    .addComponent(campoEnderecoParenteEdicao)
                    .addComponent(campoFoneParenteEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(campoNomeParenteEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(campoEnderecoParenteEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(campoFoneParenteEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Alocação de quarto"));

        comboBoxQuartoEdicao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione quarto" }));
        comboBoxQuartoEdicao.setEnabled(false);
        comboBoxQuartoEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxQuartoEdicaoActionPerformed(evt);
            }
        });

        jLabel24.setText("N° do Quarto:");

        labelNumQuartoEdicao.setText("000");

        jLabel25.setText("N° do Andar:");

        labelNumAndarEdicao.setText("00");

        jLabel26.setText("Espaço disponível (quantidade de pessoas):");

        labelEspacoDisponivelEdicao.setText("000");

        jLabel27.setText("Estado:");

        labelEstadoEdicao.setText("disponível");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxQuartoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addGap(148, 148, 148)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNumAndarEdicao)
                            .addComponent(labelNumQuartoEdicao)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelEstadoEdicao)
                            .addComponent(labelEspacoDisponivelEdicao))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBoxQuartoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(labelNumQuartoEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(labelNumAndarEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(labelEspacoDisponivelEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(labelEstadoEdicao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        comboBoxIdoso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione idoso" }));

        botaoSalvar.setText("Salvar alterações");
        botaoSalvar.setEnabled(false);

        botaoConsultar.setText("Consultar");
        botaoConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(comboBoxIdoso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoConsultar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(278, 278, 278)
                .addComponent(botaoSalvar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxIdoso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoConsultar))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botaoSalvar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consultar", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        if(validaCadastro()) {
            Idoso i = new Idoso();
            i.setAcamado(checkBoxAcamado.isSelected());
            i.setCpf((String)componentesCadastro.get(KEY_CPF).getValue());
            i.setCuidadosEspeciais((String)componentesCadastro.get(KEY_OBSERVACOES).getValue());
            i.setDataNascimento((Date)componentesCadastro.get(KEY_DATA_NASCIMENTO).getValue());
            i.setEndParente((String)componentesCadastro.get(KEY_END_PARENTE).getValue());
            i.setLocalOrigem((String)componentesCadastro.get(KEY_LOCAL_ORIGEM).getValue());
            i.setNomeIdoso((String)componentesCadastro.get(KEY_NOME).getValue());
            i.setNomeParenteResponsavel((String)componentesCadastro.get(KEY_NOME_PARENTE).getValue());
            i.setNumTelefoneParente((String)componentesCadastro.get(KEY_FONE_PARENTE).getValue());
            i.setRg((int)componentesCadastro.get(KEY_RG).getValue());
            try {
                ImplIdosoDAO.getInstance().inserir(i);
                Quarto q = (Quarto) componentesCadastro.get(KEY_QUARTO).getValue();
                q.setCapacidade(q.getCapacidade() - 1);
                if(q.getCapacidade() == 0) {
                    q.setEstado(Quarto.ESTADO_INDISPONIVEL);
                }
                ImplQuartoDAO.getInstance().atualizar(q);
                limparCadastro();
                Mensagens.cadastradoComSucesso(this);
            } catch(DAOException ex) {
                ex.printStackTrace();
            }
        }
        else {
            Mensagens.camposInvalidos(this);
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void comboBoxQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxQuartoActionPerformed
        if(((Input)componentesCadastro.get(KEY_QUARTO)).isValid()) {
            Quarto q = (Quarto) componentesCadastro.get(KEY_QUARTO).getValue();
            componentesCadastro.get(KEY_NUM_QUARTO).setValue(q.getNumQuarto());
            componentesCadastro.get(KEY_NUM_ANDAR).setValue(q.getNumAndar());
            componentesCadastro.get(KEY_CAPACIDADE).setValue(q.getCapacidade());
            componentesCadastro.get(KEY_ESTADO).setValue(q.getEstado());
        }
        else {
            componentesCadastro.get(KEY_NUM_QUARTO).limpar();
            componentesCadastro.get(KEY_NUM_ANDAR).limpar();
            componentesCadastro.get(KEY_CAPACIDADE).limpar();
            componentesCadastro.get(KEY_ESTADO).limpar();
        }
    }//GEN-LAST:event_comboBoxQuartoActionPerformed

    private void comboBoxQuartoEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxQuartoEdicaoActionPerformed
        if(((Input)componentesEdicao.get(KEY_QUARTO)).isValid()) {
            Quarto q = (Quarto) componentesEdicao.get(KEY_QUARTO).getValue();
            componentesEdicao.get(KEY_NUM_QUARTO).setValue(q.getNumQuarto());
            componentesEdicao.get(KEY_NUM_ANDAR).setValue(q.getNumAndar());
            componentesEdicao.get(KEY_CAPACIDADE).setValue(q.getCapacidade());
            componentesEdicao.get(KEY_ESTADO).setValue(q.getEstado());
        }
    }//GEN-LAST:event_comboBoxQuartoEdicaoActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if(jTabbedPane1.getSelectedIndex() == 1) {
            comboBoxIdoso.removeAllItems();
            comboBoxIdoso.addItem("Selecione idoso");
            try {
                List<Idoso> lista = ImplIdosoDAO.getInstance().encontrarTodosIdosos();
                if(lista != null) {
                    for (Iterator<Idoso> it = lista.iterator(); it.hasNext();) {
                        Idoso i = it.next();
                        comboBoxIdoso.addItem(i);
                    }
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void botaoConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConsultarActionPerformed
        if(consulta.isValid()) {
            try {
                idoso = ImplIdosoDAO.getInstance().encontrarPorCodigo(((Idoso) consulta.getValue()).getCodIdoso());
                if(idoso != null) {
                    habilitado(true);
                    botaoSalvar.setEnabled(true);
                    componentesCadastro.get(KEY_NOME).setValue(idoso.getNomeIdoso());
                    componentesCadastro.get(KEY_DATA_NASCIMENTO).setValue(idoso.getDataNascimento());
                    componentesCadastro.get(KEY_LOCAL_ORIGEM).setValue(idoso.getLocalOrigem());
                    componentesCadastro.get(KEY_CPF).setValue(idoso.getCpf());
                    componentesCadastro.get(KEY_RG).setValue(idoso.getRg());
                    componentesCadastro.get(KEY_OBSERVACOES).setValue(idoso.getCuidadosEspeciais());
                    componentesCadastro.get(KEY_NOME_PARENTE).setValue(idoso.getNomeParenteResponsavel());
                    componentesCadastro.get(KEY_END_PARENTE).setValue(idoso.getEndParente());
                    componentesCadastro.get(KEY_FONE_PARENTE).setValue(idoso.getNumTelefoneParente());
                    //componentesCadastro.get(KEY_QUARTO, quarto);

                    componentesCadastro.get(KEY_NUM_QUARTO);
                    componentesCadastro.get(KEY_NUM_ANDAR);
                    componentesCadastro.get(KEY_CAPACIDADE);
                    componentesCadastro.get(KEY_ESTADO);
                }
                else {
                    Mensagens.naoEncontradoConsulta(this);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            Mensagens.camposInvalidos(this);
        }
    }//GEN-LAST:event_botaoConsultarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaObservacoes;
    private javax.swing.JTextArea areaObservacoesEdicao;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JButton botaoConsultar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JFormattedTextField campoCPF;
    private javax.swing.JFormattedTextField campoCPFEdicao;
    private javax.swing.JFormattedTextField campoDataNascimento;
    private javax.swing.JFormattedTextField campoDataNascimentoEdicao;
    private javax.swing.JTextField campoEnderecoParente;
    private javax.swing.JTextField campoEnderecoParenteEdicao;
    private javax.swing.JFormattedTextField campoFoneParente;
    private javax.swing.JFormattedTextField campoFoneParenteEdicao;
    private javax.swing.JTextField campoLocOrigem;
    private javax.swing.JTextField campoLocOrigemEdicao;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoNomeEdicao;
    private javax.swing.JTextField campoNomeParente;
    private javax.swing.JTextField campoNomeParenteEdicao;
    private javax.swing.JFormattedTextField campoRG;
    private javax.swing.JFormattedTextField campoRGEdicao;
    private javax.swing.JCheckBox checkBoxAcamado;
    private javax.swing.JCheckBox checkBoxAcamadoEdicao;
    private javax.swing.JComboBox comboBoxIdoso;
    private javax.swing.JComboBox comboBoxQuarto;
    private javax.swing.JComboBox comboBoxQuartoEdicao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelEspacoDisponivel;
    private javax.swing.JLabel labelEspacoDisponivelEdicao;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelEstadoEdicao;
    private javax.swing.JLabel labelNumAndar;
    private javax.swing.JLabel labelNumAndarEdicao;
    private javax.swing.JLabel labelNumQuarto;
    private javax.swing.JLabel labelNumQuartoEdicao;
    // End of variables declaration//GEN-END:variables
}
