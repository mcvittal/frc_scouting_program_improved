/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scout.ultimateascent.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import scout.ultimateascent.coreutils.*;
import scout.ultimateascent.database.GameData;

/**
 *
 * @author alex
 */
public class MainScreen extends JFrame implements ActionListener {
    //Pull data for table into a 2D array
        Object [][] stuff;    
        Object [] columnNames = {"TEAM", "GP","GW", "T5AV","T3AV","T2AV", "T1AV", "TAV","T%AC", "AVGF",
                                 "A3AV", "A2AV", "A1AV", "AAV", "A%AC","CAV",
                                 "DS","FCS","%F"};
        
        JTable stats;
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        boolean flag = true;
        String database = "";
        JComboBox cboTeams = null;
    public MainScreen(String database){
        
        
        stuff = new Object[FileIO.getFileLength(Global.prefix + database + "/teams.txt")][19];
        this.database = database;
        String [] teams = FileIO.getContentsOfFile(Global.prefix + database + "/teams.txt");
        System.out.println(teams[0]);
        if(!(teams.length == 0)){
            Arrays.sort(teams);
            stats  = new JTable(fillTable(database), columnNames);

            cboTeams = new JComboBox(teams);
            JButton cmdDisplayProfile = new JButton("Open profile");
            JButton cmdScout = new JButton("Scout");
            JButton cmdHelp = new JButton("Help");       
            JButton cmdExcel = new JButton("Export to Excel");
            JButton cmdRefresh = new JButton("Refresh stats");

            stats.setAutoCreateRowSorter(true);
            JScrollPane pane = new JScrollPane(stats);
            SpringLayout layout = new SpringLayout();

            this.add(pane);
            this.add(cmdScout);
            this.add(cboTeams);
            this.add(cmdDisplayProfile);
            this.add(cmdRefresh);

            cmdScout.addActionListener(this);
            cmdDisplayProfile.addActionListener(this);
            cmdExcel.addActionListener(this);
            cmdRefresh.addActionListener(this);
            cmdRefresh.setActionCommand("refresh");
            cmdScout.setActionCommand("scout");
            cmdDisplayProfile.setActionCommand("profile");
            cmdExcel.setActionCommand("excel");

            GUIHelper.setSize(pane, 1000, 695);
            GUIHelper.setSize(cmdScout, 170, 35);
            GUIHelper.setSize(cmdDisplayProfile, 110, 20);
            GUIHelper.setSize(cboTeams, 60, 20);
            GUIHelper.setSize(cmdExcel, 150, 35);
            GUIHelper.setSize(cmdRefresh, 150, 35);
            GUIHelper.setLocation(cmdScout, layout, rootPane, 1010, 10);
            GUIHelper.setLocation(cmdExcel, layout, rootPane, 1190, 10);
            GUIHelper.setLocation(cboTeams, layout, rootPane, 1010, 55);
            GUIHelper.setLocation(cmdDisplayProfile, layout, rootPane, 1070, 55);
            GUIHelper.setLocation(cmdRefresh, layout, rootPane, 1190, 10);
            this.getContentPane().setLayout(layout);
            //fillTable(Global.prefix + database);
        }
        
        
        
        
        this.setSize(1360, 700);       
        this.setVisible(true);
        this.setTitle("Ultimate Scout | Summary Page");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getActionCommand().equals("scout")){
            this.dispose();
            ScoutingScreen s = new ScoutingScreen(database);
        }
        else if(arg0.getActionCommand().equals("profile")){
            //this.dispose();
            ProfileScreen p = new ProfileScreen(database, cboTeams.getSelectedItem().toString());
        }
        else if(arg0.getActionCommand().equals("refresh")){
            this.dispose();
            MainScreen main = new MainScreen(database);
        }
        
        
    }
    private Object [][] fillTable(String database){
        Object [] [] array = null;
        if(flag){
            flag = false;
            if(FileIO.getContentsOfFile(Global.prefix + database + "/teams.txt").length != 0){
                String [] list = FileIO.getContentsOfFile(Global.prefix + database + "/teams.txt");
                System.out.println(list[0]);
                Arrays.sort(list);
                int numTeams = list.length;
                array = new Object[numTeams][];
                for(int x = 0; x < numTeams; x++){
                    GameData g = new GameData(database, list[x]);
                    if(!g.isHidden){
                        array[x] = g.gameData;
                    }
                    else{
                        array[x] = g.blankData;
                    }
                        //array[x] = null;
                    
            }
                
        }
        
        return array;
        
        }
        else{
            return null;
        }
        
    }
    public static void setColumnWidths(JTable table, int [] sizes)
    {
        //Loop through columns, setting the respective sizes
        for(int x = 0; x < sizes.length; x++) {
            table.getColumnModel().getColumn(x).setPreferredWidth(sizes[x]);
        }

    }//End of setColumnWidths()
}
