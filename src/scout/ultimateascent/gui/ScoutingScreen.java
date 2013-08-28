/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scout.ultimateascent.gui;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import scout.ultimateascent.coreutils.Global;
import scout.ultimateascent.database.*;

/**
 *
 * @author alex
 */
public class ScoutingScreen extends JFrame implements ActionListener {
    public static String path = "";
    String spacing = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    String database = "";
    JLabel lblTeamNum = new JLabel("Team #");
    public static JTextField txtTeamNum = new JTextField(4);
    
    JLabel lblAuton = new JLabel("<html><u>Autonomous</u><br>1pt" + spacing + "2pt" + spacing + "3pt" + spacing + "missed&nbsp;&nbsp;Discs picked up");
    JButton cmdAuton1Up = new JButton("+");
    JButton cmdAuton1Down = new JButton("-");
    JLabel lblAuton1Val = new JLabel("0");
    public static int auton1;
    
    JButton cmdAuton2Up = new JButton("+");
    JButton cmdAuton2Down = new JButton("-");
    JLabel lblAuton2Val = new JLabel("0");
    public static int auton2 = 0;
    
    JButton cmdAuton3Up = new JButton("+");
    JButton cmdAuton3Down = new JButton("-");
    JLabel lblAuton3Val = new JLabel("0");
    public static int auton3 = 0;
    
    JButton cmdAutonMissedUp = new JButton("+");
    JButton cmdAutonMissedDown = new JButton("-");
    JLabel lblAutonMissedVal = new JLabel("0");
    public static int autonMissed = 0;
    
    JButton cmdAutonGroundUp = new JButton("+");
    JButton cmdAutonGroundDown = new JButton("-");
    JLabel lblAutonGroundVal = new JLabel("0");
    public static int autonGround = 0;
    
    JLabel lblTeleop = new JLabel("<html><u>Teleop</u><br>1pt&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2pt&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3pt&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5pt" + spacing + "missed");
    JButton cmdTeleop1Up = new JButton("+");
    JButton cmdTeleop1Down = new JButton("-");  
    JLabel lblTeleop1Val = new JLabel("0");
    public static int tele1 = 0;
    
    JButton cmdTeleop2Up = new JButton("+");
    JButton cmdTeleop2Down = new JButton("-");  
    JLabel lblTeleop2Val = new JLabel("0");
    public static int tele2 = 0;
    
    JButton cmdTeleop3Up = new JButton("+");
    JButton cmdTeleop3Down = new JButton("-");    
    JLabel lblTeleop3Val = new JLabel("0");
    public static int tele3 = 0;
    
    JButton cmdTeleop5Up = new JButton("+");
    JButton cmdTeleop5Down = new JButton("-");
    JLabel lblTeleop5Val = new JLabel("0");
    public static int tele5 = 0;
    
    JButton cmdTeleopMissedUp = new JButton("+");
    JButton cmdTeleopMissedDown = new JButton("-");
    JLabel lblTeleopMissedVal = new JLabel("0");
    public static int teleMissed = 0;
    
    public static JCheckBox chkMalfunction = new JCheckBox("Broke down?");
    public static JTextField txtComments = new JTextField("Comments/Driver ability",20);
    JLabel lblDriverSkill = new JLabel("Driver skill:");
    public static JComboBox cboDriverSkill = new JComboBox(new String[]{"1","2","3","4","5"});
    
    
    public static JCheckBox chkClimbAttempted = new JCheckBox("Climb attempted?");
    public static JCheckBox chkClimbSuccessful = new JCheckBox("Climb successful?");
    JLabel lblClimbTime = new JLabel("Time of climb:");
    public static JTextField txtClimbTime = new JTextField("0",3);
    JLabel lblClimb = new JLabel("Points from climb:");
    public static JComboBox cboClimb = new JComboBox(new String[]{"10","20","30"});
    
    JLabel lblOffense = new JLabel("<html><u>Offensive information</u>");
    
