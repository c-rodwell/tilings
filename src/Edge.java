import java.awt.geom.Point2D;

public class Edge {
    Face side1;
    Face side2;
    abstractPoint end1;
    abstractPoint end2;
    double length;

    public Edge(Face side1, Face side2, abstractPoint end1, abstractPoint end2){
        this.side1 = side1;
        this.side2 = side2;
        this.end1  = end1;
        this.end2  = end2;
        length = abstractPoint.distance(end1, end2);
    }

    public abstractPoint midPoint(){
        return new abstractPoint((end1.x + end2.x)/2.0 , (end1.y + end2.y)/2.0);
    }

    //vector between the ends
    //watch out - does it matter which point is 1 and which is 2?
    public abstractVector asVector(){
        return end1.differenceFrom(end2);
    }

    //length 1 vector perpendicular to this edge.
    //watch out for sign - there are 2 equally good normal vectors
    public abstractVector unitNormalVector(){
        return asVector().perpendicular().normalize();
    }

    //return the two points on the perpendicular bisector of the edge,
    //with the given angle from endpoint - point - endpoint
    public abstractPoint[] bisectingPointsAtAngle(double angle){

        abstractPoint midPoint = midPoint();
        double perpDistance = length / (2*Math.tan(angle / 2.0));
        abstractVector normalVector = unitNormalVector();
        abstractPoint perp1 = midPoint.add(normalVector.scale(perpDistance));
        abstractPoint perp2 = midPoint.add(normalVector.scale(-perpDistance));
        abstractPoint[] points = {perp1, perp2};
        return points;
    }

    //return the two points on the perpendicular bisector of the edge,
    //where distance from each of the returned points to each of the endpoints is sidelength
//    public abstractPoint[] bisectingPointsAtLength(double sidelength){
//        if (sidelength < length / 2){
//            throw new IllegalArgumentException("sidelength arg must be at least half of edge.length");
//        }
//        abstractPoint[] points = new abstractPoint[2];
//        return points;
//    }

    //split into pieces - these can be points, edges, faces
    //how to return multiple types?
        //as map
        //void, but create references in this object

    public void split(){

    }

    //break into points/edges by dividing the length into
    public void splitByRatio(double[] proportions){

    }

}
