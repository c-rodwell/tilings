import core.Edge;
import core.Face;
import core.abstractPoint;
import core.abstractPolygon;

import java.util.ArrayList;
import java.util.Arrays;

public class quartering_Square extends Face {

    //state for splitting
    //protected core.abstractPolygon polygon;

    public quartering_Square(abstractPolygon square){
        super(square);
        System.out.println("returning from quartering_square constructor with square = "+square);
    }

    public quartering_Square(double radius, double center_x, double center_y, double rotation){
        super( abstractPolygon.regPolyOnCircle(4, radius, center_x, center_y, rotation));
        System.out.println("quartering_square constructor with radius = " +radius
                +", center_x = "+center_x  +", center_y = "+center_y+", rotation = "+rotation);
    }

    public void split_edges(){

    }

    public void split_inside(){

        //important points on the square
        System.out.println("in split_inside");
        System.out.println("polygon = "+polygon);
        abstractPoint center = polygon.getCenter();

        abstractPoint mid1 = edges.get(0).midPoint();
        abstractPoint mid2 = edges.get(1).midPoint();
        abstractPoint mid3 = edges.get(2).midPoint();
        abstractPoint mid4 = edges.get(3).midPoint();

        abstractPoint corner1 = polygon.getPoint(0);
        abstractPoint corner2 = polygon.getPoint(1);
        abstractPoint corner3 = polygon.getPoint(2);
        abstractPoint corner4 = polygon.getPoint(3);

        //new inside edges
        Edge edge1 = new Edge(null, null, center, mid1);
        Edge edge2 = new Edge(null, null, center, mid1);
        Edge edge3 = new Edge(null, null, center, mid1);
        Edge edge4 = new Edge(null, null, center, mid1);

        component_edges.add(edge1);
        component_edges.add(edge2);
        component_edges.add(edge3);
        component_edges.add(edge4);

        //new inside faces
        quartering_Square square1 = new quartering_Square(new abstractPolygon(new ArrayList<abstractPoint>(
                Arrays.asList(center, mid4, corner1, mid1))));
        quartering_Square square2 = new quartering_Square(new abstractPolygon(new ArrayList<abstractPoint>(
                Arrays.asList(center, mid1, corner2, mid2))));
        quartering_Square square3 = new quartering_Square(new abstractPolygon(new ArrayList<abstractPoint>(
                Arrays.asList(center, mid2, corner3, mid3))));
        quartering_Square square4 = new quartering_Square(new abstractPolygon(new ArrayList<abstractPoint>(
                Arrays.asList(center, mid3, corner4, mid4))));

        component_faces.add(square1);
        component_faces.add(square2);
        component_faces.add(square3);
        component_faces.add(square4);
    }
}
