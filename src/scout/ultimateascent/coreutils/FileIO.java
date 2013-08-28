/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scout.ultimateascent.coreutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import scout.ultimateascent.gui.GUIHelper;

/**
 *
 * @author alex
 */
public class FileIO {
    
    public static void createFile(String fileName){
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println("");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            GUIHelper.displayErrorDialog(ex.toString(), "File IO Error");
        }
    }
    
    public static void writeToFile(String singleLine, String filename){
        try
        {
            try (PrintWriter write = new PrintWriter(new PrintWriter (new FileWriter(filename, true)), true)) {
                write.println(singleLine);
                write.close();
            }
        }
        catch(IOException e) {
             GUIHelper.displayErrorDialog(e.toString(), "File IO Error");
        }
    }
    public static void writeToFile(double [] array, String filename){
        try
        {
            try (PrintWriter write = new PrintWriter(new PrintWriter (new FileWriter(filename, true)), true)) {
                for(int x = 0; x < array.length; x++){
                    write.println(array[x]);
                }
            }
        }
        catch(IOException e) {
        }
    }
    public static void overWriteToFile(String [] array, String filename){
        try
        {
            try (PrintWriter writer = new PrintWriter(filename, "UTF-8");) {
                for(int x = 0; x < array.length; x++){
                    writer.println(array[x]);
                }
            }
        }
        catch(IOException e) {
        }
    }
    public static void writeToFile(String [] array, String filename){
        try
        {
            try (PrintWriter write = new PrintWriter(new PrintWriter (new FileWriter(filename, true)), true)) {
                for(int x = 0; x < array.length; x++){
                    write.println(array[x]);
                }
            }
        }
        catch(IOException e) {
            
        }
    }
    public static int getFileLength(String filename){
        int length = 0;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException ex) {
            
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);             
            String line;
            try {
            while ((line = bufferedReader.readLine()) != null) {
                   length++;        
                              
            }
        } catch (IOException ex) {
           
        }
        try {
            fileReader.close();
        } catch (IOException ex) {
        }
        
        
        return length;
    }
    public static String[] getContentsOfFile(String filename){
        int length = 0;
        
        String[] array = new String[getFileLength(filename)];
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException ex) {
            
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);             
            String line;
            try {
            while ((line = bufferedReader.readLine()) != null) {
                array[length] = line;   
                length++;        
                              
            }
        } catch (IOException ex) {
           
        }
        try {
            fileReader.close();
        } catch (IOException ex) {
        }       
        return array;
    }
    
    public static double[] getContentsOfNumberFile(String filename){
        
        int length = 0;
        double[] array = new double[getFileLength(filename)];
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(filename);
        } catch (FileNotFoundException ex) {
            
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);             
            String line;
            try {
            while ((line = bufferedReader.readLine()) != null) {
                array[length] = Double.parseDouble(line);   
                length++;        
                              
            }
        } catch (IOException ex) {
           
        }
        try {
            fileReader.close();
        } catch (IOException ex) {
        }       
        return array;
    }
    
    
    public static boolean createDir(String newDir){
        File dir = new File(newDir);
        boolean result = false;
        if(!dir.exists()){
            result = dir.mkdir();
        }
        return result;
    }
    public static boolean checkDir(String dir){
        File dir2 = new File(dir);
        return dir2.exists();
    }
    
    public static void writeGameToFile(String comments, double [] gameData, String database, String path, String team){
        if(!checkDir(database)){
            GUIHelper.displayWarningDialog("<html>This team does not exist in the database. A blank profile will be created.<br> It is <b> strongly </b> recommended that you add information to this profile.", "No profile found for team");
            createDir(database);
            createDir(database + "/gameData");
            createDir(database + "/profile");
            createFile(database + "/gameData/games.txt");
            createFile(database + "/gameData/comments.txt");
            createFile(database + "/profile/profile.txt");
            createFile(database + "/gameData/isHidden.txt");
            writeToFile(team, path + "/teams.txt");
            writeToFile("false", database + "/gameData/isHidden.txt");
            writeToFile(new String[]{"","","","","","","","","","","","","","",""}, database + "/profile/profile.txt");
        
        }        
        writeToFile(gameData, database + "/gameData/games.txt");
        writeToFile(comments, database + "/gameData/comments.txt");
        
    }
    public static void toExcel(JTable table, File file) throws IOException {
        TableModel model = table.getModel();
        try (FileWriter out = new FileWriter(file)) {
            for(int i=0; i < model.getColumnCount();i++) {
             out.write(model.getColumnName(i)+"\t");
           }
           out.write("\n");

           for(int i=0; i < model.getRowCount();i++){
            for(int j=0;j < model.getColumnCount();j++){
          //   I added this check for the ISBN conversion
             if(j==0) {
          //    the book Title
              out.write(model.getValueAt(i,j).toString() + "\t");
             } else {
          /*
          the ISBN Number
          Note that I added a \" to the front of the string
          and a \t followed by a closing \" to let Excel know
          that this field is to be converted as text
               */
              out.write("\""+model.getValueAt(i, j).toString()+"\t"+"\"");
             }
            }
            out.write("\n");
           }
        }
       
      }

    
}
