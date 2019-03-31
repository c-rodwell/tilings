import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import SquareAndRhombus.*;
import core.*;
import GoldenTriangles.Golden221;


//using graphics2d setup from https://www.javaworld.com/article/2076715/getting-started-with-java-2d.html

public class Playground extends JFrame { //example had a Frame - is JFrame ok?

    double x_offset;
    double y_offset;
    double scale_x;
    double scale_y;

    double phi = (1.0 + Math.sqrt(5.0))/2.0;

    public static void main(String[] args){
        new Playground(250, 150, -0.5, 0.5, 500, 500);
    }

    public Playground(double x_offset, double y_offset, double scale_x, double scale_y, int width, int height){
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
        this.scale_x = scale_x;
        this.scale_y = scale_y;
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

        //core.abstractPolygon pentagon1 = core.abstractPolygon.regPolyOnCircle(5, 100, 200, 200, Math.PI/5.0);
        //drawFace(pentagon1, g2d, Color.magenta, true);
        //System.out.println("pentagon1: "+pentagon1);

        //core.abstractPolygon pentagon2 = core.abstractPolygon.regPolyOnCircle(5, 50, 500, 370, Math.PI/5.0);
        //drawFace(pentagon2, g2d, Color.cyan, true);
        //System.out.println("pentagon2: "+pentagon2);

//        core.Edge edge1 = new core.Edge(null, null, new core.abstractPoint(700, 700), new core.abstractPoint(600, 600));
//        drawEdge(edge1, g2d, Color.cyan);
//        double[] edge1_proportions = {1, 1, 1};
//        core.Edge[] partsOfEdge = edge1.splitByRatio(edge1_proportions);
//        drawEdge(partsOfEdge[0], g2d, Color.black);
//        drawEdge(partsOfEdge[1], g2d, Color.blue);
//        drawEdge(partsOfEdge[2], g2d, Color.red);

        //core.abstractPolygon polyOnEdge = core.abstractPolygon.regPolyOnEdge(6, new core.abstractPoint(100, 100), new core.abstractPoint(200, 100));
        //core.abstractPolygon polyOnEdge = core.abstractPolygon.regPolyOnEdge(5, edge1, true);
        //core.abstractPolygon polyOnEdge2 = core.abstractPolygon.regPolyOnEdge(5, edge1, false);
        //drawFace(polyOnEdge, g2d, Color.pink, true);
        //drawFace(polyOnEdge2, g2d, Color.green, true);
        //System.out.println("polyOnEdge: "+polyOnEdge);

//        core.Face bigPentagon = core.Face.regularPolygonFace(5, 100, 400, 400, 0);
//        drawFace(bigPentagon, g2d, Color.blue, true);
//        bigPentagon.makeEdges();
//        for (core.Edge edge : bigPentagon.getEdges()){
//            drawEdge(edge, g2d, Color.BLACK);
//
//            core.abstractPolygon smallPentagon = core.abstractPolygon.regPolyOnEdge(5, edge, true); //true makes them outside
//            drawFace(smallPentagon, g2d, Color.orange, true);
//
//            core.abstractPoint[] corners = edge.bisectingPointsAtAngle(3*Math.PI/5);
//            drawPoint(corners[0], g2d, 5, Color.DARK_GRAY);
//            drawPoint(corners[1], g2d, 3, Color.GREEN);
//
//            double[] proportions = {phi, 1.0, phi};
//            core.Edge[] edgePieces = edge.splitByRatio(proportions);
//
//            core.Face edgeFace = edgePieces[1].rhombusOnEdge(Math.PI/5);
//            drawFace(edgeFace, g2d, Color.cyan, true);
//        }

//        quartering_Square bigSquare = new quartering_Square(100, 600, 300, Math.PI/4);
//        bigSquare.makeEdges();
//        System.out.println("bigSquare: "+bigSquare);
//        drawFace(bigSquare, g2d, Color.black, false);
//        bigSquare.split_inside();
//        Random r = new Random();
//        for (Face smallSquare : bigSquare.getComponentFaces()){
//            Color color = Color.getHSBColor(r.nextFloat(), r.nextFloat(), r.nextFloat());
//            drawFace(smallSquare, g2d, color, true);
//        }

//        quartering_Square bigSquare2 = new quartering_Square(100, 300, 600, Math.PI/7);
//        drawFace(bigSquare2, g2d, Color.black, false);
//        ArrayList<Face> fragments = split_iterate(bigSquare2, 3);
//        Random r2 = new Random();
//        for (Face face: fragments){
//            Color color = Color.getHSBColor(r2.nextFloat(), r2.nextFloat(), r2.nextFloat());
//            drawFace(face, g2d, color, true);
//        }

//        quarteringTriangle bigTri = new quarteringTriangle(100, 400, 200, Math.PI/6);
//        //drawFace(bigTri, g2d, Color.black, false);
//        ArrayList<Face> smallTris = split_iterate(bigTri, 4);
//        Random r3 = new Random();
//        for (Face face: smallTris){
//            Color color = Color.getHSBColor(r3.nextFloat(), r3.nextFloat(), r3.nextFloat());
//            drawFace(face, g2d, color, true);
//        }

//        Edge triangleBase = new Edge(null, null, new abstractPoint(-50, 550), new abstractPoint(450, 550));
//        Golden221 bigGoldenTri = Golden221.fromBase(triangleBase, false);
//        ArrayList<Face> split_goldens = split_iterate(bigGoldenTri, 12);
//        for (Face face: split_goldens){
//            System.out.println(face);
//            Color color = face.getClass() == Golden221.class ? Color.blue : Color.darkGray;
//            drawFace(face, g2d, color, true);
//            drawFace(face, g2d, Color.black, false);
//        }
//        drawPoint(bigGoldenTri.getPoint(0), g2d, 4, Color.red);
//        drawPoint(bigGoldenTri.getPoint(1), g2d, 4, Color.red);
//        drawPoint(bigGoldenTri.getPoint(2), g2d, 4, Color.red);

//        Square square = new Square(200, 100, 100, Math.PI/4);
//        drawPoint(square.getcenter(), g2d, 4, Color.blue);
//        ArrayList<Face> square_parts = split_iterate(square, 4);
//        Random r4 = new Random();
//        for (Face face: square_parts){
//            System.out.println(face);
//            drawFace(face, g2d, Color.black, false);
//            if(face.getClass() == Rhombus.class){
//                drawFace(face, g2d, Color.green, true);
//                //drawPoint(face.getPoint(0), g2d, 4, Color.RED);
//            } else {
//                drawFace(face, g2d, Color.blue, true);
//            }
////            Color color = Color.getHSBColor(r4.nextFloat(), r4.nextFloat(), r4.nextFloat());
////            drawFace(face, g2d, color, true);
////            drawFace(face, g2d, Color.black, false);
//            //drawPoint(face.getPoint(3), g2d, 4, Color.red);
//        }

        //Rhombus bigRhombus = Rhombus.fromShortDiag(new abstractPoint(50, 350), new abstractPoint(350, 350));
        Rhombus bigRhombus = Rhombus.fromLongDiag(new abstractPoint(100, 650), new abstractPoint(100, 50));
        //Rhombus bigRhombus = new Rhombus(abstractPolygon.rhombusOnOppositePoints(new abstractPoint(50, 350), new abstractPoint(350, 350), Math.PI/4));
        //bigRhombus.makeEdges();

        ArrayList<Face> rhombus_parts = split_iterate(bigRhombus, 3);
        for (Face face: rhombus_parts){
            System.out.println(face);
            drawFace(face, g2d, Color.black, false);
            if(face.getClass() == Rhombus.class){
                drawFace(face, g2d, Color.green, true);
                //drawPoint(face.getPoint(0), g2d, 4, Color.RED);
            } else {
                drawFace(face, g2d, Color.blue, true);
            }
//            Color color = Color.getHSBColor(r4.nextFloat(), r4.nextFloat(), r4.nextFloat());
//            drawFace(face, g2d, color, true);
//            drawFace(face, g2d, Color.black, false);
            //drawPoint(face.getPoint(3), g2d, 4, Color.red);
        }

//        bigRhombus.split_all_edges();
//        for (Face f: bigRhombus.getComponentFaces()){
//            drawFace(f, g2d, Color.yellow, true);
//        }
//
//        drawFace(bigRhombus, g2d, Color.black, false);

//        abstractPoint point0 = bigRhombus.getPoint(0);
//        abstractPoint point1 = bigRhombus.getPoint(1);
//        abstractPoint point2 = bigRhombus.getPoint(2);
//        abstractPoint point3 = bigRhombus.getPoint(3);
//
//        ArrayList<Edge> Edges = bigRhombus.getEdges();
//        Edge edge1 = Edges.get(0);
//        Edge edge2 = Edges.get(1);
//        Edge edge3 = Edges.get(2);
//        Edge edge4 = Edges.get(3);
//
//        drawPoint(point0, g2d, 10, Color.cyan);
//        drawPoint(point1, g2d, 10, Color.magenta);
//        drawPoint(point2, g2d, 10, Color.yellow);
//        drawPoint(point3, g2d, 10, Color.black);
//
//        drawEdge(edge1, g2d, Color.red);
//        drawEdge(edge2, g2d, Color.green);
//        drawEdge(edge3, g2d, Color.blue);
//
//        abstractPoint mid12 = edge1.pointAtProportion(1.0, 1.0+Math.sqrt(2.0));
//        abstractPoint mid11 = edge1.pointAtProportion(1.0+Math.sqrt(2.0), 1.0);
//
//        abstractPoint mid22 = edge2.pointAtProportion(1.0, 1.0+Math.sqrt(2.0));
//        abstractPoint mid21 = edge2.pointAtProportion(1.0+Math.sqrt(2.0), 1.0);
//
//        abstractPoint mid32 = edge3.pointAtProportion(1.0, 1.0+Math.sqrt(2.0));
//        abstractPoint mid31 = edge3.pointAtProportion(1.0+Math.sqrt(2.0), 1.0);
//
//        abstractPoint mid42 = edge4.pointAtProportion(1.0, 1.0+Math.sqrt(2.0));
//        abstractPoint mid41 = edge4.pointAtProportion(1.0+Math.sqrt(2.0), 1.0);
//
//        drawString(mid11, g2d,"1,1", Color.black);
//        drawString(mid12, g2d,"1,2", Color.black);
//        drawString(mid21, g2d,"2,1", Color.black);
//        drawString(mid22, g2d,"2,2", Color.black);
//
//        drawString(mid31, g2d,"3,1", Color.black);
//        drawString(mid32, g2d,"3,2", Color.black);
//        drawString(mid41, g2d,"4,1", Color.black);
//        drawString(mid42, g2d,"4,2", Color.black);
//
//        Face crossRhombus = new Rhombus(abstractPolygon.rhombusOnOppositePoints(mid12, mid41, 3.0*Math.PI/4.0));
//        drawFace(crossRhombus, g2d, Color.blue, true);
//
//        Face endRhombus0 = new Rhombus(abstractPolygon.parallelogramFromThreePoints(point0, mid11, mid42));
//        drawFace(endRhombus0, g2d, Color.magenta, true);
//
//        Face endRhombus1 = new Rhombus(abstractPolygon.parallelogramFromThreePoints(point2, mid22, mid31));
//        drawFace(endRhombus1, g2d, Color.black, true);
//
//        Face innerSquare1 = new Square(abstractPolygon.regPolyOnEdge(4, new Edge(null, null, mid12, point1), false));
//        Face innerSquare3 = new Square(abstractPolygon.regPolyOnEdge(4, new Edge(null, null, point3, mid41), false));
//        drawFace(innerSquare1, g2d, Color.red, true);
//        drawFace(innerSquare3, g2d, Color.red, true);
//
//        abstractPoint octagonTop = endRhombus1.getPoint(2);
//
//        Face fanRhombusLeft = new Rhombus(abstractPolygon.rhombusOnOppositePoints(octagonTop, point3, 3.0*Math.PI/4.0));
//        Face fanRhombusRight = new Rhombus(abstractPolygon.rhombusOnOppositePoints(octagonTop, point1, 3.0*Math.PI/4.0));
//        Face fanRhombusCenter = new Rhombus(abstractPolygon.rhombusOnOppositePoints(octagonTop, crossRhombus.getPoint(1), 3.0*Math.PI/4.0));
//
//        drawFace(fanRhombusLeft, g2d, Color.blue, true);
//        drawFace(fanRhombusCenter, g2d, Color.cyan, true);
//        drawFace(fanRhombusRight, g2d, Color.green, true);
    }

