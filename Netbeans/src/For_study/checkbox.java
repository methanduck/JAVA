/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package For_study;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author dhdms
 */
public class checkbox extends JFrame
{
    JCheckBox [] fruit = new JCheckBox[3];
    String  [] name = {"사과","배","체리"};
    
    JLabel sumlabel;
    public checkbox() 
    {
        super("체크하세요");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        
        c.setLayout(new FlowLayout());
        c.add(new JLabel("사과 100 배 500 체리 2000"));
        listener listen = new listener();
        for (int i = 0; i < fruit.length; i++) 
        {
            fruit[i] = new JCheckBox(name[i]);
            fruit[i].setBorderPainted(true);
            c.add(fruit[i]);
            fruit[i].addItemListener(listen);
        }
        sumlabel = new JLabel("현재 0원입니다.");
    }
    class listener implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent ie) 
        {
            
        }
        
    }
    
}
