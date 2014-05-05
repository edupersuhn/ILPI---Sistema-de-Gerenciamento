package Util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 */
public class Mensagens {

    public static void campoInvalido(Component c, String campo) {
        JOptionPane.showMessageDialog(c, "Aviso: campo " + campo + " não está preenchido ou contém valor não-aceitável!");
    }
    
    public static void cadastradoComSucesso(Component c) {
        JOptionPane.showMessageDialog(c, "Cadastrado com sucesso");
    }
    
    public static void naoEncontradoConsulta(Component c) {
        JOptionPane.showMessageDialog(c, "Aviso: nada encontrado na consulta, não consta no banco de dados!");
    }
    
    public static void alteradoComSucesso(Component c) {
        JOptionPane.showMessageDialog(c, "Alterado com sucesso");
    }
    
}
