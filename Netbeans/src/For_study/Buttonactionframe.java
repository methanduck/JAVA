/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package For_study;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.*;
/**
 *
 * @author dhdms
 */
public class Buttonactionframe  extends JFrame
{
    String text [] = {"+2","-1","%4"};
    JLabel la = new JLabel();
    JButton btn []=new JButton[text.length];

    public Buttonactionframe() throws HeadlessException 
    {
        super("0으로 만들기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container c = getContentPane();
        
        int num = (int) (Math.random()*60+1);
        la.setText(Integer.toString(num));
        la.setFont(new Font("Gothic",Font.ITALIC,20));
        
        JPanel a = new JPanel();
        c.add(a,BorderLayout.CENTER);
        a.add(la);
        
        JPanel b = new JPanel();
        c.add(b,BorderLayout.SOUTH);
        myactionlistener listener = new myactionlistener();
        for (int i = 0; i < btn.length; i++) 
        {
            btn[i]=new JButton(text[i]);
            btn[i].addActionListener(listener);
            b.add(btn[i]);
        }
        setSize(300,250);
        setVisible(true);
        

    }
     class myactionlistener implements ActionListener
            {
                 @Override
                public void actionPerformed(ActionEvent e)
                {
                    setTitle("0으로 만들기");
                    
                   int n = Integer.parseInt(la.getText());
                   switch(e.getActionCommand())
                   {
                       case "+2":
                           n +=2;
                           btn[0].setEnabled(false);
                           break;
                                   
                        case "-1":
                            n -=1;
                            btn[1].setEnabled(false);
                            break;
                                     
                        case "%4":
                            n %=4;
                            btn[2].setEnabled(false);
                            break;
                   }
                   
                   la.setText(Integer.toString(n));
                   if(n==0)
                   {
                       setTitle("성공");
                       for (int i = 0; i < text.length; i++) 
                       {
                       btn[i].setEnabled(true);
                       int num = (int)(Math.random()*60+11);
                       la.setText(Integer.toString(num));
                       }
                }
                   else
                   {
                       if(btn[0].isEnabled()==false && 
                          btn[1].isEnabled()==false &&
                          btn[2].isEnabled() == false)
                           setTitle("실패");
                   }
            }

       

               
    
    
            
}
    public static void main(String[] args) 
    {
        new Buttonactionframe();
    }
}
