package core;

public class Edge {
    Face side1;
    Face side2;
    private abstractPoint end1;
    abstractPoint end2;
    double edgeLength;

    public Edge(Face side1, Face side2, abstractPoint end1, abstractPoint end2){
        this.side1 = side1;
        this.side2 = side2;
        this.end1  = end1;
        this.end2  = end2;
        edgeLength = abstractPoint.distance(end1, end2);
    }

    @Override
    public String toString() {
        return "Edge:\n\tside1 = "+side1+"\n\tside2 = "+side2+"\n\tend1 = "+end1+"\n\tend2 = "+end2;
    }

    public abstractPoint getEnd1() {
        return end1;
    }

    public abstractPoint getEnd2() {
        return end2;
    }

    public abstractPoint midPoint(){
        return new abstractPoint((end1.x + end2.x)/2.0 , (end1.y + end2.y)/2.0);
    }

    //point at uneven distance along the edge. larger prop1 makes it closer to end1
    public abstractPoint pointAtProportion(double prop1, double prop2){
        return new abstractPoint((end1.x*prop1 + end2.x*prop2)/(prop1+prop2),
                                (end1.y*prop1 + end2.y*prop2)/(prop1+prop2));
    }

    //vector between the ends
    //watch out - does it matter which point is 1 and which is 2?
    public abstractVector asVector(){
        return end2.differenceFrom(end1);
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
        double perpDistance = edgeLength / (2*Math.tan(angle / 2.0));
        abstractVector normalVector = unitNormalVector();
        abstractPoint perp1 = midPoint.add(normalVector.scale(-perpDistance));
        abstractPoint perp2 = midPoint.add(normalVector.scale(perpDistance));
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

    public abstractPolygon rhombusOnEdge(double angle) {
        abstractPoint[] corners = bisectingPointsAtAngle(angle);
        abstractPolygon edgePiece = new abstractPolygon();
        edgePiece.addPoint(end1);
        edgePiece.addPoint(corners[0]);
        edgePiece.addPoint(end2);
        edgePiece.addPoint(corners[1]);
        return edgePiece;
    }

    //split into pieces - these can be points, edges, faces
    //how to return multiple types?
        //as map
        //void, but create references in this object
    public void split(){

    }

    //break into points/edges by dividing the length into
    public Edge[] splitByRatio(double[] proportions){
        //get sum so we can divide by it
        double sum = 0;
        for (double length : proportions){
            sum += length;
        }

        Edge[] pieces = new Edge[proportions.length];
        abstractPoint lastPoint = end1;
        abstractPoint nextPoint;
        abstractVector edgeVector = asVector(); //not normalized - length of vector is length of the edge
        for (int i=0; i<proportions.length; i++){

            nextPoint = lastPoint.add(edgeVector.scale(proportions[i]/sum)); //go whatever fraction of the way
            Edge edge = new Edge(null, null, lastPoint, nextPoint);
            pieces[i] = edge;
            lastPoint = nextPoint;
        }
        return pieces;
    }

}
