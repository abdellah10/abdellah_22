/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package git2;


import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Git3 {
    
    static final int MIN = 0;
    static final int MAX = 1000;
    static final int INT = 0;
    
    private static JLabel label = new JLabel(" 1 Euro son en dolares :");
    private static JLabel lbleuros = new JLabel("Euros");
    private static JLabel lbldolares = new JLabel("Dolares");
    private static JFrame frame = new JFrame("Conversor Euros - Dolares ");
    private static JPanel panel1 = new JPanel();
    private static JPanel panel2 = new JPanel();
    private static JPanel panel3 = new JPanel();
    private static JTextField txteuro = new JTextField("0");
    private static JTextField txtdolar = new JTextField("0");
    private static JTextField txtcambio = new JTextField("1.36");
    private static JSlider sliderdolar = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INT);
    private static JSlider slidereuro = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INT);
    
    public static void cambiotexto(ActionEvent e) {
        if (e.getSource() == txteuro) {
            
            float icambio = Float.parseFloat(txteuro.getText());
            icambio = 100 * icambio * Float.parseFloat(txtcambio.getText());
            icambio = Math.round(icambio);
            icambio = icambio / 100;
            txtdolar.setText(String.valueOf(icambio));
            //cambiar los slider
            sliderdolar.setValue(Math.round(Float.parseFloat(txtdolar.getText())));
            slidereuro.setValue(Math.round(Float.parseFloat(txteuro.getText())));
        }
        if (e.getSource() == txtdolar) {
            System.out.println("dentro");
            float icambio = Float.parseFloat(txtdolar.getText());
            icambio = 100 * icambio / Float.parseFloat(txtcambio.getText());
            icambio = Math.round(icambio);
            icambio = icambio / 100;
            txteuro.setText(String.valueOf(icambio));
            
        }
    }
    
    public static void mueveSlider(ChangeEvent e) {
        
        int valor;
        JSlider obj = (JSlider) e.getSource();
        System.out.println(obj.getValueIsAdjusting());
        System.out.println(obj.getValue());
        
        if (!obj.getValueIsAdjusting()) {
            System.out.println(obj.getValue());
            valor = (int) obj.getValue();
            if (obj == sliderdolar) {
                txtdolar.setText(String.valueOf(valor));
                float icambio = 100 * valor / Float.parseFloat(txtcambio.getText());
                icambio = Math.round(icambio);
                icambio = icambio / 100;
                //cambiar el texteuro
                int i = Math.round(icambio);
                slidereuro.setValue(i);
            }
            if (obj == slidereuro) {
                txteuro.setText(String.valueOf(valor));
                float icambio = 100 * valor * Float.parseFloat(txtcambio.getText());
                icambio = Math.round(icambio);
                icambio = icambio / 100;
                //cambiar el textdolar
                txtdolar.setText(String.valueOf(icambio));
                //cambiar slider
                int i = Math.round(icambio);
                slidereuro.setValue(i);
                
            }
            
        }
    }
    
    public static void colocaelementos() {
        frame.getContentPane().add(panel1);
        frame.getContentPane().add(panel2);
        frame.getContentPane().add(panel3);
        
        slidereuro.setBorder(BorderFactory.createTitledBorder("Euros"));
        slidereuro.setMajorTickSpacing(200);
        slidereuro.setMajorTickSpacing(100);
        slidereuro.setPaintTicks(true);
        slidereuro.setPaintLabels(true);
        slidereuro.disable();
        
        sliderdolar.setBorder(BorderFactory.createTitledBorder("Dolares"));
        sliderdolar.setMajorTickSpacing(200);
        sliderdolar.setMajorTickSpacing(100);
        sliderdolar.setPaintTicks(true);
        sliderdolar.setPaintLabels(true);
        sliderdolar.disable();
        
        panel1.add(lbleuros);
        panel2.add(txteuro);
        panel3.add(slidereuro);
        
        panel1.add(label);
        panel2.add(txtcambio);
        
        panel1.add(lbldolares);
        panel2.add(txtdolar);
        panel3.add(sliderdolar);
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        txteuro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            cambiotexto(e);
        }
    });
        txtdolar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            cambiotexto(e);
        }
    });
    }
    
}
