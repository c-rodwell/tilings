import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.util.ArrayList;


//using graphics2d setup from https://www.javaworld.com/article/2076715/getting-started-with-java-2d.html

public class Playground extends JFrame { //example had a Frame - is JFrame ok?

    double x_offset;
    double y_offset;
    double scale;

    public static void main(String[] args){
        new Playground(0, 0, 2, 500, 500);
    }

    public Playground(double x_offset, double y_offset, double scale, int width, int height){
        super("2D shape drawer");
        //dispose of resources on close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose(); System.exit(0);
            }
        });

        setSize(width, height);
        setVisible(true);
        this.x_offset = x_offset;
        this.y_offset = y_offset;
        this.scale = scale;
    }

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;

//        int[] square_x = {100, 100, 200, 200};
//        int[] square_y = {100, 200, 200, 100};
//        Polygon square = new Polygon(square_x, square_y, 4);
//        g2d.setColor(Color.green);
//        g.fillPolygon(square);
//        g2d.setColor(Color.red);
//        g2d.drawPolygon(square);

//        Polygon pentagon = regularPolygon(5, 100, 200, 200, Math.PI/5.0);
//        g2d.drawPolygon(pentagon);
//        Polygon hexagon = regularPolygon(6, 50, 100, 100, Math.PI/4.0);
//        g2d.fillPolygon(hexagon);

        abstractPolygon pentagon1 = abstractPolygon.regPolyOnCircle(5, 100, 200, 200, Math.PI/5.0);
        //drawFace(pentagon1, g2d, Color.magenta, true);
        System.out.println("pentagon1: "+pentagon1);

        abstractPolygon pentagon2 = abstractPolygon.regPolyOnCircle(5, 50, 500, 370, Math.PI/5.0);
        //drawFace(pentagon2, g2d, Color.cyan, true);
        System.out.println("pentagon2: "+pentagon2);

        Edge edge = new Edge(null, null, new abstractPoint(400, 400), new abstractPoint(400, 500));
        //abstractPolygon polyOnEdge = abstractPolygon.regPolyOnEdge(6, new abstractPoint(100, 100), new abstractPoint(200, 100));
        abstractPolygon polyOnEdge = abstractPolygon.regPolyOnEdge(5, edge, true);
        abstractPolygon polyOnEdge2 = abstractPolygon.regPolyOnEdge(5, edge, false);
        drawFace(polyOnEdge, g, Color.pink, true);
        drawFace(polyOnEdge2, g, Color.orange, true);
        System.out.println("polyOnEdge: "+polyOnEdge);
    }

    //translate a Face into a Polygon and draw it
    public void drawFace(abstractPolygon aP, Graphics g, Color color, boolean fill){
        int npoints = aP.getNpoints();
        int[] xpoints = new int[npoints];
        int[] ypoints = new int[npoints];
//        ArrayList<Double> double_xpoints = f.getXpoints();
//        ArrayList<Double> double_ypoints = f.getYpoints();
        for(int i=0; i<npoints; i++){
//            xpoints[i] = (int)(double)double_xpoints.get(i);    //what's the proper way to cast Double to int?
//            ypoints[i] = (int)(double)double_ypoints.get(i);
            //int[] new_point = mapCoords(double_xpoints.get(i), double_ypoints.get(i));
            abstractPoint point = aP.getPoint(i);
            int[] new_point = mapCoords(point.x, point.y);
            xpoints[i] = new_point[0];
            ypoints[i] = new_point[1];
        }
        g.setColor(color);
        Polygon P = new Polygon(xpoints, ypoints, npoints);
        if (fill){
            g.fillPolygon(P);
        } else{
            g.drawPolygon(P);
        }
    }

    public int[] mapCoords(double x, double y){
        int new_x = (int) ((x - x_offset)/scale);
        int new_y = (int) ((y - y_offset)/scale);
        int[] new_point = {new_x, new_y};
        return new_point;
    }


    //make a regular polygon
    //degree - number of vertices
    //radius - radius of circle through the vertices (can I use side length instead?)
    //
//    public static Polygon regularPolygon(int degree, double radius, double center_x, double center_y, double rotation){
//        Polygon p = new Polygon();
//        //point on a circle: x = r*cos(theta) , y = r*sin(theta)
//        for (int i=0; i<degree; i++){
//            double theta = (2*Math.PI *((float)i / (float)degree)) + rotation;
//            //System.out.println("theta = "+theta);
//            double x = ( radius * Math.cos(theta) ) + center_x;
//            double y = ( radius * Math.sin(theta) ) + center_y;
//            p.addPoint((int) x, (int) y);
//            //System.out.println("adding point: "+x+", "+y);
//        }
//        return p;
//    }

}
