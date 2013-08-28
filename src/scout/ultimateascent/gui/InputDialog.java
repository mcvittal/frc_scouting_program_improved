/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scout.ultimateascent.gui;

/**
 *
 * @author Alex
 */
import javax.swing.*;
import java.awt.event.*;
import scout.ultimateascent.coreutils.FileIO;


public class InputDialog extends JFrame implements ActionListener {
    String prefix = "";
    JLabel lblMsg = new JLabel();
    public JTextField txtInput = new JTextField(15);
    JButton cmdOk = new JButton("Ok");
    SpringLayout layout = new SpringLayout();
    JLabel label = null;
    public String text = "";
    String [] array;
    String path;
    int x;
    public InputDialog(String msg, String title, JLabel lbl, String prefix, String [] data, String path, int x){
        array = data;
        this.path = path;
        this.x = x;
        
        lblMsg.setText(msg);
        this.add(lblMsg);
        this.add(txtInput);
        this.add(cmdOk);
        cmdOk.addActionListener(this);
        GUIHelper.setLocation(lblMsg, layout, rootPane, 10, 10);
        GUIHelper.setLocation(txtInput, layout, rootPane, 10, 35);
        GUIHelper.setLocation(cmdOk, layout, rootPane, 185, 35);
        GUIHelper.setSize(cmdOk, 50, 20);
        
        label = lbl;
        this.prefix = prefix;
        this.setSize(250, 90);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle(title);
        this.getContentPane().setLayout(layout);
    }
    public String getTextField(){
        return txtInput.getText();
    }
    public void actionPerformed(ActionEvent e) {
         //text = txtInput.getText();
         label.setText(prefix + getTextField());
         array[x] = getTextField();
         FileIO.overWriteToFile(array, path);
         //text = txtInput.getText();
         this.setVisible(false);
         
         
    }
    
    
    
    
}
