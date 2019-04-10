package patterns.GoldenTriangles;
import core.*;

public class Golden113 extends Face {

    boolean rightHanded;

    public Golden113(abstractPolygon poly, boolean rightHanded){
        super(poly);
        this.rightHanded = rightHanded;

    }

    public static Golden113 fromBase(Edge base, boolean rightHanded){
        abstractPolygon poly = new abstractPolygon();
        abstractPoint p1;
        abstractPoint p2;
        abstractPoint p3;
        abstractPoint[] pointChoices = base.bisectingPointsAtAngle(3.0*Math.PI/5.0);
        p1 = base.getEnd1();
        p2 = base.getEnd2();
        p3 = pointChoices[0];
        poly.addPoint(p1);
        poly.addPoint(p2);
        poly.addPoint(p3);
        System.out.println("Golden113.fromBase: base = "+base);
        System.out.println("resulting triangle: "+poly);
        return new Golden113(poly, rightHanded);
    }

    public void split_edges(){

    }

    //points were added in order (p1, p2, p3)
    //where base is (p1, p2),
    // p3 is the unequal angle
    //so if makeEdges() is called:
    //edges[0] is copy of base: p1 -> p2
    //edges[1] is p2 -> p3
    //edges[2] is p3 -> p1

    //113 triangle splits to a point on the base

    public void split(){
        abstractPoint corner1 = polygon.getPoint(0);
        abstractPoint corner2 = polygon.getPoint(1);
        abstractPoint corner3 = polygon.getPoint(2);

        Edge base = getEdge(0);
        Edge leg1 = getEdge(1);
        Edge leg2 = getEdge(2);

        //split the edge by golden ratio, get the point and edges
        //should this use Edge.splitByRatio instead?
        double phi = (1.0+Math.sqrt(5.0))/2.0;

        abstractPoint mid;
        Edge innerEdge;
        Edge smallerPart;
        Edge largerPart;
        Edge new331Base;
//        Edge splitLeg;
//        Edge otherLeg;
//        abstractPoint splitCorner;
//        abstractPoint otherCorner;
        if(rightHanded) {
            mid = abstractPoint.weightedAvg(corner1, corner2, phi, 1.0);
            smallerPart = new Edge(null, null, corner1, mid);
            largerPart = new Edge(null, null, mid, corner2);
            innerEdge = new Edge(null, null, corner3, mid);
            new331Base = leg2;
        } else {
            mid = abstractPoint.weightedAvg(corner1, corner2, 1.0, phi);
            smallerPart = new Edge(null, null, mid, corner2);
            largerPart = new Edge(null, null, corner1, mid);
            innerEdge = new Edge(null, null, mid, corner3);
            new331Base = leg1;
        }

//        abstractPoint mid = base.pointAtProportion(phi, 1.0);
//        Edge innerEdge = new Edge(null, null, corner3, mid); //does point order matter here?
//        Edge smallerPart = new Edge (null, null, corner1, mid);
//        Edge largerPart = new Edge (null, null, mid, corner2);

        component_edges.add(innerEdge); //this doesn't matter yet

        Golden221 g221 = Golden221.fromBase(innerEdge, ! rightHanded);
        g221.setDepth(depth+1);
        Golden113 g113 = Golden113.fromBase(new331Base, rightHanded);
        g113.setDepth(depth+2);

        component_faces.add(g221);
        component_faces.add(g113);
    }

}