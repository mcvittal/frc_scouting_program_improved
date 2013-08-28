/* Name:    Jared Baribeau, Carson Ennis, Alex McVittie
 * Class:    GUIHelper
 */

/*
*   The purpose of this class is to contain helper methods for repetetive setting up
*   of various GUI features, as well as to display dialogues.
*
*/

package scout.ultimateascent.gui;

//Imports all necessary packages.
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

public class GUIHelper
{
    
    static SpringLayout frame = new SpringLayout();
    //Method to set button size, content fill, border
    public static void setupButton(AbstractButton button, int xSize, int ySize, boolean filled, boolean border)
    {
        //Set button dimensions
        if(xSize > 0 && ySize > 0)
        {
            button.setPreferredSize(new Dimension(xSize, ySize));
            button.setSize(new Dimension(xSize, ySize));
            button.setMaximumSize(new Dimension(xSize, ySize));
            button.setVisible(true);
        }

        //Set up fill
        if(filled == true)
            button.setContentAreaFilled(true);
        else
            button.setContentAreaFilled(false);

        //Set border painted
        if(border == true)
            button.setBorderPainted(true);
        else
            button.setBorderPainted(false);
    }//End of setUpButton()
    public static void setupComboBox(JComboBox cbo, int xSize, int ySize){
        if(xSize > 0 && ySize > 0){
            cbo.setPreferredSize(new Dimension(xSize, ySize));
            cbo.setSize(xSize, ySize);
            cbo.setMaximumSize(new Dimension(xSize, ySize));
            cbo.setVisible(true);
        }
    }
    public static void setSize(Component component, int xSize, int ySize){
            component.setPreferredSize(new Dimension(xSize, ySize));
            component.setSize(xSize, ySize);
            component.setMaximumSize(new Dimension(xSize, ySize));
            component.setVisible(true);
    }
    public static void displayWarningDialog(String msg, String title){
        JOptionPane.showMessageDialog(new Component() {},
                                    msg,
                                    title,
                                    JOptionPane.WARNING_MESSAGE);
    }
    public static void displayErrorDialog(String msg, String title){
        JOptionPane.showMessageDialog(new Component() {},
                                    msg,
                                    title,
                                    JOptionPane.ERROR_MESSAGE);
    }

    //Method to set up a JCheckBox or JRadioButton
    public static void setupSelector(JCheckBox chkBox, boolean filled, boolean border)
    {
        if(filled == true)
            chkBox.setContentAreaFilled(true);
        else
            chkBox.setContentAreaFilled(false);

        if(border == true)
            chkBox.setBorderPainted(true);
        else
            chkBox.setBorderPainted(false);
    }//End of setupCheckBox


    //Method to set the location of a feature in a springlayout in a container
    public static void setLocation(Component component, SpringLayout layout, Container contentPane, int xCoordinate, int yCoordinate)
    {
        //Set location of component
        layout.putConstraint(SpringLayout.WEST, component, xCoordinate, SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, component, yCoordinate, SpringLayout.NORTH, contentPane);
    }//End of setLocation()
    
   
}//End of class