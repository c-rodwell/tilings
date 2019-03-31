package SquareAndRhombus;

import core.*;

import java.awt.*;
import java.util.ArrayList;

public class Rhombus extends Face {

    public Rhombus(abstractPolygon poly){
        super(poly);
    }

    public static Rhombus fromLongDiag(abstractPoint p1, abstractPoint p2){
        return new Rhombus(new Edge(null, null, p1, p2).rhombusOnEdge(3.0*Math.PI/4.0));
    }

    public static Rhombus fromShortDiag(abstractPoint p1, abstractPoint p2){
        return new Rhombus(new Edge(null, null, p1, p2).rhombusOnEdge(Math.PI/4.0));
    }


    public void split_edge(Edge edge){
        //add things to component_faces for now , maybe should be in a different structure
        double[] splitProportions = {1.0, Math.sqrt(2.0), 1.0};
        Edge[] edgeParts = edge.splitByRatio(splitProportions);
        //TODO - save the edges in a structure for later?
        Square edgeSquare = new Square(edgeParts[1].rhombusOnEdge(Math.PI/2.0));
        edgeSquare.setDepth(depth+1);
        addFace(edgeSquare);
    }

    public void split_inside(){
        //placeholder - return itself as the only part
        //abstractPoint num

        abstractPoint point0 = getPoint(0);
        abstractPoint point1 = getPoint(1);
        abstractPoint point2 = getPoint(2);
        abstractPoint point3 = getPoint(3);

        ArrayList<Edge> Edges = getEdges();
        Edge edge1 = Edges.get(0);
        Edge edge2 = Edges.get(1);
        Edge edge3 = Edges.get(2);
        Edge edge4 = Edges.get(3);

        abstractPoint mid12 = edge1.pointAtProportion(1.0, 1.0+Math.sqrt(2.0));
        abstractPoint mid11 = edge1.pointAtProportion(1.0+Math.sqrt(2.0), 1.0);

        abstractPoint mid22 = edge2.pointAtProportion(1.0, 1.0+Math.sqrt(2.0));
        abstractPoint mid21 = edge2.pointAtProportion(1.0+Math.sqrt(2.0), 1.0);

        abstractPoint mid32 = edge3.pointAtProportion(1.0, 1.0+Math.sqrt(2.0));
        abstractPoint mid31 = edge3.pointAtProportion(1.0+Math.sqrt(2.0), 1.0);

        abstractPoint mid42 = edge4.pointAtProportion(1.0, 1.0+Math.sqrt(2.0));
        abstractPoint mid41 = edge4.pointAtProportion(1.0+Math.sqrt(2.0), 1.0);
        

        Face crossRhombus = new Rhombus(abstractPolygon.rhombusOnOppositePoints(mid12, mid41, 3.0*Math.PI/4.0));
        addFace(crossRhombus);

        Face endRhombus0 = new Rhombus(abstractPolygon.parallelogramFromThreePoints(point0, mid11, mid42));
        addFace(endRhombus0);

        Face endRhombus1 = new Rhombus(abstractPolygon.parallelogramFromThreePoints(point2, mid31, mid22));
        addFace(endRhombus1);

        Face innerSquare1 = new Square(abstractPolygon.regPolyOnEdge(4, new Edge(null, null, mid12, point1), false));
        Face innerSquare3 = new Square(abstractPolygon.regPolyOnEdge(4, new Edge(null, null, point3, mid41), false));
        addFace(innerSquare1);
        addFace(innerSquare3);

        abstractPoint octagonTop = endRhombus1.getPoint(2);

        Face fanRhombusLeft = new Rhombus(abstractPolygon.rhombusOnOppositePoints(octagonTop, point3, 3.0*Math.PI/4.0));
        Face fanRhombusRight = new Rhombus(abstractPolygon.rhombusOnOppositePoints(octagonTop, point1, 3.0*Math.PI/4.0));
        Face fanRhombusCenter = new Rhombus(abstractPolygon.rhombusOnOppositePoints(octagonTop, crossRhombus.getPoint(1), 3.0*Math.PI/4.0));

        addFace(fanRhombusLeft);
        addFace(fanRhombusCenter);
        addFace(fanRhombusRight);
    }


}
