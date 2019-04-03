package patterns.PentagonAndStar;

import core.*;

import java.awt.*;

public class Rhombus extends Face {

    private Rhombus(abstractPolygon poly){
        super(poly);
    }

    public static Rhombus fromLongDiag(abstractPoint p1, abstractPoint p2){
        return new Rhombus(new Edge(null, null, p1, p2).rhombusOnEdge(4*Math.PI/5.0));
    }

    public void split_edge(Edge edge){

    }

    public void split_inside() {

    }

    public Color getColor(){
        return Color.pink;
    }
}