    //Offense objects 
    public static JCheckBox chkGroundPickup = new JCheckBox("Has ground pickup?");
    public static JCheckBox chkFullCourtShooter = new JCheckBox("Has full court shooter?");
    
    public static JCheckBox chkFeederStation = new JCheckBox("Uses feeder station?");
    JLabel lblFeederTrips = new JLabel("Trips:");
    public static JComboBox cboFeederTrips = new JComboBox(new String[]{"0","1","2","3","4","5","6","7","8","9","10"});
    
    JButton cmdSubmit = new JButton("Save data");
    
    JLabel lblDefense = new JLabel("<html><u>Defensive information</u>");
    //Defense objects
    public static JCheckBox chkDefense = new JCheckBox("Did this robot play defense?");
    public static JCheckBox chkPush = new JCheckBox("Able to push other robots?");
    public static JCheckBox chkBlockFullCourtShooters = new JCheckBox("Can they block full court shooters?");
    public static JCheckBox chkBlockNonFullCourtShooters = new JCheckBox("Can they block non-full court shooters?");
    public static JCheckBox chkEasilyBlocked = new JCheckBox("Easily blocked?");
    JLabel lblFouls = new JLabel("Points awarded to opposition via fouls:");
    public static JTextField txtFouls = new JTextField("0",5);
    
    public static JCheckBox chkWin = new JCheckBox("Alliance victory?");
    
    SpringLayout layout = new SpringLayout();
    
