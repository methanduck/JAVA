/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package For_study;
import java.awt.*;
import javax.swing.*;


/**
 *
 * @author dhdms
 */
public class swing extends JFrame
{

    public swing() throws HeadlessException 
    {
        setTitle("test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container window = getContentPane();
        window.setLayout(null);
        JLabel lab = new JLabel("hello");
        window.add(lab);
        
        for (int i = 0; i < 10; i++) {
            JButton b = new JButton(Integer.toString(i));
            b.setLocation(i*15,i*15);
            b.setSize(50,20);
            window.add(b);
        }
        
        setSize(300,200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new swing();
    }
    
   
    
}
