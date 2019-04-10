package core;

import display.GraphicsPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Edge implements Fractal{
    protected Face rightSide;
    protected Face leftSide;
    protected abstractPoint end1;
    protected abstractPoint end2;
    protected double edgeLength;
    protected ArrayList<abstractPoint> splitPointsRight;
    protected ArrayList<abstractPoint> splitPointsLeft;
    private ArrayList<Edge> splitEdges;
    private ArrayList<Edge> splitEdgesRight;
    private ArrayList<Edge> splitEdgesLeft;
    private ArrayList<Face> splitFaces;
    protected boolean wasSplit;
    protected int depth;

    public Edge(Face leftSide, Face rightSide, abstractPoint end1, abstractPoint end2){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.end1  = end1;
        this.end2  = end2;
        edgeLength = abstractPoint.distance(end1, end2);
        splitPointsRight = new ArrayList<>();
        splitPointsLeft = new ArrayList<>();
        splitFaces = new ArrayList<>();
        splitEdges = new ArrayList<>();
        splitEdgesRight = new ArrayList<>();
        splitEdgesLeft = new ArrayList<>();
        wasSplit = false;
    }

    @Override
    public String toString() {
        return "Edge:\n\trightSide = "+rightSide+"\n\tleftSide = "+leftSide
                +"\n\tend1 = "+end1+"\n\tend2 = "+end2
//                +"\n\tsplitPointsRight = "+splitPointsRight+"\n\tsplitPointsLeft = "+splitPointsLeft
//                +"\n\tsplitEdgesRight = "+splitEdgesRight+"\n\tsplitEdgesLeft = "+splitEdgesLeft
//                +"\n\tsplitFaces = "+splitFaces
                +"\n\tsplitPointsRight = "+splitPointsRight.size()+" points"
                +"\n\tsplitPointsLeft = "+splitPointsLeft.size()+" points"
                +"\n\tsplitEdgesRight = "+splitEdgesRight.size()+" edges"
                +"\n\tsplitEdgesLeft = "+splitEdgesLeft.size()+" edges"
                +"\n\tsplitFaces = "+splitFaces;
    }

    public abstractPoint getEnd1() {
        return end1;
    }

    public abstractPoint getEnd2() {
        return end2;
    }

    public Face getRightSide(){
        return rightSide;
    }

    public Face getLeftSide() {
        return leftSide;
    }

    public void setRightSide(Face side1) {
        this.rightSide = side1;
    }

    public void setLeftSide(Face side2) {
        this.leftSide = side2;
    }

    public ArrayList<Face> getSplitFaces(){
        return splitFaces;
    }

    public int getDepth(){
        return depth;
    }

    public void setDepth(int depth){
        this.depth = depth;
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
    //void, but create references in this object to the faces and points
    public void split(){
        //implement this in subclasses
    }

    public void giveSplitsToFaces(){
        if (rightSide != null) {
            ArrayList<abstractPoint> splitPointsRightCopy = (ArrayList<abstractPoint>) splitPointsRight.clone();
            ArrayList<Edge> splitEdgesRightCopy = (ArrayList<Edge>) splitEdgesRight.clone();
            Collections.reverse(splitPointsRightCopy);
            Collections.reverse(splitEdgesRightCopy);
            rightSide.setEdgeSplit(this, splitPointsRightCopy, splitEdgesRightCopy);
        }
        if (leftSide != null){
            ArrayList<abstractPoint> splitPointsLeftCopy = (ArrayList<abstractPoint>) splitPointsLeft.clone();
            ArrayList<Edge> splitEdgesLeftCopy = (ArrayList<Edge>) splitEdgesLeft.clone();
            leftSide.setEdgeSplit(this, splitPointsLeftCopy, splitEdgesLeftCopy);
        }
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

    public HashMap getParts(){
        HashMap<String, ArrayList> parts = new HashMap();
        parts.put("Edges", splitEdges);
        parts.put("Faces", splitFaces);
        return parts;
    }

    public void addFace(Face face, int depth_increment){
        face.setDepth(getDepth()+depth_increment);
        splitFaces.add(face);
    }

    public void addFace(Face face){
        addFace(face, 1);
    }

    public void addEdge(Edge edge, boolean addLeft, boolean addRight, int depth_increment){
        edge.setDepth(getDepth()+depth_increment);
        splitEdges.add(edge);
        if (addLeft){
            splitEdgesLeft.add(edge);
        }
        if (addRight){
            splitEdgesRight.add(edge);
        }
    }

    public void addEdge(Edge edge, boolean addLeft, boolean addRight){
        addEdge(edge, addLeft, addRight, 1);
    }


    public void draw(Graphics g, GraphicsPanel gp){
        int[] p1 = gp.mapCoords(getEnd1());
        int[] p2 = gp.mapCoords(getEnd2());
        g.setColor(Color.black);
        g.drawLine(p1[0], p1[1], p2[0], p2[1]);
    }


}
