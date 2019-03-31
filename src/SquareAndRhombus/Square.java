package SquareAndRhombus;

import core.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Square extends Face {

    public Square(abstractPolygon square){
        super(square);
        System.out.println("returning from Square constructor with square = "+square);
    }

    public Square(double radius, double center_x, double center_y, double rotation){
        super( abstractPolygon.regPolyOnCircle(4, radius, center_x, center_y, rotation));
        System.out.println("Square constructor with radius = " +radius
                +", center_x = "+center_x  +", center_y = "+center_y+", rotation = "+rotation);
    }

    public void split_edges(){

    }

    public void split_inside(){

        //important points on the square
        System.out.println("in split_inside");
        System.out.println("polygon = "+polygon);

        Edge outer1 = edges.get(0);
        Edge outer2 = edges.get(0);
        Edge outer3 = edges.get(0);
        Edge outer4 = edges.get(0);

        abstractPoint corner1 = getPoint(0);
        abstractPoint corner2 = getPoint(1);
        abstractPoint corner3 = getPoint(2);
        abstractPoint corner4 = getPoint(3);

        abstractPoint center = polygon.getCenter();

        abstractPoint mid11 = abstractPoint.weightedAvg(corner2, corner1, 1.0, 1.0 + Math.sqrt(2.0));
        abstractPoint mid12 = abstractPoint.weightedAvg(corner2, corner1, 1.0 + Math.sqrt(2.0), 1.0 );

        abstractPoint mid21 = abstractPoint.weightedAvg(corner3, corner2, 1.0, 1.0 + Math.sqrt(2.0));
        abstractPoint mid22 = abstractPoint.weightedAvg(corner3, corner2, 1.0 + Math.sqrt(2.0), 1.0 );

        abstractPoint mid31 = abstractPoint.weightedAvg(corner4, corner3, 1.0, 1.0 + Math.sqrt(2.0));
        abstractPoint mid32 = abstractPoint.weightedAvg(corner4, corner3, 1.0 + Math.sqrt(2.0), 1.0 );

        abstractPoint mid41 = abstractPoint.weightedAvg(corner1, corner4, 1.0, 1.0 + Math.sqrt(2.0));
        abstractPoint mid42 = abstractPoint.weightedAvg(corner1, corner4, 1.0 + Math.sqrt(2.0), 1.0 );

        //abstractPoint innerPoint1 = new abstractPoint(mid11.)

        //new inside edges
        Edge edge11 = new Edge(null, null, mid42, corner1);
        Edge edge12 = new Edge(null, null, corner1, mid11);

        Edge edge21 = new Edge(null, null, mid12, corner2);
        Edge edge22 = new Edge(null, null, corner2, mid21);

        Edge edge31 = new Edge(null, null, mid22, corner3);
        Edge edge32 = new Edge(null, null, corner3, mid31);

        Edge edge41 = new Edge(null, null, mid32, corner4);
        Edge edge42 = new Edge(null, null, corner4, mid41);

        //new inside faces
        Square square1 = new Square(abstractPolygon.regPolyOnEdge(4, edge11, false));
        Square square2 = new Square(abstractPolygon.regPolyOnEdge(4, edge21, false));
        Square square3 = new Square(abstractPolygon.regPolyOnEdge(4, edge31, false));
        Square square4 = new Square(abstractPolygon.regPolyOnEdge(4, edge41, false));

        square1.setDepth(depth+1);
        square2.setDepth(depth+1);
        square3.setDepth(depth+1);
        square4.setDepth(depth+1);

        component_faces.add(square1);
        component_faces.add(square2);
        component_faces.add(square3);
        component_faces.add(square4);

        //squares on diagonal - put this in split_edges() once there is an iteration that uses split_edges()

        //these are square diagonals, not to be added in component_edges
        // (does that mean it shouldn't be an Edge? - could construct directly from the two points)
//        Edge diag1 = new Edge(null, null, mid11, mid12);
//        Edge diag2 = new Edge(null, null, mid21, mid22);
//        Edge diag3 = new Edge(null, null, mid31, mid32);
//        Edge diag4 = new Edge(null, null, mid41, mid42);

        Square diagSquare1 = new Square(new Edge(null, null, mid11, mid12).rhombusOnEdge(Math.PI/2.0));
        Square diagSquare2 = new Square(new Edge(null, null, mid21, mid22).rhombusOnEdge(Math.PI/2.0));
        Square diagSquare3 = new Square(new Edge(null, null, mid31, mid32).rhombusOnEdge(Math.PI/2.0));
        Square diagSquare4 = new Square(new Edge(null, null, mid41, mid42).rhombusOnEdge(Math.PI/2.0));

        diagSquare1.setDepth(depth+1);
        diagSquare2.setDepth(depth+1);
        diagSquare3.setDepth(depth+1);
        diagSquare4.setDepth(depth+1);

        component_faces.add(diagSquare1);
        component_faces.add(diagSquare2);
        component_faces.add(diagSquare3);
        component_faces.add(diagSquare4);

        //add some rhombi
        Rhombus rhombus11 = new Rhombus(new Edge(null, null, center, mid11).rhombusOnEdge(3.0*Math.PI/4.0));
        Rhombus rhombus12 = new Rhombus(new Edge(null, null, center, mid12).rhombusOnEdge(3.0*Math.PI/4.0));
        Rhombus rhombus21 = new Rhombus(new Edge(null, null, center, mid21).rhombusOnEdge(3.0*Math.PI/4.0));
        Rhombus rhombus22 = new Rhombus(new Edge(null, null, center, mid22).rhombusOnEdge(3.0*Math.PI/4.0));
        Rhombus rhombus31 = new Rhombus(new Edge(null, null, center, mid31).rhombusOnEdge(3.0*Math.PI/4.0));
        Rhombus rhombus32 = new Rhombus(new Edge(null, null, center, mid32).rhombusOnEdge(3.0*Math.PI/4.0));
        Rhombus rhombus41 = new Rhombus(new Edge(null, null, center, mid41).rhombusOnEdge(3.0*Math.PI/4.0));
        Rhombus rhombus42 = new Rhombus(new Edge(null, null, center, mid42).rhombusOnEdge(3.0*Math.PI/4.0));

        rhombus11.setDepth(depth+1);
        rhombus12.setDepth(depth+1);
        rhombus21.setDepth(depth+1);
        rhombus22.setDepth(depth+1);
        rhombus31.setDepth(depth+1);
        rhombus32.setDepth(depth+1);
        rhombus41.setDepth(depth+1);
        rhombus42.setDepth(depth+1);

        component_faces.add(rhombus11);
        component_faces.add(rhombus12);
        component_faces.add(rhombus21);
        component_faces.add(rhombus22);
        component_faces.add(rhombus31);
        component_faces.add(rhombus32);
        component_faces.add(rhombus41);
        component_faces.add(rhombus42);
    }
}
