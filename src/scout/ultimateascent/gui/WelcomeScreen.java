/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scout.ultimateascent.gui;

import scout.ultimateascent.coreutils.FileIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import scout.ultimateascent.coreutils.Global;

/**
 *
 * @author alex
 */
public class WelcomeScreen extends JFrame implements ActionListener {
    
    JLabel lblTitle = new JLabel("Choose from an existing scouting database:");
    private JComboBox cboDataBaseDropdown = new JComboBox();
    private JButton cmdOpen = new JButton("Open");
    private JButton cmdNew = new JButton("New Database");
    private JButton cmdHelp = new JButton("Help");
    private JButton cmdAbout = new JButton("About");
    
    SpringLayout layout = new SpringLayout();
    
    
    public WelcomeScreen(){
        this.add(lblTitle);
        this.add(cboDataBaseDropdown);
        this.add(cmdOpen);
        this.add(cmdNew);
        this.add(cmdHelp);
        this.add(cmdAbout);
        
        cmdOpen.addActionListener(this);
        cmdNew.addActionListener(this);
        cmdHelp.addActionListener(this);
        cmdAbout.addActionListener(this);
        
        cmdOpen.setActionCommand("open");
        cmdNew.setActionCommand("new");
        cmdHelp.setActionCommand("help");
        cmdAbout.setActionCommand("about");
        
        this.getContentPane().setLayout(layout);
        
        
        GUIHelper.setLocation(lblTitle, layout, rootPane, 10, 10);
        GUIHelper.setLocation(cboDataBaseDropdown, layout, rootPane, 10, 40);
        GUIHelper.setLocation(cmdOpen, layout, rootPane, 140, 40);        
        GUIHelper.setLocation(cmdNew, layout, rootPane, 230, 40);        
        GUIHelper.setLocation(cmdHelp, layout, rootPane, 140, 70);
        GUIHelper.setLocation(cmdAbout, layout, rootPane, 230, 70);
        
        
        GUIHelper.setupButton(cmdNew, 140, 24, true, true);
        GUIHelper.setupButton(cmdOpen, 80, 24, true, true);
        GUIHelper.setupButton(cmdHelp, 80, 24, true, true);
        GUIHelper.setupButton(cmdAbout, 140, 24, true, true);
        
        GUIHelper.setupComboBox(cboDataBaseDropdown, 120, 24);
        String[] databaseList = FileIO.getContentsOfFile("src/databaseList.txt");
        
        for(int x = 0; x < databaseList.length; x++){
            cboDataBaseDropdown.addItem(databaseList[x]);
        }
        
        this.setSize(380, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Ultimate Scout | Welcome");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
       
    }
    public void notImplementedMessage(){
        JOptionPane.showMessageDialog(this,
                                    "This feature is not yet implemented in this version.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
    }
    


    public void actionPerformed(ActionEvent arg0) {
        
        if(arg0.getActionCommand().equals("open")){
            if(FileIO.checkDir(Global.prefix + cboDataBaseDropdown.getSelectedItem().toString())){
                FileIO.createDir(Global.prefix + cboDataBaseDropdown.getSelectedItem().toString());
            }
            MainScreen screen = new MainScreen(cboDataBaseDropdown.getSelectedItem().toString());
            this.setVisible(false);
            this.dispose();
        }
        else if(arg0.getActionCommand().equals("new")){
            notImplementedMessage();
        }
        else if(arg0.getActionCommand().equals("help")){
            notImplementedMessage();
        }
        else if(arg0.getActionCommand().equals("about")){
            JOptionPane.showMessageDialog(this,
                                    "Ultimate Scout v" + Global.VERSION + "\nUltimate Scout was designed and written by Alex McVittie.\nThanks also to Chris Dashford, Jared Baribeau, Carson Ennis, \nMatt Wilmink, and Emily McDermott for their help with parts of this project.",
                                    "About",
                                    JOptionPane.INFORMATION_MESSAGE);
            
        }
    }   
}
