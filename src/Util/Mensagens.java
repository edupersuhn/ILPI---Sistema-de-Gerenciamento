package Util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class Mensagens {

    public static void camposInvalidos(Component c) {
        JOptionPane.showMessageDialog(c, "Aviso: campos não estão preenchidos ou contém valores não-aceitáveis!");
    }
    
    public static void cadastradoComSucesso(Component c) {
        JOptionPane.showMessageDialog(c, "Cadastrado com sucesso");
    }
    
    public static void naoEncontradoConsulta(Component c) {
        JOptionPane.showMessageDialog(c, "Aviso: nada encontrado na consulta, não consta no banco de dados!");
    }
    
    public static void nadaParaAlterar(Component c) {
        JOptionPane.showMessageDialog(c, "Aviso: nenhuma alteração para salvar!");
    }
    
    public static void alteradoComSucesso(Component c) {
        JOptionPane.showMessageDialog(c, "Alterado com sucesso");
    }
    
}
