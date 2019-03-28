import core.Edge;
import core.Face;
import core.abstractPoint;
import core.abstractPolygon;

import java.util.ArrayList;
import java.util.Arrays;

public class quarteringTriangle extends Face {

    public quarteringTriangle(abstractPolygon poly){
        super(poly);
    }

    public quarteringTriangle(double radius, double center_x, double center_y, double rotation){
        super( abstractPolygon.regPolyOnCircle(3, radius, center_x, center_y, rotation));
//        System.out.println("quarteringTriangle constructor with radius = " +radius
//                +", center_x = "+center_x  +", center_y = "+center_y+", rotation = "+rotation);
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

        abstractPoint corner1 = polygon.getPoint(0);
        abstractPoint corner2 = polygon.getPoint(1);
        abstractPoint corner3 = polygon.getPoint(2);

        //new inside edges
        Edge innerEdge1 = new Edge(null, null, mid3, mid1);
        Edge innerEdge2 = new Edge(null, null, mid1, mid2);
        Edge innerEdge3 = new Edge(null, null, mid2, mid3);

        component_edges.add(innerEdge1);
        component_edges.add(innerEdge2);
        component_edges.add(innerEdge3);

        //new inside faces
        quarteringTriangle tri_1 = new quarteringTriangle(new abstractPolygon(new ArrayList<abstractPoint>(
                Arrays.asList(mid3, corner1, mid1))));
        quarteringTriangle tri_2 = new quarteringTriangle(new abstractPolygon(new ArrayList<abstractPoint>(
                Arrays.asList(mid1, corner2, mid2))));
        quarteringTriangle tri_3 = new quarteringTriangle(new abstractPolygon(new ArrayList<abstractPoint>(
                Arrays.asList(mid2, corner3, mid3))));
        quarteringTriangle tri_center = new quarteringTriangle(new abstractPolygon(new ArrayList<abstractPoint>(
                Arrays.asList(mid1, mid2, mid3))));

        component_faces.add(tri_1);
        component_faces.add(tri_2);
        component_faces.add(tri_3);
        component_faces.add(tri_center);
    }
}