    public void drawString(abstractPoint point, Graphics g, String S, Color color){
        int[] new_coords = mapCoords(point);
        g.setColor(color);
        g.drawString(S, new_coords[0], new_coords[1]);
    }

    //translate a core.Face into a Polygon and draw it
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
            int[] new_point = mapCoords(point);
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

    public void drawPoint(abstractPoint point, Graphics g, int size, Color color){
        int[] new_coords = mapCoords(point);
        g.setColor(color);
        g.fillOval(new_coords[0] - size/2, new_coords[1] - size/2, size, size);
    }

    public void drawEdge(Edge edge, Graphics g, Color color){
        int[] p1 = mapCoords(edge.getEnd1());
        int[] p2 = mapCoords(edge.getEnd2());
        g.setColor(color);
        g.drawLine(p1[0], p1[1], p2[0], p2[1]);
    }

    public void drawFace(Face f, Graphics g, Color color, boolean fill){
        drawFace(f.getPolygon(), g, color, fill);
    }

    public int[] mapCoords(abstractPoint point){
        int new_x = (int) ((point.getX() - x_offset)/scale_x);
        int new_y = (int) ((point.getY() - y_offset)/scale_y);
        int[] new_point = {new_x, new_y};
        return new_point;
    }

    //iterate dividing the core.Face, until max depth. then draw all the max depth faces
    //for now, just iterate on Faces, ignore Edges. quartering_square can split on faces only
    //eventually this should iterate on Faces and Edges

    public ArrayList<Face> split_iterate(Face startingFace, int maxDepth){
        ArrayList<Face> current_faces = new ArrayList<>();
        startingFace.setDepth(0);
        current_faces.add(startingFace);
        ArrayList<Face> final_faces = new ArrayList<>();
        //iterate: pop a face from current_faces, split it, put new faces into current_faces
        //ends when max depth reached - could put this in core.Face
        //when done, draw all the remaining faces and edges
        while (! current_faces.isEmpty()){
            //removing from index 0 makes breadth-first traversal, since it adds on end.
            //or could remove from max index for depth-first - don't really care now
            Face nextFace = current_faces.remove(0);
            int depth = nextFace.getDepth();
            //System.out.println("pop node with depth = "+depth);
            if (depth >= maxDepth){
                final_faces.add(nextFace);
            } else {
                nextFace.makeEdges();
                nextFace.split_all_edges();
                nextFace.split_inside();
                for (Face newFace : nextFace.getComponentFaces()){
                    //newFace.setDepth(depth+1); //without this line, split_inside needs to set depth.
                    current_faces.add(newFace);
                }
            }
        }
        return final_faces;
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
