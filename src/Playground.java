import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;


//using graphics2d setup from https://www.javaworld.com/article/2076715/getting-started-with-java-2d.html

public class Playground extends JFrame { //example had a Frame - is JFrame ok?
    //play around with ideas and graphics code in here

    public static void main(String[] args){
        new Playground();
    }

    public Playground(){

        //BEGIN TUTORIAL SETUP - has to be this way to use graphics
        super("Java 2D Example01");
        setSize(400,300);
        setVisible(true);
        //Now, we want to be sure we properly dispose of resources
        //this frame is using when the window is closed.  We use
        //an anonymous inner class adapter for this.
        addWindowListener(new WindowAdapter()
                          {public void windowClosing(WindowEvent e)
         {dispose(); System.exit(0);}
         }
        );

        //END TUTORIAL SETUP - do whatever you like after this
        //but probably do it in paint instead?
    }

    public void paint(Graphics g) {

        //BEGIN EXAMPLE
//        g.setColor(Color.red);
//        g.drawRect(50,50,200,200);
//        Graphics2D g2d = (Graphics2D)g;
//        g2d.setColor(Color.blue);
//        g2d.drawRect(75,75,300,200);
        //END EXAMPLE

        Graphics2D g2d = (Graphics2D)g;

        int[] square_x = {100, 100, 200, 200};
        int[] square_y = {100, 200, 200, 100};
        Polygon square = new Polygon(square_x, square_y, 4);
        //square.
        g2d.setColor(Color.green);
        //g2d.drawPolygon(square);
        g.fillPolygon(square);
    }

}
