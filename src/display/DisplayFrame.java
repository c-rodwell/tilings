package display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DisplayFrame extends Frame {
    //drawingPanel drawer;
    GraphicsPanel drawer;
    topBar top;

    public DisplayFrame(){
        super("2D shape drawer");
        //dispose of resources on close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); System.exit(0);
            }
        });

        setSize(500, 500);
        setVisible(true);

        setLayout(new BorderLayout());
        top = new topBar();
        add(top, BorderLayout.NORTH);

        drawer = new GraphicsPanel(250, 150, -0.5, 0.5, 700, 700);
        add(drawer, BorderLayout.CENTER);
        //setBackground(Color.red);
    }

    public static void main(String[] args){
        new DisplayFrame();
    }
}

class topBar extends JPanel{
    JTextField centerXField;
    JTextField centerYField;
    JTextField scaleXField;
    JTextField scaleYField;
    JButton applyButton;
    public topBar(){
        setLayout(new GridLayout(2, 5));
        add(new JLabel("Center X"));
        add(new JLabel("Center Y"));
        add(new JLabel("X Scale"));
        add(new JLabel("Y Scale"));
        add(new Box(0));

        centerXField = new JTextField("center x");
        add(centerXField);

        centerYField = new JTextField("center y");
        add(centerYField);

        scaleXField = new JTextField("x scale");
        add(scaleXField);

        scaleYField = new JTextField("y scale");
        add(scaleYField);

        applyButton = new JButton("go");
        add(applyButton);
    }
}