    public ScoutingScreen(String database){
        resetValues();
        
        this.database = database;
        path = Global.prefix + database + "/";
        this.add(lblTeamNum);
        this.add(txtTeamNum);
        this.add(lblAuton);
        this.add(cmdAuton1Up);
        this.add(cmdAuton1Down);
        this.add(lblAuton1Val);
        this.add(cmdAuton2Up);
        this.add(cmdAuton2Down);
        this.add(lblAuton2Val);
        this.add(cmdAuton3Up);
        this.add(cmdAuton3Down);
        this.add(lblAuton3Val);
        this.add(cmdAutonMissedUp);
        this.add(cmdAutonMissedDown);
        this.add(lblAutonMissedVal);
        this.add(cmdAutonGroundUp);
        this.add(cmdAutonGroundDown);
        this.add(lblAutonGroundVal);
        
        
        this.add(lblTeleop);
        this.add(cmdTeleop1Up);
        this.add(cmdTeleop1Down);
        this.add(lblTeleop1Val);
        this.add(cmdTeleop2Up);
        this.add(cmdTeleop2Down);
        this.add(lblTeleop2Val);
        this.add(cmdTeleop3Up);
        this.add(cmdTeleop3Down);        
        this.add(lblTeleop3Val);
        this.add(cmdTeleop5Up);
        this.add(cmdTeleop5Down);
        this.add(lblTeleop5Val);
        this.add(cmdTeleopMissedUp);
        this.add(cmdTeleopMissedDown);
        this.add(lblTeleopMissedVal);
        this.add(chkMalfunction);
        
        this.add(txtComments);
        this.add(lblDriverSkill);
        this.add(cboDriverSkill);
        
        this.add(chkClimbAttempted);
        this.add(chkClimbSuccessful);
        this.add(lblClimbTime);
        this.add(txtClimbTime);
        this.add(lblClimb);
        this.add(cboClimb);
        
        this.add(lblOffense);
        this.add(chkGroundPickup);
        this.add(chkFullCourtShooter);
        this.add(chkFeederStation);
        this.add(lblFeederTrips);
        this.add(cboFeederTrips);
        
        this.add(lblDefense);
        this.add(chkDefense);
        this.add(chkPush);
        this.add(chkBlockFullCourtShooters);
        this.add(chkBlockNonFullCourtShooters);
        this.add(chkEasilyBlocked);
        this.add(lblFouls);
        this.add(txtFouls);
        
        this.add(chkWin);
        
        this.add(cmdSubmit);
        
        txtComments.addActionListener(this);
        cmdAuton1Up.addActionListener(this);
        cmdAuton1Down.addActionListener(this);        
        cmdAuton2Up.addActionListener(this);
        cmdAuton2Down.addActionListener(this);        
        cmdAuton3Up.addActionListener(this);
        cmdAuton3Down.addActionListener(this);
        cmdAutonMissedUp.addActionListener(this);
        cmdAutonMissedDown.addActionListener(this);
        cmdAutonGroundUp.addActionListener(this);
        cmdAutonGroundDown.addActionListener(this);
        
        cmdTeleop1Up.addActionListener(this);
        cmdTeleop1Down.addActionListener(this);
        cmdTeleop2Up.addActionListener(this);
        cmdTeleop2Down.addActionListener(this);
        cmdTeleop3Up.addActionListener(this);
        cmdTeleop3Down.addActionListener(this);
        cmdTeleop5Up.addActionListener(this);
        cmdTeleop5Down.addActionListener(this);
        cmdTeleopMissedUp.addActionListener(this);
        cmdTeleopMissedDown.addActionListener(this);
        
        chkClimbAttempted.addActionListener(this);
        chkClimbSuccessful.addActionListener(this);
        
        chkFeederStation.addActionListener(this);
        
        cmdSubmit.addActionListener(this);
        
        cmdAuton1Up.setActionCommand("A1+");
        cmdAuton1Down.setActionCommand("A1-");
        cmdAuton2Up.setActionCommand("A2+");
        cmdAuton2Down.setActionCommand("A2-");
        cmdAuton3Up.setActionCommand("A3+");
        cmdAuton3Down.setActionCommand("A3-");
        cmdAutonMissedUp.setActionCommand("AM+");
        cmdAutonMissedDown.setActionCommand("AM-");
        cmdAutonGroundUp.setActionCommand("AG+");
        cmdAutonGroundDown.setActionCommand("AG-");
        
        cmdTeleop1Down.setActionCommand("T1-");
        cmdTeleop1Up.setActionCommand("T1+");
        cmdTeleop2Down.setActionCommand("T2-");
        cmdTeleop2Up.setActionCommand("T2+");
        cmdTeleop3Down.setActionCommand("T3-");
        cmdTeleop3Up.setActionCommand("T3+"); 
        cmdTeleop5Down.setActionCommand("T5-");
        cmdTeleop5Up.setActionCommand("T5+");
        cmdTeleopMissedUp.setActionCommand("TM+");
        cmdTeleopMissedDown.setActionCommand("TM-");
        
        chkClimbAttempted.setActionCommand("CA");
        chkClimbSuccessful.setActionCommand("CS");
        
        chkFeederStation.setActionCommand("FS");
        
        cmdSubmit.setActionCommand("submit");
        
        txtComments.setActionCommand("comments");
        
        GUIHelper.setLocation(lblTeamNum, layout, rootPane, 10, 10);
        GUIHelper.setLocation(txtTeamNum, layout, rootPane, 60, 10);
        GUIHelper.setLocation(lblAuton, layout, rootPane, 10, 45);
        
        GUIHelper.setLocation(cmdAuton1Up, layout, rootPane, 10, 85);
        GUIHelper.setLocation(cmdAuton2Up, layout, rootPane, 60,85);
        GUIHelper.setLocation(cmdAuton3Up, layout, rootPane, 110,85);
        GUIHelper.setLocation(cmdAutonMissedUp, layout, rootPane, 160, 85);
        GUIHelper.setLocation(cmdAutonGroundUp, layout, rootPane, 210, 85);
        
        GUIHelper.setLocation(lblAuton1Val, layout, rootPane, 12, 115);
        GUIHelper.setLocation(lblAuton2Val, layout, rootPane, 62, 115);
        GUIHelper.setLocation(lblAuton3Val, layout, rootPane, 112, 115);
        GUIHelper.setLocation(lblAutonMissedVal, layout, rootPane, 162, 115);
        GUIHelper.setLocation(lblAutonGroundVal, layout, rootPane, 212, 115);
        
        GUIHelper.setLocation(cmdAuton1Down, layout, rootPane, 10, 135);
        GUIHelper.setLocation(cmdAuton2Down, layout, rootPane, 60,135);
        GUIHelper.setLocation(cmdAuton3Down, layout, rootPane, 110,135);
        GUIHelper.setLocation(cmdAutonMissedDown, layout, rootPane, 160, 135);
        GUIHelper.setLocation(cmdAutonGroundDown, layout, rootPane, 210, 135);
        
        GUIHelper.setLocation(lblTeleop, layout, rootPane, 10, 180);
        
        GUIHelper.setLocation(cmdTeleop1Up, layout, rootPane, 10, 220);
        GUIHelper.setLocation(cmdTeleop2Up, layout, rootPane, 60,220);
        GUIHelper.setLocation(cmdTeleop3Up, layout, rootPane, 110,220);
        GUIHelper.setLocation(cmdTeleop5Up, layout, rootPane, 160, 220);
        GUIHelper.setLocation(cmdTeleopMissedUp, layout, rootPane, 210, 220);
        
        GUIHelper.setLocation(lblTeleop1Val, layout, rootPane, 10, 250);
        GUIHelper.setLocation(lblTeleop2Val, layout, rootPane, 60,250);
        GUIHelper.setLocation(lblTeleop3Val, layout, rootPane, 110,250);
        GUIHelper.setLocation(lblTeleop5Val, layout, rootPane, 160, 250);
        GUIHelper.setLocation(lblTeleopMissedVal, layout, rootPane, 210, 250);
        
        GUIHelper.setLocation(cmdTeleop1Down, layout, rootPane, 10, 270);
        GUIHelper.setLocation(cmdTeleop2Down, layout, rootPane, 60,270);
        GUIHelper.setLocation(cmdTeleop3Down, layout, rootPane, 110,270);
        GUIHelper.setLocation(cmdTeleop5Down, layout, rootPane, 160, 270);
        GUIHelper.setLocation(cmdTeleopMissedDown, layout, rootPane, 210, 270);
        
        GUIHelper.setLocation(chkMalfunction, layout, rootPane, 10,300);        
        GUIHelper.setLocation(lblDriverSkill, layout, rootPane, 125, 305);
        GUIHelper.setLocation(cboDriverSkill, layout, rootPane, 200, 300);
        
        
        GUIHelper.setLocation(chkClimbAttempted, layout, rootPane, 320, 10);
        GUIHelper.setLocation(chkClimbSuccessful, layout, rootPane, 320, 35);
        GUIHelper.setLocation(lblClimbTime, layout, rootPane, 320,60);
        GUIHelper.setLocation(txtClimbTime, layout, rootPane, 400, 60);
        GUIHelper.setLocation(lblClimb, layout, rootPane, 320, 80);
        GUIHelper.setLocation(cboClimb, layout, rootPane, 430, 80);
        GUIHelper.setSize(cboClimb, 45, 20);
        
        GUIHelper.setLocation(lblOffense, layout, rootPane, 320, 120);
        GUIHelper.setLocation(chkGroundPickup, layout, rootPane, 320, 140);
        GUIHelper.setLocation(chkFullCourtShooter, layout, rootPane, 320, 160);
        GUIHelper.setLocation(chkFeederStation, layout, rootPane, 320, 180);
        GUIHelper.setLocation(lblFeederTrips, layout, rootPane, 465, 184);
        GUIHelper.setLocation(cboFeederTrips, layout, rootPane, 498, 180);
        
        GUIHelper.setLocation(lblDefense, layout, rootPane, 320, 220);
        GUIHelper.setLocation(chkDefense, layout, rootPane, 320, 240);
        GUIHelper.setLocation(chkPush, layout, rootPane, 320, 260);
        GUIHelper.setLocation(chkBlockFullCourtShooters, layout, rootPane, 320, 280);
        GUIHelper.setLocation(chkBlockNonFullCourtShooters, layout, rootPane, 320, 300);
        GUIHelper.setLocation(chkEasilyBlocked, layout, rootPane, 320, 320);
        GUIHelper.setLocation(lblFouls, layout, rootPane, 320, 350);
        GUIHelper.setLocation(txtFouls, layout, rootPane, 540, 350);
        
        GUIHelper.setLocation(txtComments, layout, rootPane, 10,350);
        
        GUIHelper.setLocation(chkWin, layout, rootPane, 320, 370);
        
        GUIHelper.setLocation(cmdSubmit, layout, rootPane, 10, 380);
        
        GUIHelper.setSize(cmdSubmit, 100, 30);
        
        
        this.getContentPane().setLayout(layout);
        
        this.setSize(650,450);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Ultimate Scout | Scouting Screen");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        chkClimbSuccessful.setEnabled(false);
        txtClimbTime.setText("0");
        txtClimbTime.setEnabled(false);
        cboClimb.setEnabled(false);
        cboFeederTrips.setEnabled(false);
        
        
        
    }
    
