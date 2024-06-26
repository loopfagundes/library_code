package br.dev.codex.joptionPane;

import javax.swing.*;

public class ExemploJOptionPane {
    /*
     * Vai abrir automaticamente de uma mensagem da janela
     */

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,"Mensagem em um diálogo");
        JOptionPane.showInternalConfirmDialog(null, "Mensagem em um confirmar Sim ou Nao");
    }
}