/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scout.ultimateascent.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.SpringLayout;
import scout.ultimateascent.coreutils.FileIO;
import scout.ultimateascent.coreutils.Global;
import scout.ultimateascent.database.GameData;

/**
 *
 * @author Alex
 */
public class ProfileScreen extends JFrame implements ActionListener{

    SpringLayout layout = new SpringLayout();
    JLabel lblPic = new JLabel("");
    ImageIcon img = null;
    
    JLabel [] lblArray;
    String [] prefixes;
    String [] newData;
    JButton [] cmdArray;
    JCheckBox chkHide = new JCheckBox("Hide from stat table");
    String database;
    String team;
    
    GameData g;
    
    
    Object [][] rawData;
    Object [] rawDataColumnNames = {"TGP","A1T", "A2T", "A3T", "AGT", "AMT",
                                    "T1T", "T2T", "T3T", "T5T", "TMT",
                                    "TF", "TDS", "TCA", "TCS", "TCP",
                                    "TGP","FCS", "FS",  "TFT", "TGD",
                                    "PR", "BFCS", "BNFCS", "TGEB", "TFP", "TW"};
    
    
    JPanel rawPanel = new JPanel();
    JTable rawStats;
     
    Object [][] calculatedData;
    Object [] calculatedDataColumnNames = {"AAV", "A%AC", "TAV", "T%AC", "%COF", "AVDS","ACT", "ACP"};   
    
    JPanel calculatedPanel = new JPanel();
    JTable calculatedStats;
    
    
    
    public ProfileScreen(String database, String team){
        
        try{
            File f = new File(Global.prefix + database + "/Photos/" + team + ".jpg");
            if(f.exists())
                img = new ImageIcon(Global.prefix + database + "/Photos/" + team + ".jpg");
            
            else
                img = new ImageIcon(Global.prefix + database + "/Photos/default.jpg");
        }
        catch(Exception e){
            img = new ImageIcon(Global.prefix + database + "/Photos/default.jpg");
        }
        
        img.setImage(img.getImage().getScaledInstance(200, 280, 1));
        
        System.out.println(Global.prefix + database + "/Photos/" + team + ".jpg");
        g = new GameData(database, team);
        rawData = new Object[1][g.rawData.length];
        rawData[0] = g.rawData;
        lblPic.setIcon(img);
        
        calculatedData = new Object[1][g.calculatedData.length];
        calculatedData[0] = g.calculatedData;
        
        rawStats = new JTable(rawData, rawDataColumnNames);
        calculatedStats = new JTable(calculatedData, calculatedDataColumnNames);
        
        this.database = database;
        this.team = team;
        String path = Global.prefix + database + "/" + team;
        lblArray = new JLabel[18];
        prefixes = new String[18];
        cmdArray = new JButton[16];
        JScrollPane rawPane = new JScrollPane(rawStats);
        JScrollPane calculatedPane = new JScrollPane(calculatedStats);
        
        
        
        prefixes[0] = "";
        prefixes[1] = "Team name:  ";
        prefixes[2] = "Wins / Event:  ";
        prefixes[3] = "W - L - T:  ";
        prefixes[4] = "Drive train:  ";
        prefixes[5] = "Drive train speed:  ";
        prefixes[6] = "Max/min height:  ";
        prefixes[7] = "Weight:  ";
        prefixes[8] = "Ground pickup?:  ";
        prefixes[9] = "Human player feeds:  ";
        prefixes[10] = "Auton position(s):  ";
        prefixes[11] = "# Auton discs:  ";
        prefixes[12] = "Teleop shooting position(s):  ";
        prefixes[13] = "Shoot in what goal?:  ";
        prefixes[14] = "Climb level:  ";
        prefixes[15] = "Full court shooter:  ";
        prefixes[16] = "Max OPS:  ";
        prefixes[17] = "<html><u>Additional comments</u><br>";
      
        this.add(lblPic);
            for(int x = 0; x < prefixes.length; x++){
            lblArray[x] = new JLabel(prefixes[x]);
            this.add(lblArray[x]);
            if(x < cmdArray.length){
                cmdArray[x] = new JButton("Change");
                cmdArray[x].addActionListener(this);
                cmdArray[x].setActionCommand(x + "");
                this.add(cmdArray[x]);
            }
            
        }      
        this.add(chkHide);
        chkHide.addActionListener(this);
        chkHide.setActionCommand("hide");
        this.add(lblPic);
        this.add(rawPane);
        this.add(calculatedPane);
       
        
        GUIHelper.setLocation(lblPic, layout, rootPane, 10, 10);
        
        for(int x = 0; x < lblArray.length; x++){
            GUIHelper.setLocation(lblArray[x], layout, rootPane, 300, 10 + x * 30 - 30);
            if(x > 0 && x <= cmdArray.length){
                GUIHelper.setLocation(cmdArray[x - 1], layout, rootPane, 220, 10 + x * 30 - 30);
            }
        }
        loadFromFile(database, team);
        
        GUIHelper.setSize(rawPane, 800, 40);
        GUIHelper.setLocation(rawPane, layout, rootPane, 520, 10);
        
        GUIHelper.setSize(calculatedPane, 400, 40);
        GUIHelper.setLocation(calculatedPane, layout, rootPane, 920, 90);
        
        GUIHelper.setLocation(chkHide, layout, rootPane, 920, 130);
        if(g.isHidden){
            chkHide.setSelected(true);
        }
        else
            chkHide.setSelected(false);
        this.getContentPane().setLayout(layout);       
        this.setSize(1360, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Ultimate Scout | Profile of team " + team);
        
    }
    private void loadFromFile(String database, String team){
        String path = Global.prefix + database + "/" + team + "/profile/profile.txt";
        String [] array = FileIO.getContentsOfFile(path);
        newData = array;
        for(int x = 0; x < array.length; x++){
                lblArray[x+1].setText(lblArray[x+1].getText() + array[x]);
        }
        String [] comments = g.getComments();
        for(int x = 0; x < FileIO.getFileLength(Global.prefix + database + "/" + team + "/gameData/comments.txt"); x++){
            lblArray[lblArray.length - 1].setText(lblArray[lblArray.length - 1].getText() + comments[x]);
        }
        
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("hide")){
            if(chkHide.isSelected()){
                FileIO.overWriteToFile(new String[]{"true"}, Global.prefix + database + "/" + team + "/gameData/isHidden.txt");
            }
            else
                FileIO.overWriteToFile(new String[]{"false"}, Global.prefix + database + "/" + team + "/gameData/isHidden.txt");
                
        }
        for(int x = 0; x < cmdArray.length-1; x++){
            if(e.getActionCommand().equals(x + "")){
                InputDialog d = new InputDialog("Change value of label:", "Title", lblArray[x + 1], prefixes[x+1], newData, Global.prefix + database + "/" + team + "/profile/profile.txt",  x);

            }
        }

            
        
    }
    
    
}
