package patterns.PentagonAndStar;

import core.*;

import java.awt.*;
import java.util.ArrayList;

public class Pentagon extends Face{

    double phi = (1.0 + Math.sqrt(5.0))/2.0;

    private Pentagon(abstractPolygon poly){
        super(poly);
    }

    public static Pentagon onCircle(double radius, abstractPoint center, double rotation){
       return new Pentagon(abstractPolygon.regPolyOnCircle(5, radius, center, rotation));
    }

    public static Pentagon onEdge(Edge edge, boolean onLeft){
        Pentagon p = new Pentagon(abstractPolygon.regPolyOnEdge(5, edge, onLeft));
        p.edges[0] = edge;
        if (onLeft){
            edge.setLeftSide(p);
        } else {
            edge.setRightSide(p);
        }
        return p;
    }

    public Edge makeEdge(Face side1, Face side2, abstractPoint point1, abstractPoint point2){
        return new PSEdge(side1, side2, point1, point2);
    }

    //old version which doesn't add edges
//    public void split_inside() {
//        //assume split_edge was called on all edges in order - can use component_edges
//        Edge edge11 = component_edges.get(0);
//        Edge edge21 = component_edges.get(2);
//        Edge edge31 = component_edges.get(4);
//        Edge edge41 = component_edges.get(6);
//        Edge edge51 = component_edges.get(8);
//
//        addFace(Pentagon.onEdge(edge11, false));
//        addFace(Pentagon.onEdge(edge21, false));
//        addFace(Pentagon.onEdge(edge31, false));
//        addFace(Pentagon.onEdge(edge41, false));
//        addFace(Pentagon.onEdge(edge51, false));
//        addFace(Pentagon.onCircle(polygon.getRadius()/(Math.pow(phi, 2)), getCenter(), polygon.getAngle()+(Math.PI/5.0)));
//    }

        public void split_inside() {
            //assume split_edge was called on all edges in order - can use component_edges

            //central pentagon
            ArrayList<abstractPoint> centerPoints = new ArrayList<>();
            for (int i=0; i<5; i++){
                centerPoints.add(edgePoints[i].get(1));
            }
            Pentagon centerPentagon = new Pentagon(new abstractPolygon(centerPoints));
            addFace(centerPentagon);
            Edge[] centerEdges = centerPentagon.getEdges();

            //5 corner pentagons
            for (int i=0; i<5; i++){
                ArrayList<Edge> nextEdgeEdges = edgeEdges[i];
                ArrayList<Edge> prevEdgeEdges = edgeEdges[Math.floorMod(i-1, 5)];
                ArrayList<abstractPoint> nextEdgePoints = edgePoints[i];
                ArrayList<abstractPoint> prevEdgePoints = edgePoints[Math.floorMod(i-1, 5)];

                ArrayList<abstractPoint> vertices = new ArrayList<>();
                vertices.add(getPoint(i));
                vertices.add(nextEdgePoints.get(0));
                vertices.add(nextEdgePoints.get(1));
                vertices.add(prevEdgePoints.get(1));
                vertices.add(prevEdgePoints.get(2));

                Pentagon cornerPentagon = new Pentagon(new abstractPolygon(vertices));
                addFace(cornerPentagon);

            }

    }

    public Color getColor(){
        return Color.orange;
    }
}