    private void resetValues(){
        auton1 = 0;
        auton2 = 0;
        auton3 = 0;
        autonMissed = 0;
        autonGround = 0;
        tele1 = 0;
        tele2 = 0;
        tele3 = 0;
        tele5 = 0;
        teleMissed = 0;
        chkMalfunction.setSelected(false);
        txtComments.setText("Comments/Driver ability");
        cboDriverSkill.setSelectedIndex(0);
        chkClimbAttempted.setSelected(false);
        chkClimbSuccessful.setSelected(false);
        txtClimbTime.setText("0");
        cboClimb.setSelectedIndex(0);
        chkGroundPickup.setSelected(false);
        chkFullCourtShooter.setSelected(false);
        chkFeederStation.setSelected(false);
        cboFeederTrips.setSelectedIndex(0);
        chkDefense.setSelected(false);
        chkPush.setSelected(false);
        chkBlockFullCourtShooters.setSelected(false);
        chkBlockNonFullCourtShooters.setSelected(false);
        chkEasilyBlocked.setSelected(false);
        txtFouls.setText("0");
        chkWin.setSelected(false);
    }
    
    
    public void actionPerformed(ActionEvent a) {
        //throw new UnsupportedOperationException("Not supported yet.");
        
        
        //Autonomous data buttons
        if(a.getActionCommand().equals("A1+")){
            auton1++;
            lblAuton1Val.setText(auton1 + "");
        }
        else if(a.getActionCommand().equals("A1-")){
            if(auton1 > 0){
                auton1--;
                lblAuton1Val.setText(auton1 + "");
            }
        }
        else if(a.getActionCommand().equals("A2+")){
            auton2++;
            lblAuton2Val.setText(auton2 + "");
        }
        else if(a.getActionCommand().equals("A2-")){
            if(auton2 > 0){
                auton2--;
                lblAuton2Val.setText(auton2 + "");
            }
        }
        else if(a.getActionCommand().equals("A3+")){
            auton3++;
            lblAuton3Val.setText(auton3 + "");
        }
        else if(a.getActionCommand().equals("A3-")){
            if(auton3 > 0){
                auton3--;
                lblAuton3Val.setText(auton3 + "");
            }
        }
        else if(a.getActionCommand().equals("AM+")){
            autonMissed++;
            lblAutonMissedVal.setText(autonMissed + "");

        }
        else if(a.getActionCommand().equals("AM-")){
            if(autonMissed > 0){
                autonMissed--;
                lblAutonMissedVal.setText(autonMissed + "");
            }
        }
        else if(a.getActionCommand().equals("AG+")){
            autonGround++;
            lblAutonGroundVal.setText(autonGround + "");
        }
        else if(a.getActionCommand().equals("AG-")){
            if(autonGround > 0){
                autonGround--;
                lblAutonGroundVal.setText(autonGround + "");
            }
        }
        
        //Teleop data buttons
        else if(a.getActionCommand().equals("T1+")){
            tele1++;
            lblTeleop1Val.setText(tele1+"");
        }
        else if(a.getActionCommand().equals("T1-")){
            if(tele1 > 0){
                tele1--;
                lblTeleop1Val.setText(tele1+"");
            }
        }
        else if(a.getActionCommand().equals("T2+")){
            tele2++;
            lblTeleop2Val.setText(tele2 + "");
        }
        else if(a.getActionCommand().equals("T2-")){
            if(tele2 > 0){
                tele2--;
                lblTeleop2Val.setText(tele2 + "");
            }
        }
        else if(a.getActionCommand().equals("T3+")){
            tele3++;
            lblTeleop3Val.setText(tele3 + "");
        }
        else if(a.getActionCommand().equals("T3-")){
            if(tele3 > 0){
                tele3--;
                lblTeleop3Val.setText(tele3 + "");
            }
            
        }
        else if(a.getActionCommand().equals("T5+")){
            tele5++;
            lblTeleop5Val.setText(tele5 + "");
        }
        else if(a.getActionCommand().equals("T5-")){
            if(tele5 > 0){
                tele5--;
                lblTeleop5Val.setText(tele5 + "");
            }
        }
        else if(a.getActionCommand().equals("TM+")){
            teleMissed++;
            lblTeleopMissedVal.setText(teleMissed + "");
        }
        else if(a.getActionCommand().equals("TM-")){
            if(teleMissed > 0){
                teleMissed--;
                lblTeleopMissedVal.setText(teleMissed + "");
            }
        }
        else if(a.getActionCommand().equals("CA")){
            if(chkClimbAttempted.isSelected()){
                if(chkClimbSuccessful.isSelected()){
                    txtClimbTime.setText("0");
                    txtClimbTime.setEnabled(true);
                    cboClimb.setEnabled(true);
                }
                chkClimbSuccessful.setEnabled(true);
                
                
            }
            else{
                chkClimbSuccessful.setEnabled(false);
                txtClimbTime.setText("0");
                txtClimbTime.setEnabled(false);
                cboClimb.setEnabled(false);
            }
           
        }
        else if(a.getActionCommand().equals("CS")){
            if(chkClimbSuccessful.isSelected()){
                txtClimbTime.setText("0");
                txtClimbTime.setEnabled(true);
                cboClimb.setEnabled(true);
                }
            else{
                txtClimbTime.setText("0");
                txtClimbTime.setEnabled(false);
                cboClimb.setEnabled(false);
            }
        }
        else if(a.getActionCommand().equals("FS")){
            if(chkFeederStation.isSelected()){
                cboFeederTrips.setEnabled(true);
            }
            else{
                cboFeederTrips.setEnabled(false);
            }
        }
        else if(a.getActionCommand().equals("submit")){
            try{
                Integer.parseInt(txtTeamNum.getText());
                Integer.parseInt(txtClimbTime.getText());
                Integer.parseInt(txtFouls.getText());
                
                try{
                    GameData.readFromGUI(this);
                    this.dispose();
                    MainScreen m = new MainScreen(database);
                }
                catch(Exception f){
                    JOptionPane.showMessageDialog(this,
                    "Sorry, something went wrong. Contact the developer for a bug fix. \nError code: SS_SUB_T-C2",
                    "Unknown Error",
                    JOptionPane.ERROR_MESSAGE);
                    txtTeamNum.setText("");
                    txtClimbTime.setText("0");
                    txtFouls.setText("0");
                    txtTeamNum.requestFocus();
                }                
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(this,
                "Please doublecheck all text fields and make sure that input is numeric.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
                txtTeamNum.setText("");
                txtClimbTime.setText("0");
                txtFouls.setText("0");
                txtTeamNum.requestFocus();
            }
        }
        else if(a.getActionCommand().equals("comments")){
            if(txtComments.getText().equals("Comments/Driver ability")){
                txtComments.setText("");
            }
        }
        else{
            JOptionPane.showMessageDialog(this,
                "I'm not sure what you've done to break my program. Please stahp.",
                "Severe error",
                JOptionPane.ERROR_MESSAGE);
                System.exit(0);
        }
    }   
}