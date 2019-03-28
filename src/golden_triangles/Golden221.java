package golden_triangles;
import core.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Golden221 extends Face {

    boolean rightHanded;

    public Golden221(abstractPolygon poly, boolean rightHanded){
        super(poly);
        this.rightHanded = rightHanded;
    }

    //order of the points is always clockwise - p1, p2, p3: so p3 must be a left turn from p1->p2
    //rightHanded param is for chiral splits
    public static Golden221 fromBase(Edge base, boolean rightHanded){
        abstractPolygon poly = new abstractPolygon();
        abstractPoint p1;
        abstractPoint p2;
        abstractPoint p3;
        abstractPoint[] pointChoices = base.bisectingPointsAtAngle(Math.PI/5.0);
        p1 = base.getEnd1();
        p2 = base.getEnd2();
        p3 = pointChoices[0]; //same one every time -> clockwise point order
        poly.addPoint(p1);
        poly.addPoint(p2);
        poly.addPoint(p3);
        System.out.println("\nGolden221.fromBase: base = "+base);
        System.out.println("resulting triangle: "+poly+"\n");
        return new Golden221(poly, rightHanded);
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

    //arbitrarily say we split to a point on edges[1] (could be edges[2] also for 221)

    public void split_inside(){
        abstractPoint corner1 = polygon.getPoint(0);
        abstractPoint corner2 = polygon.getPoint(1);
        abstractPoint corner3 = polygon.getPoint(2);

        Edge base = edges.get(0);
        Edge leg1 = edges.get(1);
        Edge leg2 = edges.get(2);

        //split the edge by golden ratio, get the point and edges
        //should this use Edge.splitByRatio instead?
        double phi = (1.0+Math.sqrt(5.0))/2.0;
        abstractPoint mid;
        Edge innerEdge;
        Edge smallerPart;
        Edge largerPart;
        Edge splitLeg;
        Edge otherLeg;
        abstractPoint splitCorner;
        abstractPoint otherCorner;
        if(rightHanded) {
            splitLeg = leg1;
            otherLeg = leg2;
            splitCorner = corner1;
            otherCorner = corner2;
            mid = abstractPoint.weightedAvg(otherCorner, corner3, phi, 1.0);
            innerEdge = new Edge(null, null, splitCorner, mid);
            smallerPart = new Edge (null, null, otherCorner, mid);
            largerPart = new Edge (null, null, mid, corner3);
        } else {
            splitLeg = leg2;
            otherLeg = leg1;
            splitCorner = corner2;
            otherCorner = corner1;
            mid = abstractPoint.weightedAvg(otherCorner, corner3, phi, 1.0);
            innerEdge = new Edge(null, null, mid, splitCorner);
            smallerPart = new Edge (null, null, mid, otherCorner);
            largerPart = new Edge (null, null, corner3, mid);
        }

//        System.out.println("Golden221: mid = "+mid);
//        System.out.println("corner2 to mid: "+abstractPoint.distance(mid, corner2));
//        System.out.println("mid to corner3: "+abstractPoint.distance(mid, corner3));

        component_edges.add(innerEdge);
        //smallerPart and largerPart are outside edges, but don't need that distinction here

        Golden221 g221 = Golden221.fromBase(smallerPart, rightHanded);
        g221.setDepth(depth+2);
        Golden113 g113 = Golden113.fromBase(otherLeg, rightHanded);
        g113.setDepth(depth+1);

        component_faces.add(g221);
        component_faces.add(g113);
    }
}
