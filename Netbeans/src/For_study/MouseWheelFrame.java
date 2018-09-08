/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package For_study;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JLabel;
/**
 *
 * @author dhdms
 */
public class MouseWheelFrame extends JFrame
{ 
    JLabel label = new JLabel("Love Java");
    public MouseWheelFrame()
    {
        super("마우스 휠 굴리기 연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container c =getContentPane();
        c.setLayout(new FlowLayout());
        
       
        label.setFont(new Font("Arial",Font.PLAIN,10));
        label.addMouseWheelListener(new MouseWheelListener() 
            {
                @Override
                public void mouseWheelMoved(MouseWheelEvent mwe) 
                {
                    int n = mwe.getWheelRotation();
                    if(n<0)
                    {
                        Font f = label.getFont();
                        int size = f.getSize();
                        if(size<5)
                            return;
                        label.setFont(new Font("Arial",Font.PLAIN,size-5));
                    }
                    else
                    {
                        Font f=label.getFont();
                        int size = f.getSize();
                        label.setFont(new Font("Arial",Font.PLAIN,size+5));
                    }
                }
            }
        );
        
        c.add(label);
        c.setSize(300,300);
        c.setVisible(true);
        label.requestFocus();
    }
    public static void main(String[] args) 
    {
        new MouseWheelFrame().show();
    }
}
