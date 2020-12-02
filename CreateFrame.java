// Created the popup text window with the names

import javax.swing.*;
import java.awt.*;

public class CreateFrame {
    private JFrame frame;

    public CreateFrame() {
        frame = new JFrame("Second");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        JLabel label1 = new JLabel("Giovanni DuVall - gduvall@asu.edu | ");
        JLabel label2 = new JLabel(" | Christian Chiles - cchiles@asu.edu | ");
        JLabel label3 = new JLabel(" | Aaron Craighead - acraighe@asu.edu | ");
        JLabel label4 = new JLabel(" | Seth Ryals - sryals1@asu.edu | ");
        JLabel label5 = new JLabel(" | Jacob Rollings - jnrollin@asu.edu");
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label2.setHorizontalTextPosition(JLabel.CENTER);
        label3.setHorizontalTextPosition(JLabel.CENTER);
        label4.setHorizontalTextPosition(JLabel.CENTER);
        label5.setHorizontalTextPosition(JLabel.CENTER);
        frame.setLayout(new FlowLayout());

        //label1.setToolTipText("<html>Giovanni DuVall - gduvall@asu.edu<br>Christian Chiles - cchiles@asu.edu<br>" +
               // "Aaron Craighead - acraighe@asu.edu<br>Seth Ryals - sryals1@asu.edu<br>" +
                //"Jacob Rollings - jnrollin@asu.edu</html>");



        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);





        //frame.pack();
        frame.setSize(700,700);
        frame.setVisible(true);

    }
}