package patterns.PentagonAndStar;

import core.*;

import java.awt.*;

public class Pentagon extends Face{

    double phi = (1.0 + Math.sqrt(5.0))/2.0;

    private Pentagon(abstractPolygon poly){
        super(poly);
    }

    public static Pentagon onCircle(double radius, abstractPoint center, double rotation){
       return new Pentagon(abstractPolygon.regPolyOnCircle(5, radius, center, rotation));
    }

    public static Pentagon onEdge(Edge edge, boolean chooseFirst){
        return new Pentagon(abstractPolygon.regPolyOnEdge(5, edge, chooseFirst));
    }

    public Edge makeEdge(Face side1, Face side2, abstractPoint point1, abstractPoint point2){
        return new PSEdge(side1, side2, point1, point2);
    }

//    public void split_edge(Edge edge){
//        double[] proportions = {phi, 1.0, phi};
//        Edge[] pieces = edge.splitByRatio(proportions);
//
//        Edge edge1 = pieces[0];
//        edge1.setSide1(this);
//        component_edges.add(edge1);
//
//        Edge edge2 = pieces[2];
//        edge1.setSide2(this);
//        component_edges.add(edge2);
//
//        Face edgeRhombus = new Face(pieces[1].rhombusOnEdge(Math.PI/5.0));
//        addFace(edgeRhombus);
//    }

    public void split_inside() {
        //assume split_edge was called on all edges in order - can use component_edges
        Edge edge11 = component_edges.get(0);
        Edge edge21 = component_edges.get(2);
        Edge edge31 = component_edges.get(4);
        Edge edge41 = component_edges.get(6);
        Edge edge51 = component_edges.get(8);

        addFace(Pentagon.onEdge(edge11, false));
        addFace(Pentagon.onEdge(edge21, false));
        addFace(Pentagon.onEdge(edge31, false));
        addFace(Pentagon.onEdge(edge41, false));
        addFace(Pentagon.onEdge(edge51, false));
        addFace(Pentagon.onCircle(polygon.getRadius()/(Math.pow(phi, 2)), getCenter(), polygon.getAngle()+(Math.PI/5.0)));

    }

    public Color getColor(){
        return Color.orange;
    }
}
